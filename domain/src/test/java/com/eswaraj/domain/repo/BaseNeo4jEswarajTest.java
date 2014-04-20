package com.eswaraj.domain.repo;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;

import com.eswaraj.domain.nodes.Location;
import com.eswaraj.domain.nodes.LocationType;

public class BaseNeo4jEswarajTest extends BaseEswarajTest {

	protected List<Object> allCreatedNodes;
	
	@Autowired Neo4jTemplate neo4jTemplate;
	@Autowired LocationRepository locationRepository;

	@Before
	public void init(){
		allCreatedNodes = new ArrayList<>();
	}

	@After
	public void destroyTest(){
		if(allCreatedNodes != null){
			for(Object oneObject:allCreatedNodes){
				System.out.println("   Deleting " + oneObject);
				neo4jTemplate.delete(oneObject);
			}
		}
	}
	
	protected Location createLocation(String name, LocationType locationType, Location parentLocation){
		Location location = new Location();
		location.setName(name);
		location.setLocationType(locationType);
		location.setParentLocation(parentLocation);
		location = locationRepository.save(location);
		allCreatedNodes.add(location);
		return location;
	}


}
