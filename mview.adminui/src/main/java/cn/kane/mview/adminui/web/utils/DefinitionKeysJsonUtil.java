package cn.kane.mview.adminui.web.utils;

import java.util.ArrayList;
import java.util.List;

import cn.kane.mview.service.definition.entity.DefinitionKey;

import com.alibaba.fastjson.JSON;

public class DefinitionKeysJsonUtil{

	public static List<DefinitionKey> parseDataReadServiceKey(List<String> dataReadServiceKeys){
		if(null == dataReadServiceKeys || dataReadServiceKeys.isEmpty()){
			return null ;
		}
		List<DefinitionKey> keys = new ArrayList<DefinitionKey>(dataReadServiceKeys.size()) ;
		for(String key : dataReadServiceKeys){
			DefinitionKey defKey = JSON.parseObject(key, DefinitionKey.class) ;
			keys.add(defKey);
		}
		return keys ;
	}
	
	public static List<String> formatDataReadServiceKey(List<DefinitionKey> keys){
		if(null == keys || keys.isEmpty()){
			return null ;
		}
		List<String> strKeys = new ArrayList<String>(keys.size()) ;
		for(DefinitionKey key : keys){
			strKeys.add(JSON.toJSONString(key)) ;
		}
		return strKeys ;
	}
}
