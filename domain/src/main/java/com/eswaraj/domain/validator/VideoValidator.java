package com.eswaraj.domain.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eswaraj.domain.nodes.Video;
import com.eswaraj.domain.validator.exception.ValidationException;

@Component
public class VideoValidator extends BaseValidator<Video>{

	@Autowired
	public VideoValidator(ValidationManager validationManager) {
		super(Video.class, validationManager);
	}

	public void validateBeforeSave(Video video) throws ValidationException {
		checkIfEmpty("OrgUrl", video.getOrgUrl(),"Original video url can't be empty");
	}

	public void validateBeforeDelete(Video video) throws ValidationException {
		
	}
}
