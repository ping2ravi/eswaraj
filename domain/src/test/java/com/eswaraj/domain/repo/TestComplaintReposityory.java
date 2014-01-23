package com.eswaraj.domain.repo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.eswaraj.domain.nodes.Complaint;
import com.eswaraj.domain.validator.exception.ValidationException;


/**
 * Test for complaint repo
 * @author anuj
 * @data Jan 22, 2014
 */

@ContextConfiguration(locations = { "classpath:eswaraj-domain-test.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TestComplaintReposityory {

	@Autowired ComplaintRepository complaintRepository;
	
	@Test
	public void shouldSaveComplaint() {
		Complaint complaint = new Complaint();
		complaint.setTitle("test complaint");
		complaint = complaintRepository.save(complaint);
		
		Complaint expectedComplaint = complaintRepository.getComplaintById(complaint.getId());
		
		assertEquals(expectedComplaint.getTitle(), complaint.getTitle());
	}
	
	@Test(expected=ValidationException.class)
	public void shouldCheckEmpty_Title() {
		Complaint complaint = new Complaint();
		complaint = complaintRepository.save(complaint);
	}
}
