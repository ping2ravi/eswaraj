package com.eswaraj.domain.repo;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.eswaraj.domain.nodes.Category;
import com.eswaraj.domain.nodes.Complaint;
import com.eswaraj.domain.nodes.Person;
import com.eswaraj.domain.nodes.Status.Mode;
import com.eswaraj.domain.validator.exception.ValidationException;


/**
 * Test for complaint repo
 * @author anuj
 * @data Jan 22, 2014
 */

@ContextConfiguration(locations = { "classpath:eswaraj-domain-test.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@Ignore
public class TestComplaintReposityory {

	@Autowired ComplaintRepository complaintRepository;
	@Autowired LocationRepository locationRepository;
	@Autowired CategoryRepository categoryRepository;
	@Autowired DepartmentRepository departmentRepository;
	@Autowired PersonRepository personRepository;
	
	@Test
	public void shouldSaveComplaint() {
		Complaint complaint = new Complaint("test complaint");
		Category category = new Category("cat1");
		complaint.setCategory(category);
		
		Person person = new Person();
		person.setName("Foo Bar");
		person.setEmail("foo@bar.com");
		person = personRepository.save(person);
		complaint.setPerson(person);
		
		complaint = complaintRepository.save(complaint);
		Complaint expectedComplaint = complaintRepository.getById(complaint.getId());
		assertEquals(expectedComplaint.getTitle(), complaint.getTitle());
	}
	
	@Test
	public void shouldGetComplaint_ByLocation() {
		Complaint complaint = new Complaint("test complaint");
		Category category = new Category("cat1");
		complaint.setCategory(category);
		
		Person person = new Person();
		person.setName("Foo Bar");
		person.setEmail("foo@bar.com");
		person = personRepository.save(person);
		complaint.setPerson(person);
		
		complaint = complaintRepository.save(complaint);
	}
	
	@Test
	public void shouldGetComplaint_ByCategory() {
		Complaint complaint = new Complaint("test complaint");
		Category category = new Category("cat1");
		category = categoryRepository.save(category);
		complaint.setCategory(category);
		
		Person person = new Person();
		person.setName("Foo Bar");
		person.setEmail("foo@bar.com");
		person = personRepository.save(person);
		complaint.setPerson(person);

		complaint = complaintRepository.save(complaint);
		
		Category category1 = categoryRepository.getById(complaint.getCategory().getId());
		Complaint expectedComplaint = complaintRepository.getByCategory(category1);
		assertEquals(expectedComplaint.getCategory().getName(), complaint.getCategory().getName());
		assertEquals(expectedComplaint.getCategory().getId(), complaint.getCategory().getId());
	}
	
	@Test(expected=ValidationException.class)
	public void shouldCheckEmpty_Title() {
		Complaint complaint = new Complaint(null);
		complaint = complaintRepository.save(complaint);
	}
	
	@Test(expected=ValidationException.class)
	public void shouldCheck_NoCatergory() {
		Complaint complaint = new Complaint("test complaint");
		complaint = complaintRepository.save(complaint);
	}
	
	@Test(expected=ValidationException.class)
	public void shouldCheck_NoLocation() {
		Complaint complaint = new Complaint("test complaint");
		Category category = new Category("cat1");
		complaint.setCategory(category);
		complaint = complaintRepository.save(complaint);
	}
	
	@Test(expected=ValidationException.class)
	public void shouldCheck_NoPerson() {
		Complaint complaint = new Complaint("test complaint");
		Category category = new Category("cat1");
		complaint.setCategory(category);
		complaint = complaintRepository.save(complaint);
	}
	
	@Test
	public void shouldLodgeComplaint_AsPending() {
		Complaint complaint = new Complaint("Test Complaint");
		Category category = new Category("cat1");
		complaint.setCategory(category);
		
		Person person = new Person();
		person.setName("Foo Bar");
		person.setEmail("foo@bar.com");
		person = personRepository.save(person);
		complaint.setPerson(person);

		complaint = complaintRepository.save(complaint);
		Complaint expectedComplaint = complaintRepository.getById(complaint.getId());
		assertEquals(expectedComplaint.getStatus().getMode(), Mode.PENDING);
	}
}
