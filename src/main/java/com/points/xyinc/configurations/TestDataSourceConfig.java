package com.points.xyinc.configurations;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.points.xyinc.services.DatabaseService;

@Configuration
@Profile("test")
public class TestDataSourceConfig {
	
	@Autowired
	private DatabaseService dbService;

	@Bean
	public Boolean instantiateDataBase() throws ParseException {
		dbService.initializeDataBase();		
		return true;
	}
}