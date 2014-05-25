package com.eswaraj.core.service.impl;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eswaraj.core.exceptions.ApplicationException;
import com.eswaraj.core.service.CustomService;
import com.eswaraj.core.service.FileService;
import com.eswaraj.core.service.LocationService;
import com.eswaraj.core.service.QueueService;
import com.eswaraj.web.dto.LocationBoundaryFileDto;

@Component
public class CustomServiceImpl implements CustomService {

	@Autowired
	private LocationService locationService;
	
	@Autowired
	private FileService fileService;
	
	@Autowired
	private QueueService queueService;
	
	
	@Override
	public void processLocationBoundaryFile(Long locationId, InputStream inputStream) throws ApplicationException {
		
		LocationBoundaryFileDto locationBoundaryFileDto = locationService.createNewLocationBoundaryFile(locationId, inputStream, fileService);
        //Submit file to queue to be processed by backend
		queueService.sendBoundaryfileMessage(locationBoundaryFileDto);

	}

}
