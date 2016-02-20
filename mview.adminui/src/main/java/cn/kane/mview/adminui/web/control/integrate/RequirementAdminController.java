package cn.kane.mview.adminui.web.control.integrate;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.kane.mview.adminui.integrate.entity.Requirement;
import cn.kane.mview.adminui.integrate.service.RequirementManageService;

@Controller
public class RequirementAdminController {

	@Autowired
	private RequirementManageService requirementManageService ;
	
	@RequestMapping(value="requirements",method=RequestMethod.GET)
	@ResponseBody
	public List<Requirement> list(@RequestParam(value="_filters",required=false)Map<String,Object> filters){
		return requirementManageService.list(null, null, null) ;
	}
	
	@RequestMapping(value="/requirements/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Requirement view(@PathVariable("id")String id){
		return requirementManageService.get(id) ;
		
	}
	
	@RequestMapping(value="/requirements/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public Requirement edit(@RequestBody Requirement req){
		requirementManageService.edit(req);
		return req ;
	}
	
	@RequestMapping(value="/requirements",method=RequestMethod.POST)
	@ResponseBody
	public Requirement add(@RequestBody Requirement user){
		requirementManageService.add(user);
		return user ;
	}
	
	@RequestMapping(value="/requirements/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public Requirement delete(@PathVariable("id")String id){
		Requirement req = requirementManageService.get(id) ;
		requirementManageService.remove(id);
		return req ;
	}
	
}
