package com.eswaraj.domain.repo;

import org.springframework.data.neo4j.repository.GraphRepository;

import com.eswaraj.domain.nodes.ExecutiveAdministrator;

/**
 * Repo for executive administrator queries
 * @author anuj
 * @data Jan 28, 2014
 */
public interface ExecutiveAdministratorRepository extends GraphRepository<ExecutiveAdministrator>{
	
	public ExecutiveAdministrator getById(Long id);
	
}
