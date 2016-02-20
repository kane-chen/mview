package cn.kane.mview.manager.definition.manager;

import cn.kane.mview.service.definition.entity.TemplateDefinition;
import cn.kane.mview.service.definition.service.TemplateDefinitionManager;


public class BaseTemplateDefinitionManager extends AbstractResourceDefinitionManager<TemplateDefinition>
		implements TemplateDefinitionManager {

	@Override
	public TemplateDefinition formatBeforeWrite(TemplateDefinition definition) {
		return definition;
	}

	@Override
	public TemplateDefinition parseAfterRead(TemplateDefinition definition) {
		return definition;
	}

	@Override
	public Class<TemplateDefinition> getTargetClass() {
		return TemplateDefinition.class;
	}

}
