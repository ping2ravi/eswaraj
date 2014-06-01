package com.eswaraj.domain.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eswaraj.domain.nodes.LocationType;
import com.eswaraj.domain.validator.exception.ValidationException;
import com.google.gdata.util.common.base.StringUtil;

@Component
public class LocationTypeValidator extends BaseValidator<LocationType>{

	@Autowired
	public LocationTypeValidator(ValidationManager validationManager) {
		super(LocationType.class, validationManager);
	}

	public void validateBeforeSave(LocationType locationType) throws ValidationException {
		if(StringUtil.isEmpty(locationType.getName())){
			throw new ValidationException("LocationType Name can not be null or empty");
		}
		if(locationType.getParentLocationType() == null && locationType.getDataClient() ==null){
			throw new ValidationException("To create a root location type you must provide DataClient");
		}
	}

	public void validateBeforeDelete(LocationType locationType) throws ValidationException {
		
	}
}
