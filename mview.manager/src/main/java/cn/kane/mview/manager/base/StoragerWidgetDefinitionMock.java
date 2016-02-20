package cn.kane.mview.manager.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import cn.kane.mview.service.definition.entity.WidgetDefinition;
import cn.kane.mview.service.definition.entity.DefinitionKey;

public class StoragerWidgetDefinitionMock implements Storager<DefinitionKey,WidgetDefinition> {

	private Map<DefinitionKey,WidgetDefinition> store = new ConcurrentHashMap<DefinitionKey,WidgetDefinition>() ;
	
	@Override
	public boolean save(WidgetDefinition res) {
		getStore().put(res.getKey(), res);
		return true;
	}

	@Override
	public boolean update(WidgetDefinition res) {
		getStore().put(res.getKey(), res);
		return true;
	}

	@Override
	public boolean remove(DefinitionKey key) {
		store.remove(key) ;
		return true;
	}
	
	@Override
	public WidgetDefinition get(DefinitionKey key) {
		if(null == key){
			return null ;
		}
		return getStore().get(key);
	}

	@Override
	public List<WidgetDefinition> query(Object... param) {
		List<WidgetDefinition> allDefs = new ArrayList<WidgetDefinition>();
		allDefs.addAll(store.values());
		return allDefs ;
	}
	
	public Map<DefinitionKey,WidgetDefinition> getStore() {
		return store;
	}

	public void setStore(Map<DefinitionKey,WidgetDefinition> store) {
		this.store = store;
	}

}
