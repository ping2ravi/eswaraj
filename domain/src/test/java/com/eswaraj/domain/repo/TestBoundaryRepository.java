package com.eswaraj.domain.repo;


import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.eswaraj.domain.nodes.division.Boundary;
import com.eswaraj.domain.nodes.division.GeoPoint;

/**
 * Test for Video repository
 * @author ravi
 * @data May 29, 2014
 */

@ContextConfiguration(locations = { "classpath:eswaraj-domain-test.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TestBoundaryRepository extends BaseNeo4jEswarajTest{

	@Autowired BoundaryRepository boundaryRepository;

	/**
	 * Given we provide all required attributes of a boundary
	 */
	@Test
	public void shouldSaveBoundary() {
		Boundary boundary = new Boundary();
		GeoPoint bottomRight = new GeoPoint();
		bottomRight.setLattitude(randomDouble(90));
		bottomRight.setLongitude(randomDouble(180));
		bottomRight.setBoundary(boundary);
		boundary.setBottomRight(bottomRight);
		boundary = boundaryRepository.save(boundary);
		System.out.println("Boundary Id = "+boundary.getId());
		System.out.println("getBottomRight Id = "+boundary.getBottomRight().getId());
		assertNotNull(boundary.getId());
	}
	
}
