package com.eswaraj.web.dto;


public class LocationDto extends BaseDto {

	private static final long serialVersionUID = 1L;
	private String name;
	private LocationType locationType;
	private Long parentLocationId;
	private Double lattitude;
	private Double longitude;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocationType getLocationType() {
		return locationType;
	}
	public void setLocationType(LocationType locationType) {
		this.locationType = locationType;
	}
	public Long getParentLocationId() {
		return parentLocationId;
	}
	public void setParentLocationId(Long parentLocationId) {
		this.parentLocationId = parentLocationId;
	}
	public Double getLattitude() {
		return lattitude;
	}
	public void setLattitude(Double lattitude) {
		this.lattitude = lattitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	@Override
	public String toString() {
		return "LocationDto [name=" + name + ", locationType=" + locationType + ", parentLocationId=" + parentLocationId + ", lattitude=" + lattitude
				+ ", longitude=" + longitude + ", getId()=" + getId() + "]";
	}

	
}
