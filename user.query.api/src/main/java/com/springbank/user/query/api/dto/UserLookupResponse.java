package com.springbank.user.query.api.dto;

import java.util.ArrayList;
import java.util.List;

import com.springbank.user.core.dto.BaseResponse;
import com.springbank.user.core.models.User;


public class UserLookupResponse extends BaseResponse{
	
	private List<User> users;
	
	public UserLookupResponse(User user, String message) {
		super(message);
		this.users = new ArrayList<>();
		this.users.add(user);
	}
	
	public UserLookupResponse(User user) {
		this(user,null);
	}
	
	public UserLookupResponse(List<User> users) {
		super(null);
		this.users = users;	
	}
	
	public UserLookupResponse(String message) {
		super(message);
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	

}
