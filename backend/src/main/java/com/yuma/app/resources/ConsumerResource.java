package com.yuma.app.resources;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yuma.app.service.ConsumerService;
import com.yuma.app.to.ConsumerTO;

@Slf4j
@RestController
@RequestMapping("/rest")
public class ConsumerResource {

	final Logger logger = LoggerFactory.getLogger("initial Logger");

	private ConsumerService consumersService;

	public ConsumerResource(ConsumerService consumerService) {
		this.consumersService = consumerService;
	}

	@GetMapping("/all")
	public List<ConsumerTO> getAll() {
		logger.info("retrieving consumers list from DB");
		return this.consumersService.list();
	}
}
