package com.eswaraj.core.service;

import java.util.List;

import com.eswaraj.core.exceptions.ApplicationException;
import com.eswaraj.web.dto.LocationDto;
import com.eswaraj.web.dto.LocationType;

public interface LocationService {

	
	LocationDto saveLocation(LocationDto locationDto) throws ApplicationException;
	
	LocationDto getLocationById(Long id) throws ApplicationException;
	
	List<LocationDto> getChildLocationsOfParent(Long parentLocationId) throws ApplicationException;
	
	LocationDto getLocationByNameAndType(String locationName, LocationType locationType) throws ApplicationException;
	
}
