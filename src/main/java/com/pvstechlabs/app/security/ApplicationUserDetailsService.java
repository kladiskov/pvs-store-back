package com.pvstechlabs.app.security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pvstechlabs.app.data.entities.Credential;
import com.pvstechlabs.app.data.entities.User;
import com.pvstechlabs.app.data.services.UserService;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {

	@Autowired
	private UserService service;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Credential credential = service.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
		return ApplicationUser.create(credential);
	}

	@Transactional
	public UserDetails loadUserById(Long id) {
		User user = service.findById(id).orElseThrow(() -> new com.pvstechlabs.app.exceptions.ResourceNotFoundException(
				"User with id'" + id + "' not found."));
		return ApplicationUser.create(user.getCredential());
	}

}
