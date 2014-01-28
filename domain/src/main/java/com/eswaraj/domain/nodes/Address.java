package com.eswaraj.domain.nodes;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.eswaraj.domain.base.BaseLocationNode;

@NodeEntity
public class Address extends BaseLocationNode {

	private String line1;
	private String line2;
	private String line3;
	private String line4;
	private String postalCode;
		
	
	

	

}
