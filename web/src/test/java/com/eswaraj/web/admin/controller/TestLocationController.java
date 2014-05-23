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
	 * Save  State when Client passes LocationType as State in json
	 * It should save it as LocationType is always ignored
	 * @throws Exception
	 */
	@Test
	public void test03_saveState() throws Exception {
		// create Test expectation data
		Long parentLocationId = randomLong();
		LocationType newLocationTypeToBeSaved = LocationType.STATE;
		LocationDto newLocationToBeSaved = createOneLocation(parentLocationId, newLocationTypeToBeSaved);
		LocationDto locationAfterSave = copyOneLocation(newLocationToBeSaved);
		//For creating the new Location set Id as Null
		newLocationToBeSaved.setId(null);
		
		
		// Set Mock expectation
		when(locationService.saveLocation(newLocationToBeSaved)).thenReturn(locationAfterSave);

		// Run test
		MediaType expectedMediaType = MediaType.APPLICATION_JSON;
		ResultActions result = mockMvc.perform(post("/ajax/location/state/save").accept(expectedMediaType)
                .contentType(APPLICATION_JSON_UTF8)
                .content(convertObjectToJsonBytes(newLocationToBeSaved))
        );
		//ResultActions result = this.mockMvc.perform(get("/ajax/location/getchild/" + parentLocationId).accept(expectedMediaType));
		result.andExpect(status().isOk());
		// System.out.println("Content = "+content().);
		//result.andExpect(content().contentType("application/json;charset=UTF-8"));

		checkLocation(result, locationAfterSave, parentLocationId);
	}
	/**
	 * Save  State when Client passes LocationType as null
	 * It should save it as LocationType is always ignored
	 * @throws Exception
	 */
	@Test
	public void test04_saveState() throws Exception {
		// create Test expectation data
		Long parentLocationId = randomLong();
		LocationType newLocationTypeToBeSaved = LocationType.STATE;
		LocationDto newLocationToBeSaved = createOneLocation(parentLocationId, newLocationTypeToBeSaved);
		LocationDto locationAfterSave = copyOneLocation(newLocationToBeSaved);
		//For creating the new Location set Id as Null
		newLocationToBeSaved.setId(null);
		LocationDto clientLocationToBeSavedWithNullType = copyOneLocation(newLocationToBeSaved);
		clientLocationToBeSavedWithNullType.setLocationType(null);
		
		
		// Set Mock expectation
		when(locationService.saveLocation(newLocationToBeSaved)).thenReturn(locationAfterSave);

		// Run test
		MediaType expectedMediaType = MediaType.APPLICATION_JSON;
		ResultActions result = mockMvc.perform(post("/ajax/location/state/save").accept(expectedMediaType)
                .contentType(APPLICATION_JSON_UTF8)
                .content(convertObjectToJsonBytes(newLocationToBeSaved))
        );
		result.andExpect(status().isOk());
		result.andExpect(content().contentType("application/json;charset=UTF-8"));

		checkLocation(result, locationAfterSave, parentLocationId);
	}
	
	/**
	 * Save  State when Client passes LocationType as Non State, i.e Country
	 * It should save it as LocationType is always ignored
	 * @throws Exception
	 */
	@Test
	public void test05_saveState() throws Exception {
		// create Test expectation data
		Long parentLocationId = randomLong();
		LocationType newLocationTypeToBeSaved = LocationType.STATE;
		LocationDto newLocationToBeSaved = createOneLocation(parentLocationId, newLocationTypeToBeSaved);
		LocationDto locationAfterSave = copyOneLocation(newLocationToBeSaved);
		//For creating the new Location set Id as Null
		newLocationToBeSaved.setId(null);
		LocationDto clientLocationToBeSavedWithNullType = copyOneLocation(newLocationToBeSaved);
		clientLocationToBeSavedWithNullType.setLocationType(LocationType.COUNTRY);
		
		
		// Set Mock expectation
		when(locationService.saveLocation(newLocationToBeSaved)).thenReturn(locationAfterSave);

		// Run test
		MediaType expectedMediaType = MediaType.APPLICATION_JSON;
		ResultActions result = mockMvc.perform(post("/ajax/location/state/save").accept(expectedMediaType)
                .contentType(APPLICATION_JSON_UTF8)
                .content(convertObjectToJsonBytes(newLocationToBeSaved))
        );
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
