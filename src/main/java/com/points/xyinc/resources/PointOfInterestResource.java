package com.points.xyinc.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.points.xyinc.domain.PointOfInterest;
import com.points.xyinc.services.PointOfInterestService;

@RestController
@RequestMapping(value = "/points")
public class PointOfInterestResource {

	@Autowired
	private PointOfInterestService pointService;

	// GET: /points
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<PointOfInterest>> findAll() {

		List<PointOfInterest> listPoints = pointService.findAll();
		return ResponseEntity.ok().body(listPoints);
	}

	// POST: /points
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody PointOfInterest point) {
		PointOfInterest pointResponse = pointService.insert(point);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pointResponse.getId())
				.toUri();

		return ResponseEntity.created(uri).build();

	}
	
	// POST: /findByCoordenates?x={0}&y={1}&distance={2}
	@RequestMapping(value = "/findByCoordenates", method = RequestMethod.GET)
	public ResponseEntity<List<PointOfInterest>> findByCoordenates(
			@RequestParam(value = "x") Integer coordinateX,
			@RequestParam(value = "y") Integer coordinateY,
			@RequestParam(value = "distance") Integer distance) {
		
		List<PointOfInterest> returnPoints = pointService.findByCoordenates(coordinateX, coordinateY, distance);
		
		return ResponseEntity.ok().body(returnPoints);
	}

}
