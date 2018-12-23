package com.yuma.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	private static Logger logger = LoggerFactory.getLogger(Application.class);

	@Value("${rest.api.base.path}")
	private String restApiBasePath;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		logger.info("Started application....");
	}

}
