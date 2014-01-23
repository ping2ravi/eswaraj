package com.eswaraj.domain.repo;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.eswaraj.domain.nodes.Category;
import com.eswaraj.domain.nodes.Complaint;
import com.eswaraj.domain.nodes.Department;
import com.eswaraj.domain.repo.ComplaintRepository;


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
		complaint = complaintRepository.save(complaint);
		
		Complaint expectedComplaint = complaintRepository.getComplaintById(complaint.getId());
		
		assertEquals(expectedComplaint, complaint);
	}
}
