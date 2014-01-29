package com.eswaraj.domain.nodes.different;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

import com.eswaraj.domain.base.BaseRelationship;

@RelationshipEntity(type="PART_OF")
public class LocationDivisions extends BaseRelationship {
	
	@StartNode Location parent;
	@EndNode Location child;

	private LocationType type;
}
