package com.eswaraj.domain.repo;

import org.springframework.data.neo4j.repository.GraphRepository;

import com.eswaraj.domain.nodes.PoliticalAdministrator;

public interface PoliticalAdministratorRepository extends GraphRepository<PoliticalAdministrator>{
	
	public PoliticalAdministrator getById(Long id);

}
