package cn.kane.mview.adminui.web.control.integrate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.kane.mview.adminui.integrate.service.ChangesManageService;
import cn.kane.mview.adminui.web.utils.DefinitionKeysJsonUtil;
import cn.kane.mview.adminui.web.utils.JsonDataEditor;
import cn.kane.mview.adminui.web.vo.ChangeVO;
import cn.kane.mview.adminui.web.vo.PageDefinitionVO;
import cn.kane.mview.adminui.web.vo.WidgetDefinitionVO;
import cn.kane.mview.service.definition.entity.DefinitionKey;
import cn.kane.mview.service.definition.entity.TemplateDefinition;
import cn.kane.mview.service.definition.service.PageDefinitionManager;
import cn.kane.mview.service.definition.service.TemplateDefinitionManager;
import cn.kane.mview.service.definition.service.WidgetDefinitionManager;

@Controller
public class ChangeAdminController {

	@Autowired
	private TemplateDefinitionManager templateDefinitionManager ;
	@Autowired
	private WidgetDefinitionManager widgetDefinitionManager ;
	@Autowired
	private PageDefinitionManager pageDefinitionManager ;
	@Autowired
	private ChangesManageService changesManageService ;
	
	@RequestMapping(value="changes",method=RequestMethod.GET)
	@ResponseBody
	public List<ChangeVO> list(@RequestParam(value="_filters",required=false)Map<String,Object> filters) throws ParseException{
		String requirementId = (String) filters.get("requirement_id") ;
		if(null == requirementId){
			return null ;
		}
		List<DefinitionKey> changes = changesManageService.list(requirementId) ;
		if(null == changes || changes.isEmpty()){
			return null ;
		}
		List<ChangeVO> changevos = new ArrayList<ChangeVO>(changes.size()) ;
		for(DefinitionKey key : changes){
			changevos.add(ChangeVO.build(key)) ;
		}
		return changevos ;
	}
	
	@RequestMapping(value="changes/{key}",method=RequestMethod.GET)
	@ResponseBody
	public TemplateDefinition view(@PathVariable("key")String keystr){
		DefinitionKey key = DefinitionKeysJsonUtil.parseKey(keystr);
		if("widget".equals(key.getType())){
			return WidgetDefinitionVO.format(widgetDefinitionManager.get(key)) ;
		}else if("page".equals(key.getType())){
			return PageDefinitionVO.format(pageDefinitionManager.get(key)) ;
		}else{
			return templateDefinitionManager.get(key ) ;
		}
	}
	
	@RequestMapping(value="changes",method=RequestMethod.DELETE)
	@ResponseBody
	public TemplateDefinition delete(@RequestParam("key")String keystr,
			@RequestParam("requirementId")String requirementId){
		TemplateDefinition definition ;
		DefinitionKey key = DefinitionKeysJsonUtil.parseKey(keystr) ;
		if("widget".equals(key.getType())){
			definition = WidgetDefinitionVO.format(widgetDefinitionManager.get(key)) ;
			widgetDefinitionManager.remove(key) ;
		}else if("page".equals(key.getType())){
			definition = PageDefinitionVO.format(pageDefinitionManager.get(key)) ;
			pageDefinitionManager.remove(key) ;
		}else{
			definition = templateDefinitionManager.get(key ) ;
			templateDefinitionManager.remove(key) ;
		}
		changesManageService.remove(requirementId, key);
		return definition ;
	}
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder){
		dataBinder.registerCustomEditor(Map.class, new JsonDataEditor());
	}
	
}
