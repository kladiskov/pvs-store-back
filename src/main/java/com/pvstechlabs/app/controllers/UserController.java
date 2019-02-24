package com.pvstechlabs.app.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pvstechlabs.app.data.entities.Credential;
import com.pvstechlabs.app.data.entities.User;
import com.pvstechlabs.app.data.entities.User.UserBuilder;
import com.pvstechlabs.app.data.repositories.UserRepository;
import com.pvstechlabs.app.data.services.UserService;
import com.pvstechlabs.app.exceptions.ResourceNotFoundException;
import com.pvstechlabs.app.payload.APIResponse;
import com.pvstechlabs.app.payload.AuthenticationResponse;
import com.pvstechlabs.app.payload.Login;
import com.pvstechlabs.app.payload.Register;
import com.pvstechlabs.app.security.SecurityTokenProvider;

@CrossOrigin
@RestController
@RequestMapping(value = "/pvs-store/api/user")
public class UserController {

	private static final String USER_ROLE = "ROLE_USER";

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private SecurityTokenProvider tokenProvider;

	@PostMapping(value = "/login")
	public ResponseEntity<?> authenticate(@Valid @RequestBody Login login) {
		System.out.println("recevied login: " + login);
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = tokenProvider.generateToken(authentication);
		return ResponseEntity.ok(new AuthenticationResponse(token));
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> registerUser(@RequestBody Register register) throws ResourceNotFoundException {
		if (userService.existsByUsername(register.getUsername())) {
			return new ResponseEntity(new APIResponse(false, "Username is already in use."), HttpStatus.BAD_REQUEST);
		}
		Credential credential = new Credential(register.getUsername(), passwordEncoder.encode(register.getPassword()),
				USER_ROLE);
		User user = new UserBuilder().setEmailId(register.getEmail()).setFirstName(register.getFirstname())
				.setLastName(register.getLastname()).setPhoneNumber(register.getPhone()).build();

		/*headers.add("x-auth-token", token);
		headers.add("Access-Control-Allow-Credentials", "x-auth-token");
		return new ResponseEntity<APIResponse>(new APIResponse(true, "User registered successfully."), headers,
				HttpStatus.OK);*/
		user.setCredential(credential);
		credential.setUser(user);
		User result = userService.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{username}")
				.buildAndExpand(result.getCredential().getUsername()).toUri();
		return ResponseEntity.created(location).body(new APIResponse(true, "User registered successfully."));
	}

}
