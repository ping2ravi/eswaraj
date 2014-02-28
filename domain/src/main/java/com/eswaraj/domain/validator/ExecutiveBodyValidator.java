package com.eswaraj.domain.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eswaraj.domain.nodes.ExecutiveBody;
import com.eswaraj.domain.validator.exception.ValidationException;

@Component
public class ExecutiveBodyValidator extends BaseValidator<ExecutiveBody>{

	@Autowired
	public ExecutiveBodyValidator(ValidationManager validationManager) {
		super(ExecutiveBody.class, validationManager);
	}

	public void validateBeforeSave(ExecutiveBody executiveBody) throws ValidationException {
		
	}

	public void validateBeforeDelete(ExecutiveBody executiveBody) throws ValidationException {
		
	}
}
