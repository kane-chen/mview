package cn.kane.mview.adminui.integrate.state;

import cn.kane.mview.adminui.integrate.service.IntegrateStatus;
import cn.kane.mview.adminui.integrate.service.State;
import cn.kane.mview.service.definition.entity.DefinitionKey;

public class DisabledState implements State {

	private static final IntegrateStatus DISABLE_STATE = IntegrateStatus.DISABLED ;
	
	@Override
	public String status(String requirementId) {
		return DISABLE_STATE.status();
	}

	@Override
	public String actionName(String requirementId) {
		return DISABLE_STATE.forwardName();
	}

	@Override
	public String backwardName(String requirementId) {
		return DISABLE_STATE.backwardName();
	}
	
	@Override
	public String disableName(String requirementId) {
		return DISABLE_STATE.disableName();
	}

	@Override
	public void add(String requirementId, DefinitionKey key) {
		throw new UnsupportedOperationException("disable cannot do any action") ;
	}

	@Override
	public void action(String requirementId) {
		throw new UnsupportedOperationException("disable cannot do any action") ;
	}

	@Override
	public void backward(String requirementId) {
		throw new UnsupportedOperationException("disable cannot do any action") ;

	}

	@Override
	public void disable(String requirementId) {
		throw new UnsupportedOperationException("disable cannot do any action") ;
	}

}
