package com.eswaraj.core.convertors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eswaraj.core.exceptions.ApplicationException;
import com.eswaraj.domain.nodes.LocationType;
import com.eswaraj.domain.repo.LocationTypeRepository;
import com.eswaraj.web.dto.LocationTypeDto;

@Component
public class LocationTypeConvertor extends BaseConvertor<LocationType, LocationTypeDto> {

	@Autowired
	private LocationTypeRepository locationTypeRepository;
	

	@Override
	protected LocationType convertInternal(LocationTypeDto locationTypeDto) throws ApplicationException {
		LocationType locationType = getObjectIfExists(locationTypeDto, "LocationType", locationTypeRepository) ;
		if(locationType == null){
			locationType = new LocationType();
		}
		BeanUtils.copyProperties(locationTypeDto, locationType);
		if(locationTypeDto.getParentLocationTypeId() != null){
			LocationType parentLocationType = locationTypeRepository.findOne(locationTypeDto.getParentLocationTypeId());
			if(parentLocationType == null){
				throw new ApplicationException("No such Location type[id="+locationTypeDto.getParentLocationTypeId()+"] found as parent location type");
			}
			locationType.setParentLocationType(parentLocationType);
		}
		return locationType;
	}

	@Override
	protected LocationTypeDto convertBeanInternal(LocationType dbLocationType) {
		LocationTypeDto locationTypeDto = new LocationTypeDto();
		BeanUtils.copyProperties(dbLocationType, locationTypeDto);
		if(dbLocationType.getParentLocationType() != null){
			locationTypeDto.setParentLocationTypeId(dbLocationType.getParentLocationType().getId());
		}
		return locationTypeDto;
	}

}
