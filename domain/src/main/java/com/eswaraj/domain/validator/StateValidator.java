package com.eswaraj.domain.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eswaraj.domain.nodes.division.State;
import com.eswaraj.domain.validator.exception.ValidationException;

@Component
public class StateValidator extends BaseValidator<State>{

	@Autowired
	public StateValidator(ValidationManager validationManager) {
		super(State.class, validationManager);
	}

	@Override
	public void validateBeforeSave(State state) throws ValidationException {
		checkIfEmpty("Name", state.getName(),"State name can not be Null or Empty");
		checkIfNull("Country", state.getCountry(),"Country Can not be null for State");
	}

	@Override
	public void validateBeforeDelete(State state) throws ValidationException {
		// TODO Auto-generated method stub
		
	}

	

}
