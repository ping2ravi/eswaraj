package com.eswaraj.domain.nodes;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.eswaraj.domain.base.BaseNode;

/**
 * Highest responsible person for that particular body
 * @author anuj
 * @date Jan 18, 2014
 *
 */
@NodeEntity
public class Administrator extends BaseNode {
	private Person person;
	
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
}
