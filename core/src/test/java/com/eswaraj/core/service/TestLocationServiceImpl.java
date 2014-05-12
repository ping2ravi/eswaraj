package com.eswaraj.core.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.eswaraj.core.BaseNeo4jEswarajTest;
import com.eswaraj.core.exceptions.ApplicationException;
import com.eswaraj.web.dto.LocationDto;
import com.eswaraj.web.dto.LocationType;

/**
 * Test for Location repository
 * @author ravi
 * @data Apr 20, 2014
 */

@ContextConfiguration(locations = { "classpath:eswaraj-core-test.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TestLocationServiceImpl extends BaseNeo4jEswarajTest{

	@Autowired private LocationService locationService;

	/**
	 * Simple Test to save Location 
	 * No parent Location id provided
	 * @throws ApplicationException
	 */
	@Test
	public void test01_save() throws ApplicationException{
		LocationDto location = createLocation("India", LocationType.COUNTRY, null);
		LocationDto savedLocation = locationService.saveLocation(location);
		assertNotNull(savedLocation.getId());
		assertEqualLocations(location, savedLocation);
	}
	
	/**
	 * Simple Test to save Location with parent
	 * Parent Location id provided
	 * @throws ApplicationException
	 */
	@Test
	public void test02_save() throws ApplicationException{
		LocationDto location = createLocation("India", LocationType.COUNTRY, null);
		LocationDto parentLocation = locationService.saveLocation(location);
		LocationDto haryanaLocation = createLocation("India", LocationType.STATE, parentLocation.getId());
		LocationDto savedLaryanaLocation = locationService.saveLocation(haryanaLocation);
		assertNotNull(savedLaryanaLocation.getId());
		assertNotNull(savedLaryanaLocation.getParentLocationId());
		assertEqualLocations(haryanaLocation, savedLaryanaLocation);
	}
	
	/**
	 * Simple Test to save Location and then retrieve it back
	 * @throws ApplicationException
	 */
	@Test
	public void test03_getLocationById() throws ApplicationException{
		LocationDto location = createLocation("India", LocationType.COUNTRY, null);
		LocationDto savedLocation = locationService.saveLocation(location);
		LocationDto dbLocation = locationService.getLocationById(savedLocation.getId()); 
		assertNotNull(dbLocation.getId());
		assertEqualLocations(location, dbLocation);
	}
	
	/**
	 * Simple Test to save Location and its child and then retrieve children by parent
	 * @throws ApplicationException
	 */
	@Test
	public void test04_getChildLocationsOfParent() throws ApplicationException{
		LocationDto location = createLocation("India", LocationType.COUNTRY, null);
		LocationDto parentLocation = locationService.saveLocation(location);
		LocationDto haryanaLocation = createLocation("Haryana", LocationType.STATE, parentLocation.getId());
		LocationDto savedLaryanaLocation = locationService.saveLocation(haryanaLocation);
		List<LocationDto> childrenLocations = locationService.getChildLocationsOfParent(parentLocation.getId());
		
		assertNotNull(childrenLocations);
		assertEquals(1, childrenLocations.size());
		assertEqualLocations(haryanaLocation, childrenLocations.get(0));
		assertEqualLocations(savedLaryanaLocation, childrenLocations.get(0));
	}
}
