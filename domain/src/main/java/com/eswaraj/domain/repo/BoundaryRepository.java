package com.eswaraj.domain.repo;

import org.springframework.data.neo4j.repository.GraphRepository;

import com.eswaraj.domain.nodes.division.Boundary;

public interface BoundaryRepository extends GraphRepository<Boundary>{
	
	public Boundary getById(Long id);

}
