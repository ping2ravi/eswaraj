package com.eswaraj.domain.repo;

import org.springframework.data.neo4j.repository.GraphRepository;

import com.eswaraj.domain.nodes.Person;

public interface PersonRepository extends GraphRepository<Person>{
	
	public Person getPersonById(Long id);

}
