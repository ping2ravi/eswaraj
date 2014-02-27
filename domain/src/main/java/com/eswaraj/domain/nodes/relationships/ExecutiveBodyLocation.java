package com.eswaraj.domain.nodes.relationships;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

import com.eswaraj.domain.base.BaseRelationship;
import com.eswaraj.domain.nodes.Department;
import com.eswaraj.domain.nodes.ExecutiveBody;
import com.eswaraj.domain.nodes.Location;

/**
 * Relationship for a location that is served by an executive body
 * @author anuj
 * @data Jan 28, 2014
 */
@RelationshipEntity(type="SERVED_BY")
public class ExecutiveBodyLocation extends BaseRelationship {
	
	@Fetch @StartNode ExecutiveBody executiveBody;
	@Fetch @EndNode Location location;
	
	private Department department;
	
	public ExecutiveBodyLocation() {}

	public ExecutiveBodyLocation(Location location,ExecutiveBody executiveBody, Department department) {
		this.location = location;
		this.executiveBody = executiveBody;
		this.department = department;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public ExecutiveBody getExecutiveBody() {
		return executiveBody;
	}

	public void setExecutiveBody(ExecutiveBody executiveBody) {
		this.executiveBody = executiveBody;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
}
