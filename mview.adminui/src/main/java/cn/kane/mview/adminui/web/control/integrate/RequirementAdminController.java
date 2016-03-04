package cn.kane.mview.adminui.web.control.integrate;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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
import cn.kane.mview.adminui.integrate.service.State;
import cn.kane.mview.adminui.web.vo.RequirementVO;
import cn.kane.mview.adminui.web.vo.RetCode;

@Controller
public class RequirementAdminController {

	@Autowired
	private RequirementManageService requirementManageService ;
	@Resource(name="stateFacade")
	private State state ;
	
	@RequestMapping(value="requirements",method=RequestMethod.GET)
	@ResponseBody
	public List<Requirement> list(@RequestParam(value="_filters",required=false)Map<String,Object> filters){
		return requirementManageService.list(null, null, null) ;
	}
	
	@RequestMapping(value="requirements/{id}",method=RequestMethod.GET)
	@ResponseBody
	public RequirementVO view(@PathVariable("id")String id){
		Requirement requirement = requirementManageService.get(id) ;
		if(null == requirement){
			return null ;
		}
		String stateName = state.status(id) ;
		String forwardName = state.actionName(id) ;
		String backwardName = state.backwardName(id) ;
		String disableName = state.disableName(id) ;
		return RequirementVO.build(requirement, stateName, forwardName, backwardName, disableName) ;
	}
	
	@RequestMapping(value="requirements/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public Requirement edit(@RequestBody Requirement req){
		requirementManageService.edit(req);
		return req ;
	}
	
	@RequestMapping(value="requirements",method=RequestMethod.POST)
	@ResponseBody
	public Requirement add(@RequestBody Requirement user){
		requirementManageService.add(user);
		return user ;
	}
	
	@RequestMapping(value="requirements/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public Requirement delete(@PathVariable("id")String id){
		Requirement req = requirementManageService.get(id) ;
		requirementManageService.remove(id);
		return req ;
	}
	
	@RequestMapping(value="requirements/forward",method=RequestMethod.POST)
	@ResponseBody
	public RetCode forward(@RequestParam("id")String id){
		state.action(id);
		RetCode resp = new RetCode() ;
		resp.setCode("200");
		resp.setErrMsg("SUCCESS");
		return resp ;
	}
	
	@RequestMapping(value="requirements/backward",method=RequestMethod.POST)
	@ResponseBody
	public RetCode backward(@RequestParam("id")String id){
		state.backward(id);
		RetCode resp = new RetCode() ;
		resp.setCode("200");
		resp.setErrMsg("SUCCESS");
		return resp ;
	}
	
	@RequestMapping(value="requirements/disable",method=RequestMethod.POST)
	@ResponseBody
	public RetCode disable(@RequestParam("id")String id){
		state.disable(id);
		RetCode resp = new RetCode() ;
		resp.setCode("200");
		resp.setErrMsg("SUCCESS");
		return resp ;
	}
	
}
