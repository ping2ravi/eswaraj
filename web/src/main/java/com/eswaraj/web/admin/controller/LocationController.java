package com.eswaraj.web.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.eswaraj.core.exceptions.ApplicationException;
import com.eswaraj.core.service.LocationService;
import com.eswaraj.web.dto.LocationDto;
import com.eswaraj.web.dto.LocationType;

@Controller
public class LocationController {

	@Autowired
	private LocationService locationService;
	
	@RequestMapping(value = "/locations", method = RequestMethod.GET)
	public ModelAndView showIndexsPages(ModelAndView mv) {
		mv.setViewName("locationManager");
		return mv;
	}
	
	@RequestMapping(value = "/ajax/getroot", method = RequestMethod.GET)
	public @ResponseBody LocationDto getRootLocationNode(ModelAndView mv) throws ApplicationException {
		LocationDto locationDto = locationService.getLocationByNameAndType("India", LocationType.COUNTRY);
		if(locationDto == null){
			locationDto = new LocationDto();
			locationDto.setName("India");
			locationDto.setLocationType(LocationType.COUNTRY);
			locationDto = locationService.saveLocation(locationDto);
		}
		return locationDto;
	}
	
	@RequestMapping(value = "/ajax/getchild/{parentId}", method = RequestMethod.GET)
	public @ResponseBody List<LocationDto> getChildLocationNode(ModelAndView mv, @PathVariable Long parentId) throws ApplicationException {
		List<LocationDto> locationDtos = locationService.getChildLocationsOfParent(parentId);
		return locationDtos;
	}

}
