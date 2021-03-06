package com.eswaraj.domain.repo;

import java.util.Set;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.eswaraj.domain.nodes.ExecutiveAdministrator;
import com.eswaraj.domain.nodes.ExecutiveBody;

/**
 * Repo for executivebody queries
 * @author anuj
 * @data Jan 28, 2014
 */
public interface ExecutiveBodyRepository extends GraphRepository<ExecutiveBody>{
	
	public ExecutiveBody getById(Long id);
	
	@Query("start executiveBody=node({0})" +
			"match (executiveBody)<-[:WORKS_FOR]-(executiveAdministrator) return executiveAdministrator")
	public Set<ExecutiveAdministrator> findAdministrators(ExecutiveBody executiveBody);

}
