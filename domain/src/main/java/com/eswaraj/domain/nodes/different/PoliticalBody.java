package com.eswaraj.domain.nodes.different;

import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedToVia;

import com.eswaraj.domain.base.BaseNode;
import com.eswaraj.domain.nodes.Address;
import com.eswaraj.domain.nodes.division.Boundary;

/**
 * Represents a political body like a political party
 * @author anuj
 * @date Jan 28, 2014
 *
 */

@NodeEntity
public class PoliticalBody extends BaseNode {

	private String name;
	private Address address;
	private Boundary boundary;
	
	@RelatedToVia(type="MEMBER_OF", direction=Direction.INCOMING)
	private Set<PoliticalBodyAdministrator> memberships;
	
}
