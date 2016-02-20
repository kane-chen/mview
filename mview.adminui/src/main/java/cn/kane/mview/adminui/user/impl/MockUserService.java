package cn.kane.mview.adminui.user.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import cn.kane.mview.adminui.user.entity.User;
import cn.kane.mview.adminui.user.service.UserService;

@Service("userService")
public class MockUserService implements UserService {

	private Map<Long,User> store = new HashMap<Long,User>() ;
	private AtomicLong seq = new AtomicLong(0) ;
	
	private long nextId(){
		return seq.incrementAndGet() ;
	}
	
	@PostConstruct
	public void init(){
		User user = new User() ;
		user.setId(this.nextId());
		user.setUserName("kane");
		user.setEmail("kane@google.com");
		this.add(user);
	}
	
	@Override
	public List<User> list() {
		return new ArrayList<User>(store.values());
	}

	@Override
	public void add(User user) {
		user.setId(this.nextId());
		store.put(user.getId(), user) ;
	}

	@Override
	public void edit(User user) {
		store.put(user.getId(), user) ;
	}

	@Override
	public User get(long id) {
		return store.get(id) ;
	}

	@Override
	public User delete(long id) {
		return store.remove(id) ;
	}

}
