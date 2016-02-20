package cn.kane.mview.service.definition.service;

import java.util.List;

import cn.kane.mview.service.definition.entity.AbstractDefinition;
import cn.kane.mview.service.definition.entity.DefinitionKey;

public interface ResourceDefinitionManager<T extends AbstractDefinition> {

    boolean add(T definition) ;
    
    boolean edit(T definition) ;
    
    boolean remove(DefinitionKey key) ;
    
    T get(DefinitionKey key) ;
    
    /**
     * select range(key.type must be special)
     * @param fromKey (inclusive)
     * @param toKey   (exclusive)
     * @return
     */
    List<T> list(DefinitionKey fromKey,DefinitionKey toKey) ;
}
