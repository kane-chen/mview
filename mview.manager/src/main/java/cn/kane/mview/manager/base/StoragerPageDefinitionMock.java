package cn.kane.mview.manager.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import cn.kane.mview.service.definition.entity.PageDefinition;
import cn.kane.mview.service.definition.entity.DefinitionKey;

public class StoragerPageDefinitionMock implements Storager<DefinitionKey,PageDefinition> {

	private Map<DefinitionKey,PageDefinition> store = new ConcurrentHashMap<DefinitionKey,PageDefinition>() ;
	
	@Override
	public boolean save(PageDefinition res) {
		getStore().put(res.getKey(), res);
		return true;
	}

	@Override
	public boolean update(PageDefinition res) {
		getStore().put(res.getKey(), res);
		return true;
	}

	@Override
	public boolean remove(DefinitionKey key) {
		store.remove(key) ;
		return true;
	}
	
	@Override
	public PageDefinition get(DefinitionKey key) {
		if(null == key){
			return null ;
		}
		return getStore().get(key);
	}

	@Override
	public List<PageDefinition> query(Object... param) {
		List<PageDefinition> allDefs = new ArrayList<PageDefinition>();
		allDefs.addAll(store.values());
		return allDefs ;
	}
	
	public Map<DefinitionKey,PageDefinition> getStore() {
		return store;
	}

	public void setStore(Map<DefinitionKey,PageDefinition> store) {
		this.store = store;
	}

}
