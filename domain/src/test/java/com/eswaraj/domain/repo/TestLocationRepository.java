package com.eswaraj.domain.repo;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.eswaraj.domain.nodes.DataClient;
import com.eswaraj.domain.nodes.Location;
import com.eswaraj.domain.nodes.LocationType;
import com.eswaraj.domain.validator.exception.ValidationException;

/**
 * Test for Location repository
 * @author ravi
 * @data Apr 20, 2014
 */

@ContextConfiguration(locations = { "classpath:eswaraj-domain-test.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TestLocationRepository extends BaseNeo4jEswarajTest {

	@Autowired LocationRepository locationRepository;
	@Autowired LocationTypeRepository locationTypeRepository;
	@Autowired DataClientRepository dataClientRepository;
	/**
	 * A simple test to create a Country/Location and get it back by Id
	 */
	@Test
	public void test01_LocationRepository(){
		DataClient dataClient = createDataClient(dataClientRepository, randomAlphaString(16));
		LocationType countryLocationType = createLocationType(locationTypeRepository, "Country", null, dataClient);
		final String countryName = randomAlphaString(10);
		
		Location countryLocation = createLocation(locationRepository, countryName, countryLocationType, null);
		Location dbCountryLocation = locationRepository.findOne(countryLocation.getId());
		
		assertNotNull(dbCountryLocation);
		assertEquals(countryLocation, dbCountryLocation);
		
	}
	
	/**
	 * A simple test to create a Country/Location and get it back by name
	 */
	@Test
	public void test02_LocationRepository(){
		DataClient dataClient = createDataClient(dataClientRepository, randomAlphaString(16));
		LocationType countryLocationType = createLocationType(locationTypeRepository, "Country", null, dataClient);
		final String countryName = randomAlphaString(10);
		
		
		Location countryLocation = createLocation(locationRepository, countryName, countryLocationType, null);
		Location dbCountryLocation = locationRepository.getLocationFromName(countryName);
		
		assertNotNull(dbCountryLocation);
		assertEquals(countryLocation, dbCountryLocation);
		
	}
	
	/**
	 * A simple test to create a Country/Location where Name is null
	 */
	@Test(expected=ValidationException.class)
	public void test03_LocationRepository(){
		DataClient dataClient = createDataClient(dataClientRepository, randomAlphaString(16));
		LocationType countryLocationType = createLocationType(locationTypeRepository, "Country", null, dataClient);
		final String countryName = null;
		createLocation(locationRepository, countryName, countryLocationType, null);
	}
	
	/**
	 * A simple test to create a Country/Root Location where Data Client is null
	 */
	@Test(expected=ValidationException.class)
	public void test04_LocationRepository(){
		DataClient dataClient = null;
		LocationType countryLocationType = createLocationType(locationTypeRepository, "Country", null, dataClient);
		final String countryName = null;
		createLocation(locationRepository, countryName, countryLocationType, null);
	}
	
	
	/**
	 * A simple test to create a Country/Location where name is Not null but Location Type is null
	 */
	@Test(expected=ValidationException.class)
	public void test05_LocationRepository(){
		LocationType countryLocationType = null;
		final String countryName = randomAlphaString(16);
		createLocation(locationRepository, countryName, countryLocationType, null);
	}
	
	/**
	 * A simple test to create a Root Location and get it back by getRootLocationByLocationType
	 */
	@Test
	public void test06_getRootLocationByLocationType(){
		DataClient dataClient = createDataClient(dataClientRepository, randomAlphaString(16));
		LocationType countryLocationType = createLocationType(locationTypeRepository, "Country", null, dataClient);
		final String countryName = randomAlphaString(10);
		
		Location countryLocation = createLocation(locationRepository, countryName, countryLocationType, null);
		Location dbCountryLocation = locationRepository.getRootLocationByLocationType(countryLocationType.getId());
		
		assertNotNull(dbCountryLocation);
		assertEquals(countryLocation, dbCountryLocation);
		
	}
	
	
}
