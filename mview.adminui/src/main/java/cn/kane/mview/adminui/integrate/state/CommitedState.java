package cn.kane.mview.adminui.integrate.state;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.kane.mview.adminui.integrate.service.ChangesManageService;
import cn.kane.mview.adminui.integrate.service.IntegrateStatus;
import cn.kane.mview.adminui.integrate.service.RequirementManageService;
import cn.kane.mview.adminui.integrate.service.State;
import cn.kane.mview.service.definition.entity.DefinitionKey;

public class CommitedState implements State {

	private static final IntegrateStatus PUSH_STATE = IntegrateStatus.COMMITED ;
	
	@Autowired
	private RequirementManageService requirementManageService ;
	@Autowired
	private ChangesManageService changesManageService ;
	
	@Override
	public String status(String requirementId) {
		return PUSH_STATE.status();
	}

	@Override
	public String actionName(String requirementId) {
		return PUSH_STATE.forwardName();
	}

	@Override
	public String backwardName(String requirementId) {
		return PUSH_STATE.backwardName();
	}

	@Override
	public void add(String requirementId, DefinitionKey key) {
		throw new UnsupportedOperationException(requirementId) ;
	}

	@Override
	public void action(String requirementId) {
		changesManageService.backup(requirementId) ;
		List<DefinitionKey> changedKeys = changesManageService.list(requirementId) ;
		changesManageService.writeTrunk(changedKeys);
		requirementManageService.compareAndSetStatus(requirementId,this.status(requirementId),IntegrateStatus.PUBLISHED.status());
	}

	@Override
	public void backward(String requirementId) {
		requirementManageService.compareAndSetStatus(requirementId,this.status(requirementId),IntegrateStatus.NEW.status());
	}

	@Override
	public void disable(String requirementId) {
		requirementManageService.compareAndSetStatus(requirementId,this.status(requirementId),IntegrateStatus.DISABLED.status());
	}

	@Override
	public void rollback(String requirementId) {
		throw new UnsupportedOperationException(requirementId) ;
	}

}
