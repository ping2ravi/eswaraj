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
public class District extends BaseNode{

	private String Name;
	private State state;
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
}
