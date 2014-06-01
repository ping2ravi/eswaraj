package com.eswaraj.domain.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eswaraj.domain.nodes.division.Boundary;
import com.eswaraj.domain.validator.exception.ValidationException;

@Component
public class BoundaryValidator extends BaseValidator<Boundary>{

	@Autowired
	public BoundaryValidator(ValidationManager validationManager) {
		super(Boundary.class, validationManager);
	}

	public void validateBeforeSave(Boundary boundary) throws ValidationException {
	}

	public void validateBeforeDelete(Boundary boundary) throws ValidationException {
		
	}
}
