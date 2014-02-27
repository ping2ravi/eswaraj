package com.eswaraj.domain.nodes;

import com.eswaraj.domain.base.BaseNode;

/**
 * A executive department 
 * @author ravi
 * @date Jan 18, 2014
 *
 */
public class Department {

	
	private String name;
	private String description;
	private DepartmentType type;
	
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
		return "Department [Name=" + name + ", description=" + description + "]";
	}
}
