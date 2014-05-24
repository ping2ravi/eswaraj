package com.eswaraj.web.admin.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.eswaraj.base.BaseEswarajMockitoTest;
import com.eswaraj.core.service.LocationService;
import com.eswaraj.web.dto.LocationDto;
import com.eswaraj.web.dto.LocationType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:eswaraj-servlet.xml", "classpath:eswaraj-web-test.xml" })
public class TestLocationController extends BaseEswarajMockitoTest {

	@Autowired
	private WebApplicationContext wac;

	@Autowired
	private LocationService locationService;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void test01_getroot() throws Exception {
		// create Test expectation data
		Long parentLocationId = null;
		LocationType rootLocationType = LocationType.COUNTRY;
		LocationDto locationDto = createOneLocation(parentLocationId, rootLocationType);

		// Set Mock expectation
		when(locationService.getLocationByNameAndType("India", LocationType.COUNTRY)).thenReturn(locationDto);

		// Run test
		MediaType expectedMediaType = MediaType.APPLICATION_JSON;
		ResultActions result = this.mockMvc.perform(get("/ajax/location/getroot").accept(expectedMediaType));
		result.andExpect(status().isOk());
		// System.out.println("Content = "+content().);
		result.andExpect(content().contentType("application/json;charset=UTF-8"));

		checkLocation(result, locationDto, parentLocationId);
	}

	@Test
	public void test02_getChildLocations() throws Exception {
		// create Test expectation data
		Long parentLocationId = randomLong();
		LocationType childLocationType = LocationType.STATE;
		LocationDto firstLocationDto = createOneLocation(parentLocationId, childLocationType);
		LocationDto secondLocationDto = createOneLocation(parentLocationId, childLocationType);
		List<LocationDto> childLocations = new ArrayList<>();
		childLocations.add(firstLocationDto);
		childLocations.add(secondLocationDto);
		// Set Mock expectation
		when(locationService.getChildLocationsOfParent(parentLocationId)).thenReturn(childLocations);

		// Run test
		MediaType expectedMediaType = MediaType.APPLICATION_JSON;
		ResultActions result = this.mockMvc.perform(get("/ajax/location/getchild/" + parentLocationId).accept(expectedMediaType));
		result.andExpect(status().isOk());
		// System.out.println("Content = "+content().);
		result.andExpect(content().contentType("application/json;charset=UTF-8"));

		checkLocationArray(result, childLocations, parentLocationId);
	}

	/**
	 * Save State when Client passes LocationType as State in json It should
	 * save it as LocationType is always ignored
	 * 
	 * @throws Exception
	 */
	@Test
	public void test03_saveState() throws Exception {
		saveLocationWithLocationType("/ajax/location/state/save", LocationType.STATE, LocationType.STATE);
	}

	/**
	 * Save State when Client passes LocationType as null It should save it as
	 * LocationType is always ignored
	 * 
	 * @throws Exception
	 */
	@Test
	public void test04_saveState() throws Exception {
		saveLocationWithLocationType("/ajax/location/state/save", LocationType.STATE, null);
	}

	/**
	 * Save State when Client passes LocationType as Non State, i.e Country It
	 * should save it as LocationType is always ignored
	 * 
	 * @throws Exception
	 */
	@Test
	public void test05_saveState() throws Exception {
		saveLocationWithLocationType("/ajax/location/state/save", LocationType.STATE, LocationType.COUNTRY);
		saveLocationWithLocationType("/ajax/location/state/save", LocationType.STATE, LocationType.CITY);
	}
	
	/**
	 * Save District when Client passes LocationType as District in json It should
	 * save it as LocationType is always ignored
	 * 
	 * @throws Exception
	 */
	@Test
	public void test06_saveDistrict() throws Exception {
		saveLocationWithLocationType("/ajax/location/district/save", LocationType.DISTRICT, LocationType.DISTRICT);
	}

	/**
	 * Save District when Client passes LocationType as null It should save it as
	 * LocationType is always ignored
	 * 
	 * @throws Exception
	 */
	@Test
	public void test07_saveDistrict() throws Exception {
		saveLocationWithLocationType("/ajax/location/district/save", LocationType.DISTRICT, null);
	}

	/**
	 * Save District when Client passes LocationType as Non District, i.e State 
	 * It should save it as LocationType is always ignored
	 * 
	 * @throws Exception
	 */
	@Test
	public void test08_saveDistrict() throws Exception {
		saveLocationWithLocationType("/ajax/location/district/save", LocationType.DISTRICT, LocationType.STATE);
	}
	
	/**
	 * Save AC when Client passes LocationType as AC in json It should
	 * save it as LocationType is always ignored
	 * 
	 * @throws Exception
	 */
	@Test
	public void test09_saveAc() throws Exception {
		saveLocationWithLocationType("/ajax/location/ac/save", LocationType.ASSEMBLY_CONSTITUENCY, LocationType.ASSEMBLY_CONSTITUENCY);
	}

	/**
	 * Save AC when Client passes LocationType as null It should save it as
	 * LocationType is always ignored
	 * 
	 * @throws Exception
	 */
	@Test
	public void test10_saveAc() throws Exception {
		saveLocationWithLocationType("/ajax/location/ac/save", LocationType.ASSEMBLY_CONSTITUENCY, null);
	}

	/**
	 * Save AC when Client passes LocationType as Non AC, i.e State 
	 * It should save it as LocationType is always ignored
	 * 
	 * @throws Exception
	 */
	@Test
	public void test11_saveAc() throws Exception {
		saveLocationWithLocationType("/ajax/location/ac/save", LocationType.ASSEMBLY_CONSTITUENCY, LocationType.STATE);
	}
	
	
	/**
	 * Save PC when Client passes LocationType as PC in json It should
	 * save it as LocationType is always ignored
	 * 
	 * @throws Exception
	 */
	@Test
	public void test12_savePc() throws Exception {
		saveLocationWithLocationType("/ajax/location/pc/save", LocationType.PARLIAMENT_CONSTITUENCY, LocationType.PARLIAMENT_CONSTITUENCY);
	}

	/**
	 * Save PC when Client passes LocationType as null It should save it as
	 * LocationType is always ignored
	 * 
	 * @throws Exception
	 */
	@Test
	public void test13_savePc() throws Exception {
		saveLocationWithLocationType("/ajax/location/pc/save", LocationType.PARLIAMENT_CONSTITUENCY, null);
	}

	/**
	 * Save PC when Client passes LocationType as Non PC, i.e State 
	 * It should save it as LocationType is always ignored
	 * 
	 * @throws Exception
	 */
	@Test
	public void test14_savePc() throws Exception {
		saveLocationWithLocationType("/ajax/location/pc/save", LocationType.PARLIAMENT_CONSTITUENCY, LocationType.STATE);
	}

	/**
	 * Save CITY when Client passes LocationType as CITY in json It should
	 * save it as LocationType is always ignored
	 * 
	 * @throws Exception
	 */
	@Test
	public void test15_saveCity() throws Exception {
		saveLocationWithLocationType("/ajax/location/city/save", LocationType.CITY, LocationType.CITY);
	}

	/**
	 * Save CITY when Client passes LocationType as null It should save it as
	 * LocationType is always ignored
	 * 
	 * @throws Exception
	 */
	@Test
	public void test16_saveCity() throws Exception {
		saveLocationWithLocationType("/ajax/location/city/save", LocationType.CITY, null);
	}

	/**
	 * Save CITY when Client passes LocationType as Non CITY, i.e State 
	 * It should save it as LocationType is always ignored
	 * 
	 * @throws Exception
	 */
	@Test
	public void test17_saveCity() throws Exception {
		saveLocationWithLocationType("/ajax/location/city/save", LocationType.CITY, LocationType.STATE);
	}
	
	/**
	 * Save LocalArea when Client passes LocationType as LocalArea in json It should
	 * save it as LocationType is always ignored
	 * 
	 * @throws Exception
	 */
	@Test
	public void test18_saveLocalArea() throws Exception {
		saveLocationWithLocationType("/ajax/location/localarea/save", LocationType.LOCAL_AREA, LocationType.LOCAL_AREA);
	}

	/**
	 * Save LocalArea when Client passes LocationType as null It should save it as
	 * LocationType is always ignored
	 * 
	 * @throws Exception
	 */
	@Test
	public void test19_saveLocalArea() throws Exception {
		saveLocationWithLocationType("/ajax/location/localarea/save", LocationType.LOCAL_AREA, null);
	}

	/**
	 * Save LocalArea when Client passes LocationType as Non LocalArea, i.e State 
	 * It should save it as LocationType is always ignored
	 * 
	 * @throws Exception
	 */
	@Test
	public void test20_saveLocalArea() throws Exception {
		saveLocationWithLocationType("/ajax/location/localarea/save", LocationType.LOCAL_AREA, LocationType.STATE);
	}
	
	/**
	 * Save MUNCIPLE when Client passes LocationType as MUNCIPLE in json It should
	 * save it as LocationType is always ignored
	 * 
	 * @throws Exception
	 */
	@Test
	public void test21_saveMunciple() throws Exception {
		saveLocationWithLocationType("/ajax/location/munciple/save", LocationType.MUNCIPLE, LocationType.MUNCIPLE);
	}

	/**
	 * Save MUNCIPLE when Client passes LocationType as null It should save it as
	 * LocationType is always ignored
	 * 
	 * @throws Exception
	 */
	@Test
	public void test22_saveMunciple() throws Exception {
		saveLocationWithLocationType("/ajax/location/munciple/save", LocationType.MUNCIPLE, null);
	}

	/**
	 * Save Munciple when Client passes LocationType as Non Munciple, i.e State 
	 * It should save it as LocationType is always ignored
	 * 
	 * @throws Exception
	 */
	@Test
	public void test23_saveMunciple() throws Exception {
		saveLocationWithLocationType("/ajax/location/munciple/save", LocationType.MUNCIPLE, LocationType.STATE);
	}
	
	
	/**
	 * Save VILLAGE when Client passes LocationType as VILLAGE in json It should
	 * save it as LocationType is always ignored
	 * 
	 * @throws Exception
	 */
	@Test
	public void test24_saveVillage() throws Exception {
		saveLocationWithLocationType("/ajax/location/village/save", LocationType.VILLAGE, LocationType.VILLAGE);
	}

	/**
	 * Save VILLAGE when Client passes LocationType as null It should save it as
	 * LocationType is always ignored
	 * 
	 * @throws Exception
	 */
	@Test
	public void test25_saveVillage() throws Exception {
		saveLocationWithLocationType("/ajax/location/village/save", LocationType.VILLAGE, null);
	}

	/**
	 * Save VILLAGE when Client passes LocationType as Non VILLAGE, i.e State 
	 * It should save it as LocationType is always ignored
	 * 
	 * @throws Exception
	 */
	@Test
	public void test26_saveVillage() throws Exception {
		saveLocationWithLocationType("/ajax/location/village/save", LocationType.VILLAGE, LocationType.STATE);
	}
	
	/**
	 * Save WARD when Client passes LocationType as WARD in json It should
	 * save it as LocationType is always ignored
	 * 
	 * @throws Exception
	 */
	@Test
	public void test27_saveWard() throws Exception {
		saveLocationWithLocationType("/ajax/location/ward/save", LocationType.WARD, LocationType.WARD);
	}

	/**
	 * Save WARD when Client passes LocationType as null It should save it as
	 * LocationType is always ignored
	 * 
	 * @throws Exception
	 */
	@Test
	public void test28_saveWard() throws Exception {
		saveLocationWithLocationType("/ajax/location/ward/save", LocationType.WARD, null);
	}

	/**
	 * Save WARD when Client passes LocationType as Non WARD, i.e State 
	 * It should save it as LocationType is always ignored
	 * 
	 * @throws Exception
	 */
	@Test
	public void test29_saveWard() throws Exception {
		saveLocationWithLocationType("/ajax/location/ward/save", LocationType.WARD, LocationType.STATE);
	}

	/*
	 * when saving a location with the locationType as per url
	 */
	private void saveLocationWithLocationType(String saveUrl, LocationType locationType, LocationType actualLocationTypeSent) throws Exception {
		// create Test expectation data
		Long parentLocationId = randomLong();
		LocationDto newLocationToBeSaved = createOneLocation(parentLocationId, locationType);
		LocationDto locationAfterSave = copyOneLocation(newLocationToBeSaved);
		// For creating the new Location set Id as Null
		newLocationToBeSaved.setId(null);
		LocationDto clientLocationToBeSavedWithNullType = copyOneLocation(newLocationToBeSaved);
		clientLocationToBeSavedWithNullType.setLocationType(actualLocationTypeSent);

		// Set Mock expectation
		when(locationService.saveLocation(newLocationToBeSaved)).thenReturn(locationAfterSave);

		// Run test
		MediaType expectedMediaType = MediaType.APPLICATION_JSON;
		ResultActions result = mockMvc.perform(post(saveUrl).accept(expectedMediaType).contentType(APPLICATION_JSON_UTF8)
				.content(convertObjectToJsonBytes(newLocationToBeSaved)));
		result.andExpect(status().isOk());
		result.andExpect(content().contentType("application/json;charset=UTF-8"));

		checkLocation(result, locationAfterSave, parentLocationId);
	}

	private void checkLocationArray(ResultActions result, List<LocationDto> locations, Long parentLocationId) throws Exception {
		LocationDto locationDto;
		int sizeOfArray = locations.size();
		result.andExpect(jsonPath("$", hasSize(sizeOfArray)));
		for (int i = 0; i < sizeOfArray; i++) {
			locationDto = locations.get(i);
			result.andExpect(jsonPath("$[" + i + "].name").value(locationDto.getName()));
			result.andExpect(jsonPath("$[" + i + "].id").value(locationDto.getId()));
			result.andExpect(jsonPath("$[" + i + "].lattitude").value(locationDto.getLattitude()));
			result.andExpect(jsonPath("$[" + i + "].longitude").value(locationDto.getLongitude()));
			result.andExpect(jsonPath("$[" + i + "].locationType").value(locationDto.getLocationType().name()));
			// For country Parent Id must Not be null and must be equal to
			// parametr we passed to url
			if (parentLocationId == null) {
				result.andExpect(jsonPath("$[" + i + "].parentLocationId").doesNotExist());
			} else {
				result.andExpect(jsonPath("$[" + i + "].parentLocationId").value(parentLocationId));
			}
		}
	}

	private void checkLocation(ResultActions result, LocationDto locationDto, Long parentLocationId) throws Exception {
		result.andExpect(jsonPath("$.name").value(locationDto.getName()));
		result.andExpect(jsonPath("$.id").value(locationDto.getId()));
		result.andExpect(jsonPath("$.lattitude").value(locationDto.getLattitude()));
		result.andExpect(jsonPath("$.longitude").value(locationDto.getLongitude()));
		result.andExpect(jsonPath("$.locationType").value(locationDto.getLocationType().name()));
		// For country Parent Id must Not be null and must be equal to parametr
		// we passed to url
		if (parentLocationId == null) {
			result.andExpect(jsonPath("$.parentLocationId").doesNotExist());
		} else {
			result.andExpect(jsonPath("$.parentLocationId").value(parentLocationId));
		}
	}

	private LocationDto createOneLocation(Long parentLocationId, LocationType locationType) {
		Long firstLocationId = randomLong();
		String firstLocationName = randomAlphaString(30);
		Double firstLocationLattitude = randomDouble(180);
		Double rootLocationLongitude = randomDouble(180);

		LocationDto locationDto = new LocationDto();
		locationDto.setId(firstLocationId);
		locationDto.setName(firstLocationName);
		locationDto.setLattitude(firstLocationLattitude);
		locationDto.setLongitude(rootLocationLongitude);
		locationDto.setLocationType(locationType);
		locationDto.setParentLocationId(parentLocationId);

		return locationDto;
	}

	private LocationDto copyOneLocation(LocationDto sourceLocation) {
		LocationDto locationDto = new LocationDto();
		locationDto.setId(sourceLocation.getId());
		locationDto.setName(sourceLocation.getName());
		locationDto.setLattitude(sourceLocation.getLattitude());
		locationDto.setLongitude(sourceLocation.getLongitude());
		locationDto.setLocationType(sourceLocation.getLocationType());
		locationDto.setParentLocationId(sourceLocation.getParentLocationId());

		return locationDto;
	}

	public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		return mapper.writeValueAsBytes(object);
	}

}
