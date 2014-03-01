package com.eswaraj.domain.nodes;

import java.util.Set;

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
	private Location location;
	private Location village;
	private Location ward;
	private Location city;
	private Location district;
	private Location state;
	private Location country;
	
	@RelatedTo(type="BELONGS_TO")
	private Category category;
	@RelatedTo(type="LODGED_BY")
	private Person person;
	@RelatedTo(type="SERVED_BY")
	private ExecutiveAdministrator administrator;
	@RelatedTo(type="IS_IN")
	@Fetch
	private Status status;
	@RelatedTo(type="ENDORSED_BY", elementClass=Person.class)
	private Set<Person> endorsements;
	@RelatedTo(type="SERVED_BY", elementClass=Administrator.class)
	private Set<PoliticalAdministrator> servants;
	private Set<Photo> photos;
	private Set<Video> videos;
	
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
	public GeoPoint getGeoPoint() {
		return geoPoint;
	}
	public void setGeoPoint(GeoPoint geoPoint) {
		this.geoPoint = geoPoint;
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
	public ExecutiveAdministrator getAdministrator() {
		return administrator;
	}
	public void setAdministrator(ExecutiveAdministrator administrator) {
		this.administrator = administrator;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Set<Person> getEndorsements() {
		return endorsements;
	}
	public void setEndorsements(Set<Person> endorsements) {
		this.endorsements = endorsements;
	}
	public Set<PoliticalAdministrator> getServants() {
		return servants;
	}
	public void setServants(Set<PoliticalAdministrator> servants) {
		this.servants = servants;
	}
	public Set<Photo> getPhotos() {
		return photos;
	}
	public void setPhotos(Set<Photo> photos) {
		this.photos = photos;
	}
	public Set<Video> getVideos() {
		return videos;
	}
	public void setVideos(Set<Video> videos) {
		this.videos = videos;
	}
}
