package com.eswaraj.domain.nodes;

import static org.neo4j.graphdb.Direction.INCOMING;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.data.neo4j.annotation.RelatedToVia;

import com.eswaraj.domain.base.BaseNode;
import com.eswaraj.domain.nodes.relationships.ExecutiveBodyLocation;
import com.eswaraj.domain.nodes.relationships.LocationDivision;
import com.eswaraj.domain.nodes.relationships.PoliticalBodyLocation;

/**
 * Location of the complaint
 * @author anuj
 * @date Jan 28, 2014
 *
 */
@NodeEntity
public class Location extends BaseNode {

	private String name;
	private LocationType type;
	
	@RelatedTo(type="PART_OF")
	private Location location;
	
	@RelatedToVia(type="PART_OF",elementClass = LocationDivision.class)
	private Set<LocationDivision> locationDivisions;

	@RelatedToVia(type="SERVED_BY", direction= INCOMING)
	@Fetch
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

	public ExecutiveBodyLocation servedBy(ExecutiveBody executiveBody, Department department) {
		ExecutiveBodyLocation executiveBodyLocation = new ExecutiveBodyLocation(this, executiveBody, department);
		executiveBodyLocations.add(executiveBodyLocation);
		return executiveBodyLocation;
	}

	public PoliticalBodyLocation governedBy(PoliticalBody politicalBody, PoliticalBodyType type) {
		PoliticalBodyLocation politicalBodyLocation = new PoliticalBodyLocation(this, politicalBody, type);
		//politicalBodyLocations.add(politicalBodyLocation);
		return politicalBodyLocation;
	}
	
	public LocationDivision partOf(Location location, DivisionType type) {
		LocationDivision locationDivision = new LocationDivision(this, location, type);
		//locationDivisions.add(locationDivision);
		return locationDivision;
	}
}
