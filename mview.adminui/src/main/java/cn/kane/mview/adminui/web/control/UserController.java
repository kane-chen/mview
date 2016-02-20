package cn.kane.mview.adminui.web.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.kane.mview.adminui.user.entity.User;
import cn.kane.mview.adminui.user.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService ;
	
	@RequestMapping(value="/users")
	@ResponseBody
	public List<User> list(){
		return userService.list() ;
	}
	
	@RequestMapping(value="/users/{id}",method=RequestMethod.GET)
	@ResponseBody
	public User view(@PathVariable("id")Long id){
		return userService.get(id) ;
		
	}
	
	@RequestMapping(value="/users/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public User edit(@RequestBody User user){
		userService.edit(user);
		return user ;
	}
	
	@RequestMapping(value="/users",method=RequestMethod.POST)
	@ResponseBody
	public User add(@RequestBody User user){
		userService.add(user);
		return user ;
	}
	
	@RequestMapping(value="/users/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public User delete(@PathVariable("id")Long id){
		return userService.delete(id) ;
	}
}
