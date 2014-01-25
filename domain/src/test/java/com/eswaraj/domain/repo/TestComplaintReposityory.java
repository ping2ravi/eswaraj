package com.eswaraj.domain.repo;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.eswaraj.domain.nodes.Complaint;
import com.eswaraj.domain.nodes.Location;
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
public class TestComplaintReposityory {

	@Autowired ComplaintRepository complaintRepository;
	@Autowired LocationRepository locationRepository;
	
	@Test
	public void shouldSaveComplaint() {
		Complaint complaint = new Complaint("test complaint");
		complaint = complaintRepository.save(complaint);
		Complaint expectedComplaint = complaintRepository.getById(complaint.getId());
		assertEquals(expectedComplaint.getTitle(), complaint.getTitle());
	}
	
	@Test
	public void shouldGetComplaint_ByLocation() {
		Complaint complaint = new Complaint("test complaint");
		complaint.setLocation(new Location("Loc1"));
		complaint = complaintRepository.save(complaint);
		Location location = locationRepository.getById(complaint.getLocation().getId());
		Complaint expectedComplaint = complaintRepository.getByLocation(location);
		assertEquals(expectedComplaint.getLocation().getName(), complaint.getLocation().getName());
	}
	
	@Test(expected=ValidationException.class)
	public void shouldCheckEmpty_Title() {
		Complaint complaint = new Complaint(null);
		complaint = complaintRepository.save(complaint);
	}
	
	@Test
	public void shouldLodgeComplaint_AsPending() {
		Complaint complaint = new Complaint("Test Complaint");
		complaint = complaintRepository.save(complaint);
		Complaint expectedComplaint = complaintRepository.getById(complaint.getId());
		assertEquals(expectedComplaint.getStatus().getMode(), Mode.PENDING);
	}
}
