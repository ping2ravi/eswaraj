package com.eswaraj.domain.nodes.different;

import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.data.neo4j.annotation.RelatedToVia;

import com.eswaraj.domain.base.BaseNode;
import com.eswaraj.domain.nodes.Person;

/**
 * Highest responsible person for that particular division
 * @author anuj
 * @date Jan 18, 2014
 *
 */
public class Administrator extends BaseNode {
	private Person person;
	@RelatedTo(type="REPORTS_TO")
	private Person manager;
	
	@RelatedTo(type="REPORTS_TO", direction=Direction.INCOMING)
	private Set<Person> directReports;
	
	@RelatedToVia(type="WORKS_FOR")
	private Set<ExecutiveBodyAdministrator> employees;
	
	@RelatedToVia(type="MEMBER_OF", direction=Direction.INCOMING)
	private Set<ExecutiveBodyAdministrator> memberships;

	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public Person getManager() {
		return manager;
	}
	public void setManager(Person manager) {
		this.manager = manager;
	}
	public Set<Person> getDirectReports() {
		return directReports;
	}
	public void setDirectReports(Set<Person> directReports) {
		this.directReports = directReports;
	}
}
