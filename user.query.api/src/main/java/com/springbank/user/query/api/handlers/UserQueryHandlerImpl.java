package com.springbank.user.query.api.handlers;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbank.user.query.api.dto.UserLookupResponse;
import com.springbank.user.query.api.queries.FindAllUsersQuery;
import com.springbank.user.query.api.queries.FindUserByIdQuery;
import com.springbank.user.query.api.queries.SearchUsersQuery;
import com.springbank.user.query.api.repositories.UserRepository;

@Service
public class UserQueryHandlerImpl implements UserQueryHandler {
	
	private final UserRepository userRepository;

	@Autowired
	public UserQueryHandlerImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	@QueryHandler
	@Override
	public UserLookupResponse getUserById(FindUserByIdQuery query) {
		var user=userRepository.findById(query.getId());
		return user.isPresent()? new UserLookupResponse(user.get()) : null;
	}

	@QueryHandler
	@Override
	public UserLookupResponse searchUsers(SearchUsersQuery query) {
		var searchResults = userRepository.findByRegexFilter(query.getFilter());
		UserLookupResponse response = new UserLookupResponse(searchResults);
		return response;
	}
	
	@QueryHandler
	@Override
	public UserLookupResponse getAllUsers(FindAllUsersQuery query) {
		// TODO Auto-generated method stub
		var users = userRepository.findAll();
		UserLookupResponse response = new UserLookupResponse(users);
		return response;
	}

}
