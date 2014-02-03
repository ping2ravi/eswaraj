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
		
	}
}
