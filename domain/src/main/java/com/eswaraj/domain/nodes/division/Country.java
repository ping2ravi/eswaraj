package com.eswaraj.domain.nodes.division;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.eswaraj.domain.base.BaseNode;

/**
 * Country level administrative division
 * @author Ravi
 * @date Jan 18, 2014
 *
 */
@NodeEntity
public class Country extends BaseNode{
	
	private String name;
	private String isdCode;
	private String countryCode;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIsdCode() {
		return isdCode;
	}
	public void setIsdCode(String isdCode) {
		this.isdCode = isdCode;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	@Override
	public String toString() {
		return "Country [name=" + name + ", isdCode=" + isdCode + ", countryCode=" + countryCode + "]";
	}
	
}
