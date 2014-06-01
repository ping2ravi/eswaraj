package com.eswaraj.domain.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eswaraj.domain.nodes.Location;
import com.eswaraj.domain.validator.exception.ValidationException;
import com.google.gdata.util.common.base.StringUtil;

@Component
public class LocationValidator extends BaseValidator<Location>{

	@Autowired
	public LocationValidator(ValidationManager validationManager) {
		super(Location.class, validationManager);
	}

	public void validateBeforeSave(Location location) throws ValidationException {
		if(StringUtil.isEmpty(location.getName())){
			throw new ValidationException("Location Name can not be null");
		}
		if(location.getLocationType() == null){
			throw new ValidationException("Location Type can not be null");
		}
	}

	public void validateBeforeDelete(Location location) throws ValidationException {
		
	}
}
