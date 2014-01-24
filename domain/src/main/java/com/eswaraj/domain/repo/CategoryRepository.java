package com.eswaraj.domain.repo;

import org.springframework.data.neo4j.repository.GraphRepository;

import com.eswaraj.domain.nodes.Category;

public interface CategoryRepository extends GraphRepository<Category>{

	public Category getById(Long id);

}
