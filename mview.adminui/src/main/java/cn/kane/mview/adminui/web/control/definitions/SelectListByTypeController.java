package cn.kane.mview.adminui.web.control.definitions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.kane.mview.adminui.web.vo.TemplateDefinitionVO;
import cn.kane.mview.adminui.web.vo.WidgetDefinitionVO;
import cn.kane.mview.service.definition.entity.DefinitionKey;
import cn.kane.mview.service.definition.entity.TemplateDefinition;
import cn.kane.mview.service.definition.entity.WidgetDefinition;
import cn.kane.mview.service.definition.service.TemplateDefinitionManager;
import cn.kane.mview.service.definition.service.WidgetDefinitionManager;

@Controller
public class SelectListByTypeController {

	@Autowired
	private TemplateDefinitionManager templateDefinitionManager;
	@Autowired
	private WidgetDefinitionManager widgetDefinitionManager ; 

	@RequestMapping(value = "templates/type/{type}", method = RequestMethod.GET)
	@ResponseBody
	public List<TemplateDefinitionVO> templateListByType(@PathVariable("type") String type) throws ParseException {
		DefinitionKey fromKey = new DefinitionKey();
		fromKey.setType(type);
		List<TemplateDefinition> templateDefs = templateDefinitionManager.list(fromKey, new DefinitionKey());
		return this.getTemplateDefVO(templateDefs);
	}

	private List<TemplateDefinitionVO> getTemplateDefVO(List<TemplateDefinition> templateDefs) {
		if (null == templateDefs) {
			return null;
		}
		List<TemplateDefinitionVO> defViews = new ArrayList<TemplateDefinitionVO>(templateDefs.size());
		for (TemplateDefinition def : templateDefs) {
			TemplateDefinitionVO defForView = new TemplateDefinitionVO(def);
			defViews.add(defForView);
		}
		return defViews;
	}
	
	@RequestMapping(value = "widgets/type", method = RequestMethod.GET)
	@ResponseBody
	public List<WidgetDefinitionVO> widgetList() throws ParseException {
		List<WidgetDefinition> widgets = widgetDefinitionManager.list(new DefinitionKey(), new DefinitionKey());
		return this.getWidgetDefVO(widgets);
	}

	private List<WidgetDefinitionVO> getWidgetDefVO(List<WidgetDefinition> widgetDefs) {
		if (null == widgetDefs) {
			return null;
		}
		List<WidgetDefinitionVO> vos = new ArrayList<WidgetDefinitionVO>(widgetDefs.size());
		for (WidgetDefinition def : widgetDefs) {
			WidgetDefinitionVO vo = WidgetDefinitionVO.format(def);
			vos.add(vo);
		}
		return vos;
	}
}
