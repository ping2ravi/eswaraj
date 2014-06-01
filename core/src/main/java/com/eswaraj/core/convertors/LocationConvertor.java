package com.eswaraj.core.convertors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eswaraj.core.exceptions.ApplicationException;
import com.eswaraj.domain.nodes.Location;
import com.eswaraj.domain.nodes.LocationType;
import com.eswaraj.domain.repo.LocationRepository;
import com.eswaraj.domain.repo.LocationTypeRepository;
import com.eswaraj.web.dto.LocationDto;

@Component
public class LocationConvertor extends BaseConvertor<Location, LocationDto> {

	@Autowired
	private LocationRepository locationRepository;
	@Autowired
	private LocationTypeRepository locationTypeRepository;

	@Override
	protected Location convertInternal(LocationDto webDto) throws ApplicationException {
		Location location = getObjectIfExists(webDto, "Location", locationRepository) ;
		if(location == null){
			location = new Location();
		}
		BeanUtils.copyProperties(webDto, location);
		if(webDto.getParentLocationId() != null){
			Location parentLocation = locationRepository.findOne(webDto.getParentLocationId());
			if(parentLocation == null){
				throw new ApplicationException("No such Location exists[id="+webDto.getParentLocationId()+"]");
			}
			location.setParentLocation(parentLocation);
		}
		if(webDto.getLocationTypeId() != null){
			LocationType locationType = locationTypeRepository.findOne(webDto.getLocationTypeId());
			location.setLocationType(locationType);
		}
		return location;
	}

	@Override
	protected LocationDto convertBeanInternal(Location dbDto) {
		LocationDto locationDto = new LocationDto();
		BeanUtils.copyProperties(dbDto, locationDto);
		if(dbDto.getParentLocation() != null){
			locationDto.setParentLocationId(dbDto.getParentLocation().getId());	
		}
		if(dbDto.getLocationType() != null){
			locationDto.setLocationTypeId(dbDto.getLocationType().getId());
		}
		return locationDto;
	}

}
