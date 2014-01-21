package com.next.esw.persistance;

import java.util.Date;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;

@NodeEntity
public class ParliamentConstituency {

	@GraphId
	private Long id;
	private Date dateCreated;
	private Date dateModified;
	private Long creatorId;
	private Long modifierId;
	
	private String Name;
	private String description;
	private District district;
	

	
}
