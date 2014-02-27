package com.eswaraj.domain.nodes;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

import com.eswaraj.domain.base.BaseRelationship;

@RelationshipEntity(type="PART_OF")
public class LocationDivision extends BaseRelationship {
	
	@StartNode Location child;
	@EndNode Location parent;

	private LocationType type;

	public LocationDivision(Location parent, Location child, LocationType type) {
		this.parent = parent;
		this.child = child;
		this.type = type;
	}

	public Location getParent() {
		return parent;
	}

	public void setParent(Location parent) {
		this.parent = parent;
	}

	public Location getChild() {
		return child;
	}

	public void setChild(Location child) {
		this.child = child;
	}

	public LocationType getType() {
		return type;
	}

	public void setType(LocationType type) {
		this.type = type;
	}
}
