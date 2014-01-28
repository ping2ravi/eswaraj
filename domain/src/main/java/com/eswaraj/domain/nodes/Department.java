package com.eswaraj.domain.nodes;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.eswaraj.domain.base.BaseNode;

/**
 * A Governemnt department 
 * @author ravi
 * @date Jan 18, 2014
 *
 */
@NodeEntity
public class Department extends BaseNode{

	
	private String name;
	private String description;
	
	public Department(){}
	public Department(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Department [Name=" + name + ", description=" + description + ", getId()=" + getId() + "]";
	}
}
