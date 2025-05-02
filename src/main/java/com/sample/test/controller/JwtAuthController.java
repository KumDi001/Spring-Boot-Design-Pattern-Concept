package com.sample.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.test.entity.AuthRequest;
import com.sample.test.entity.AuthResponse;
import com.sample.test.entity.Employee;
import com.sample.test.exceptions.EmployeeAlreadyExistsException;
import com.sample.test.serviceImpl.EmployeeServiceImpl;
import com.sample.test.util.JwtUtil;

@RestController
@RequestMapping("/auth")
public class JwtAuthController {

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	private EmployeeServiceImpl service;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome this endpoint is validated with Jwt Token";
	}

	@PostMapping("/generateTokenV1")
	public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
		if (authentication.isAuthenticated()) {
			String token = jwtUtil.generateToken(userDetails);
			AuthResponse response = AuthResponse.builder().username(userDetails.getUsername()).token(token).build();
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			throw new UsernameNotFoundException("Invalid user request!");
		}
	}

	@PostMapping("/addNewUser")
	public Employee addNewUser(@RequestBody Employee userInfo) throws EmployeeAlreadyExistsException {
		return service.saveEmployee(userInfo);
	}

	// Removed the role checks here as they are already managed in SecurityConfig

	@PostMapping("/generateToken")
	public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

		UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
		if (authentication.isAuthenticated()) {
			return jwtUtil.generateToken(userDetails);
		} else {
			throw new UsernameNotFoundException("Invalid user request!");
		}
	}

}
