package cn.kane.mview.adminui.web.vo;

import cn.kane.mview.adminui.web.utils.DefinitionKeysJsonUtil;
import cn.kane.mview.service.definition.entity.DefinitionKey;

public class ChangeVO {

	private String requirementId ;
	private DefinitionKey key ;
	private String vkey ;
	private String pathkey ;
	
	public static ChangeVO build(String requirementId,DefinitionKey key){
		if(null == key){
			return null ;
		}
		ChangeVO change = new ChangeVO() ;
		change.setRequirementId(requirementId);
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

	public String getRequirementId() {
		return requirementId;
	}

	public void setRequirementId(String requirementId) {
		this.requirementId = requirementId;
	}
	
}
