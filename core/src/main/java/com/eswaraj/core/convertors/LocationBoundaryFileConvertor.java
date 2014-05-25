package com.eswaraj.core.convertors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eswaraj.core.exceptions.ApplicationException;
import com.eswaraj.domain.nodes.Location;
import com.eswaraj.domain.nodes.LocationBoundaryFile;
import com.eswaraj.domain.repo.LocationRepository;
import com.eswaraj.web.dto.LocationBoundaryFileDto;

@Component
public class LocationBoundaryFileConvertor extends BaseConvertor<LocationBoundaryFile, LocationBoundaryFileDto> {

	@Autowired
	private LocationRepository LocationRepository;
	

	@Override
	protected LocationBoundaryFile convertInternal(LocationBoundaryFileDto webDto) throws ApplicationException {
		LocationBoundaryFile LocationBoundaryFile = new LocationBoundaryFile();
		BeanUtils.copyProperties(webDto, LocationBoundaryFile);
		if(webDto.getLocationId() != null){
			Location location = LocationRepository.findOne(webDto.getLocationId());
			if(location == null){
				throw new ApplicationException("No such LocationBoundaryFile exists[id="+webDto.getLocationId()+"]");
			}
			LocationBoundaryFile.setLocation(location);
		}
		return LocationBoundaryFile;
	}

	@Override
	protected LocationBoundaryFileDto convertBeanInternal(LocationBoundaryFile dbDto) {
		LocationBoundaryFileDto locationBoundaryFileDto = new LocationBoundaryFileDto();
		BeanUtils.copyProperties(dbDto, locationBoundaryFileDto);
		if(dbDto.getLocation() != null){
			locationBoundaryFileDto.setLocationId(dbDto.getLocation().getId());	
		}
		return locationBoundaryFileDto;
	}

}
