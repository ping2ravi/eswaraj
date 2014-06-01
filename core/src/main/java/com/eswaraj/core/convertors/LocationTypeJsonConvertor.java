package com.eswaraj.core.convertors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eswaraj.core.exceptions.ApplicationException;
import com.eswaraj.domain.nodes.LocationType;
import com.eswaraj.domain.repo.LocationTypeRepository;
import com.eswaraj.web.dto.LocationTypeJsonDto;

@Component
public class LocationTypeJsonConvertor extends BaseConvertor<LocationType, LocationTypeJsonDto> {

	@Autowired
	private LocationTypeRepository locationTypeRepository;
	

	@Override
	protected LocationType convertInternal(LocationTypeJsonDto locationTypeJsonDto) throws ApplicationException {
		LocationType locationType = getObjectIfExists(locationTypeJsonDto, "LocationType", locationTypeRepository) ;
		if(locationType == null){
			locationType = new LocationType();
		}
		BeanUtils.copyProperties(locationTypeJsonDto, locationType);
		if(locationTypeJsonDto.getParentLocationTypeId() != null){
			LocationType parentLocationType = locationTypeRepository.findOne(locationTypeJsonDto.getParentLocationTypeId());
			if(parentLocationType == null){
				throw new ApplicationException("No such Location type[id="+locationTypeJsonDto.getParentLocationTypeId()+"] found as parent location type");
			}
			locationType.setParentLocationType(parentLocationType);
		}
		return locationType;
	}

	@Override
	protected LocationTypeJsonDto convertBeanInternal(LocationType dbLocationType) {
		LocationTypeJsonDto locationTypeJsonDto = new LocationTypeJsonDto();
		BeanUtils.copyProperties(dbLocationType, locationTypeJsonDto);
		if(dbLocationType.getParentLocationType() != null){
			locationTypeJsonDto.setParentLocationTypeId(dbLocationType.getParentLocationType().getId());
		}
		return locationTypeJsonDto;
	}
	
	public LocationTypeJsonDto convertToJsonBean(Collection<LocationType> dbLocationTypes) throws ApplicationException{
		LocationTypeJsonDto locationTypeJsonDto = null;
		LocationTypeJsonDto parentLocationTypeJsonDto = null;
		LocationTypeJsonDto rootLocationTypeJsonDto = null;
		Map<Long, LocationTypeJsonDto> locationTypeMapById = new HashMap<Long, LocationTypeJsonDto>();
		Map<Long, List<LocationTypeJsonDto>> childLocationTypeMapById = new HashMap<Long, List<LocationTypeJsonDto>>();
		List<LocationTypeJsonDto> childrenList;
		for(LocationType oneLocationType : dbLocationTypes){
			locationTypeJsonDto = convertBean(oneLocationType);
			//add children list if already created
			locationTypeJsonDto.setChildren(childLocationTypeMapById.get(locationTypeJsonDto.getId()));
			if(locationTypeJsonDto.getParentLocationTypeId() == null){
				rootLocationTypeJsonDto = locationTypeJsonDto;
			}else{ 
				childrenList = null;
				parentLocationTypeJsonDto = locationTypeMapById.get(locationTypeJsonDto.getParentLocationTypeId());
				
				if(parentLocationTypeJsonDto != null){
					childrenList = parentLocationTypeJsonDto.getChildren();
				}
				if(childrenList == null){
					childrenList = new ArrayList<>();
				}
				childrenList.add(locationTypeJsonDto);
				childLocationTypeMapById.put(locationTypeJsonDto.getParentLocationTypeId(), childrenList);
				if(parentLocationTypeJsonDto != null){
					parentLocationTypeJsonDto.setChildren(childrenList);
				}
			}
			locationTypeMapById.put(locationTypeJsonDto.getId(), locationTypeJsonDto);
		}
		return rootLocationTypeJsonDto;
	}

}
