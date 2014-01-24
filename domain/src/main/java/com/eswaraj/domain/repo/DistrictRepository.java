package com.eswaraj.domain.repo;

import org.springframework.data.neo4j.repository.GraphRepository;

import com.eswaraj.domain.nodes.division.District;

public interface DistrictRepository extends GraphRepository<District>{

	public District getDistrictById(Long id);

}
