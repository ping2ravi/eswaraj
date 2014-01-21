package com.next.esw.persistance;

import java.util.Date;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

@NodeEntity
public class LocationPoint {

	@GraphId
	private Long id;
	private Date dateCreated;
	private Date dateModified;
	private Long creatorId;
	private Long modifierId;
	@Indexed
	private Double longitude; 
	@Indexed
	private Double lattitude; 
	
	
}
