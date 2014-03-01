package com.eswaraj.domain.nodes;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.eswaraj.domain.base.BaseNode;


/**
 * A executive department 
 * @author ravi
 * @date Jan 18, 2014
 *
 */
@NodeEntity
public class Department extends BaseNode {

	
	private String name;
	private String description;
	private DepartmentType type;
	
	public Department(){}
	public Department(String name, DepartmentType type) {
		this.name = name;
		this.type = type;
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
	public DepartmentType getType() {
		return type;
	}
	public void setType(DepartmentType type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "Department [Name=" + name + ", description=" + description + "]";
	}
}
