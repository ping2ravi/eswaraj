package com.eswaraj.domain.repo;

import org.springframework.data.neo4j.repository.GraphRepository;

import com.eswaraj.domain.nodes.Party;

public interface PartyRepository extends GraphRepository<Party>{
	
	public Party getById(Long id);

}
