package com.eswaraj.domain.nodes;

import com.eswaraj.domain.base.BaseNode;

/**
 * Data about a location
 * @author anuj
 * @date Jan 28, 2014
 *
 */
public class LocationData extends BaseNode {

	private Location village;
	private Location ward;
	private Location city;
	private Location district;
	private Location state;
	private Location country;
	
	public Location getVillage() {
		return village;
	}
	public void setVillage(Location village) {
		this.village = village;
	}
	public Location getWard() {
		return ward;
	}
	public void setWard(Location ward) {
		this.ward = ward;
	}
	public Location getCity() {
		return city;
	}
	public void setCity(Location city) {
		this.city = city;
	}
	public Location getDistrict() {
		return district;
	}
	public void setDistrict(Location district) {
		this.district = district;
	}
	public Location getState() {
		return state;
	}
	public void setState(Location state) {
		this.state = state;
	}
	public Location getCountry() {
		return country;
	}
	public void setCountry(Location country) {
		this.country = country;
	}
}
