package com.eswaraj.core.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.InputStream;
import java.util.List;

import org.jmock.Expectations;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.eswaraj.core.BaseNeo4jEswarajTest;
import com.eswaraj.core.exceptions.ApplicationException;
import com.eswaraj.core.service.FileService;
import com.eswaraj.core.service.LocationService;
import com.eswaraj.web.dto.LocationBoundaryFileDto;
import com.eswaraj.web.dto.LocationDto;
import com.eswaraj.web.dto.LocationTypeDto;
import com.eswaraj.web.dto.LocationTypeJsonDto;

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
	public void test01_saveLocation() throws ApplicationException{
		String locationTypeName = "Country";
		LocationTypeDto locationTypeDto = createLocationType(locationTypeName, null);
		locationTypeDto = locationService.saveLocationType(locationTypeDto);
		LocationDto location = createLocation("India", locationTypeDto, null);
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
		LocationTypeDto countryLocationTypeDto = createAndSaveLocationType(locationService, "Country", null);

		LocationTypeDto stateLocationTypeDto = createAndSaveLocationType(locationService, "State", countryLocationTypeDto.getId());

		
		LocationDto location = createLocation("India", countryLocationTypeDto, null);
		LocationDto parentLocation = locationService.saveLocation(location);
		LocationDto haryanaLocation = createLocation("Haryana", stateLocationTypeDto, parentLocation.getId());
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
		LocationTypeDto countryLocationTypeDto = createAndSaveLocationType(locationService, "Country", null);

		LocationDto location = createLocation("India", countryLocationTypeDto, null);
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
		LocationTypeDto countryLocationTypeDto = createAndSaveLocationType(locationService, "Country", null);
		LocationTypeDto stateLocationTypeDto = createAndSaveLocationType(locationService, "State", countryLocationTypeDto.getId());
		
		LocationDto location = createLocation("India", countryLocationTypeDto, null);
		LocationDto parentLocation = locationService.saveLocation(location);
		LocationDto haryanaLocation = createLocation("Haryana", stateLocationTypeDto, parentLocation.getId());
		LocationDto savedLaryanaLocation = locationService.saveLocation(haryanaLocation);
		List<LocationDto> childrenLocations = locationService.getChildLocationsOfParent(parentLocation.getId());
		
		assertNotNull(childrenLocations);
		assertEquals(1, childrenLocations.size());
		assertEqualLocations(haryanaLocation, childrenLocations.get(0));
		assertEqualLocations(savedLaryanaLocation, childrenLocations.get(0));
	}
	
	/**
	 * Simple Test to save Location and then update it
	 * @throws ApplicationException
	 */
	@Test
	public void test05_saveLocation() throws ApplicationException{
		LocationTypeDto countryLocationTypeDto = createAndSaveLocationType(locationService, "Country", null);
		LocationDto location = createLocation("India", countryLocationTypeDto, null);
		LocationDto savedLocation = locationService.saveLocation(location);
		savedLocation.setName("US");
		savedLocation = locationService.saveLocation(savedLocation);
		assertEquals("US", savedLocation.getName());
		
		LocationDto dbLocation = locationService.getLocationById(savedLocation.getId());
		assertEqualLocations(savedLocation, dbLocation);
		
		assertEquals("US", dbLocation.getName());
	}
	
	/**
	 * Simple Test to update Location which do not exists
	 * @throws ApplicationException
	 */
	@Test(expected=ApplicationException.class)
	public void test06_saveLocation() throws ApplicationException{
		LocationTypeDto countryLocationTypeDto = createAndSaveLocationType(locationService, "Country", null);
		LocationDto location = createLocation("India", countryLocationTypeDto, null);
		location.setId(100L);
		locationService.saveLocation(location);
	}
	
	/**
	 * Simple Test to save Location where given id is 0
	 * It will create a new location with new Id and will consider id as null only
	 * @throws ApplicationException
	 */
	@Test
	public void test07_saveLocation() throws ApplicationException{
		LocationTypeDto countryLocationTypeDto = createAndSaveLocationType(locationService, "Country", null);
		LocationDto location = createLocation("India", countryLocationTypeDto, null);
		location.setId(0L);
		LocationDto savedLocation = locationService.saveLocation(location);
		assertNotNull(savedLocation);
		assertEqualLocations(location, savedLocation);
		
	}
	
	/**
	 * Simple Test to get Location where given id do not exist
	 * It will create a new location with new Id and will consider id as null only
	 * @throws ApplicationException
	 */
	@Test
	public void test08_getLocationById() throws ApplicationException{
		LocationDto location = locationService.getLocationById(100000000L);
		assertNull(location);
		
	}
	
	/**
	 * Simple Test to save Location where parent do not exists
	 * @throws ApplicationException
	 */
	@Test(expected=ApplicationException.class)
	public void test09_saveLocation() throws ApplicationException{
		LocationTypeDto countryLocationTypeDto = createAndSaveLocationType(locationService, "Country", null);
		LocationDto location = createLocation("India", countryLocationTypeDto, null);
		
		location.setParentLocationId(randomLong(100000));
		locationService.saveLocation(location);
	}
	
	/**
	 * Simple Test to save Location where parent do not exists
	 * @throws ApplicationException
	 */
	@Test
	public void test10_saveLocation() throws ApplicationException{
		String locationName = "India";
		LocationTypeDto countryLocationTypeDto = createAndSaveLocationType(locationService, "Country", null);
		LocationDto location = createLocation(locationName, countryLocationTypeDto, null);
		
		/*
		LocationDto savedLocation = locationService.saveLocation(location);
		
		LocationDto dbLocation = locationService.getLocationByNameAndType(locationName, countryLocationTypeDto);
		assertEqualLocations(savedLocation, dbLocation);
		*/
	}
	
	/**
	 * Test to upload a LocationBoundary file
	 * @throws ApplicationException
	 */
	@Test
	public void test11_createNewLocationBoundaryFile() throws ApplicationException{
		
		//Create a location
		String locationName = "India";
		LocationTypeDto countryLocationTypeDto = createAndSaveLocationType(locationService, "Country", null);
		LocationDto location = createLocation(locationName, countryLocationTypeDto, null);
		LocationDto savedLocation = locationService.saveLocation(location);
		
		final FileService fileService = mock(FileService.class, "fileService");
		
		final InputStream inputStream = mock(InputStream.class, "inputStream");
		expect(new Expectations() {{
            oneOf (fileService).saveFile(with(any(String.class)), with(any(String.class)), with(any(InputStream.class)));
        }});
		
		LocationBoundaryFileDto locationBoundaryFileDto = locationService.createNewLocationBoundaryFile(savedLocation.getId(), inputStream, fileService);
		assertNotNull(locationBoundaryFileDto);
		assertNotNull(locationBoundaryFileDto.getId());
		assertNotNull(locationBoundaryFileDto.getFileNameAndPath());
		assertEquals(savedLocation.getId(), locationBoundaryFileDto.getLocationId());
		assertEquals("Pending", locationBoundaryFileDto.getStatus());
	}
	/**
	 * Test to upload a LocationBoundary file for a location which do not exists
	 * it will throw ApplicationException
	 * @throws ApplicationException
	 */
	@Test(expected=ApplicationException.class)
	public void test12_createNewLocationBoundaryFile() throws ApplicationException{
		final Long locationId = randomPositiveLong();
		final FileService fileService = mock(FileService.class, "fileService");
		final InputStream inputStream = mock(InputStream.class, "inputStream");
		expect(new Expectations() {{
        }});
		
		locationService.createNewLocationBoundaryFile(locationId, inputStream, fileService);
	}
	/**
	 * Try to save a root location type when a root location type already exist
	 * @throws ApplicationException 
	 */
	@Test(expected=ApplicationException.class)
	public void test13_saveLocationType() throws ApplicationException{
		String firstRootLocationTypeName = uniqueAlphaNumericString(randomInteger(32) + 1, "locationTypeName");
		String secondRootLocationTypeName = uniqueAlphaNumericString(randomInteger(32) + 1, "locationTypeName");
		LocationTypeDto locationTypeDto = createAndSaveLocationType(locationService, firstRootLocationTypeName, null);
		assertNotNull(locationTypeDto);
		//Means First root location type(parent is null) is saved
		//Now try to create another one with parent as null/Root it must throw Application Exception
		createAndSaveLocationType(locationService, secondRootLocationTypeName, null);
	}
	/**
	 * Try to save a root location type when No root location exists
	 */
	@Test
	public void test14_saveLocationType() throws ApplicationException{
		String firstRootLocationTypeName = uniqueAlphaNumericString(randomInteger(32) + 1, "locationTypeName");
		LocationTypeDto locationTypeDto = createAndSaveLocationType(locationService, firstRootLocationTypeName, null);
		assertNotNull(locationTypeDto);
	}
	/**
	 * Try to save a root location type and then creat its child location type
	 */
	@Test
	public void test15_saveLocationType() throws ApplicationException{
		String firstRootLocationTypeName = uniqueAlphaNumericString(randomInteger(32) + 1, "locationTypeName");
		String secondRootLocationTypeName = uniqueAlphaNumericString(randomInteger(32) + 1, "locationTypeName");
		LocationTypeDto parentLocationTypeDto = createAndSaveLocationType(locationService, firstRootLocationTypeName, null);
		assertNotNull(parentLocationTypeDto);
		LocationTypeDto childLocationTypeDto = createAndSaveLocationType(locationService, secondRootLocationTypeName, parentLocationTypeDto.getId());
		assertNotNull(childLocationTypeDto);
		//Now get it back via getLocationType service
		LocationTypeJsonDto locationTypeJsonDto = locationService.getLocationTypes(randomAlphaString(16));
		assertEqualLocationTypes(parentLocationTypeDto, locationTypeJsonDto, true);
		assertEquals(1, locationTypeJsonDto.getChildren().size());
		assertEqualLocationTypes(childLocationTypeDto, locationTypeJsonDto.getChildren().get(0), true);
	}
	
	/**
	 * Simple Test to save Location 
	 * No parent Location id provided
	 * and the get it back by getRootLocationForSwarajIndia
	 * @throws ApplicationException
	 */
	@Test
	public void test16_saveLocation() throws ApplicationException{
		String locationTypeName = randomAlphaString(16);
		LocationTypeDto locationTypeDto = createLocationType(locationTypeName, null);
		locationTypeDto = locationService.saveLocationType(locationTypeDto);
		LocationDto location = createLocation(randomAlphaString(16), locationTypeDto, null);
		LocationDto savedLocation = locationService.saveLocation(location);
		assertNotNull(savedLocation.getId());
		assertEqualLocations(location, savedLocation);
		
		LocationDto dbLocation = locationService.getRootLocationForSwarajIndia();
		assertNotNull(dbLocation.getId());
		assertEqualLocations(location, dbLocation);
	}
	
	/**
	 * no root location exists for eswaraj-india and then call getRootLocationForSwarajIndia
	 * It shud returne back a root lcaotion
	 * @throws ApplicationException
	 */
	@Test
	public void test17_getRootLocationForSwarajIndia() throws ApplicationException{
		LocationDto dbLocation = locationService.getRootLocationForSwarajIndia();
		assertNotNull(dbLocation.getId());
		assertEquals("India", dbLocation.getName());
		//also location Type must have been created
		LocationTypeJsonDto locationTypeJsonDto = locationService.getLocationTypes(randomAlphaString(16));
		assertEquals("Country", locationTypeJsonDto.getName());
		assertNull(locationTypeJsonDto.getChildren());
		
	}
	
	/**
	 * Simple Test to save Location with wrong parent
	 * @throws ApplicationException
	 */
	@Test(expected=ApplicationException.class)
	public void test018_save() throws ApplicationException{
		LocationTypeDto countryLocationTypeDto = createAndSaveLocationType(locationService, "Country", null);

		LocationTypeDto stateLocationTypeDto = createAndSaveLocationType(locationService, "State", countryLocationTypeDto.getId());
		
		LocationTypeDto districtLocationTypeDto = createAndSaveLocationType(locationService, "District", stateLocationTypeDto.getId());

		
		LocationDto location = createLocation("India", countryLocationTypeDto, null);
		LocationDto parentLocation = locationService.saveLocation(location);
		LocationDto faridabadLocation = createLocation("Faridabad", districtLocationTypeDto, parentLocation.getId());
		//must throw exception
		locationService.saveLocation(faridabadLocation);
	}
	/**
	 * Simple Test to save Location with location type which can be root
	 * @throws ApplicationException
	 */
	@Test(expected=ApplicationException.class)
	public void test019_save() throws ApplicationException{
		LocationTypeDto countryLocationTypeDto = createAndSaveLocationType(locationService, "Country", null);

		LocationTypeDto stateLocationTypeDto = createAndSaveLocationType(locationService, "State", countryLocationTypeDto.getId());
		

		
		LocationDto locationDto = createLocation("Haryana", stateLocationTypeDto, null);
		locationService.saveLocation(locationDto);
	}
	
}