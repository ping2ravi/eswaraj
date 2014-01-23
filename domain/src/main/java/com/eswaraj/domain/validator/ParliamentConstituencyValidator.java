package com.eswaraj.domain.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eswaraj.domain.nodes.division.ParliamentConstituency;
import com.eswaraj.domain.validator.exception.ValidationException;

@Component
public class ParliamentConstituencyValidator extends BaseValidator<ParliamentConstituency>{

	@Autowired
	public ParliamentConstituencyValidator(ValidationManager validationManager) {
		super(ParliamentConstituency.class, validationManager);
	}

	@Override
	public void validateBeforeSave(ParliamentConstituency parliamentConstituency) throws ValidationException {
		checkIfEmpty("Name", parliamentConstituency.getName(),"ParliamentConstituency name can not be Null or Empty");
		checkIfNull("State", parliamentConstituency.getState(),"State Can not be null for Parliament Constituency");

	}

	@Override
	public void validateBeforeDelete(ParliamentConstituency parliamentConstituency) throws ValidationException {
		// TODO Auto-generated method stub
		
	}

	

}
