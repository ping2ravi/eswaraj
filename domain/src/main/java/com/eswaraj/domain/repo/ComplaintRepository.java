package com.eswaraj.domain.repo;

import org.springframework.data.neo4j.repository.GraphRepository;

import com.eswaraj.domain.nodes.Category;
import com.eswaraj.domain.nodes.Complaint;

/**
 * Repository for Complaint node
 * @author anuj
 * @date Jan 22, 2014
 *
 */
public interface ComplaintRepository extends GraphRepository<Complaint>{

	public Complaint getById(Long id);
	
	public Complaint getByCategory(Category category);

}
