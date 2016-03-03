package cn.kane.mview.adminui.web.vo;

import org.springframework.cglib.beans.BeanCopier;

import cn.kane.mview.adminui.integrate.entity.Requirement;

public class RequirementVO extends Requirement{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2361720174089814251L;
	private static BeanCopier copier =	BeanCopier.create(Requirement.class,RequirementVO.class,false);
	
	private String stateName ;
	private String stateForwardName ;
	private String stateBackwardName ;
	private String stateDisableName ;
	
	private RequirementVO(){
		
	}
	
	public static RequirementVO build(Requirement req,String stateName,String forwardName,String backwardName,String disableName){
		if(null == req){
			return null ;
		}
		RequirementVO reqvo = new RequirementVO() ;
		copier.copy(req, reqvo, null);
		reqvo.setStateName(stateName);
		reqvo.setStateForwardName(forwardName);
		reqvo.setStateBackwardName(backwardName);
		reqvo.setStateDisableName(disableName);
		return reqvo ;
	}
	
	public String getStateName() {
		return stateName;
	}

	private void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getStateForwardName() {
		return stateForwardName;
	}

	private void setStateForwardName(String stateForwardName) {
		this.stateForwardName = stateForwardName;
	}

	public String getStateBackwardName() {
		return stateBackwardName;
	}

	private void setStateBackwardName(String stateBackwardName) {
		this.stateBackwardName = stateBackwardName;
	}

	public String getStateDisableName() {
		return stateDisableName;
	}

	private void setStateDisableName(String stateDisableName) {
		this.stateDisableName = stateDisableName;
	}
	
}
