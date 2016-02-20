package cn.kane.mview.service.resource.entity;

import java.io.Serializable;

import cn.kane.mview.service.definition.entity.DefinitionKey;

public abstract class Resource implements Serializable {

	private static final long serialVersionUID = 6606477140113563011L;

	private DefinitionKey definitionKey;

	public DefinitionKey getDefinitionKey() {
		return definitionKey;
	}

	public void setDefinitionKey(DefinitionKey definitionKey) {
		this.definitionKey = definitionKey;
	}

}
