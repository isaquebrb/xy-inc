package com.points.xyinc.services;

import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.points.xyinc.domain.PointOfInterest;
import com.points.xyinc.repositories.PointOfInterestRepository;

@Service
public class DatabaseService {
	
	@Autowired
	private PointOfInterestRepository pointRepository;
	
	public void initializeDataBase() throws ParseException {
		PointOfInterest lanchonete = new PointOfInterest(null, "Lanchonete", 27,12);
		PointOfInterest posto = new PointOfInterest(null, "Posto", 31,18);
		PointOfInterest joalheria = new PointOfInterest(null, "Joalheria", 15,12);
		PointOfInterest floricultura = new PointOfInterest(null, "Floricultura", 19,21);
		PointOfInterest pub = new PointOfInterest(null, "Pub", 12,8);
		PointOfInterest supermercado = new PointOfInterest(null, "Supermercado", 23,6);
		PointOfInterest churrascaria = new PointOfInterest(null, "Churrascaria", 28,2);
		
		pointRepository.saveAll(Arrays.asList(lanchonete,posto,joalheria,floricultura,pub,supermercado,churrascaria));
	}
}
