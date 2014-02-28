package com.eswaraj.domain.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eswaraj.domain.nodes.PoliticalAdministrator;
import com.eswaraj.domain.validator.exception.ValidationException;

@Component
public class PoliticalAdministratorValidator extends BaseValidator<PoliticalAdministrator>{

	@Autowired
	public PoliticalAdministratorValidator(ValidationManager validationManager) {
		super(PoliticalAdministrator.class, validationManager);
	}

	public void validateBeforeSave(PoliticalAdministrator politicalAdministrator) throws ValidationException {
		
	}

	public void validateBeforeDelete(PoliticalAdministrator politicalAdministrator) throws ValidationException {
	}
}
