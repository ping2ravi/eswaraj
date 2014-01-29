package com.eswaraj.domain.nodes.different;

import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Repo for executivebody queries
 * @author anuj
 * @data Jan 28, 2014
 */
public interface ExecutiveBodyRepository extends GraphRepository<ExecutiveBody>{
	
	public ExecutiveBody getById(Long id);

}
