package com.eswaraj.domain.nodes.different;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.data.neo4j.annotation.RelatedToVia;

import com.eswaraj.domain.base.BaseNode;

/**
 * Location of the complaint
 * @author anuj
 * @date Jan 28, 2014
 *
 */
@NodeEntity
public class Location extends BaseNode {

	private String name;
	
	@RelatedTo(type="PART_OF")
	private Location location;
	
	@RelatedToVia(type="PART_OF",elementClass = LocationDivision.class)
	private Set<LocationDivision> locationDivisions;

	@Fetch @RelatedToVia(type="SERVED_BY", elementClass = ExecutiveBodyLocation.class)
	private Set<ExecutiveBodyLocation> executiveBodyLocations;
	
	@RelatedToVia(type="GOVERNED_BY", elementClass=PoliticalBodyLocation.class)
	private Set<PoliticalBodyLocation> politicalBodyLocations;
	
	{
		executiveBodyLocations = new HashSet<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Set<LocationDivision> getLocationDivisions() {
		return locationDivisions;
	}

	public void setLocationDivisions(Set<LocationDivision> locationDivisions) {
		this.locationDivisions = locationDivisions;
	}

	public Set<ExecutiveBodyLocation> getExecutiveBodyLocations() {
		return executiveBodyLocations;
	}

	public void setExecutiveBodyLocations(Set<ExecutiveBodyLocation> executiveBodyLocations) {
		this.executiveBodyLocations = executiveBodyLocations;
	}

	public Set<PoliticalBodyLocation> getPoliticalBodyLocations() {
		return politicalBodyLocations;
	}

	public void setPoliticalBodyLocations(Set<PoliticalBodyLocation> politicalBodyLocations) {
		this.politicalBodyLocations = politicalBodyLocations;
	}

	public ExecutiveBodyLocation servedBy(ExecutiveBody executiveBody, ExecutiveBodyType type) {
		ExecutiveBodyLocation executiveBodyLocation = new ExecutiveBodyLocation(this, executiveBody, type);
		executiveBodyLocations.add(executiveBodyLocation);
		return executiveBodyLocation;
	}

	public PoliticalBodyLocation governedBy(PoliticalBody politicalBody, PoliticalBodyType type) {
		PoliticalBodyLocation politicalBodyLocation = new PoliticalBodyLocation(this, politicalBody, type);
		//politicalBodyLocations.add(politicalBodyLocation);
		return politicalBodyLocation;
	}
	
	public LocationDivision partOf(Location location, LocationType type) {
		LocationDivision locationDivision = new LocationDivision(this, location, type);
		//locationDivisions.add(locationDivision);
		return locationDivision;
	}
}
