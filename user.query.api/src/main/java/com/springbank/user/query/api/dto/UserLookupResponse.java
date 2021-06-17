package com.springbank.user.query.api.dto;

import java.util.ArrayList;
import java.util.List;

import com.springbank.user.core.models.User;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLookupResponse {
	
	private List<User> users;
	
	public UserLookupResponse(User user) {
		this.users = new ArrayList<>();
		this.users.add(user);
	}

}
