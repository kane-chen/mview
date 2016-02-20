package cn.kane.mview.worker.resource.loader.builder;

import cn.kane.mview.service.definition.entity.DefinitionKey;
import cn.kane.mview.service.resource.entity.Resource;


public interface ResourceBuilder<T extends Resource> {

    T build(DefinitionKey key) ;
    
}
