package com.eswaraj.domain.nodes.division;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.eswaraj.domain.base.BaseNode;

/**
 * district level administrative division
 * @author anuj
 * @date Jan 18, 2014
 *
 */
@NodeEntity
public class ParliamentConstituency extends BaseNode{

	private String name;
	private State state;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "ParliamentConstituency [Name=" + name + ", state=" + state	+ "]";
	}
	
}
