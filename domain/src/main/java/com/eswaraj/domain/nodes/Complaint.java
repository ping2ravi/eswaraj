package com.eswaraj.domain.nodes;

import java.util.Collection;

import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.data.neo4j.annotation.RelatedToVia;

import com.eswaraj.domain.base.BaseNode;
import com.eswaraj.domain.nodes.Status.Mode;
import com.eswaraj.domain.nodes.division.GeoPoint;

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
	@Indexed
	@RelatedTo(type="IS_AT")
	private GeoPoint geoPoint;
	@RelatedTo(type="BELONGS_TO")
	private Category category;
	@RelatedToVia(type="LODGED_BY")
	private Person person;
	@RelatedToVia(type="SERVED_BY")
	private Administrator administrator;
	@RelatedTo(type="IS_IN")
	@Fetch
	private Status status;
	@RelatedTo(type="ENDORSED_BY", elementClass=Person.class)
	private Collection<Person> endorsements;
	@RelatedTo(type="SERVED_BY", elementClass=Administrator.class)
	private Collection<Administrator> administrators;
	private Collection<Photo> photos;
	private Collection<Video> videos;
	
	public Complaint(){}
	public Complaint(String title) {
		this.title = title;
		this.status = new Status(Mode.PENDING);
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public Administrator getAdministrator() {
		return administrator;
	}
	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}
	public Collection<Person> getEndorsements() {
		return endorsements;
	}
	public void setEndorsements(Collection<Person> endorsements) {
		this.endorsements = endorsements;
	}
	public Collection<Administrator> getAdministrators() {
		return administrators;
	}
	public void setAdministrators(Collection<Administrator> administrators) {
		this.administrators = administrators;
	}
	public Collection<Photo> getPhotos() {
		return photos;
	}
	public void setPhotos(Collection<Photo> photos) {
		this.photos = photos;
	}
	public Collection<Video> getVideos() {
		return videos;
	}
	public void setVideos(Collection<Video> videos) {
		this.videos = videos;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
}
