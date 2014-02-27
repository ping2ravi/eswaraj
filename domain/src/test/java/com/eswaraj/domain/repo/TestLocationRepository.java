package com.eswaraj.domain.repo;


import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.eswaraj.domain.nodes.ExecutiveBody;
import com.eswaraj.domain.nodes.ExecutiveBodyLocation;
import com.eswaraj.domain.nodes.ExecutiveBodyRepository;
import com.eswaraj.domain.nodes.DepartmentType;
import com.eswaraj.domain.nodes.Location;
import com.eswaraj.domain.nodes.LocationRepository;

/**
 * Test for Person repository
 * @author anuj
 * @data Jan 22, 2014
 */

@ContextConfiguration(locations = { "classpath:eswaraj-domain-test.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TestLocationRepository {

	@Autowired LocationRepository locationRepository;
	@Autowired ExecutiveBodyRepository executiveBodyRepository;
	
	@Test
	@Ignore
	public void shouldFetch_AllExecutiveBodies() {
		Location location = new Location();
		location.setName("Loc1");
	
		ExecutiveBody executiveBody1 = new ExecutiveBody();
		executiveBody1.setName("EX1");
		executiveBody1 = executiveBodyRepository.save(executiveBody1);
		location.servedBy(executiveBody1, DepartmentType.ELECTRICITY);
		
		ExecutiveBody executiveBody2 = new ExecutiveBody();
		executiveBody2.setName("EX2");
		executiveBody2 = executiveBodyRepository.save(executiveBody2);
		location.servedBy(executiveBody2, DepartmentType.WATER);
		
		ExecutiveBody executiveBody3 = new ExecutiveBody();
		executiveBody3.setName("EX3");
		executiveBody3 = executiveBodyRepository.save(executiveBody3);
		location.servedBy(executiveBody3, DepartmentType.FIRE);
		
		location = locationRepository.save(location);
		
		Set<ExecutiveBody> bodies = locationRepository.findExecutiveBodies(location);
		assertEquals(bodies.size(), 3);
	}
	
	@Test
	public void shouldFetch_AllRelationships() {
		Location location = locationRepository.findByPropertyValue("name", "Loc1");
		location = locationRepository.getById(new Long(69));
		Set<ExecutiveBodyLocation> locations = location.getExecutiveBodyLocations();
		for (ExecutiveBodyLocation executiveBodyLocation : locations) {
			System.out.println(executiveBodyLocation.getType());
		}
		assertEquals(location.getExecutiveBodyLocations().size(), 3);
	}
}
