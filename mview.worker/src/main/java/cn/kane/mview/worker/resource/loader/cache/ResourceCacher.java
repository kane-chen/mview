package cn.kane.mview.worker.resource.loader.cache;

import java.util.Set;

import cn.kane.mview.service.resource.entity.Resource;

public interface ResourceCacher<DefinitionKey,T extends Resource> {

    boolean put(DefinitionKey key,T value) ;
    
    T get(DefinitionKey key) ;
    
    boolean invalidate(DefinitionKey key) ;
    
    Set<DefinitionKey> getAllKeys() ;
}
