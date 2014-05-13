package com.eswaraj.core.convertors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eswaraj.core.exceptions.ApplicationException;
import com.eswaraj.domain.nodes.Location;
import com.eswaraj.domain.repo.LocationRepository;
import com.eswaraj.web.dto.LocationDto;

@Component
public class LocationConvertor extends BaseConvertor<Location, LocationDto> {

	@Autowired
	private LocationRepository locationRepository;
	

	@Override
	protected Location convertInternal(LocationDto webDto) throws ApplicationException {
		Location location = new Location();
		BeanUtils.copyProperties(webDto, location);
		if(webDto.getParentLocationId() != null){
			Location parentLocation = locationRepository.findOne(webDto.getParentLocationId());
			if(parentLocation == null){
				throw new ApplicationException("No such Location exists[id="+webDto.getParentLocationId()+"]");
			}
			location.setParentLocation(parentLocation);
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
		return locationDto;
	}

}
