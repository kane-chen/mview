package cn.kane.mview.adminui.web.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/sample")
public class SampleController {

	@RequestMapping(value="echo")
	public ModelAndView echo(@RequestParam("userName")String userName){
		ModelAndView view = new ModelAndView() ;
		view.addObject("userName", userName) ;
		view.setViewName("echo");
		return view ;
	}
	
}
