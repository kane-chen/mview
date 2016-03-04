package cn.kane.mview.adminui.web.control.definitions;

import java.io.IOException;
import java.util.List;
import java.util.Map;

//import javax.annotation.PostConstruct;

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
import cn.kane.mview.adminui.web.vo.PageDefinitionVO;
import cn.kane.mview.service.definition.entity.DefinitionKey;
import cn.kane.mview.service.definition.entity.PageDefinition;
import cn.kane.mview.service.definition.service.PageDefinitionManager;

@Controller
public class PageAdminController {

	@Autowired
	private PageDefinitionManager pageDefinitionManager ; 
	
	@RequestMapping("pages")
	@ResponseBody
	public List<PageDefinition> list(@RequestParam(value="_filters",required=false)Map<String,Object> filters){
		return pageDefinitionManager.list(new DefinitionKey(), new DefinitionKey()) ;
	}
	
	@RequestMapping(value="pages/{type}/{name}/{version}",method=RequestMethod.GET)
	@ResponseBody
	public PageDefinitionVO view(@PathVariable("type")String type,
			@PathVariable("name")String name,
			@PathVariable("version")String version) throws IOException{
		DefinitionKey key = this.buildDefinitionKey(type, name, version) ;
		PageDefinition def = pageDefinitionManager.get(key) ;
		return PageDefinitionVO.format(def) ;
	}
	
	@RequestMapping(value="pages",method=RequestMethod.POST)
	@ResponseBody
	public PageDefinitionVO add(@RequestBody PageDefinitionVO widget) throws ParseException{
		widget.parse();
		pageDefinitionManager.add(widget) ;
		return widget ;
	}

	@RequestMapping(value="pages/{type}/{name}/{version}",method=RequestMethod.PUT)
	@ResponseBody
	public PageDefinitionVO edit(@PathVariable("type")String type,
			@PathVariable("name")String name,
			@PathVariable("version")String version,
			@RequestBody PageDefinitionVO widget) throws ParseException{
		widget.parse();
		pageDefinitionManager.edit(widget) ;
		return widget ;
	}
	
	@RequestMapping(value="pages/{type}/{name}/{version}",method=RequestMethod.DELETE)
	@ResponseBody
	public PageDefinition delete(@PathVariable("type")String type,
			@PathVariable("name")String name,
			@PathVariable("version")String version){
		DefinitionKey key = this.buildDefinitionKey(type, name, version) ;
		PageDefinition widget = pageDefinitionManager.get(key ) ;
		pageDefinitionManager.remove(key) ;
		return widget ;
	}
	
//	@PostConstruct
	@Deprecated
	public void init(){
		DefinitionKey key = this.buildDefinitionKey("page", "simple", "2") ;
		PageDefinition page = new PageDefinition() ;
		page.setKey(key);
		page.setOperator("kane");
		page.setCssDefinition(this.buildDefinitionKey("css", "simple", "2"));
		page.setJsDefinition(this.buildDefinitionKey("js", "simple", "2"));
		pageDefinitionManager.add(page) ;
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


