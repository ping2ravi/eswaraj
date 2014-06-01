package com.eswaraj.core.service;

import java.io.InputStream;
import java.util.List;

import com.eswaraj.core.exceptions.ApplicationException;
import com.eswaraj.web.dto.BoundaryDto;
import com.eswaraj.web.dto.GeoPointDto;
import com.eswaraj.web.dto.LocationBoundaryFileDto;
import com.eswaraj.web.dto.LocationDto;
import com.eswaraj.web.dto.LocationTypeDto;
import com.eswaraj.web.dto.LocationTypeJsonDto;

public interface LocationService {

	
	LocationDto saveLocation(LocationDto locationDto) throws ApplicationException;
	
	LocationDto getLocationById(Long id) throws ApplicationException;
	
	List<LocationDto> getChildLocationsOfParent(Long parentLocationId) throws ApplicationException;
	
	LocationTypeDto saveLocationType(LocationTypeDto locationTypeDto) throws ApplicationException;
	
	LocationTypeJsonDto getLocationTypes(String clientName) throws ApplicationException;
	
	//LocationDto getLocationByNameAndType(String locationName, LocationType locationType) throws ApplicationException;
	
	LocationDto getRootLocationForSwarajIndia() throws ApplicationException;
	
	/**
	 * We are passing the file service from outside as its not possible to inject FileService for testing on proxy implemention
	 * @param locationId
	 * @param inputStream
	 * @param fileService
	 * @return
	 * @throws ApplicationException
	 */
	LocationBoundaryFileDto createNewLocationBoundaryFile(Long locationId, InputStream inputStream, FileService fileService) throws ApplicationException;
	
	BoundaryDto saveBoundary(BoundaryDto boundaryDto) throws ApplicationException;
	
	GeoPointDto saveBoundaryPoint(GeoPointDto geoPointDto) throws ApplicationException;
	
	
}
