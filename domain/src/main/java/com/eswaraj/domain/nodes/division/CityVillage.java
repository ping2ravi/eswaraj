package com.eswaraj.domain.nodes.division;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.eswaraj.domain.base.BaseNode;

/**
 * Village level administrative division
 * @author ravi
 * @date Jan 18, 2014
 *
 */
@NodeEntity
public class CityVillage extends BaseNode{

	private String name;
	private AssemblyConstituency assemblyConstituency;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public AssemblyConstituency getAssemblyConstituency() {
		return assemblyConstituency;
	}
	public void setAssemblyConstituency(AssemblyConstituency assemblyConstituency) {
		this.assemblyConstituency = assemblyConstituency;
	}
}
