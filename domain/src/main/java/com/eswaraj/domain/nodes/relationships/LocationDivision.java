package com.eswaraj.domain.nodes.relationships;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

import com.eswaraj.domain.base.BaseRelationship;
import com.eswaraj.domain.nodes.DivisionType;
import com.eswaraj.domain.nodes.Location;

@RelationshipEntity(type="PART_OF")
public class LocationDivision extends BaseRelationship {
	
	@StartNode Location child;
	@EndNode Location parent;

	private DivisionType type;

	public LocationDivision(Location parent, Location child, DivisionType type) {
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

	public DivisionType getType() {
		return type;
	}

	public void setType(DivisionType type) {
		this.type = type;
	}
}
