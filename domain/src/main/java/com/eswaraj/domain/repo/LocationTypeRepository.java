package com.eswaraj.domain.repo;

import java.util.Collection;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;

import com.eswaraj.domain.nodes.DataClient;
import com.eswaraj.domain.nodes.LocationType;

/**
 * Repo for location queries
 * @author ravi
 * @data May 30, 2014
 */
public interface LocationTypeRepository extends GraphRepository<LocationType>{
	
	@Query("start locationType=node:LocationType(name={0}) return locationType")
    public LocationType getLocationTypeByName(String name);
	
	@Query("start locationType=node:LocationType(name={0}) match (locationType)-[:PART_OF]->(parentlocationType) return parentlocationType")
    public LocationType getParentLocationType(String locationTypeName);

	@Query("start locationType=node:LocationType(name={0}) match (locationType)<-[:PART_OF]-(childlocationTypes) return childlocationTypes")
    public Collection<LocationType> findLocationTypeByParentLocation(String parentLocationName);
	
	@Query("start locationType=node({0}) match (locationType)<-[:PART_OF]-(childlocationType) return childlocationType")
    public Collection<LocationType> findLocationTypeByParentLocation(LocationType locationType);

	@Query("start locationType=node:LocationType(name={0}) match (locationType)-[:BELONGS_TO]->(dataClient) return locationType")
    public LocationType getLocationTypeByNameAndDataClientType(String name, DataClient dataClient);

	@Query("start dataClient=node:DataClient(name={0}) match (dataClient)<-[:BELONGS_TO]-(locationType) where (NOT Has(locationType.parentLocationType)) or locationType.parentLocationType is null return locationType")
    public LocationType getRootLocationTypeByDataClient(String dataClientName);

	@Query("start dataClient=node({0}) match (dataClient)<-[:BELONGS_TO]-(locationType) return locationType")
    public Collection<LocationType> getAllLocationTypeOfDataClient(Long dataClientId);

}
