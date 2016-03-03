package cn.kane.mview.adminui.integrate.state;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.kane.mview.adminui.integrate.service.ChangesManageService;
import cn.kane.mview.adminui.integrate.service.IntegrateStatus;
import cn.kane.mview.adminui.integrate.service.RequirementManageService;
import cn.kane.mview.adminui.integrate.service.State;
import cn.kane.mview.service.definition.entity.DefinitionKey;

public class PublishedState implements State {

	private static final IntegrateStatus PUBLISH_STATE = IntegrateStatus.PUBLISHED ;
	@Autowired
	private RequirementManageService requirementManageService;
	@Autowired
	private ChangesManageService changesManageService ;
	@Override
	public String status(String requirementId) {
		return PUBLISH_STATE.status();
	}

	@Override
	public String actionName(String requirementId) {
		return PUBLISH_STATE.forwardName();
	}

	@Override
	public String backwardName(String requirementId) {
		return PUBLISH_STATE.backwardName();
	}
	
	@Override
	public String disableName(String requirementId) {
		return PUBLISH_STATE.disableName();
	}

	@Override
	public void add(String requirementId, DefinitionKey key) {
		throw new UnsupportedOperationException(requirementId) ;
	}

	@Override
	public void action(String requirementId) {
		throw new UnsupportedOperationException(requirementId) ;
	}

	@Override
	public void backward(String requirementId) {
//		requirementManageService.compareAndSetStatus(requirementId,this.status(requirementId),IntegrateStatus.COMMITED.status());
		List<DefinitionKey> backupKeys = changesManageService.listBackup(requirementId) ;
		changesManageService.writeTrunk(backupKeys);
		requirementManageService.compareAndSetStatus(requirementId,this.status(requirementId),IntegrateStatus.ROLLBACKED.status());
	}

	@Override
	public void disable(String requirementId) {
		requirementManageService.compareAndSetStatus(requirementId,this.status(requirementId),IntegrateStatus.DISABLED.status());
	}

}
