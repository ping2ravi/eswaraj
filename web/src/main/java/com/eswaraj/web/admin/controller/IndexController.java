package com.eswaraj.web.admin.controller;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

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

}
