package com.eswaraj.domain.nodes.different;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

import com.eswaraj.domain.base.BaseRelationship;

/**
 * Relationship for a location that is served by a political body
 * @author anuj
 * @data Jan 28, 2014
 */
@RelationshipEntity(type="GOVERNED_BY")
public class PoliticalBodyLocation extends BaseRelationship {
	
	@StartNode Location location;
	@EndNode PoliticalBody politicalBody;
	
	private PoliticalBodyType type;

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public PoliticalBody getPoliticalBody() {
		return politicalBody;
	}

	public void setPoliticalBody(PoliticalBody politicalBody) {
		this.politicalBody = politicalBody;
	}

	public PoliticalBodyType getType() {
		return type;
	}

	public void setType(PoliticalBodyType type) {
		this.type = type;
	}
}
