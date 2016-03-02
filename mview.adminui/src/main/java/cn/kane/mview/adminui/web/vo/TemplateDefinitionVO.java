package cn.kane.mview.adminui.web.vo;

import org.springframework.cglib.beans.BeanCopier;

import com.alibaba.fastjson.JSON;

import cn.kane.mview.service.definition.entity.TemplateDefinition;

public class TemplateDefinitionVO extends TemplateDefinition{
	/**
	 * 
	 */
	private static final long serialVersionUID = -128876353928779592L;
	private static BeanCopier copier =	BeanCopier.create(TemplateDefinition.class, TemplateDefinitionVO.class, false);
	private String vkey;

	public TemplateDefinitionVO(TemplateDefinition templateDefinition) {
		copier.copy(templateDefinition, this, null);
		if (null != templateDefinition && null != templateDefinition.getKey()) {
			vkey = JSON.toJSONString(templateDefinition.getKey());
		}
	}

	public String getVkey() {
		return vkey;
	}

}