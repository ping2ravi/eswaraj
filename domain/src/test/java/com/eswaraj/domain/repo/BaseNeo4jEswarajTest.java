package com.eswaraj.domain.repo;

import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;

import com.eswaraj.base.BaseEswarajTest;
import com.eswaraj.base.aspect.TestObjectContextManager;
import com.eswaraj.domain.nodes.Location;
import com.eswaraj.web.dto.LocationType;

public class BaseNeo4jEswarajTest extends BaseEswarajTest {

	@Autowired Neo4jTemplate neo4jTemplate;
	@Autowired LocationRepository locationRepository;
	@Autowired(required=false) TestObjectContextManager testObjectContextManager;

	@Before
	public void init(){
	}

	@After
	public void destroyTest(){
		if(testObjectContextManager != null){
			testObjectContextManager.clearAllObjectsCreatdDuringTest();
		}
	}
	
	protected Location createLocation(String name, LocationType locationType, Location parentLocation){
		Location location = new Location();
		location.setName(name);
		location.setLocationType(locationType);
		location.setParentLocation(parentLocation);
		location = locationRepository.save(location);
		return location;
	}


}
