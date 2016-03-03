package cn.kane.mview.adminui.integrate.service;

import cn.kane.mview.service.definition.entity.DefinitionKey;

public interface State {
	
	String status(String requirementId) ;
	
	String actionName(String requirementId);
	
	String backwardName(String requirementId) ;
	
	String disableName(String requirementId) ;
	
	void add(String requirementId,DefinitionKey key) ;
	
	void action(String requirementId) ;
	
	void backward(String requirementId) ;

	void disable(String requirementId) ;

	
}
