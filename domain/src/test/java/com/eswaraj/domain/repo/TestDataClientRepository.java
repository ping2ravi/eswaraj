package com.eswaraj.domain.repo;


import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.eswaraj.domain.nodes.DataClient;
import com.eswaraj.domain.validator.exception.ValidationException;

/**
 * Test for LocationType repository
 * @author ravi
 * @data Apr 20, 2014
 */

@ContextConfiguration(locations = { "classpath:eswaraj-domain-test.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TestDataClientRepository extends BaseNeo4jEswarajTest {
	@Autowired DataClientRepository dataClientRepository;
	/**
	 * A simple test to create a Data Client and get it back by Id
	 */
	@Test
	public void test01_LocationTypeRepository(){
		final String dataClientName = randomAlphaString(16);
		
		DataClient dataClient = createDataClient(dataClientRepository, dataClientName);
		DataClient dbDataClient = dataClientRepository.findOne(dataClient.getId());
		
		assertNotNull(dbDataClient);
		assertDataClientEquals(dataClient, dbDataClient, true);
	}
	
	/**
	 * A simple test to create a Data Client and get it back by name
	 */
	@Test
	public void test02_LocationTypeRepository(){
		final String dataClientName = randomAlphaString(16);
		
		DataClient dataClient = createDataClient(dataClientRepository, dataClientName);
		DataClient dbDataClient = dataClientRepository.getDataClientByName(dataClientName);
		
		assertNotNull(dbDataClient);
		assertDataClientEquals(dataClient, dbDataClient, true);
		
	}
	
	/**
	 * A simple test to create a Data Client where Name is null
	 */
	@Test(expected=ValidationException.class)
	public void test03_LocationTypeRepository(){
		
		final String dataClientName = null;
		createDataClient(dataClientRepository, dataClientName);
	}
	
}
