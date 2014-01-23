package com.eswaraj.domain.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eswaraj.domain.nodes.Photo;
import com.eswaraj.domain.validator.exception.ValidationException;

@Component
public class PhotoValidator extends BaseValidator<Photo>{

	@Autowired
	public PhotoValidator(ValidationManager validationManager) {
		super(Photo.class, validationManager);
	}

	public void validateBeforeSave(Photo photo) throws ValidationException {
		checkIfEmpty("OrgUrl", photo.getOrgUrl(),"Original photo url can't be empty");
	}

	public void validateBeforeDelete(Photo photo) throws ValidationException {
		
	}
}
