package cn.kane.mview.service.resource.service;

import cn.kane.mview.service.definition.entity.DefinitionKey;
import cn.kane.mview.service.resource.entity.Resource;

public interface ResourceLoader<T extends Resource> {

    T getResourceByKey(DefinitionKey definitionKey) ;
    
    T getResourceBykeyWithoutCache(DefinitionKey definitionKey) ;
}
