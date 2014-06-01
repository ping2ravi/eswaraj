package com.eswaraj.core.service.impl;

import java.io.InputStream;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.eswaraj.core.convertors.LocationBoundaryFileConvertor;
import com.eswaraj.core.convertors.LocationConvertor;
import com.eswaraj.core.convertors.LocationTypeConvertor;
import com.eswaraj.core.convertors.LocationTypeJsonConvertor;
import com.eswaraj.core.exceptions.ApplicationException;
import com.eswaraj.core.service.FileService;
import com.eswaraj.core.service.LocationService;
import com.eswaraj.core.util.DateTimeUtil;
import com.eswaraj.domain.nodes.DataClient;
import com.eswaraj.domain.nodes.Location;
import com.eswaraj.domain.nodes.LocationBoundaryFile;
import com.eswaraj.domain.nodes.LocationType;
import com.eswaraj.domain.repo.DataClientRepository;
import com.eswaraj.domain.repo.LocationBoundaryFileRepository;
import com.eswaraj.domain.repo.LocationRepository;
import com.eswaraj.domain.repo.LocationTypeRepository;
import com.eswaraj.web.dto.BoundaryDto;
import com.eswaraj.web.dto.GeoPointDto;
import com.eswaraj.web.dto.LocationBoundaryFileDto;
import com.eswaraj.web.dto.LocationDto;
import com.eswaraj.web.dto.LocationTypeDto;
import com.eswaraj.web.dto.LocationTypeJsonDto;

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
	@Autowired
	private LocationTypeRepository locationTypeRepository;
	@Autowired
	private LocationTypeConvertor locationTypeConvertor;
	@Autowired
	private LocationTypeJsonConvertor locationTypeJsonConvertor;
	@Autowired
	private DataClientRepository dataClientRepository;
	
	private String baseDirectoryForlocationFiles = "/tmp";
	private String indiaEswarajClientName = "Eswaraj-India";
	private String indiaEswarajRootLocationTypeName = "Country";
	

	@Override
	public LocationDto saveLocation(LocationDto locationDto)  throws ApplicationException{
		Location location = locationConvertor.convert(locationDto);
		//Check Parent child rule
		checkParentChildRule(location);
		locationRepository.save(location);
		return locationConvertor.convertBean(location);
	}
	private void checkParentChildRule(Location location) throws ApplicationException{
		//LocationType parentLocationType = locationTypeRepository.findOne(location.getParentLocation().getLocationType().getId());
		if(location.getParentLocation() == null){
			if(location.getLocationType().getParentLocationType() != null){
				throw new ApplicationException("Can not create a Location of type ["+location.getLocationType().getName()+"], without a parent Location");
			}
		}else{
			Location parentLocation = location.getParentLocation();
			if(!location.getLocationType().getParentLocationType().getId().equals(parentLocation.getLocationType().getId())){
				LocationType parentLocationType = locationTypeRepository.findOne(parentLocation.getLocationType().getId());
				throw new ApplicationException("Can not create a Location of type ["+location.getLocationType().getName()+"], under location type ["+parentLocationType.getName()+"]");
			}
		}
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

	@Override
	public BoundaryDto saveBoundary(BoundaryDto boundaryDto) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GeoPointDto saveBoundaryPoint(GeoPointDto geoPointDto) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}
	
	private DataClient getOrCreateDataClientIndiaEswaraj(){
		DataClient dataClient = dataClientRepository.getDataClientByName(indiaEswarajClientName);
		if(dataClient == null){
			dataClient = new DataClient();
			dataClient.setName(indiaEswarajClientName);
			dataClient = dataClientRepository.save(dataClient);
		}
		return dataClient;
	}
	private LocationType getOrCreateRootLocationTypeIndiaEswaraj(DataClient dataClient){
		LocationType locationType = locationTypeRepository.getRootLocationTypeByDataClient(dataClient.getName());
		if(locationType == null){
			locationType = new LocationType();
			locationType.setName(indiaEswarajRootLocationTypeName);
			locationType.setDataClient(dataClient);
			locationType = locationTypeRepository.save(locationType);
		}
		return locationType;
	}
	
	

	private void checkIfRootLocationAlreadyExists(LocationType locationType, DataClient dataClient) throws ApplicationException {
		if(locationType.getParentLocationType() == null){
			//now check if one ROOT Location already exists for this Data client
			//If yes then throw exception as we can not have more then one Root Location(usually country) for a Data client
			LocationType existingRootLocationType = locationTypeRepository.getRootLocationTypeByDataClient(dataClient.getName());
			if(existingRootLocationType != null){
				throw new ApplicationException("One root location type already exists["+ existingRootLocationType.getName()+","+existingRootLocationType.getId()+"], you can not create more then one root location Type. If you are trying to create child location type then make sure you set parentLocationType");
			}
		}
	}
	@Override
	public LocationTypeDto saveLocationType(LocationTypeDto locationTypeDto) throws ApplicationException {
		LocationType locationType = locationTypeConvertor.convert(locationTypeDto);
		//get the data Client, right now its hard coded for eswaraj-India, in future we will get it from client
		DataClient dataClient = getOrCreateDataClientIndiaEswaraj();
		locationType.setDataClient(dataClient);
		if(locationType.getId() == null){
			//means we are trying to create new location type
			checkIfRootLocationAlreadyExists(locationType, dataClient);
		}
		locationType = locationTypeRepository.save(locationType);
		
		return locationTypeConvertor.convertBean(locationType);
	}

	@Override
	public LocationDto getRootLocationForSwarajIndia() throws ApplicationException {
		DataClient dataClient = getOrCreateDataClientIndiaEswaraj();
		LocationType locationType = getOrCreateRootLocationTypeIndiaEswaraj(dataClient);
		Location location = locationRepository.getRootLocationByLocationType(locationType.getId());
		if(location == null){
			//create default location India
			location = new Location();
			location.setLocationType(locationType);
			location.setName("India");
			location = locationRepository.save(location);
		}
		return locationConvertor.convertBean(location);
	}

	@Override
	public LocationTypeJsonDto getLocationTypes(String clientName) throws ApplicationException {
		//Ignoring passed client name and will use hardcoded client name
		DataClient dataClient = getOrCreateDataClientIndiaEswaraj();
		Collection<LocationType> locationTypes = locationTypeRepository.getAllLocationTypeOfDataClient(dataClient.getId());
		return locationTypeJsonConvertor.convertToJsonBean(locationTypes);
	}

}
