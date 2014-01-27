package com.eswaraj.domain.nodes;

import java.util.Collection;
import java.util.Date;

import javax.xml.registry.infomodel.TelephoneNumber;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedToVia;

import com.eswaraj.domain.base.BaseNode;
import com.google.gdata.data.contacts.Gender;

/**
 * Person making or resolving the complaint
 * @author anuj
 * @date Jan 18, 2014
 *
 */
@NodeEntity
public class Person extends BaseNode {

	private String name;
	private Date dob;
	private Gender gender;
	private Location location;
	private String email;
	private Collection<TelephoneNumber> numbers;
	@RelatedToVia(type="LODGED_BY", direction=Direction.INCOMING)
	private Iterable<Complaint> complaints;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Collection<TelephoneNumber> getNumbers() {
		return numbers;
	}
	public void setNumbers(Collection<TelephoneNumber> numbers) {
		this.numbers = numbers;
	}
	
	@Override
	public String toString() {
		return "Person [name=" + name + ", gender=" + gender + ", email=" + email + "]";
	}
}
