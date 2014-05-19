package com.eswaraj.web.admin.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author ravi
 * @date Mar 01, 2014
 *
 */
@Controller
public class DepartmentController {

	@RequestMapping(value = "/department.html", method = RequestMethod.GET)
	public ModelAndView login(ModelAndView mv,
			HttpServletRequest httpServletRequest) {
		
		mv.setViewName("categories");
		return mv;
	}

}
