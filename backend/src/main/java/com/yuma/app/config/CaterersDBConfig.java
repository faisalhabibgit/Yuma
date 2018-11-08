package com.yuma.app.config;


import java.util.UUID;
import java.util.ArrayList;

import com.yuma.app.document.Address;
import com.yuma.app.document.Caterer;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.yuma.app.repository.CatererRepository;

@Slf4j
@EnableMongoRepositories(basePackageClasses = CatererRepository.class)

@Configuration
public class CaterersDBConfig {

    private Logger logger = LoggerFactory.getLogger("caterer logger");


    @Bean
    CommandLineRunner commandLineRunner(CatererRepository catererRepository) {
        logger.info("this is logging stuff into the DB");
        return strings -> {
            catererRepository.save(new Caterer(UUID.randomUUID(),"Orel","orelmoisa@gmail.com", new Address(5601, "smart", "CSL","QC","Canada","H4W2m4"), "chinese",new ArrayList<>(), "234"));


        };
    }
}
