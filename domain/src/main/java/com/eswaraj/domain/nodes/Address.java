package com.eswaraj.domain.nodes;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.eswaraj.domain.base.BaseNode;

@NodeEntity
public class Address extends BaseNode {

	private String line1;
	private String line2;
	private String line3;
	private String postalCode;
	
	private Location village;
	private Location ward;
	private Location city;
	private Location district;
	private Location state;
	private Location country;
	
	public String getLine1() {
		return line1;
	}
	public void setLine1(String line1) {
		this.line1 = line1;
	}
	public String getLine2() {
		return line2;
	}
	public void setLine2(String line2) {
		this.line2 = line2;
	}
	public String getLine3() {
		return line3;
	}
	public void setLine3(String line3) {
		this.line3 = line3;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
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
