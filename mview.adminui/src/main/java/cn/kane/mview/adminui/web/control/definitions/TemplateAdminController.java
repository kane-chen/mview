package cn.kane.mview.adminui.web.control.definitions;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.kane.mview.adminui.web.utils.JsonDataEditor;
import cn.kane.mview.service.definition.entity.DefinitionKey;
import cn.kane.mview.service.definition.entity.TemplateDefinition;
import cn.kane.mview.service.definition.service.TemplateDefinitionManager;

@Controller
public class TemplateAdminController {

	@Autowired
	private TemplateDefinitionManager templateDefinitionManager ;
	
	@RequestMapping(value="templates",method=RequestMethod.GET)
	@ResponseBody
	public List<TemplateDefinition> list(@RequestParam(value="_filters",required=false)Map<String,Object> filters) throws ParseException{
		DefinitionKey fromKey = new DefinitionKey() ;
		if(null!=filters){
			fromKey.setType((String)filters.get("keytype"));
		}
		return templateDefinitionManager.list(fromKey, new DefinitionKey()) ;
	}
	
	@RequestMapping(value="templates/{type}/{name}/{version}",method=RequestMethod.GET)
	@ResponseBody
	public TemplateDefinition view(@PathVariable("type")String type,
			@PathVariable("name")String name,
			@PathVariable("version")String version){
		DefinitionKey key = this.buildDefinitionKey(type, name, version);
		return templateDefinitionManager.get(key ) ;
	}
	
	@RequestMapping(value="templates/{key}",method=RequestMethod.GET)
	@ResponseBody
	public TemplateDefinition viewkey(@PathVariable("key")DefinitionKey key){
		return templateDefinitionManager.get(key ) ;
	}
	
	private DefinitionKey buildDefinitionKey(String type,String name,String version){
		DefinitionKey key = new DefinitionKey() ;
		key.setType(type);
		key.setName(name);
		key.setVersion(version);
		return key ;
	}
	
	@RequestMapping(value="templates/{type}/{name}/{version}",method=RequestMethod.PUT)
	@ResponseBody
	public TemplateDefinition edit(@PathVariable("type")String type,
			@PathVariable("name")String name,
			@PathVariable("version")String version,
			@RequestBody TemplateDefinition template){
		templateDefinitionManager.edit(template) ;
		return template ;
	}
	
	@RequestMapping(value="templates",method=RequestMethod.POST)
	@ResponseBody
	public TemplateDefinition add(@RequestBody TemplateDefinition template){
		templateDefinitionManager.add(template) ;
		return template ;
	}
	
	@RequestMapping(value="templates/{type}/{name}/{version}",method=RequestMethod.DELETE)
	@ResponseBody
	public TemplateDefinition delete(@PathVariable("type")String type,
			@PathVariable("name")String name,
			@PathVariable("version")String version){
		DefinitionKey key = this.buildDefinitionKey(type, name, version);
		TemplateDefinition template = templateDefinitionManager.get(key) ;
		templateDefinitionManager.remove(key) ;
		return template ;
	}
	
	@PostConstruct
	public void init(){
		TemplateDefinition css = this.buildTemplateDefinition("css", "simple", "2", "css-content") ;
		TemplateDefinition js = this.buildTemplateDefinition("js", "simple", "2", "js-content") ;
		TemplateDefinition dt = this.buildTemplateDefinition("dataTemplate", "simple", "2", "datatemplate-content") ;
		TemplateDefinition vt = this.buildTemplateDefinition("viewTemplate", "simple", "2", "viewtemplate-content") ;
		TemplateDefinition ds = this.buildTemplateDefinition("dataReadService", "simple", "1", "dataReadService-content") ;
		TemplateDefinition ds2 = this.buildTemplateDefinition("dataReadService", "simple2", "2", "dataReadService222-content") ;
		TemplateDefinition layout = this.buildTemplateDefinition("layout", "simple", "2", "layout-content") ;
		templateDefinitionManager.add(css) ;
		templateDefinitionManager.add(js) ;
		templateDefinitionManager.add(dt) ;
		templateDefinitionManager.add(vt) ;
		templateDefinitionManager.add(ds) ;
		templateDefinitionManager.add(ds2) ;
		templateDefinitionManager.add(layout) ;
	}
	
	private TemplateDefinition buildTemplateDefinition(String type, String name, String version, String content){
		DefinitionKey key = this.buildDefinitionKey(type, name, version) ;
		TemplateDefinition def = new TemplateDefinition() ;
		def.setKey(key);
		def.setContent(content);
		def.setOperator("kane");
		return def ;
	}

	@InitBinder
	public void initBinder(WebDataBinder dataBinder){
		dataBinder.registerCustomEditor(Map.class, new JsonDataEditor());
	}
	
}
