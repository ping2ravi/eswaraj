package com.eswaraj.core;

import org.junit.After;
import static org.junit.Assert.*;

import org.junit.Before;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.eswaraj.base.BaseEswarajTest;
import com.eswaraj.base.aspect.TestObjectContextManager;
import com.eswaraj.web.dto.LocationDto;
import com.eswaraj.web.dto.LocationType;

public class BaseNeo4jEswarajTest extends BaseEswarajTest {

	@Autowired Neo4jTemplate neo4jTemplate;
	@Autowired(required=false) TestObjectContextManager testObjectContextManager;

	@Autowired
	private GraphDatabaseService graphDb;
	
	@Before
	public void init(){
	}

	@After
	public void destroyTest(){
		if(testObjectContextManager != null){
			Transaction txn = graphDb.beginTx();
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
	
	protected LocationDto createLocation(String name, LocationType locationType, Long parentLocationId){
		LocationDto location = new LocationDto();
		location.setName(name);
		location.setLocationType(locationType);
		location.setParentLocationId(parentLocationId);
		return location;
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
		assertEquals(expectedLocation.getLocationType(), actualLocation.getLocationType());
		assertEquals(expectedLocation.getParentLocationId(), actualLocation.getParentLocationId());
		assertEquals(expectedLocation.getName(), actualLocation.getName());
	}


}
