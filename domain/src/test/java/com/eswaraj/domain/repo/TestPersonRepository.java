package com.eswaraj.domain.repo;


import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.eswaraj.domain.nodes.Person;
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
		person = personRespository.save(person);
		Person expectedPerson = personRespository.getPersonById(person.getId());
		assertEquals(expectedPerson.getName(), person.getName());
		assertEquals(expectedPerson.getGender(), person.getGender());
		assertEquals(expectedPerson.getEmail(), person.getEmail());
	}
}
