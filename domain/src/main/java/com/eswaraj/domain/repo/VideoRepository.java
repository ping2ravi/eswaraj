package com.eswaraj.domain.repo;

import org.springframework.data.neo4j.repository.GraphRepository;

import com.eswaraj.domain.nodes.Video;

public interface VideoRepository extends GraphRepository<Video>{
	
	public Video getById(Long id);
}
