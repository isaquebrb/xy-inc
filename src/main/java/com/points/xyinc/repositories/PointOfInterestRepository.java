package com.points.xyinc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.points.xyinc.domain.PointOfInterest;

public interface PointOfInterestRepository extends JpaRepository<PointOfInterest, Integer>{

}