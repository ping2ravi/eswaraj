package com.eswaraj.core.service;

import java.util.List;

import com.eswaraj.core.exceptions.ApplicationException;
import com.eswaraj.web.dto.LocationDto;

public interface LocationService {

	
	LocationDto saveLocation(LocationDto locationDto) throws ApplicationException;
	
	LocationDto getLocationById(Long id) throws ApplicationException;
	
	List<LocationDto> getChildLocationsOfParent(Long parentLocationId) throws ApplicationException;
	
}
