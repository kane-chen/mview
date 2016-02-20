package cn.kane.mview.adminui.web.vo;

import com.alibaba.fastjson.JSON;

import cn.kane.mview.service.definition.entity.TemplateDefinition;

public class TemplateDefinitionVO {
	private TemplateDefinition templateDefinition;
	private String vkey;

	public TemplateDefinitionVO(TemplateDefinition templateDefinition) {
		this.templateDefinition = templateDefinition;
		if (null != templateDefinition && null != templateDefinition.getKey()) {
			vkey = JSON.toJSONString(templateDefinition.getKey());
		}
	}

	public TemplateDefinition getTemplateDefinition() {
		return templateDefinition;
	}

	public String getVkey() {
		return vkey;
	}
}