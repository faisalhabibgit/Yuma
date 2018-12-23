package com.yuma.app;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.yuma.app.document.Role;
import com.yuma.app.repository.RoleRepository;

@SpringBootApplication
public class Application {

	private static Logger logger = LoggerFactory.getLogger(Application.class);

	@Value("${rest.api.base.path}")
	private String restApiBasePath;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		logger.info("Started application....");
	}

	@Bean
	CommandLineRunner init(RoleRepository roleRepository) {

		return args -> {

			Optional<Role> adminRole = roleRepository.findByRole("ADMIN");
			if (adminRole == null) {
				Role newAdminRole = new Role();
				newAdminRole.setRole("ADMIN");
				roleRepository.save(newAdminRole);
			}

			Optional<Role> userRole = roleRepository.findByRole("USER");
			if (userRole == null) {
				Role newUserRole = new Role();
				newUserRole.setRole("USER");
				roleRepository.save(newUserRole);
			}
		};

	}


}
