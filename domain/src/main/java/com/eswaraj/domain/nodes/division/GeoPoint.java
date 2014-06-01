package com.eswaraj.domain.nodes.division;

import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;

/**
 * district level administrative division
 * @author anuj
 * @date Jan 18, 2014
 *
 */
@NodeEntity
public class GeoPoint {
	@GraphId
	protected Long id;
	private double lattitude;
	private double longitude;
	private int sequence;
	private Boundary boundary;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double getLattitude() {
		return lattitude;
	}
	public void setLattitude(double lattitude) {
		this.lattitude = lattitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	public Boundary getBoundary() {
		return boundary;
	}
	public void setBoundary(Boundary boundary) {
		this.boundary = boundary;
	}
}
