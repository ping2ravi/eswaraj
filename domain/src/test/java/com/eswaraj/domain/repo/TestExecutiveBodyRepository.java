package com.eswaraj.domain.repo;


import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.eswaraj.domain.nodes.different.ExecutiveAdministrator;
import com.eswaraj.domain.nodes.different.ExecutiveBody;
import com.eswaraj.domain.nodes.different.ExecutiveBodyRepository;
import com.eswaraj.domain.nodes.different.ExecutiveBodyType;
import com.eswaraj.domain.nodes.different.Location;
import com.eswaraj.domain.nodes.different.LocationRepository;

/**
 * Test for Person repository
 * @author anuj
 * @data Jan 22, 2014
 */

@ContextConfiguration(locations = { "classpath:eswaraj-domain-test.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TestExecutiveBodyRepository {

	@Autowired ExecutiveBodyRepository executiveBodyRepository;
	@Autowired ExecutiveAdministratorRepository executiveAdministratorRepository;
	
	@Test
	public void shouldFetch_AllExecutiveBodies() {
		
		ExecutiveBody executiveBody = new ExecutiveBody();
		executiveBody.setName("EX111");
		
		ExecutiveAdministrator adm1 = new ExecutiveAdministrator();
		
		Location location = new Location();
		location.setName("Loc1");
	
		ExecutiveBody executiveBody1 = new ExecutiveBody();
		executiveBody1.setName("EX1");
		executiveBody1 = executiveBodyRepository.save(executiveBody1);
		location.servedBy(executiveBody1, ExecutiveBodyType.ELECTRICITY);
		
		ExecutiveBody executiveBody2 = new ExecutiveBody();
		executiveBody2.setName("EX2");
		executiveBody2 = executiveBodyRepository.save(executiveBody2);
		location.servedBy(executiveBody2, ExecutiveBodyType.WATER);
		
		ExecutiveBody executiveBody3 = new ExecutiveBody();
		executiveBody3.setName("EX3");
		executiveBody3 = executiveBodyRepository.save(executiveBody3);
		location.servedBy(executiveBody3, ExecutiveBodyType.FIRE);
		
		location = locationRepository.save(location);
		
		Set<ExecutiveBody> bodies = locationRepository.findExecutiveBodies(location);
		assertEquals(bodies.size(), 3);
	}
}
