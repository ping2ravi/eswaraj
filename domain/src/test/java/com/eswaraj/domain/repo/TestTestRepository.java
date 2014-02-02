package com.eswaraj.domain.repo;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.eswaraj.domain.nodes.different.Code;
import com.eswaraj.domain.nodes.different.CodeRepository;
import com.eswaraj.domain.nodes.different.TestRepository;

/**
 * Test for Person repository
 * @author anuj
 * @data Jan 22, 2014
 */

@ContextConfiguration(locations = { "classpath:eswaraj-domain-test.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TestTestRepository {

	@Autowired TestRepository testRepository;
	@Autowired CodeRepository codeRepository;
	
	@Test
	public void shouldFetch_AllExecutiveBodies() {
		com.eswaraj.domain.nodes.different.Test test = new com.eswaraj.domain.nodes.different.Test();
		
		Code code = new Code();
		code = codeRepository.save(code);
		
		Code code1 = new Code();
		code1 = codeRepository.save(code1);
		
		
		test = testRepository.save(test);
	}
	
}
