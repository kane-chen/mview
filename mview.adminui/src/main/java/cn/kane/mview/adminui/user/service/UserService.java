package cn.kane.mview.adminui.user.service;

import java.util.List;

import cn.kane.mview.adminui.user.entity.User;

public interface UserService {

	List<User> list() ;
	
	void add(User user) ;
	
	void edit(User user) ;
	
	User get(long id) ;

	User delete(long id) ;
	
}
