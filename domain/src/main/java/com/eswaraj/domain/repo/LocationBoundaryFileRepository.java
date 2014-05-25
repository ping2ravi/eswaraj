package com.eswaraj.domain.repo;

import org.springframework.data.neo4j.repository.GraphRepository;

import com.eswaraj.domain.nodes.LocationBoundaryFile;

/**
 * Repo for location queries
 * @author anuj
 * @data Jan 28, 2014
 */
public interface LocationBoundaryFileRepository extends GraphRepository<LocationBoundaryFile>{
	
}
