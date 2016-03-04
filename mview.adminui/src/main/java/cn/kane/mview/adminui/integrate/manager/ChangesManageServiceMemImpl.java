package cn.kane.mview.adminui.integrate.manager;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.kane.mview.adminui.integrate.entity.Changes;
import cn.kane.mview.adminui.integrate.service.ChangesManageService;
import cn.kane.mview.service.definition.entity.AbstractDefinition;
import cn.kane.mview.service.definition.entity.DefinitionKey;
import cn.kane.mview.service.definition.service.ResourceDefinitionManager;

@Service("changesManageService")
public class ChangesManageServiceMemImpl implements ChangesManageService {

	private ConcurrentHashMap<String,Changes> store = new ConcurrentHashMap<String, Changes>() ;
	private ConcurrentHashMap<String,Changes> backupStore = new ConcurrentHashMap<String, Changes>() ;
	@Autowired
	private ResourceDefinitionManager<AbstractDefinition> resourceDefinitionManagerFacade ;
	
	@Override
	public void add(String requirementId, DefinitionKey key) {
		if(null == requirementId || null == key){
			throw new IllegalArgumentException("requirementId & definitionKey cannot be null") ;
		}
		Changes stored = store.get(requirementId) ;
		if(null == stored){
			stored = new Changes() ;
			stored.setId(requirementId) ;
			Changes preStored = store.putIfAbsent(requirementId, stored) ;
			if(null != preStored){
				stored = preStored ;
			}
		}
		stored.getChanges().add(key) ;
	}

	@Override
	public void remove(String requirementId, DefinitionKey key) {
		if(null == requirementId || null == key){
			return ;
		}
		Changes changes = store.get(requirementId) ;
		if(null!=changes){
			changes.getChanges().remove(key) ;
		}
	}
	
	@Override
	public List<DefinitionKey> list(String requirementId) {
		if(!store.containsKey(requirementId)){
			return null;
		}
		return new ArrayList<DefinitionKey>(store.get(requirementId).getChanges()) ;
	}

	@Override
	public void backup(String requirementId) {
		//changed-keys
		List<DefinitionKey> changedKeys = this.list(requirementId) ;
		if(null == requirementId || null == changedKeys || changedKeys.isEmpty()){
			//WARN
			return ;
		}
		//backup-keys
		List<DefinitionKey> backupKeys = this.getBackupOfChangedKeys(changedKeys) ;
		if(null == backupKeys || backupKeys.isEmpty()){
			//nothing need to backup
			return ;
		}
		//ReEntrant,no check duplicate-backup
//		Changes stored = backupStore.get(requirementId) ;
//		if(null != stored){
//			throw new IllegalArgumentException(String.format("Requirement[%s] existed", requirementId)) ;
//		}
		//backup
		Changes store = new Changes() ;
		store.setId(requirementId) ;
		store.getChanges().addAll(backupKeys) ;
		backupStore.put(requirementId, store) ;
	}
	
	private List<DefinitionKey> getBackupOfChangedKeys(List<DefinitionKey> changedKeys){
		List<DefinitionKey> backupKeys = new ArrayList<DefinitionKey>(changedKeys.size()) ;
		for(DefinitionKey key : changedKeys){
			DefinitionKey tmpKey = this.clone(key) ;
			tmpKey.setVersion(TRUNK_VERSION) ;
			AbstractDefinition prevDef = resourceDefinitionManagerFacade.get(tmpKey) ;
			if(null!=prevDef){
				tmpKey.setVersion(prevDef.getApplyVersion()) ;
			}else{
				tmpKey.setVersion(REMOVED_VERSION);
			}
			backupKeys.add(tmpKey) ;
		}
		return backupKeys ;
	}

	private DefinitionKey clone(DefinitionKey key){
		if(key == null){
			return null ;
		}
		DefinitionKey clone = new DefinitionKey() ;
		clone.setType(key.getType());
		clone.setName(key.getName());
		clone.setVersion(key.getVersion());
		return clone ;
	}
	
	@Override
	public List<DefinitionKey> listBackup(String requirementId) {
		Changes stored = backupStore.get(requirementId) ;
		if(null == stored){
			return  null ;
		}
		return new ArrayList<DefinitionKey>(stored.getChanges()) ;
	}

	@Override
	public void writeTrunk(List<DefinitionKey> keys) {
		//TODO non-transaction
		this.removeTrunk(keys);
		List<AbstractDefinition> definitions = this.getDefinitions(keys) ;
		this.writeToTrunk(definitions);
	}

	private void removeTrunk(List<DefinitionKey> keys){
		for(DefinitionKey key : keys){
			//add >> RollBack >> remove
			if(REMOVED_VERSION.equalsIgnoreCase(key.getVersion())){
				DefinitionKey newKey = this.clone(key) ;
				newKey.setVersion(TRUNK_VERSION) ;
				resourceDefinitionManagerFacade.remove(newKey);
			}
		}
	}
	
	private void writeToTrunk(List<AbstractDefinition> defs){
		for(AbstractDefinition def : defs){
			//new-trunk-definition
			AbstractDefinition newTrunk = this.buildTrunkDefinition(def) ;
			//write trunk
			if(null!=resourceDefinitionManagerFacade.get(newTrunk.getKey())){
				resourceDefinitionManagerFacade.edit(def) ;
			}else{
				resourceDefinitionManagerFacade.add(def) ;
			}
		}
	}

	private AbstractDefinition buildTrunkDefinition(AbstractDefinition definition){
		if(null == definition){
			return null ;
		}
		AbstractDefinition trunkDefinition = null ;
		try{
			//for the same entity in mem(other not required)
			trunkDefinition = definition.getClass().newInstance() ;
			BeanUtils.copyProperties(definition, trunkDefinition);
			//set all trunk-version
			this.setAllKeyVersion(trunkDefinition) ;
			//apply-version
			trunkDefinition.setApplyVersion(definition.getKey().getVersion());
		}catch(Exception e){
			throw new IllegalArgumentException(e);
		}
		return trunkDefinition ;
	}
	
	private AbstractDefinition setAllKeyVersion(AbstractDefinition definition) throws IllegalArgumentException, IllegalAccessException {
		for (Field field : definition.getClass().getDeclaredFields()) {
			if (field.getType() == DefinitionKey.class) {
				field.setAccessible(true);
				Object fieldVal = field.get(definition);
				if (null != fieldVal) {
					DefinitionKey key = (DefinitionKey) fieldVal;
					field.set(definition, this.clone(key));
				}
			} else if (field.getType() == List.class) {
				if (this.isGenericDefinitionKey(field)) {
					field.setAccessible(true);
					Object fieldvals = field.get(definition);
					if (null != fieldvals) {
						@SuppressWarnings("unchecked")
						List<DefinitionKey> keys = (List<DefinitionKey>) fieldvals;
						List<DefinitionKey> newkeys = new ArrayList<DefinitionKey>(
								keys.size());
						for (DefinitionKey key : keys) {
							DefinitionKey newkey = this.clone(key);
							newkey.setVersion("TRUNK");
							newkeys.add(newkey);
						}
						field.set(definition, newkeys);
					}
				}
			}
		}
		return definition;
	}

	private boolean isGenericDefinitionKey(Field field){
		if(null!=field.getGenericType()){
			if(field.getGenericType() instanceof ParameterizedType){
				ParameterizedType type = (ParameterizedType) field.getGenericType() ;
				return type .getActualTypeArguments()[0] == DefinitionKey.class ;
			}
		}
		return false ;
	}
	
	
	private List<AbstractDefinition> getDefinitions(List<DefinitionKey> keys){
		List<AbstractDefinition> defs = new ArrayList<AbstractDefinition>(keys.size());
		for(DefinitionKey key : keys){
			AbstractDefinition def = resourceDefinitionManagerFacade.get(key) ;
			if(null != def){
				defs.add(def) ;
			}
		}
		return defs ;
	}

}
