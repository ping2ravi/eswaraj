package com.eswaraj.domain.nodes;

import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import com.eswaraj.domain.base.BaseNode;

/**
 * Location of the complaint
 * @author anuj
 * @date Jan 28, 2014
 *
 */
@NodeEntity
public class Location extends BaseNode {

	@Indexed
	private String name;
	
	@RelatedTo(type="OF_TYPE")
	private LocationType locationType;
	
	@RelatedTo(type="PART_OF")
	private Location parentLocation;
	
	private Double lattitude;
	
	private Double longitude;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocationType getLocationType() {
		return locationType;
	}

	public void setLocationType(LocationType locationType) {
		this.locationType = locationType;
	}

	public Location getParentLocation() {
		return parentLocation;
	}

	public void setParentLocation(Location parentLocation) {
		this.parentLocation = parentLocation;
	}

	public Double getLattitude() {
		return lattitude;
	}

	public void setLattitude(Double lattitude) {
		this.lattitude = lattitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "Location [name=" + name + ", locationType=" + locationType + ", parentLocation=" + parentLocation + ", getId()=" + getId() + "]";
	}

	
}
