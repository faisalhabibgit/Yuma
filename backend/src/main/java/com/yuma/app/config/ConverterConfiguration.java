package com.yuma.app.config;

import com.yuma.app.converter.MealConverter;
import com.yuma.app.converter.MealToConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.ConverterRegistry;
import lombok.val;

@Configuration
public class ConverterConfiguration {

    @Autowired
    public void configureConverterService(final ConversionService conversionService) {

        ConverterRegistry registry = (ConverterRegistry) conversionService;

        registry.addConverter(new MealConverter());
        registry.addConverter(new MealToConverter());
    }
}
