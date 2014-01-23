package com.eswaraj.domain.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eswaraj.domain.nodes.Person;
import com.eswaraj.domain.validator.exception.ValidationException;

@Component
public class PersonValidator extends BaseValidator<Person>{

	@Autowired
	public PersonValidator(ValidationManager validationManager) {
		super(Person.class, validationManager);
	}

	public void validateBeforeSave(Person person) throws ValidationException {
		checkIfEmpty("Name", person.getName(),"Persons name can not be Null or Empty");
		checkIfEmpty("Email", person.getEmail(),"Person's email cannot be empty or null");
		checkIfNull("Location", person.getLocation(),"Persons location cannot be null");
	}

	public void validateBeforeDelete(Person person) throws ValidationException {
		
	}
}
