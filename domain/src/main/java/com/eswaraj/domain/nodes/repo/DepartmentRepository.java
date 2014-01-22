package com.eswaraj.domain.nodes.repo;

import org.springframework.data.neo4j.repository.GraphRepository;

import com.eswaraj.domain.nodes.Department;

public interface DepartmentRepository extends GraphRepository<Department>{

	public Department getDepartmentById(Long id);

}
