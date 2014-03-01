package com.eswaraj.domain.repo;


import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.eswaraj.domain.nodes.Department;
import com.eswaraj.domain.nodes.DepartmentType;
import com.eswaraj.domain.nodes.ExecutiveBody;
import com.eswaraj.domain.nodes.Location;
import com.eswaraj.domain.nodes.relationships.ExecutiveBodyLocation;

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
	public void shouldFetch_AllExecutiveBodies() {
		Location location = new Location();
		location.setName("Loc1");
	
		ExecutiveBody executiveBody1 = new ExecutiveBody();
		executiveBody1.setName("EX1");
		executiveBody1 = executiveBodyRepository.save(executiveBody1);
		
		Department elec = new Department("Electricity Board",DepartmentType.ELECTRICITY);
		location.servedBy(executiveBody1, elec);
		
		ExecutiveBody executiveBody2 = new ExecutiveBody();
		executiveBody2.setName("EX2");
		executiveBody2 = executiveBodyRepository.save(executiveBody2);
		Department water = new Department("Water Board",DepartmentType.WATER);
		location.servedBy(executiveBody2, water);
		
		ExecutiveBody executiveBody3 = new ExecutiveBody();
		executiveBody3.setName("EX3");
		executiveBody3 = executiveBodyRepository.save(executiveBody3);
		
		Department fire = new Department("Fire Board",DepartmentType.WATER);
		location.servedBy(executiveBody2, fire);
		
		location.servedBy(executiveBody3, fire);
		
		location = locationRepository.save(location);
		
		Set<ExecutiveBody> bodies = locationRepository.findExecutiveBodies(location);
		assertEquals(bodies.size(), 3);
	}
	
	@Test
	public void shouldFetch_AllRelationships() {
		Location location = locationRepository.findByPropertyValue("name", "Loc1");
		Set<ExecutiveBodyLocation> locations = location.getExecutiveBodyLocations();
		assertEquals(locations.size(), 3);
	}
}
