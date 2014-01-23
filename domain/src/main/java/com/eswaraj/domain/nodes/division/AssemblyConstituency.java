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
public class AssemblyConstituency extends BaseNode{

	private String name;
	private District district;
	private ParliamentConstituency parliamentConstituency;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public District getDistrict() {
		return district;
	}
	public void setDistrict(District district) {
		this.district = district;
	}
	public ParliamentConstituency getParliamentConstituency() {
		return parliamentConstituency;
	}
	public void setParliamentConstituency(ParliamentConstituency parliamentConstituency) {
		this.parliamentConstituency = parliamentConstituency;
	}
	@Override
	public String toString() {
		return "AssemblyConstituency [Name=" + name + ", district=" + district + "]";
	}
}
