package com.eswaraj.web.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.eswaraj.core.exceptions.ApplicationException;
import com.eswaraj.core.service.CustomService;
import com.eswaraj.core.service.LocationService;
import com.eswaraj.web.dto.LocationDto;
import com.eswaraj.web.dto.LocationTypeDto;
import com.eswaraj.web.dto.LocationTypeJsonDto;

@Controller
public class LocationController {

	@Autowired
	private LocationService locationService;
	
	@Autowired
	private CustomService customService;
	
	@RequestMapping(value = "/locations", method = RequestMethod.GET)
	public ModelAndView showIndexsPages(ModelAndView mv) {
		mv.setViewName("locationManager");
		return mv;
	}
	@RequestMapping(value="/location/upload/{locationId}", method=RequestMethod.POST)
    public @ResponseBody String handleFileUpload(@RequestParam("name") String name,
            @RequestParam("file") MultipartFile file, @PathVariable Long locationId){
        if (!file.isEmpty()) {
            try {
            	customService.processLocationBoundaryFile(locationId, file.getInputStream());
                return "You successfully uploaded " + name + " into " + name + "-uploaded !";
            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + name + " because the file was empty.";
        }
    }
	
	@RequestMapping(value = "/ajax/locationtype/get", method = RequestMethod.GET)
	public @ResponseBody LocationTypeJsonDto getLocationTypes(ModelAndView mv) throws ApplicationException {
		LocationTypeJsonDto locationTypeJsonDto = locationService.getLocationTypes("beingIgnored");
		return locationTypeJsonDto;
	}
	@RequestMapping(value = "/ajax/locationtype/save", method = RequestMethod.POST)
	public @ResponseBody LocationTypeDto saveLocationTypes(ModelAndView mv, @RequestBody LocationTypeDto locationTypeDto) throws ApplicationException {
		locationTypeDto = locationService.saveLocationType(locationTypeDto);
		return locationTypeDto;
	}

	@RequestMapping(value = "/ajax/location/getroot", method = RequestMethod.GET)
	public @ResponseBody LocationDto getRootLocationNode(ModelAndView mv) throws ApplicationException {
		LocationDto locationDto = locationService.getRootLocationForSwarajIndia();
		return locationDto;
	}
	
	@RequestMapping(value = "/ajax/location/getchild/{parentId}", method = RequestMethod.GET)
	public @ResponseBody List<LocationDto> getChildLocationNode(ModelAndView mv, @PathVariable Long parentId) throws ApplicationException {
		List<LocationDto> locationDtos = locationService.getChildLocationsOfParent(parentId);
		return locationDtos;
	}
	
	@RequestMapping(value = "/ajax/location/save", method = RequestMethod.POST)
	public @ResponseBody LocationDto saveState(ModelAndView mv, @RequestBody LocationDto locationDto) throws ApplicationException {
		locationDto = locationService.saveLocation(locationDto);
		return locationDto;
	}
}
