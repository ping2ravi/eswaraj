package com.eswaraj.core.service;

import com.eswaraj.core.exceptions.ApplicationException;
import com.eswaraj.web.dto.LocationBoundaryFileDto;

public interface QueueService {

	void sendBoundaryfileMessage(LocationBoundaryFileDto locationBoundaryFileDto) throws ApplicationException;
}
