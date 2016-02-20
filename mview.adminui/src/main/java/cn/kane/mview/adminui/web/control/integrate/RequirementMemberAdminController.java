package cn.kane.mview.adminui.web.control.integrate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.kane.mview.adminui.integrate.service.RequirementMemberService;
import cn.kane.mview.adminui.web.vo.RetCode;

@Controller
public class RequirementMemberAdminController {

	@Autowired
	private RequirementMemberService requirementMemberService ;
	
	@RequestMapping("requirement/members/join")
	@ResponseBody
	public RetCode join(@RequestParam("reqId")String requirementId){
		RetCode retCode = new RetCode() ;
		retCode.setCode("OK");
		String operator = "kane";//logined-operate
		requirementMemberService.join(operator , requirementId);
		return retCode ;
	}
	
}
