package cn.kane.mview.adminui.web.vo;

import cn.kane.mview.adminui.web.utils.DefinitionKeysJsonUtil;
import cn.kane.mview.service.definition.entity.DefinitionKey;

public class ChangeVO {

	private DefinitionKey key ;
	private String vkey ;
	private String pathkey ;

	public static ChangeVO build(DefinitionKey key){
		if(null == key){
			return null ;
		}
		ChangeVO change = new ChangeVO() ;
		change.setKey(key);
		change.setVkey(DefinitionKeysJsonUtil.format(key));
		change.setPathkey(key.getType()+"/"+key.getName()+"/"+key.getVersion());
		return change ;
	}
	
	public DefinitionKey getKey() {
		return key;
	}
	public void setKey(DefinitionKey key) {
		this.key = key;
	}
	public String getVkey() {
		return vkey;
	}
	public void setVkey(String vkey) {
		this.vkey = vkey;
	}
	public String getPathkey() {
		return pathkey;
	}
	public void setPathkey(String pathkey) {
		this.pathkey = pathkey;
	}
	
}
