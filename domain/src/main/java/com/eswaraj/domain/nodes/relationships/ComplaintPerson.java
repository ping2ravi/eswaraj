package com.eswaraj.domain.nodes.relationships;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

import com.eswaraj.domain.nodes.Complaint;
import com.eswaraj.domain.nodes.Person;

@RelationshipEntity(type="CREATED_BY")
public class ComplaintPerson {

	private int weight;
	
	@StartNode Complaint complaint;
	@EndNode Person person;
	
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public Complaint getComplaint() {
		return complaint;
	}
	public void setComplaint(Complaint complaint) {
		this.complaint = complaint;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
}
