package com.eswaraj.domain.nodes;

import java.util.Collection;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.eswaraj.domain.base.BaseNode;

/**
 * Complaint made by a person
 * @author anuj
 * @date Jan 18, 2014
 *
 */
@NodeEntity
public class Complaint extends BaseNode {

	private String title;
	private String description;
	private Location location;
	private Category category;
	private Person person;
	private Administrator administrator;
	private Collection<Person> endorsements;
	private Collection<Administrator> administrators;
	private Collection<Photo> photos;
	private Collection<Video> videos;
	
}
