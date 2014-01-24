package com.eswaraj.domain.repo;

import org.springframework.data.neo4j.repository.GraphRepository;

import com.eswaraj.domain.nodes.division.CityVillage;

public interface CityVillageRepository extends GraphRepository<CityVillage>{

	public CityVillage getCityVillageById(Long id);

}
