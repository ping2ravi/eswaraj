package com.eswaraj.domain.nodes.different;

import static com.eswaraj.domain.nodes.different.Relationships.SERVED_BY;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.RelationshipType;
import org.springframework.data.neo4j.annotation.StartNode;

import com.eswaraj.domain.base.BaseRelationship;

/**
 * Relationship for a location that is served by an executive body
 * @author anuj
 * @data Jan 28, 2014
 */
@RelationshipEntity(type="SERVED_BY")
public class ExecutiveBodyLocation extends BaseRelationship {
	
	@Fetch @StartNode Location location;
	@Fetch @EndNode ExecutiveBody executiveBody;
	
	private ExecutiveBodyType type;
	
	@RelationshipType
	private final Relationships relationship = SERVED_BY;

	public ExecutiveBodyLocation() {}

	public ExecutiveBodyLocation(Location location,ExecutiveBody executiveBody, ExecutiveBodyType type) {
		this.location = location;
		this.executiveBody = executiveBody;
		this.type = type;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public ExecutiveBody getExecutiveBody() {
		return executiveBody;
	}

	public void setExecutiveBody(ExecutiveBody executiveBody) {
		this.executiveBody = executiveBody;
	}

	public ExecutiveBodyType getType() {
		return type;
	}

	public void setType(ExecutiveBodyType type) {
		this.type = type;
	}
}
