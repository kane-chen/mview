package cn.kane.mview.adminui.web.vo;

import java.util.List;

import cn.kane.mview.adminui.integrate.entity.Requirement;

public class RequirementVO {

	private Requirement requirement ;
	private List<String> changes ;
	
	public Requirement getRequirement() {
		return requirement;
	}
	public void setRequirement(Requirement requirement) {
		this.requirement = requirement;
	}
	public List<String> getChanges() {
		return changes;
	}
	public void setChanges(List<String> changes) {
		this.changes = changes;
	}
	
}
