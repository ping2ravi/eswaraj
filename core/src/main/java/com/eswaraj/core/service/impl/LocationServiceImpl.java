package com.eswaraj.core.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.eswaraj.core.convertors.LocationConvertor;
import com.eswaraj.core.exceptions.ApplicationException;
import com.eswaraj.core.service.LocationService;
import com.eswaraj.domain.nodes.Location;
import com.eswaraj.domain.repo.LocationRepository;
import com.eswaraj.web.dto.LocationDto;
import com.eswaraj.web.dto.LocationType;

@Component
@Transactional
public class LocationServiceImpl implements LocationService {

	@Autowired 
	private LocationRepository locationRepository;
	@Autowired
	private LocationConvertor locationConvertor;
	
	public LocationServiceImpl() {
	}
	
	private <T> T getObjectIfExists(Long id, String objectName, GraphRepository<T> repository) throws ApplicationException{
		T dbObject = null;
		if(id == null || id <= 0){
			return dbObject;
		}else{
			dbObject = repository.findOne(id);
			if(dbObject == null){
				throw new ApplicationException("No such location exists[id="+ id +"]");
			}
		}
		
		return dbObject;
	}

	@Override
	public LocationDto saveLocation(LocationDto locationDto)  throws ApplicationException{
		Location location = getObjectIfExists(locationDto.getId(), "Location", locationRepository);
		if(location == null){
			location = new Location();
			locationDto.setId(null);
		}
		location = locationConvertor.convert(locationDto);
		locationRepository.save(location);
		return locationConvertor.convertBean(location);
	}

	@Override
	public LocationDto getLocationById(Long id)  throws ApplicationException{
		Location dbLocation = locationRepository.findOne(id);
		return locationConvertor.convertBean(dbLocation);
	}

	@Override
	public List<LocationDto> getChildLocationsOfParent(Long parentLocationId)  throws ApplicationException{
		Location parenLocation = locationRepository.findOne(parentLocationId);
		Collection<Location> childLocations = locationRepository.findLocationByParentLocation(parenLocation);
		return locationConvertor.convertBeanList(childLocations);
	}

	@Override
	public LocationDto getLocationByNameAndType(String locationName, LocationType locationType) throws ApplicationException {
		Location location = locationRepository.getLocationByNameAndType(locationName, locationType);
		return locationConvertor.convertBean(location);
	}

}
