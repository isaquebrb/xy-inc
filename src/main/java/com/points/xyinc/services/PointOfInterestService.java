package com.points.xyinc.services;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.points.xyinc.domain.PointOfInterest;
import com.points.xyinc.repositories.PointOfInterestRepository;
import com.points.xyinc.services.exceptions.InvalidParametersException;

@Service
public class PointOfInterestService{

	@Autowired
	private PointOfInterestRepository pointsRepository;

	// Returns one list of registred interest points
	public List<PointOfInterest> findAll() {
		return pointsRepository.findAll();
	}

	// Register a point of interest
	public PointOfInterest insert(PointOfInterest point) {

		point.setId(null);

		if (point.getName() == null || point.getName().isEmpty() ||
				point.getCoordinateX() == null || point.getCoordinateY() == null) {
			throw new InvalidParametersException(
					"It was not possivel register the interest point, the 'name', 'coordinate X' and 'coordinate Y' are required");
		}

		return pointsRepository.save(point);
	}

	// Return near interest point by coordinate x, coordinate y and distance
	public List<PointOfInterest> findByCoordenates(Integer x, Integer y, Integer distance) {

		List<PointOfInterest> allPoints = this.findAll();

		Stream<PointOfInterest> filterDistance = allPoints.stream().filter(p -> p.getDistance(x, y) < distance);

		return filterDistance.collect(Collectors.toList());
	}
}
