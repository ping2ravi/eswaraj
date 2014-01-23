package com.eswaraj.domain.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eswaraj.domain.nodes.division.District;
import com.eswaraj.domain.validator.exception.ValidationException;

@Component
public class DistrictValidator extends BaseValidator<District>{

	@Autowired
	public DistrictValidator(ValidationManager validationManager) {
		super(District.class, validationManager);
	}

	@Override
	public void validateBeforeSave(District district) throws ValidationException {
		checkIfEmpty("Name", district.getName(),"District name can not be Null or Empty");
		checkIfNull("State", district.getState(),"State Can not be null for District");

	}

	@Override
	public void validateBeforeDelete(District district) throws ValidationException {
		// TODO Auto-generated method stub
		
	}

	

}
