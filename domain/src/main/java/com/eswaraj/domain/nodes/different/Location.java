package com.eswaraj.domain.nodes.different;

import java.util.Collection;
import java.util.Set;

import org.neo4j.graphdb.Direction;
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
	
//	@RelatedTo(type="PART_OF")
//	private Location location;
//	
//	@RelatedTo(type="PART_OF", direction= Direction.INCOMING)
//	private Set<Location> locations;

	@RelatedToVia(type="SERVED_BY", direction = Direction.INCOMING)
	private Set<ExecutiveBody> executiveBodies;
	
//	@RelatedToVia(elementClass=PoliticalBodyLocation.class, type="GOVERNED_BY", direction = Direction.INCOMING)
//	private Set<PoliticalBodyLocation> politicalBodyLocations;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public Location getLocation() {
//		return location;
//	}
//
//	public void setLocation(Location location) {
//		this.location = location;
//	}
//
//	public Set<Location> getLocations() {
//		return locations;
//	}
//
//	public void setLocations(Set<Location> locations) {
//		this.locations = locations;
//	}

	public Set<ExecutiveBody> getExecutiveBodies() {
		return executiveBodies;
	}

	public void setExecutiveBodies(Set<ExecutiveBody> executiveBodies) {
		this.executiveBodies = executiveBodies;
	}

//	public Set<PoliticalBodyLocation> getPoliticalBodyLocations() {
//		return politicalBodyLocations;
//	}
//
//	public void setPoliticalBodyLocations(
//			Set<PoliticalBodyLocation> politicalBodyLocations) {
//		this.politicalBodyLocations = politicalBodyLocations;
//	}
}
