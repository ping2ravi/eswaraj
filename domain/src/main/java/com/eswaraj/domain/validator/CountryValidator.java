package com.eswaraj.domain.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eswaraj.domain.nodes.division.Country;
import com.eswaraj.domain.validator.exception.ValidationException;

@Component
public class CountryValidator extends BaseValidator<Country>{

	@Autowired
	public CountryValidator(ValidationManager validationManager) {
		super(Country.class, validationManager);
	}

	@Override
	public void validateBeforeSave(Country country) throws ValidationException {
		checkIfEmpty("Name", country.getName(),"Country name can not be Null or Empty");
		
	}

	@Override
	public void validateBeforeDelete(Country country) throws ValidationException {
		// TODO Auto-generated method stub
		
	}

	

}
