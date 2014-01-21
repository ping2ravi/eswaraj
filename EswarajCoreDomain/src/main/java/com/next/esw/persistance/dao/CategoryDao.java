package com.next.esw.persistance.dao;

import org.springframework.data.neo4j.repository.GraphRepository;

import com.next.esw.persistance.Category;

public interface CategoryDao extends GraphRepository<Category>{

	public Category getCategoryById(Long id);

}