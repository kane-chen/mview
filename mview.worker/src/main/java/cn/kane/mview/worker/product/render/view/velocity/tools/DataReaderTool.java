package cn.kane.mview.worker.product.render.view.velocity.tools;

import java.util.Map;

import cn.kane.mview.service.definition.entity.DefinitionKey;
import cn.kane.mview.service.resource.entity.DataReader;
import cn.kane.mview.worker.util.DefinitionKeyUtils;

public class DataReaderTool {

	public static Object getData(String formatedKey,Map<String,Object> params){
		DefinitionKey key = DefinitionKeyUtils.parse(formatedKey) ;
		if(null == key || !"dataReadService".equals(key.getType())){
			return null ;
		}
		DataReader dataReader = ContainerBeanFactory.getInstance().getDataReaderLoader().getResourceByKey(key) ;
		if(null==dataReader){
			return null ;
		}
		return dataReader.getData(params) ;
	}
	
}
