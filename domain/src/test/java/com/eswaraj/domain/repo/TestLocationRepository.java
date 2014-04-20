package com.eswaraj.domain.repo;


import java.util.Collection;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

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

	/**
	 * A simple test to create a Country/Location and get it back by Id
	 */
	@Test
	public void test01_LocationRepository(){
		
		final String countryName = randomAlphaString(10);
		
		Location countryLocation = createLocation(countryName, LocationType.COUNTRY, null);
		Location dbCountryLocation = locationRepository.findOne(countryLocation.getId());
		
		assertNotNull(dbCountryLocation);
		assertEquals(countryLocation, dbCountryLocation);
		
	}
	
	/**
	 * A simple test to create a Country/Location and get it back by name
	 */
	@Test
	public void test02_LocationRepository(){
		
		final String countryName = randomAlphaString(10);
		
		Location countryLocation = createLocation(countryName, LocationType.COUNTRY, null);
		Location dbCountryLocation = locationRepository.getLocationFromName(countryName);
		
		assertNotNull(dbCountryLocation);
		assertEquals(countryLocation, dbCountryLocation);
		
	}
	
	/**
	 * A simple test to create a Country/Location where Name is null
	 */
	@Test(expected=ValidationException.class)
	public void test03_LocationRepository(){
		
		final String countryName = null;
		createLocation(countryName, LocationType.COUNTRY, null);
	}
	
	/**
	 * A simple test to create a Country/Location where name is Not null but Location Type is null
	 */
	@Test(expected=ValidationException.class)
	public void test04_LocationRepository(){
		
		final String countryName = randomAlphaString(16);
		createLocation(countryName, null, null);
	}
	
	/**
	 * when creating a State/Location where parentLocation is null
	 * then creating location must throw ValidationException as State must have country as parent
	 */
	@Test(expected=ValidationException.class)
	public void test05_LocationRepository(){
		
		final String stateName = randomAlphaString(16);
		createLocation(stateName, LocationType.STATE, null);
	}
	
	/**
	 * when creating a State/Location where parentLocation is another State
	 * then creating location must throw ValidationException as State must have country as parent
	 */
	@Test(expected=ValidationException.class)
	public void test06_LocationRepository(){
		
		final String stateName1 = randomAlphaString(16);
		final String stateName2 = randomAlphaString(16);
		final String countryName = randomAlphaString(16);
		
		Location country = createLocation(countryName, LocationType.COUNTRY, null);
		Location state1 = createLocation(stateName1, LocationType.STATE, country);
		
		createLocation(stateName2, LocationType.STATE, state1);
	}
	
	/**
	 * when creating a country and State
	 * then get all states of the country
	 * return all states
	 */
	@Test
	public void test07_LocationRepository(){
		createTwoLocationParentAndChildSuccessfully(LocationType.COUNTRY, LocationType.STATE, null);
	}
	
	/**
	 * when creating a State and District
	 * then get all district of the State
	 * return all district
	 */
	@Test
	public void test08_LocationRepository(){
		Location parentLocationParent = createLocation(randomAlphaString(10), LocationType.COUNTRY, null);
		createTwoLocationParentAndChildSuccessfully(LocationType.STATE, LocationType.DISTRICT, parentLocationParent);
	}
	
	/**
	 * when creating a State and Parliament Constituency
	 * then get all Parliament Constituency of the State
	 * return all Parliament Constituencies
	 */
	@Test
	public void test09_LocationRepository(){
		Location parentLocationParent = createLocation(randomAlphaString(10), LocationType.COUNTRY, null);
		createTwoLocationParentAndChildSuccessfully(LocationType.STATE, LocationType.PARLIAMENT_CONSTITUENCY, parentLocationParent);
	}
	
	/**
	 * when creating a District and City
	 * then get all City of the district
	 * return all Cities
	 */
	@Test
	public void test10_LocationRepository(){
		Location country = createLocation(randomAlphaString(10), LocationType.COUNTRY, null);
		Location state = createLocation(randomAlphaString(10), LocationType.STATE, country);
		createTwoLocationParentAndChildSuccessfully(LocationType.DISTRICT, LocationType.CITY, state);
	}
	
	/**
	 * when creating a Parliament Constituency and Assembly Constituency
	 * then get all AC of the PC
	 * return all AC
	 */
	@Test
	public void test11_LocationRepository(){
		Location country = createLocation(randomAlphaString(10), LocationType.COUNTRY, null);
		Location state = createLocation(randomAlphaString(10), LocationType.STATE, country);
		createTwoLocationParentAndChildSuccessfully(LocationType.PARLIAMENT_CONSTITUENCY, LocationType.ASSEMBLY_CONSTITUENCY, state);
	}
	
	/**
	 * when creating a District/Location where parentLocation is null
	 * then creating location must throw ValidationException as District must have State as parent
	 */
	@Test(expected=ValidationException.class)
	public void test12_LocationRepository(){
		
		final String districtName = randomAlphaString(16);
		createLocation(districtName, LocationType.DISTRICT, null);
	}
	
	/**
	 * when creating a AC/Location where parentLocation is null
	 * then creating location must throw ValidationException as AC must have PC as parent
	 */
	@Test(expected=ValidationException.class)
	public void test13_LocationRepository(){
		
		final String acName = randomAlphaString(16);
		createLocation(acName, LocationType.ASSEMBLY_CONSTITUENCY, null);
	}
	
	/**
	 * when creating a PC/Location where parentLocation is null
	 * then creating location must throw ValidationException as PC must have State as parent
	 */
	@Test(expected=ValidationException.class)
	public void test14_LocationRepository(){
		
		final String pcName = randomAlphaString(16);
		createLocation(pcName, LocationType.PARLIAMENT_CONSTITUENCY, null);
	}
	
	/**
	 * when creating a WARD/Location where parentLocation is null
	 * then creating location must throw ValidationException as Ward must have Munciple as parent
	 */
	@Test(expected=ValidationException.class)
	public void test15_LocationRepository(){
		
		final String wardName = randomAlphaString(16);
		createLocation(wardName, LocationType.WARD, null);
	}
	
	/**
	 * when creating a City/Location where parentLocation is null
	 * then creating location must throw ValidationException as City must have District as parent
	 */
	@Test(expected=ValidationException.class)
	public void test16_LocationRepository(){
		
		final String cityName = randomAlphaString(16);
		createLocation(cityName, LocationType.CITY, null);
	}
	
	/**
	 * when creating a LocalArea/Location where parentLocation is null
	 * then creating location must throw ValidationException as LocalArea must have City/Village as parent
	 */
	@Test(expected=ValidationException.class)
	public void test17_LocationRepository(){
		
		final String localAreaName = randomAlphaString(16);
		createLocation(localAreaName, LocationType.LOCAL_AREA, null);
	}
	
	/**
	 * when creating a Munciple/Location where parentLocation is null
	 * then creating location must throw ValidationException as Munciple must have AC as parent
	 */
	@Test(expected=ValidationException.class)
	public void test18_LocationRepository(){
		
		final String muncipleName = randomAlphaString(16);
		createLocation(muncipleName, LocationType.MUNCIPLE, null);
	}
	
	/**
	 * when creating a Village/Location where parentLocation is null
	 * then creating location must throw ValidationException as Village must have District as parent
	 */
	@Test(expected=ValidationException.class)
	public void test19_LocationRepository(){
		
		final String villageName = randomAlphaString(16);
		createLocation(villageName, LocationType.VILLAGE, null);
	}
	
	private void createTwoLocationParentAndChildSuccessfully(LocationType parentLocationType, LocationType childLocationType, Location parentLocationParent){
		final String childLocationName = randomAlphaString(16);
		final String parentLocationName = randomAlphaString(16);
		
		Location parentLocation = createLocation(parentLocationName, parentLocationType, parentLocationParent);
		Location childLocation = createLocation(childLocationName, childLocationType, parentLocation);

		Collection<Location> allChildrenLocation = locationRepository.findLocationByParentLocationAndLocationType(parentLocationName, childLocationType);
		assertEquals(1, allChildrenLocation.size());
		Location dbChildLocation = allChildrenLocation.iterator().next();
		assertEquals(childLocationType, dbChildLocation.getLocationType());
		assertEquals(childLocation.getName(), dbChildLocation.getName());
		assertEquals(childLocation.getId(), dbChildLocation.getId());
		
	}
	
	
	@Test
	public void test99_LocationRepository(){
		
		final String stateName = randomAlphaString(10);
		
		Location indiaLocation = createLocation("India", LocationType.COUNTRY, null);
		indiaLocation = locationRepository.findOne(indiaLocation.getId());
		
		
		Location haryanaLocation = createLocation(stateName, LocationType.STATE, indiaLocation);
		haryanaLocation = locationRepository.findOne(haryanaLocation.getId());
		
		Location faridabadDistrictLocation = createLocation("Faridabad", LocationType.DISTRICT, haryanaLocation);
		faridabadDistrictLocation = locationRepository.findOne(faridabadDistrictLocation.getId());

		Location palwalDistrictLocation = createLocation("Palwal", LocationType.DISTRICT, haryanaLocation);
		palwalDistrictLocation = locationRepository.findOne(palwalDistrictLocation.getId());

		Location pcFaridabadLocation = createLocation("Faridabad", LocationType.PARLIAMENT_CONSTITUENCY, haryanaLocation);
		pcFaridabadLocation = locationRepository.findOne(pcFaridabadLocation.getId());
		
		indiaLocation = locationRepository.findOne(indiaLocation.getId());
		
		System.out.println("India from Haryana"+haryanaLocation.getParentLocation());
		
		String haryanName = haryanaLocation.getName();
		haryanaLocation = locationRepository.getParentLocation(haryanName);
		System.out.println("Haryana is (After Parent search)"+haryanName+" , "+haryanaLocation);
		
		Collection<Location> states = locationRepository.findLocationByParentLocation(indiaLocation.getName());
		System.out.println("states="+states);
		Collection<Location> districtPc = locationRepository.findLocationByParentLocation(stateName);
		System.out.println("districtPc="+districtPc);
		
		Collection<Location> districts = locationRepository.findLocationByParentLocationAndLocationType(stateName, LocationType.DISTRICT);
		System.out.println("districts="+districts);
		
		Collection<Location> pcs = locationRepository.findLocationByParentLocationAndLocationType(stateName, LocationType.PARLIAMENT_CONSTITUENCY);
		System.out.println("pcs="+pcs);
		
		
	}
	
}
