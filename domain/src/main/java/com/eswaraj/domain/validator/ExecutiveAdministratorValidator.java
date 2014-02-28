package com.eswaraj.domain.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eswaraj.domain.nodes.ExecutiveAdministrator;
import com.eswaraj.domain.validator.exception.ValidationException;

@Component
public class ExecutiveAdministratorValidator extends BaseValidator<ExecutiveAdministrator>{

	@Autowired
	public ExecutiveAdministratorValidator(ValidationManager validationManager) {
		super(ExecutiveAdministrator.class, validationManager);
	}

	public void validateBeforeSave(ExecutiveAdministrator executiveAdministrator) throws ValidationException {
		
	}

	public void validateBeforeDelete(ExecutiveAdministrator executiveAdministrator) throws ValidationException {
	}
}
