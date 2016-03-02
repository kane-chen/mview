package cn.kane.mview.adminui.web.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import cn.kane.mview.service.definition.entity.DefinitionKey;

import com.alibaba.fastjson.JSON;

public class DefinitionKeysJsonUtil{
	
	public static DefinitionKey parseKey(String key){
		if(StringUtils.isNoneBlank(key)){
			return JSON.parseObject(key, DefinitionKey.class) ;
		}
		return null ;
	}

	public static List<DefinitionKey> parseKeys(List<String> dataReadServiceKeys){
		if(null == dataReadServiceKeys || dataReadServiceKeys.isEmpty()){
			return null ;
		}
		List<DefinitionKey> keys = new ArrayList<DefinitionKey>(dataReadServiceKeys.size()) ;
		for(String key : dataReadServiceKeys){
			DefinitionKey defKey = parseKey(key) ;
			keys.add(defKey);
		}
		return keys ;
	}
	
	public static String format(DefinitionKey dkey){
		if(null != dkey){
			return JSON.toJSONString(dkey) ;
		}
		return null ;
	}
	
	public static List<String> formatKeys(List<DefinitionKey> keys){
		if(null == keys || keys.isEmpty()){
			return null ;
		}
		List<String> strKeys = new ArrayList<String>(keys.size()) ;
		for(DefinitionKey key : keys){
			strKeys.add(format(key)) ;
		}
		return strKeys ;
	}
}
