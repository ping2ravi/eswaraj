package com.eswaraj.domain.repo;

import org.springframework.data.neo4j.repository.GraphRepository;

import com.eswaraj.domain.nodes.division.ColonyLocalArea;

public interface ColonyLocalAreaRepository extends GraphRepository<ColonyLocalArea>{

	public ColonyLocalArea getColonyLocalAreaById(Long id);

}
