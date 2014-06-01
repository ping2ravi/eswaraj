package com.eswaraj.domain.repo;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.eswaraj.domain.nodes.DataClient;
import com.eswaraj.domain.nodes.LocationType;
import com.eswaraj.domain.validator.exception.ValidationException;

/**
 * Test for LocationType repository
 * @author ravi
 * @data Apr 20, 2014
 */

@ContextConfiguration(locations = { "classpath:eswaraj-domain-test.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TestLocationTypeRepository extends BaseNeo4jEswarajTest {
	@Autowired LocationTypeRepository locationTypeRepository;
	@Autowired DataClientRepository dataClientRepository;
	/**
	 * A simple test to create a Country sLocationType and get it back by Id
	 */
	@Test
	public void test01_LocationTypeRepository(){
		final String countryName = "Country";
		System.out.println("dataClientRepository="+dataClientRepository);
		System.out.println("locationTypeRepository="+locationTypeRepository);
		DataClient dataClient = createDataClient(dataClientRepository, randomAlphaString(16));
		
		LocationType countryLocationType = createLocationType(locationTypeRepository, countryName, null, dataClient);
		System.out.println("countryLocationType="+countryLocationType);
		LocationType dbCountryLocationType = locationTypeRepository.findOne(countryLocationType.getId());
		
		assertNotNull(dbCountryLocationType);
		assertLocationTypeEquals(countryLocationType, dbCountryLocationType, true);
	}
	
	/**
	 * A simple test to create a Country/LocationType and get it back by name
	 */
	@Test
	public void test02_LocationTypeRepository(){
		final String countryName = "Country";
		DataClient dataClient = createDataClient(dataClientRepository, randomAlphaString(16));
		LocationType countryLocationType = createLocationType(locationTypeRepository, countryName, null, dataClient);
		LocationType dbCountryLocationType = locationTypeRepository.getLocationTypeByName(countryName);
		
		assertNotNull(dbCountryLocationType);
		assertLocationTypeEquals(countryLocationType, dbCountryLocationType, true);
		
	}
	
	/**
	 * A simple test to create a Country/LocationType where Name is null
	 */
	@Test(expected=ValidationException.class)
	public void test03_LocationTypeRepository(){
		DataClient dataClient = createDataClient(dataClientRepository, randomAlphaString(16));
		final String countryName = null;
		createLocationType(locationTypeRepository, countryName, null, dataClient);
	}
	

	
	/**
	 * when creating a country(parent) and State(child)
	 * then get all states of the country
	 * return all states
	 */
	@Test
	public void test04_LocationTypeRepository(){
		final String childLocationName = randomAlphaString(16);
		final String parentLocationName = randomAlphaString(16);
		DataClient parentDataClient = createDataClient(dataClientRepository, randomAlphaString(16));
		DataClient childDataClient = createDataClient(dataClientRepository, randomAlphaString(16));
		
		LocationType parentLocationType = createLocationType(locationTypeRepository, parentLocationName, null, parentDataClient);
		LocationType childLocationType = createLocationType(locationTypeRepository, childLocationName, parentLocationType, childDataClient);

		Collection<LocationType> allChildrenLocationType = locationTypeRepository.findLocationTypeByParentLocation(parentLocationType);
		assertEquals(1, allChildrenLocationType.size());
		LocationType dbChildLocationType = allChildrenLocationType.iterator().next();
		assertLocationTypeEquals(childLocationType, dbChildLocationType, true);
	}
	
	/**
	 * A simple test to create a Country sLocationType and get it back by getRootLocationTypeByDataClient
	 */
	@Test
	public void test05_getRootLocationTypeByDataClient(){
		final String countryName = "Country";
		DataClient dataClient = createDataClient(dataClientRepository, randomAlphaString(16));
		LocationType countryLocationType = createLocationType(locationTypeRepository, countryName, null, dataClient);
		LocationType dbCountryLocationType = locationTypeRepository.getRootLocationTypeByDataClient(dataClient.getName());
		
		assertNotNull(dbCountryLocationType);
		assertLocationTypeEquals(countryLocationType, dbCountryLocationType, true);
	}
	/**
	 * A simple test to create a Country sLocationType and get it back by getLocationTypeByNameAndDataClientType
	 */
	@Test
	public void test06_getLocationTypeByNameAndDataClientType(){
		final String countryName = "Country";
		DataClient dataClient = createDataClient(dataClientRepository, randomAlphaString(randomInteger(32)));
		LocationType countryLocationType = createLocationType(locationTypeRepository, countryName, null, dataClient);
		LocationType dbCountryLocationType = locationTypeRepository.getLocationTypeByNameAndDataClientType("Country", dataClient);
		
		assertNotNull(dbCountryLocationType);
		assertLocationTypeEquals(countryLocationType, dbCountryLocationType, true);
	}
	
}
