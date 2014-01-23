package com.eswaraj.domain.repo;

import org.springframework.data.neo4j.repository.GraphRepository;

import com.eswaraj.domain.nodes.Complaint;
import com.eswaraj.domain.nodes.Location;

/**
 * Repository for Location node
 * @author anuj
 * @date Jan 22, 2014
 *
 */
public interface LocationRepository extends GraphRepository<Location>{

	public Location getLocationById(Long id);

}
