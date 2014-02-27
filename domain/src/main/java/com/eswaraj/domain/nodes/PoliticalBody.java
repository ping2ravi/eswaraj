package com.eswaraj.domain.nodes;

import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedToVia;

import com.eswaraj.domain.base.BaseNode;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Boundary getBoundary() {
		return boundary;
	}

	public void setBoundary(Boundary boundary) {
		this.boundary = boundary;
	}

	public Set<PoliticalBodyAdministrator> getMemberships() {
		return memberships;
	}

	public void setMemberships(Set<PoliticalBodyAdministrator> memberships) {
		this.memberships = memberships;
	}
}
