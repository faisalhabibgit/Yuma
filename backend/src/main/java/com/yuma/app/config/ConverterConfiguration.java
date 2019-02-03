package com.yuma.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.ConverterRegistry;

import com.yuma.app.converter.CombinationReportTOConverter;
import com.yuma.app.converter.MealConverter;
import com.yuma.app.converter.MealTOConverter;
import com.yuma.app.converter.SignupRequestConverter;
import com.yuma.app.converter.UserConverter;
import com.yuma.app.converter.UserTOConverter;

@Configuration
public class ConverterConfiguration {

	@Autowired
	public void configureConverterService(final ConversionService conversionService) {
		ConverterRegistry registry = (ConverterRegistry) conversionService;

		registry.addConverter(new MealConverter());
		registry.addConverter(new MealTOConverter());
		registry.addConverter(new UserConverter());
		registry.addConverter(new UserTOConverter());
		registry.addConverter(new SignupRequestConverter());
		registry.addConverter(new CombinationReportTOConverter(conversionService));
	}
}
