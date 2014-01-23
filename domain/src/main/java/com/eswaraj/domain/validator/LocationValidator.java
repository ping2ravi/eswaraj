package com.eswaraj.domain.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eswaraj.domain.nodes.Location;
import com.eswaraj.domain.validator.exception.ValidationException;

@Component
public class LocationValidator extends BaseValidator<Location>{

	@Autowired
	public LocationValidator(ValidationManager validationManager) {
		super(Location.class, validationManager);
	}

	public void validateBeforeSave(Location location) throws ValidationException {
		
	}

	public void validateBeforeDelete(Location location) throws ValidationException {
		
	}
}
