package cn.kane.mview.adminui.integrate.entity;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.builder.ToStringBuilder;

import cn.kane.mview.service.definition.entity.DefinitionKey;


public class Changes {

	private String id ;
	private Set<DefinitionKey> changes = new HashSet<DefinitionKey>() ;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Set<DefinitionKey> getChanges() {
		return changes;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
