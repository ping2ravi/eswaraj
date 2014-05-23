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
	@Override
	public int hashCode() {
		if(getId() != null){
			return super.hashCode();
		}
		final int prime = 31;
		int result = 1;
		result = prime * result + ((lattitude == null) ? 0 : lattitude.hashCode());
		result = prime * result + ((locationType == null) ? 0 : locationType.hashCode());
		result = prime * result + ((longitude == null) ? 0 : longitude.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((parentLocationId == null) ? 0 : parentLocationId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LocationDto other = (LocationDto) obj;
		if(getId() != null && other.getId() != null){
			return super.equals(other);
		}
		if (lattitude == null) {
			if (other.lattitude != null)
				return false;
		} else if (!lattitude.equals(other.lattitude))
			return false;
		if (locationType != other.locationType)
			return false;
		if (longitude == null) {
			if (other.longitude != null)
				return false;
		} else if (!longitude.equals(other.longitude))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (parentLocationId == null) {
			if (other.parentLocationId != null)
				return false;
		} else if (!parentLocationId.equals(other.parentLocationId))
			return false;
		return true;
	}

	
}
