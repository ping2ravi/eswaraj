package com.eswaraj.domain.nodes.different;

import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.NodeEntity;
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
	
	@RelatedToVia(type="PART_OF")
	private Location location;
	
	@RelatedToVia(type="PART_OF", direction= Direction.INCOMING)
	private Set<Location> locations;

	@RelatedToVia(type="SERVED_BY")
	private Set<ExecutiveBodyLocation> executiveBodyLocations;
	
	@RelatedToVia(type="GOVERNED_BY")
	private Set<PoliticalBodyLocation> politicalBodyLocations;
}
