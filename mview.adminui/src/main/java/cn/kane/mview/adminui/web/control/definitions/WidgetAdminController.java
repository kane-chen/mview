package cn.kane.mview.adminui.web.control.definitions;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.common.json.ParseException;

import cn.kane.mview.adminui.web.utils.JsonDataEditor;
import cn.kane.mview.adminui.web.vo.WidgetDefinitionVO;
import cn.kane.mview.service.definition.entity.DefinitionKey;
import cn.kane.mview.service.definition.entity.WidgetDefinition;
import cn.kane.mview.service.definition.service.WidgetDefinitionManager;

@Controller
public class WidgetAdminController {

	@Autowired
	private WidgetDefinitionManager widgetDefinitionManager ; 
	
	@RequestMapping("widgets")
	@ResponseBody
	public List<WidgetDefinition> list(@RequestParam(value="_filters",required=false)Map<String,Object> filters){
		return widgetDefinitionManager.list(new DefinitionKey(), new DefinitionKey()) ;
	}
	
	@RequestMapping(value="widgets/{type}/{name}/{version}",method=RequestMethod.GET)
	@ResponseBody
	public WidgetDefinitionVO view(@PathVariable("type")String type,
			@PathVariable("name")String name,
			@PathVariable("version")String version) throws IOException{
		DefinitionKey key = this.buildDefinitionKey(type, name, version) ;
		WidgetDefinition def = widgetDefinitionManager.get(key) ;
		return WidgetDefinitionVO.format(def) ;
	}
	
	@RequestMapping(value="widgets",method=RequestMethod.POST)
	@ResponseBody
	public WidgetDefinitionVO add(@RequestBody WidgetDefinitionVO widget) throws ParseException{
		widget.parse();
		widgetDefinitionManager.add(widget) ;
		return widget ;
	}

	@RequestMapping(value="widgets/{type}/{name}/{version}",method=RequestMethod.PUT)
	@ResponseBody
	public WidgetDefinitionVO edit(@PathVariable("type")String type,
			@PathVariable("name")String name,
			@PathVariable("version")String version,
			@RequestBody WidgetDefinitionVO widget) throws ParseException{
		widget.parse();
		widgetDefinitionManager.edit(widget) ;
		return widget ;
	}
	
	@RequestMapping(value="widgets/{type}/{name}/{version}",method=RequestMethod.DELETE)
	@ResponseBody
	public WidgetDefinition delete(@PathVariable("type")String type,
			@PathVariable("name")String name,
			@PathVariable("version")String version){
		DefinitionKey key = this.buildDefinitionKey(type, name, version) ;
		WidgetDefinition widget = widgetDefinitionManager.get(key ) ;
		widgetDefinitionManager.remove(key) ;
		return widget ;
	}
	
	@PostConstruct
	public void init(){
		DefinitionKey widgetKey = this.buildDefinitionKey("widget", "simple", "2") ;
		WidgetDefinition simpleWidget = new WidgetDefinition() ;
		simpleWidget.setKey(widgetKey);
		simpleWidget.setOperator("kane");
		simpleWidget.setCssDefinition(this.buildDefinitionKey("css", "simple", "2"));
		simpleWidget.setJsDefinition(this.buildDefinitionKey("js", "simple", "2"));
		widgetDefinitionManager.add(simpleWidget) ;
	}
	
	private DefinitionKey buildDefinitionKey(String type,String name,String version){
		DefinitionKey key = new DefinitionKey() ;
		key.setType(type);
		key.setName(name);
		key.setVersion(version);
		return key ;
	}

	@InitBinder
	public void initBinder(WebDataBinder dataBinder){
		dataBinder.registerCustomEditor(Map.class, new JsonDataEditor());
	}
	
}


