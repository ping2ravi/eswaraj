package com.eswaraj.web.dto;


/**
 * A point on map
 * @author ravi
 * @date May 29, 2014
 *
 */
public class GeoPointDto extends BaseDto{
	private double lattitude;
	private double longitude;
	private int sequence;
	protected Long boundaryId;
	
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
	public Long getBoundaryId() {
		return boundaryId;
	}
	public void setBoundaryId(Long boundaryId) {
		this.boundaryId = boundaryId;
	}
}
