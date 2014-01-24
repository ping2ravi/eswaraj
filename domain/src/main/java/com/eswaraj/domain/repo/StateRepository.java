package com.eswaraj.domain.repo;

import org.springframework.data.neo4j.repository.GraphRepository;

import com.eswaraj.domain.nodes.division.State;

public interface StateRepository extends GraphRepository<State>{

	public State getStateById(Long id);

}
