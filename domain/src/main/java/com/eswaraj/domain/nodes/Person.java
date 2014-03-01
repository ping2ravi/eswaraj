package com.eswaraj.domain.nodes;

import java.util.Collection;
import java.util.Date;

import javax.xml.registry.infomodel.TelephoneNumber;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.eswaraj.domain.base.BaseNode;
import com.google.gdata.data.contacts.Gender;

/**
 * Represents a person making or resolving the complaint
 * @author anuj
 * @date Jan 18, 2014
 *
 */
@NodeEntity
public class Person extends BaseNode {

	private String name;
	private Date dob;
	private Gender gender;
	private String email;
	//TODO impelemnt telephone number
	private Collection<TelephoneNumber> numbers;
	
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
