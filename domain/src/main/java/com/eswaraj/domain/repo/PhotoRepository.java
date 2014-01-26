package com.eswaraj.domain.repo;

import org.springframework.data.neo4j.repository.GraphRepository;

import com.eswaraj.domain.nodes.Photo;

public interface PhotoRepository extends GraphRepository<Photo>{
	
	public Photo getById(Long id);

}
