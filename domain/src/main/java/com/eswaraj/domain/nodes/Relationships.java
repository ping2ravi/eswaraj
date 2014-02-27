package com.eswaraj.domain.nodes;

import org.neo4j.graphdb.RelationshipType;

public enum Relationships implements RelationshipType {
	SERVED_BY, GOVERNED_BY;
}
