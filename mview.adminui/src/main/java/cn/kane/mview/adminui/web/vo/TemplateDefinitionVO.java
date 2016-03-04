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

	private TemplateDefinitionVO(){
		
	}
	public static TemplateDefinitionVO build(TemplateDefinition templateDefinition) {
		if(null == templateDefinition){
			return null ;
		}
		TemplateDefinitionVO vo = new TemplateDefinitionVO() ;
		copier.copy(templateDefinition, vo, null);
		if (null != templateDefinition && null != templateDefinition.getKey()) {
			vo.vkey = JSON.toJSONString(templateDefinition.getKey());
		}
		return vo ;
	}

	public String getVkey() {
		return vkey;
	}

}