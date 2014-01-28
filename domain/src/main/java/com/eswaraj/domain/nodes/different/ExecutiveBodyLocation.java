package com.eswaraj.domain.nodes.different;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

import com.eswaraj.domain.base.BaseRelationship;

/**
 * Relationship for a location that is served by an executive body
 * @author anuj
 * @data Jan 28, 2014
 */
@RelationshipEntity(type="SERVED_BY")
public class ExecutiveBodyLocation extends BaseRelationship {
	
	@StartNode Location location;
	@EndNode ExecutiveBody executiveBody;
	
	private ExecutiveBodyType type;
	
}
