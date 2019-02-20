package com.pvstechlabs.app.data.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pvstechlabs.app.data.entities.Credential;
import com.pvstechlabs.app.data.entities.User;
import com.pvstechlabs.app.data.repositories.CredentialRepository;
import com.pvstechlabs.app.data.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private CredentialRepository credRepo;

	public User save(User newUser) {
		return userRepo.save(newUser);
	}

	public Optional<Credential> findByUsername(String username) {
		return credRepo.findByUsername(username);
	}

	public Optional<User> findById(Long id) {
		return userRepo.findById(id);
	}

	public Boolean existsByUsername(String username) {
		return credRepo.existsByUsername(username);
	}

}
