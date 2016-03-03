package cn.kane.mview.adminui.integrate.state;

import org.springframework.beans.factory.annotation.Autowired;

import cn.kane.mview.adminui.integrate.service.ChangesManageService;
import cn.kane.mview.adminui.integrate.service.IntegrateStatus;
import cn.kane.mview.adminui.integrate.service.RequirementManageService;
import cn.kane.mview.adminui.integrate.service.State;
import cn.kane.mview.service.definition.entity.DefinitionKey;

public class NewState implements State {

	private static final IntegrateStatus NEW_STATE = IntegrateStatus.NEW ;
	@Autowired
	private RequirementManageService requirementManageService ;
	@Autowired
	private ChangesManageService changesManageService ;
	
	@Override
	public String status(String requirementId) {
		return NEW_STATE.status();
	}
	
	@Override
	public String actionName(String requirementId) {
		return NEW_STATE.forwardName();
	}
	
	@Override
	public String backwardName(String requirementId) {
		return NEW_STATE.backwardName();
	}
	
	@Override
	public String disableName(String requirementId) {
		return NEW_STATE.disableName();
	}
	
	@Override
	public void add(String requirementId, DefinitionKey key) {
		changesManageService.add(requirementId, key);
	}

	@Override
	public void action(String requirementId) {
		requirementManageService.compareAndSetStatus(requirementId,this.status(requirementId),IntegrateStatus.COMMITED.status());
	}

	@Override
	public void backward(String requirementId) {
		throw new UnsupportedOperationException(requirementId) ;
	}

	@Override
	public void disable(String requirementId) {
		requirementManageService.compareAndSetStatus(requirementId,this.status(requirementId),IntegrateStatus.DISABLED.status());
	}

}
