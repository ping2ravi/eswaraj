package com.eswaraj.domain.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eswaraj.domain.nodes.Person;
import com.eswaraj.domain.validator.exception.ValidationException;

@Component
public class LocationValidator extends BaseValidator<Person>{

	@Autowired
	public LocationValidator(ValidationManager validationManager) {
		super(Person.class, validationManager);
	}

	public void validateBeforeSave(Person person) throws ValidationException {
		
	}

	public void validateBeforeDelete(Person person) throws ValidationException {
		
	}
}
