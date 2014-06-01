package com.eswaraj.core;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;

import com.eswaraj.base.BaseEswarajTest;
import com.eswaraj.base.aspect.TestObjectContextManager;
import com.eswaraj.core.exceptions.ApplicationException;
import com.eswaraj.core.service.LocationService;
import com.eswaraj.web.dto.LocationDto;
import com.eswaraj.web.dto.LocationTypeDto;

public class BaseNeo4jEswarajTest extends BaseEswarajTest {

	@Autowired Neo4jTemplate neo4jTemplate;
	@Autowired(required=false) TestObjectContextManager testObjectContextManager;

	@Autowired
	private GraphDatabaseService graphDatabaseService;
	
	@Before
	public void init(){
	}

	@After
	public void destroyTest(){
		if(testObjectContextManager != null){
			Transaction txn = graphDatabaseService.beginTx();
			try{
				testObjectContextManager.clearAllObjectsCreatdDuringTest();
				txn.success();
			}catch(Exception ex){
				txn.failure();
			}finally{
				txn.finish();
			}
		}
	}
	
	protected LocationDto createLocation(String name, LocationTypeDto locationTypeDto, Long parentLocationId){
		LocationDto location = new LocationDto();
		location.setName(name);
		if(locationTypeDto != null){
			location.setLocationTypeId(locationTypeDto.getId());	
		}
		location.setParentLocationId(parentLocationId);
		return location;
	}
	protected LocationTypeDto createLocationType(String name, Long parentLocationTypeId){
		LocationTypeDto location = new LocationTypeDto();
		location.setName(name);
		location.setParentLocationTypeId(parentLocationTypeId);
		return location;
	}
	protected LocationTypeDto createAndSaveLocationType(LocationService locationService, String name, Long parentLocationTypeId) throws ApplicationException{
		LocationTypeDto locationTypeDto = createLocationType(name, parentLocationTypeId);
		locationTypeDto = locationService.saveLocationType(locationTypeDto);
		return locationTypeDto;
	}
	/**
	 * If you dont want your test to delete newly created DB objects to be deleted, then call this method from your test
	 */
	protected void dontDeleteDbObjects(){
		testObjectContextManager.setDontDeleteForThisTest(true);
	}
	
	protected void assertEqualLocations(LocationDto expectedLocation, LocationDto actualLocation){
		assertEquals(expectedLocation.getLattitude(), actualLocation.getLattitude());
		assertEquals(expectedLocation.getLongitude(), actualLocation.getLongitude());
		assertEquals(expectedLocation.getLocationTypeId(), actualLocation.getLocationTypeId());
		assertEquals(expectedLocation.getParentLocationId(), actualLocation.getParentLocationId());
		assertEquals(expectedLocation.getName(), actualLocation.getName());
	}
	
	protected void assertEqualLocationTypes(LocationTypeDto expectedLocation, LocationTypeDto actualLocation, boolean checkId){
		if(checkId){
			assertEquals(expectedLocation.getId(), actualLocation.getId());	
		}
		assertEquals(expectedLocation.getName(), actualLocation.getName());
	}


}
