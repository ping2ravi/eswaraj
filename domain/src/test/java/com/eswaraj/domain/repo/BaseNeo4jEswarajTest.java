package com.eswaraj.domain.repo;

import org.junit.After;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;

import com.eswaraj.base.BaseEswarajTest;
import com.eswaraj.base.aspect.TestObjectContextManager;
import com.eswaraj.domain.nodes.DataClient;
import com.eswaraj.domain.nodes.Location;
import com.eswaraj.domain.nodes.LocationType;

public class BaseNeo4jEswarajTest extends BaseEswarajTest {

	@Autowired Neo4jTemplate neo4jTemplate;
	@Autowired(required=false) TestObjectContextManager testObjectContextManager;

	@Before
	public void init(){
	}

	/**
	 * If you dont want your test to delete newly created DB objects to be deleted, then call this method from your test
	 */
	protected void dontDeleteDbObjects(){
		testObjectContextManager.setDontDeleteForThisTest(true);
	}

	@After
	public void destroyTest(){
		if(testObjectContextManager != null){
			testObjectContextManager.clearAllObjectsCreatdDuringTest();
		}
	}
	
	protected Location createLocation(LocationRepository locationRepository, String name, LocationType locationType, Location parentLocation){
		Location location = new Location();
		location.setName(name);
		location.setLocationType(locationType);
		location.setParentLocation(parentLocation);
		location = locationRepository.save(location);
		return location;
	}
	
	protected LocationType createLocationType(LocationTypeRepository locationTypeRepository, String name, LocationType parentLocationType, DataClient dataClient){
		LocationType locationType = new LocationType();
		locationType.setName(name);
		locationType.setParentLocationType(parentLocationType);
		locationType.setDataClient(dataClient);
		locationType = locationTypeRepository.save(locationType);
		return locationType;
	}
	protected DataClient createDataClient(DataClientRepository dataClientRepository, String name){
		DataClient dataClient = new DataClient();
		dataClient.setName(name);
		dataClient = dataClientRepository.save(dataClient);
		return dataClient;
	}

	protected void assertLocationTypeEquals(LocationType expected, LocationType actual, boolean compareId){
		if(compareId){
			assertEquals(expected.getId(), actual.getId());
		}
		assertEquals(expected.getName(), actual.getName());
	}
	protected void assertDataClientEquals(DataClient expected, DataClient actual, boolean compareId){
		if(compareId){
			assertEquals(expected.getId(), actual.getId());
		}
		assertEquals(expected.getName(), actual.getName());
	}

}
