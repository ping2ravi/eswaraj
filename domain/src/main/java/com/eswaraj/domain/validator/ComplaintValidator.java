package com.eswaraj.domain.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eswaraj.domain.nodes.Complaint;
import com.eswaraj.domain.validator.exception.ValidationException;

@Component
public class ComplaintValidator extends BaseValidator<Complaint>{

	@Autowired
	public ComplaintValidator(ValidationManager validationManager) {
		super(Complaint.class, validationManager);
	}

	public void validateBeforeSave(Complaint complaint) throws ValidationException {
		System.out.println("ComplaintValidator.validateBeforeSave="+complaint);
	}

	public void validateBeforeDelete(Complaint complaint) throws ValidationException {
		System.out.println("ComplaintValidator.validateBeforeDelete="+complaint);
	}
}
