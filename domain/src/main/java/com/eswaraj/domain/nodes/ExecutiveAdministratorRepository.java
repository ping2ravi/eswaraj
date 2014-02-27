package com.eswaraj.domain.nodes;

import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Repo for executive administrator queries
 * @author anuj
 * @data Jan 28, 2014
 */
public interface ExecutiveAdministratorRepository extends GraphRepository<ExecutiveAdministrator>{
	
	public ExecutiveAdministrator getById(Long id);
	
}
