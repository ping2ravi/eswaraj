package com.eswaraj.domain.repo;

import org.springframework.data.neo4j.repository.GraphRepository;

import com.eswaraj.domain.nodes.relationships.LocationDivision;

/**
 * Repo for location queries
 * @author ravi
 * @data Apr 20, 2014
 */
public interface LocationDivisionRepository extends GraphRepository<LocationDivision>{
	
}
