package com.eswaraj.web.admin.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.eswaraj.core.exceptions.ApplicationException;
import com.eswaraj.core.service.LocationService;
import com.eswaraj.web.dto.LocationDto;
import com.eswaraj.web.dto.LocationType;

@Controller
public class IndexController {

	@Autowired
	private LocationService locationService;
	
	public IndexController(){
		System.out.println("Constructor Index Controller created");
	}
	
	@PostConstruct
	public void init(){
		System.out.println("Index Controller created");
	}
	
	@RequestMapping(value = "/index.html", method = RequestMethod.GET)
	public ModelAndView showIndexPage(ModelAndView mv) {
		mv.setViewName("index");
		return mv;
	}
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView showIndexsPage(ModelAndView mv) {
		mv.setViewName("index");
		return mv;
	}

	@RequestMapping(value = "/eswaraj/index.html", method = RequestMethod.GET)
	public ModelAndView showIdndexPage(ModelAndView mv) {
		mv.setViewName("index");
		return mv;
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView showIndexsPages(ModelAndView mv) {
		mv.setViewName("index");
		return mv;
	}

	@RequestMapping("/simple")
	public ModelAndView simple(ModelAndView mv) throws ApplicationException {
		LocationDto locationDto = new LocationDto();
		locationDto.setName("India");
		locationDto.setLocationType(LocationType.COUNTRY);
		locationDto = locationService.saveLocation(locationDto);
		mv.getModel().put("locationDto", locationDto);
		mv.setViewName("index");
		return mv;
	}

}
