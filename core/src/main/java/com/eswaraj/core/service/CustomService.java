package com.eswaraj.core.service;

import java.io.InputStream;

import com.eswaraj.core.exceptions.ApplicationException;

public interface CustomService {

	void processLocationBoundaryFile(Long locationId, InputStream inputStream) throws ApplicationException;
}
