package com.points.xyinc;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.points.xyinc.repositories.PointOfInterestRepository;

@SpringBootApplication
public class XyIncApplication implements CommandLineRunner {
	
	@Autowired
	private PointOfInterestRepository pointsRepository;

	public static void main(String[] args) {
		SpringApplication.run(XyIncApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {		
	}
}
