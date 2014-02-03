package com.eswaraj.domain.repo;

import org.springframework.data.neo4j.repository.GraphRepository;

import com.eswaraj.domain.nodes.different.ExecutiveAdministrator;

public interface ExecutiveAdministratorRepository extends GraphRepository<ExecutiveAdministrator>{
	
	public ExecutiveAdministrator getById(Long id);

}
