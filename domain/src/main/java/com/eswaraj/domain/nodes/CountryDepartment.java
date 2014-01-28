package com.eswaraj.domain.nodes;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.eswaraj.domain.base.BaseNode;
import com.eswaraj.domain.nodes.division.Country;

@NodeEntity
public class CountryDepartment extends BaseNode{

	
	private String name;
	private Country country;
	private Department department;
	
}
