package com.yuma.app.controller;

import java.net.URI;

import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.yuma.app.document.Admin;
import com.yuma.app.payload.ApiResponse;
import com.yuma.app.payload.JwtAuthenticationResponse;
import com.yuma.app.payload.LoginRequest;
import com.yuma.app.payload.SignUpRequest;
import com.yuma.app.security.JwtTokenProvider;
import com.yuma.app.service.AdminService;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

	final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	AdminService adminService;

	@Autowired
	JwtTokenProvider tokenProvider;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		logger.info("processing sign in request");
		Authentication authentication = authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(
				loginRequest.getEmail(),
				loginRequest.getPassword()
			)
		);

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = tokenProvider.generateToken(authentication);
		return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
		logger.info("processing sign up request");
		if(adminService.existsByEmail(signUpRequest.getEmail())) {
			return new ResponseEntity<>(new ApiResponse(false, "Username is already taken!"),
				HttpStatus.BAD_REQUEST);
		}

		if(adminService.existsByEmail(signUpRequest.getEmail())) {
			return new ResponseEntity<>(new ApiResponse(false, "Email Address already in use!"),
				HttpStatus.BAD_REQUEST);
		}
		
		Admin result = adminService.saveAdmin(signUpRequest);

		URI location = ServletUriComponentsBuilder
			.fromCurrentContextPath().path("/users/{username}")
			.buildAndExpand(result.getEmail()).toUri();

		return ResponseEntity.created(location).body(new ApiResponse(true, "Admin registered successfully"));
	}
}
