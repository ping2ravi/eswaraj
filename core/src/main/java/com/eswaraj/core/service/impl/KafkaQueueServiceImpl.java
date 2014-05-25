package com.eswaraj.core.service.impl;

import org.springframework.stereotype.Component;

import com.eswaraj.core.exceptions.ApplicationException;
import com.eswaraj.core.service.QueueService;
import com.eswaraj.web.dto.LocationBoundaryFileDto;

@Component
public class KafkaQueueServiceImpl implements QueueService {

	@Override
	public void sendBoundaryfileMessage(LocationBoundaryFileDto locationBoundaryFileDto) throws ApplicationException {
		// TODO Auto-generated method stub

	}

}
