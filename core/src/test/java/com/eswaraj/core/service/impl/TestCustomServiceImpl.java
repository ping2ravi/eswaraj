package com.eswaraj.core.service.impl;

import java.io.InputStream;

import org.jmock.Expectations;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.eswaraj.core.BaseNeo4jEswarajTest;
import com.eswaraj.core.exceptions.ApplicationException;
import com.eswaraj.core.service.CustomService;
import com.eswaraj.core.service.FileService;
import com.eswaraj.core.service.LocationService;
import com.eswaraj.core.service.QueueService;
import com.eswaraj.web.dto.LocationBoundaryFileDto;

@ContextConfiguration(locations = { "classpath:eswaraj-core-test.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TestCustomServiceImpl extends BaseNeo4jEswarajTest{

	@Autowired private CustomService customService;

	/**
	 * Simple Test to save Location 
	 * No parent Location id provided
	 * @throws ApplicationException
	 */
	@Test
	public void test01_processLocationBoundaryFile() throws ApplicationException{
		final LocationService locationService = mock(LocationService.class, "locationService");
		final FileService fileService = mock(FileService.class, "fileService");
		final QueueService queueService = mock(QueueService.class, "queueService");
		final InputStream inputStream = mock(InputStream.class, "inputStream");
		final LocationBoundaryFileDto locationBoundaryFileDto = new LocationBoundaryFileDto();
		final long locationId = randomPositiveLong();
		inject(customService, "locationService", locationService);
		inject(customService, "fileService", fileService);
		inject(customService, "queueService", queueService);
		
		expect(new Expectations() {{
            oneOf (locationService).createNewLocationBoundaryFile(locationId, inputStream, fileService);
            	will(returnValue(locationBoundaryFileDto));
            oneOf (queueService).sendBoundaryfileMessage(locationBoundaryFileDto);
        }});
		customService.processLocationBoundaryFile(locationId, inputStream);

	}
}
