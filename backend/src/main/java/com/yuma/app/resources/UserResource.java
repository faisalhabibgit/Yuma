package com.yuma.app.resources;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yuma.app.document.Consumer;
import com.yuma.app.repository.ConsumersRepository;

@Slf4j
@RestController
@RequestMapping("api/rest")
public class UserResource {

	final Logger logger = LoggerFactory.getLogger("initial Logger");

	private ConsumersRepository consumersRepository;

	public UserResource(ConsumersRepository consumersRepository) {
		this.consumersRepository = consumersRepository;
	}

	@GetMapping("/all")
	public List<Consumer> getAll() {
		logger.info("retrieving consumers list from DB");
		return this.consumersRepository.findAll();
	}
}
