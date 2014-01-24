package com.eswaraj.domain.repo;

import org.springframework.data.neo4j.repository.GraphRepository;

import com.eswaraj.domain.nodes.division.ParliamentConstituency;

public interface ParliamentConstituencyRepository extends GraphRepository<ParliamentConstituency>{

	public ParliamentConstituency getParliamentConstituencyById(Long id);

}
