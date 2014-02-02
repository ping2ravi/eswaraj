package com.eswaraj.domain.nodes.different;

import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedToVia;

import com.eswaraj.domain.base.BaseNode;
import com.eswaraj.domain.nodes.Address;
import com.eswaraj.domain.nodes.division.Boundary;

/**
 * Represents an executive body like Water Department or Fire Department
 * @author anuj
 * @date Jan 28, 2014
 *
 */

@NodeEntity
public class ExecutiveBody extends BaseNode {

	private String name;
	private Address address;
	private Boundary boundary;
	
	@RelatedToVia(type="WORKS_FOR", direction=Direction.INCOMING)
	private Set<ExecutiveBodyAdministrator> employees;
	
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
	public Set<ExecutiveBodyAdministrator> getEmployees() {
		return employees;
	}
	public void setEmployees(Set<ExecutiveBodyAdministrator> employees) {
		this.employees = employees;
	}
}