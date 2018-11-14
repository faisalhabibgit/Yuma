package com.yuma.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import com.yuma.app.document.Consumer;
import com.yuma.app.repository.ConsumersRepository;
import com.yuma.app.to.ConsumerTO;

@Service
public class ConsumerService {
	private ConsumersRepository consumersRepository;
	private ConversionService conversionService;

	public ConsumerService(ConsumersRepository consumersRepository, ConversionService conversionService) {
		this.consumersRepository = consumersRepository;
		this.conversionService = conversionService;
	}

	public List<ConsumerTO> list() {
		List<ConsumerTO> consumerTOS = new ArrayList<>();
		List<Consumer> consumerList = consumersRepository.findAll();
		
		for (Consumer consumer: consumerList){
			consumerTOS.add(conversionService.convert(consumer, ConsumerTO.class));
		}
		
		return consumerTOS;
	}
}
