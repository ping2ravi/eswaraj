package com.eswaraj.domain.nodes;

import org.springframework.data.neo4j.annotation.NodeEntity;

@NodeEntity
public class Address {

	private String line1;
	private String line2;
	private String line3;
	private String postalCode;
	
	private Location village;
	private Location ward;
	private Location city;
	private Location district;
	private Location state;
	private Location country;
}
