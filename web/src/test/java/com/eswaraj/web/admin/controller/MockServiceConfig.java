package com.eswaraj.web.admin.controller;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.eswaraj.core.service.LocationService;

@Configuration
public class MockServiceConfig {

	@Bean
	public LocationService locationService() {
		return Mockito.mock(LocationService.class);
	}

}
