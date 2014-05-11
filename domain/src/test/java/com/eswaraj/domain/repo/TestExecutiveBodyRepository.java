package com.eswaraj.domain.repo;


import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.eswaraj.domain.nodes.ExecutiveAdministrator;
import com.eswaraj.domain.nodes.ExecutiveBody;
import com.eswaraj.domain.nodes.Post;

/**
 * Test for Person repository
 * @author anuj
 * @data Jan 22, 2014
 */

@ContextConfiguration(locations = { "classpath:eswaraj-domain-test.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@Ignore
public class TestExecutiveBodyRepository {

	@Autowired ExecutiveBodyRepository executiveBodyRepository;
	@Autowired ExecutiveAdministratorRepository executiveAdministratorRepository;
	
	@Test
	public void shouldFetch_AllExecutiveBodies() {
		
		ExecutiveBody executiveBody = new ExecutiveBody();
		executiveBody.setName("EX111");
		
		ExecutiveAdministrator adm1 = new ExecutiveAdministrator();
		adm1 = executiveAdministratorRepository.save(adm1);
		ExecutiveAdministrator adm2 = new ExecutiveAdministrator();
		adm2 = executiveAdministratorRepository.save(adm2);
		ExecutiveAdministrator adm3 = new ExecutiveAdministrator();
		adm3 = executiveAdministratorRepository.save(adm3);
		
		executiveBody.employs(adm1, new Post("SHO"));
		executiveBody.employs(adm2, new Post("DIG"));
		executiveBody.employs(adm3, new Post("CONSTABLE"));
		
		executiveBody = executiveBodyRepository.save(executiveBody);
		
		Set<ExecutiveAdministrator> admins = executiveBodyRepository.findAdministrators(executiveBody);
		
		assertEquals(3, admins.size());
	}
}
