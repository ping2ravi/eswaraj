package com.eswaraj.domain.repo;

import org.springframework.data.neo4j.repository.GraphRepository;

import com.eswaraj.domain.nodes.division.AssemblyConstituency;

public interface AssemblyConstituencyRepository extends GraphRepository<AssemblyConstituency>{
	
	public AssemblyConstituency getAssemblyConstituencyById(Long id);

}
