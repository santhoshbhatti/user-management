package com.springbank.user.oauth2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springbank.user.oauth2.repositories.UserRepository;

@Service("userService")
public class UserService implements UserDetailsService{

	private UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		var user = userRepository.findByUsername(username);
		if (user.isEmpty()) {
			throw new UsernameNotFoundException("incorrect username or password supplied");
		}
		
		var account = user.get().getAccount();
		var userDetails = org.springframework.security.core.userdetails.User
				.withUsername(account.getUsername())
				.password(account.getPassword())
				.authorities(account.getRoles())
				.accountExpired(false)
				.accountLocked(false)
				.credentialsExpired(false)
				.disabled(false)
				.build();
		return userDetails;
	}
	
	
}
