package com.eswaraj.domain.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eswaraj.domain.nodes.Party;
import com.eswaraj.domain.validator.exception.ValidationException;

@Component
public class PartyValidator extends BaseValidator<Party>{

	@Autowired
	public PartyValidator(ValidationManager validationManager) {
		super(Party.class, validationManager);
	}

	public void validateBeforeSave(Party party) throws ValidationException {
		checkIfEmpty("Name", party.getName(),"Party name can't be empty");
	}

	public void validateBeforeDelete(Party party) throws ValidationException {
		
	}
}
