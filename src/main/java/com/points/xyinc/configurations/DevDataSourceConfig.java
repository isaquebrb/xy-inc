package com.points.xyinc.configurations;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.points.xyinc.services.DatabaseService;

@Configuration
@Profile("dev")
public class DevDataSourceConfig {
	@Autowired
	private DatabaseService dbService;

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy; 
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		
		if(!"create".equals(strategy)) {
			return false;
		}
		
		dbService.initializeDataBase();
		
		return true;
	}
}
