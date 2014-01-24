package com.eswaraj.domain.repo;

import org.springframework.data.neo4j.repository.GraphRepository;

import com.eswaraj.domain.nodes.division.Ward;

public interface WardRepository extends GraphRepository<Ward>{

	public Ward getWardById(Long id);

}
