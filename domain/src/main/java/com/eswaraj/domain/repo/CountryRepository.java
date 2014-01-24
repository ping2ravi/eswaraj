package com.eswaraj.domain.repo;

import org.springframework.data.neo4j.repository.GraphRepository;

import com.eswaraj.domain.nodes.division.Country;

public interface CountryRepository extends GraphRepository<Country>{

	public Country getCountryById(Long id);

}
