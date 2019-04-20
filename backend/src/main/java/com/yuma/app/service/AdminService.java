package com.yuma.app.service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import com.yuma.app.document.Consumer;
import lombok.extern.slf4j.Slf4j;

import org.springframework.core.convert.ConversionService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.yuma.app.document.Admin;
import com.yuma.app.document.Role;
import com.yuma.app.payload.SignUpRequest;
import com.yuma.app.repository.AdminRepository;
import com.yuma.app.repository.RoleRepository;
import com.yuma.app.repository.UserRepository;
import com.yuma.app.updater.UpdateUsers;

@Slf4j
@Service
public class AdminService {
	private AdminRepository adminRepository;
	private ConversionService conversionService;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;
	private UserRepository userRepository;
	private UpdateUsers updateUsers;
	private UpdateDataMapperService updateDataMapper;

	public AdminService(AdminRepository adminRepository, ConversionService conversionService, RoleRepository roleRepository, PasswordEncoder passwordEncoder, UpdateUsers updateUsers, UpdateDataMapperService updateDataMapper, UserRepository userRepository) {
		this.adminRepository = adminRepository;
		this.conversionService = conversionService;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
		this.updateUsers = updateUsers;
		this.updateDataMapper = updateDataMapper;
		this.userRepository = userRepository;
	}

	public Admin saveAdmin(SignUpRequest req) {

		log.info("saving admin {}", req.getEmail());
    
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
