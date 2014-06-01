package com.eswaraj.domain.repo;

import org.springframework.data.neo4j.repository.GraphRepository;

import com.eswaraj.domain.nodes.division.GeoPoint;

public interface GeoPointRepository extends GraphRepository<GeoPoint>{
	
	public GeoPoint getById(Long id);

}
