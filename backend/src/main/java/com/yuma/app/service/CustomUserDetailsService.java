package com.yuma.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuma.app.document.Admin;
import com.yuma.app.repository.AdminRepository;
import com.yuma.app.security.UserPrincipal;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private AdminRepository adminRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Admin admin = adminRepository.findByEmail(email)
			.orElseThrow(() ->
				new UsernameNotFoundException("Admin not found with username or email : " + email)
			);

		return UserPrincipal.create(admin);
	}

	@Transactional
	public UserDetails loadUserById(String id) {
		Admin admin = adminRepository.findByUserId(id).orElseThrow(
			() -> new UsernameNotFoundException("Admin not found with id : " + id)
		);

		return UserPrincipal.create(admin);
	}
}
