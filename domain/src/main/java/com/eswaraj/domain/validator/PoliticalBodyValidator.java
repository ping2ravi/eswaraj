package com.eswaraj.domain.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eswaraj.domain.nodes.PoliticalBody;
import com.eswaraj.domain.validator.exception.ValidationException;

@Component
public class PoliticalBodyValidator extends BaseValidator<PoliticalBody>{

	@Autowired
	public PoliticalBodyValidator(ValidationManager validationManager) {
		super(PoliticalBody.class, validationManager);
	}

	public void validateBeforeSave(PoliticalBody politicalBody) throws ValidationException {
		
	}

	public void validateBeforeDelete(PoliticalBody politicalBody) throws ValidationException {
	}
}
