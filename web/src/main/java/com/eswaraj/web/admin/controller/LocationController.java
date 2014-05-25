package com.eswaraj.web.admin.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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
import com.eswaraj.web.dto.LocationType;

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
            	/*
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(name + "-uploaded")));
                stream.write(bytes);
                stream.close();
                */
                return "You successfully uploaded " + name + " into " + name + "-uploaded !";
            } catch (Exception e) {
                return "You failed to upload " + name + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload " + name + " because the file was empty.";
        }
    }
	
	@RequestMapping(value = "/ajax/location/getroot", method = RequestMethod.GET)
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
	
	@RequestMapping(value = "/ajax/location/getchild/{parentId}", method = RequestMethod.GET)
	public @ResponseBody List<LocationDto> getChildLocationNode(ModelAndView mv, @PathVariable Long parentId) throws ApplicationException {
		List<LocationDto> locationDtos = locationService.getChildLocationsOfParent(parentId);
		return locationDtos;
	}
	
	@RequestMapping(value = "/ajax/location/state/save", method = RequestMethod.POST)
	public @ResponseBody LocationDto saveState(ModelAndView mv, @RequestBody LocationDto locationDto) throws ApplicationException {
		locationDto.setLocationType(LocationType.STATE);
		locationDto = locationService.saveLocation(locationDto);
		return locationDto;
	}
	@RequestMapping(value = "/ajax/location/district/save", method = RequestMethod.POST, consumes="application/json")
	public @ResponseBody LocationDto saveDistrict(ModelAndView mv, @RequestBody LocationDto locationDto) throws ApplicationException {
		locationDto.setLocationType(LocationType.DISTRICT);
		locationDto = locationService.saveLocation(locationDto);
		return locationDto;
	}
	@RequestMapping(value = "/ajax/location/ac/save", method = RequestMethod.POST)
	public @ResponseBody LocationDto saveAc(ModelAndView mv, @RequestBody LocationDto locationDto) throws ApplicationException {
		locationDto.setLocationType(LocationType.ASSEMBLY_CONSTITUENCY);
		locationDto = locationService.saveLocation(locationDto);
		return locationDto;
	}
	@RequestMapping(value = "/ajax/location/city/save", method = RequestMethod.POST)
	public @ResponseBody LocationDto saveCity(ModelAndView mv, @RequestBody LocationDto locationDto) throws ApplicationException {
		locationDto.setLocationType(LocationType.CITY);
		locationDto = locationService.saveLocation(locationDto);
		return locationDto;
	}
	@RequestMapping(value = "/ajax/location/localarea/save", method = RequestMethod.POST)
	public @ResponseBody LocationDto saveLocalArea(ModelAndView mv, @RequestBody LocationDto locationDto) throws ApplicationException {
		locationDto.setLocationType(LocationType.LOCAL_AREA);
		locationDto = locationService.saveLocation(locationDto);
		return locationDto;
	}
	@RequestMapping(value = "/ajax/location/munciple/save", method = RequestMethod.POST)
	public @ResponseBody LocationDto saveMunciple(ModelAndView mv, @RequestBody LocationDto locationDto) throws ApplicationException {
		locationDto.setLocationType(LocationType.MUNCIPLE);
		locationDto = locationService.saveLocation(locationDto);
		return locationDto;
	}
	@RequestMapping(value = "/ajax/location/pc/save", method = RequestMethod.POST)
	public @ResponseBody LocationDto savePc(ModelAndView mv, @RequestBody LocationDto locationDto) throws ApplicationException {
		locationDto.setLocationType(LocationType.PARLIAMENT_CONSTITUENCY);
		locationDto = locationService.saveLocation(locationDto);
		return locationDto;
	}
	@RequestMapping(value = "/ajax/location/village/save", method = RequestMethod.POST)
	public @ResponseBody LocationDto saveVillage(ModelAndView mv, @RequestBody LocationDto locationDto) throws ApplicationException {
		locationDto.setLocationType(LocationType.VILLAGE);
		locationDto = locationService.saveLocation(locationDto);
		return locationDto;
	}
	@RequestMapping(value = "/ajax/location/ward/save", method = RequestMethod.POST)
	public @ResponseBody LocationDto saveWard(ModelAndView mv, @RequestBody LocationDto locationDto) throws ApplicationException {
		locationDto.setLocationType(LocationType.WARD);
		locationDto = locationService.saveLocation(locationDto);
		return locationDto;
	}

}
