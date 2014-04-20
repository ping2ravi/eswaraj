package com.eswaraj.domain.repo;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.eswaraj.domain.nodes.ExecutiveBody;
import com.eswaraj.domain.nodes.Location;
import com.eswaraj.domain.nodes.LocationType;
import com.eswaraj.domain.nodes.PoliticalBody;

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
	
	@Query("start location=node:Location(name={0}) return location")
    public Location getLocationFromName(String name);
	
	@Query("start location=node:Location(locationType={0}) return location")
    public List<Location> findLocationByLocationtype(LocationType locationType);

	@Query("start location=node:Location(name={0}) match (location)-[:PART_OF]->(parentlocation) return parentlocation")
    public Location getParentLocation(String locationName);

	@Query("start location=node:Location(name={0}) match (location)<-[:PART_OF]-(childlocation) return childlocation")
    public Collection<Location> findLocationByParentLocation(String parentLocationName);
	
	@Query("start location=node:Location(name={0}) match (location)<-[:PART_OF]-(childlocation) where childlocation.locationType={1}  return childlocation")
    public Collection<Location> findLocationByParentLocationAndLocationType(String parentLocationName, LocationType locationType);

}
