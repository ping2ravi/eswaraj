package com.eswaraj.domain.repo;


import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.eswaraj.domain.nodes.Location;
import com.eswaraj.domain.nodes.Person;
import com.eswaraj.domain.validator.exception.ValidationException;
import com.google.gdata.data.contacts.Gender;
import com.google.gdata.data.contacts.Gender.Value;

/**
 * Test for Person repository
 * @author anuj
 * @data Jan 22, 2014
 */

@ContextConfiguration(locations = { "classpath:eswaraj-domain-test.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TestPersonRepository {

	@Autowired PersonRepository personRespository;
	
	@Test
	public void shouldSavePerson() {
		Person person = new Person();
		person.setName("Foo Bar");
		person.setEmail("foo@bar.com");
		person.setGender(new Gender(Value.MALE));
		person.setLocation(new Location());
		person = personRespository.save(person);
		Person expectedPerson = personRespository.getPersonById(person.getId());
		assertEquals(expectedPerson.getName(), person.getName());
		assertEquals(expectedPerson.getGender(), person.getGender());
		assertEquals(expectedPerson.getEmail(), person.getEmail());
	}

	@Test(expected=ValidationException.class)
	public void shouldCheckEmptyPersonName() {
		Person person = new Person();
		person = personRespository.save(person);
	}
	
	@Test(expected=ValidationException.class)
	public void shouldCheckNullLocation() {
		Person person = new Person();
		person.setName("foo bar");
		person = personRespository.save(person);
	}
	
	@Test(expected=ValidationException.class)
	public void shouldCheckEmptyEmail() {
		Person person = new Person();
		person.setName("foo bar");
		person.setLocation(new Location());
		person = personRespository.save(person);
	}
	
	@Test(expected=ValidationException.class)
	public void shouldCheckNameLength() {
		Person person = new Person();
		person.setEmail("foo@bar.com");
		person.setLocation(new Location());
		person.setName("f");
		person = personRespository.save(person);
	}
	
	@Test(expected=ValidationException.class)
	public void shouldCheckValidCharacters() {
		Person person = new Person();
		person.setEmail("foo@bar.com");
		person.setLocation(new Location());
		person.setName("Ff12 hhh h");
		person = personRespository.save(person);
	}
	
	
}
