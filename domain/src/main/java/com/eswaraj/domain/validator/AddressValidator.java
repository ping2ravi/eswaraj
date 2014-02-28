package com.eswaraj.domain.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eswaraj.domain.nodes.Address;
import com.eswaraj.domain.validator.exception.ValidationException;

@Component
public class AddressValidator extends BaseValidator<Address>{

	@Autowired
	public AddressValidator(ValidationManager validationManager) {
		super(Address.class, validationManager);
	}

	public void validateBeforeSave(Address address) throws ValidationException {
		
	}

	public void validateBeforeDelete(Address address) throws ValidationException {
	}
}
