package com.eswaraj.core.service.impl;

import java.io.InputStream;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.eswaraj.core.convertors.LocationBoundaryFileConvertor;
import com.eswaraj.core.convertors.LocationConvertor;
import com.eswaraj.core.exceptions.ApplicationException;
import com.eswaraj.core.service.FileService;
import com.eswaraj.core.service.LocationService;
import com.eswaraj.core.util.DateTimeUtil;
import com.eswaraj.domain.nodes.Location;
import com.eswaraj.domain.nodes.LocationBoundaryFile;
import com.eswaraj.domain.repo.LocationBoundaryFileRepository;
import com.eswaraj.domain.repo.LocationRepository;
import com.eswaraj.web.dto.LocationBoundaryFileDto;
import com.eswaraj.web.dto.LocationDto;
import com.eswaraj.web.dto.LocationType;

@Component
@Transactional
public class LocationServiceImpl implements LocationService {

	@Autowired
	private DateTimeUtil dateTimeUtil;
	@Autowired 
	private LocationRepository locationRepository;
	@Autowired
	private LocationConvertor locationConvertor;
	@Autowired
	private LocationBoundaryFileRepository locationBoundaryFileRepository;
	@Autowired
	private LocationBoundaryFileConvertor locationBoundaryFileConvertor;
	
	private String baseDirectoryForlocationFiles = "/tmp";
	
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

	@Override
	public LocationBoundaryFileDto createNewLocationBoundaryFile(Long locationId, InputStream inputStream, FileService fileService) throws ApplicationException {
		Location location = locationRepository.findOne(locationId);
		if(location == null){
			throw new ApplicationException("No such location exists[id="+locationId+"]");
		}
		String currenttime = dateTimeUtil.getCurrentTimeYYYYMMDDHHMMSS();
		String fileName = UUID.randomUUID().toString()+"_"+currenttime;
        //save file to a storage
		fileService.saveFile(baseDirectoryForlocationFiles, fileName, inputStream);

		//create LocationBoudaryFile
		LocationBoundaryFile locationBoundaryFile = new LocationBoundaryFile();
		locationBoundaryFile.setLocation(location);
		locationBoundaryFile.setFileNameAndPath(baseDirectoryForlocationFiles + fileName);
		locationBoundaryFile.setStatus("Pending");
		locationBoundaryFile.setUploadDate(new Date());
		
		locationBoundaryFile = locationBoundaryFileRepository.save(locationBoundaryFile);

		return locationBoundaryFileConvertor.convertBean(locationBoundaryFile);
	}

}
