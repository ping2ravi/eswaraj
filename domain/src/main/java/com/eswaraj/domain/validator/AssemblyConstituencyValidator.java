package com.eswaraj.domain.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eswaraj.domain.nodes.division.AssemblyConstituency;
import com.eswaraj.domain.validator.exception.ValidationException;

@Component
public class AssemblyConstituencyValidator extends BaseValidator<AssemblyConstituency>{

	@Autowired
	public AssemblyConstituencyValidator(ValidationManager validationManager) {
		super(AssemblyConstituency.class, validationManager);
	}

	@Override
	public void validateBeforeSave(AssemblyConstituency assemblyConstituency) throws ValidationException {
		checkIfEmpty("Name", assemblyConstituency.getName(),"AssemblyConstituency name can not be Null or Empty");
		checkIfNull("District", assemblyConstituency.getDistrict(),"District Can not be null for Assembly Constituency");
		checkIfNull("Parliament Constituency", assemblyConstituency.getParliamentConstituency(),"District Can not be null for Assembly Constituency");
		
	}

	@Override
	public void validateBeforeDelete(AssemblyConstituency assemblyConstituency) throws ValidationException {
		// TODO Auto-generated method stub
		
	}

	

}
