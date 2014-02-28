package com.eswaraj.domain.nodes.division;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.eswaraj.domain.base.BaseNode;

/**
 * State level administrative division
 * @author ravi
 * @date Jan 18, 2014
 *
 */
@NodeEntity
public class State extends BaseNode{

	private String name;
	private Country country;
	//@RelatedToVia(type="BELONGS_TO", direction=Direction.INCOMING)
	//private List<StateExecutiveBody> stateExecutiveBodies;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	@Override
	public String toString() {
		return "State [Name=" + name + ", country=" + country + "]";
	}
}
