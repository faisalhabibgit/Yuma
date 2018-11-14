package com.yuma.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.ConverterRegistry;

import com.yuma.app.converter.CatererConverter;
import com.yuma.app.converter.CatererTOConverter;
import com.yuma.app.converter.ConsumerConverter;
import com.yuma.app.converter.ConsumerTOConverter;
import com.yuma.app.converter.MealConverter;
import com.yuma.app.converter.MealTOConverter;

@Configuration
public class ConverterConfiguration {

	@Autowired
	public void configureConverterService(final ConversionService conversionService) {

		ConverterRegistry registry = (ConverterRegistry) conversionService;

		registry.addConverter(new MealConverter());
    registry.addConverter(new MealTOConverter());
		registry.addConverter(new CatererConverter());
		registry.addConverter(new CatererTOConverter());
		registry.addConverter(new ConsumerConverter());
		registry.addConverter(new ConsumerTOConverter());

	}
}
