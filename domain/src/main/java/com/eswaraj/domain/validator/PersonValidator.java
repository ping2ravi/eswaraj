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
		System.out.println("PersonValidator.validateBeforeSave="+person);
	}

	public void validateBeforeDelete(Person person) throws ValidationException {
		System.out.println("PersonValidator.validateBeforeDelete="+person);
	}
}
