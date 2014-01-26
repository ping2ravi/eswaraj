package com.eswaraj.domain.nodes;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.eswaraj.domain.base.BaseNode;

/**
 * Represents a political party
 * @author anuj
 * @date Jan 18, 2014
 *
 */

@NodeEntity
public class Party extends BaseNode {

	private String name;
	private boolean inPower;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isInPower() {
		return inPower;
	}
	public void setInPower(boolean inPower) {
		this.inPower = inPower;
	}
}
