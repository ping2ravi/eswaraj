package com.eswaraj.domain.nodes.different;

import java.util.Set;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.annotation.RelatedToVia;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.eswaraj.domain.nodes.Person;

/**
 * Repo for location queries
 * @author anuj
 * @data Jan 28, 2014
 */
public interface LocationRepository extends GraphRepository<Location>{
	
	public Location getById(Long id);

	@Query("start location=node({0})" +
			"match (location)<-[:SERVED_BY]-(executiveBody) return executiveBody")
	public Set<ExecutiveBody> findExecutiveBodies(Location location);
	
	@Query("start location=node({0}) " +
			"match location<--politicalBody return politicalBody")
	public Set<PoliticalBody> findPoliticalBodies(Location location);
	
}
