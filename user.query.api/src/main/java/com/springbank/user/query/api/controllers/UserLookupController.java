package com.springbank.user.query.api.controllers;

import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbank.user.query.api.dto.UserLookupResponse;
import com.springbank.user.query.api.queries.FindAllUsersQuery;
import com.springbank.user.query.api.queries.FindUserByIdQuery;
import com.springbank.user.query.api.queries.SearchUsersQuery;

@RestController
@RequestMapping("/api/v1/userLookup")
public class UserLookupController {

	private final QueryGateway queryGateway;

	@Autowired
	public UserLookupController(QueryGateway queryGateway) {
		super();
		this.queryGateway = queryGateway;
	}

	@GetMapping
	public ResponseEntity<UserLookupResponse> getAllUsers() {
		try {
			var response = queryGateway.query(new FindAllUsersQuery(), UserLookupResponse.class).join();
			if (response == null || response.getUsers() == null) {
				return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			var safeErrorMessage = "error fetching all users";
			System.out.println("error fetching user details from db "+e.getMessage());
			return new ResponseEntity<UserLookupResponse>(new UserLookupResponse(safeErrorMessage)
					,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<UserLookupResponse> getUserById(@PathVariable("id") String id ){
		try {
			var query = new FindUserByIdQuery(id);
			var response = queryGateway.query(query, UserLookupResponse.class).join();
			return new ResponseEntity<>(response, HttpStatus.OK);
		}catch (Exception e) {
			var safeErrorMessage = "error fetching all users";
			System.out.println("error fetching user with id "+ id +" details from db "+e.getMessage());
			return new ResponseEntity<UserLookupResponse>(new UserLookupResponse(safeErrorMessage)
					,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(path = "/search/{filter}")
	public ResponseEntity<UserLookupResponse> searchuserByFilter(@PathVariable("filter") String filter ){
		try {
			var query = new SearchUsersQuery(filter);
			var response = queryGateway.query(query, UserLookupResponse.class).join();
			return new ResponseEntity<>(response, HttpStatus.OK);
		}catch (Exception e) {
			var safeErrorMessage = "error fetching all users";
			System.out.println("error fetching user with filter "+ filter +" details from db "+e.getMessage());
			return new ResponseEntity<UserLookupResponse>(new UserLookupResponse(safeErrorMessage)
					,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
