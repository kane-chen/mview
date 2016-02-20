package cn.kane.mview.adminui.user.entity;

import java.io.Serializable;

public class User implements Serializable{

	private static final long serialVersionUID = 5720432293508618281L;

	private long id ;
	
	private String userName ;
	
	private String email ;

	public long getId() {
		return id;
	}

	public void setId(long userId) {
		this.id = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
