package cn.kane.mview.manager.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import cn.kane.mview.service.definition.entity.TemplateDefinition;
import cn.kane.mview.service.definition.entity.DefinitionKey;

public class StoragerTemplateDefinitionMock implements Storager<DefinitionKey,TemplateDefinition> {

	private Map<DefinitionKey,TemplateDefinition> store = new ConcurrentHashMap<DefinitionKey,TemplateDefinition>() ;
	
	@Override
	public boolean save(TemplateDefinition res) {
		getStore().put(res.getKey(), res);
		return true;
	}

	@Override
	public boolean update(TemplateDefinition res) {
		getStore().put(res.getKey(), res);
		return true;
	}

	@Override
	public boolean remove(DefinitionKey key) {
		store.remove(key) ;
		return true;
	}
	
	@Override
	public TemplateDefinition get(DefinitionKey key) {
		if(null == key){
			return null ;
		}
		return getStore().get(key);
	}

	@Override
	public List<TemplateDefinition> query(Object... param) {
		DefinitionKey key1 = (DefinitionKey) param[0] ;
		List<TemplateDefinition> allDefs = new ArrayList<TemplateDefinition>();
		if(null == key1.getType()){
			allDefs.addAll(store.values());
		}else{
			for(DefinitionKey key : store.keySet()){
				if(key1.getType().equals(key.getType())){
					allDefs.add(store.get(key)) ;
				}
			}
		}
		return allDefs ;
	}
	
	public Map<DefinitionKey,TemplateDefinition> getStore() {
		return store;
	}

	public void setStore(Map<DefinitionKey,TemplateDefinition> store) {
		this.store = store;
	}

}
