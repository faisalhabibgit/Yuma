package com.yuma.app.service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.yuma.app.document.Admin;
import com.yuma.app.document.Role;
import com.yuma.app.payload.SignUpRequest;
import com.yuma.app.repository.AdminRepository;
import com.yuma.app.repository.RoleRepository;

@Slf4j
@Service
public class AdminService {
	private Logger userServiceLogger = LoggerFactory.getLogger(UserService.class);
	private AdminRepository adminRepository;
	private ConversionService conversionService;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;

	public AdminService(AdminRepository adminRepository, ConversionService conversionService, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
		this.adminRepository = adminRepository;
		this.conversionService = conversionService;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public Admin saveAdmin(SignUpRequest req) {
		this.userServiceLogger.info("saving admin {}", req.getEmail());

		Admin admin = conversionService.convert(req, Admin.class);
		admin.setPassword(passwordEncoder.encode(admin.getPassword()));
		admin.setActive(true);

		Optional<Role> adminRole = roleRepository.findByRole("ADMIN");
		adminRole.ifPresent(x -> admin.setRoles(new HashSet<>(Collections.singletonList(x))));
		return adminRepository.save(admin);
	}

	public boolean existsByEmail(String email){
		return adminRepository.existsByEmail(email);
	}


}
