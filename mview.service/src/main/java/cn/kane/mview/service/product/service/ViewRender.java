package cn.kane.mview.service.product.service;

import java.util.Map;

import cn.kane.mview.service.definition.entity.DefinitionKey;
import cn.kane.mview.service.product.vo.ViewModel;
import cn.kane.mview.service.resource.entity.Resource;


public interface ViewRender<T extends Resource> {

    ViewModel reander(DefinitionKey defKey,Map<String,Object> params) ;
    
}
