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
		checkIfEmpty("Title", complaint.getTitle(), "Complaint title cannot be empty or null");
		checkIfNull("Category", complaint.getCategory(), "Complaint needs to belong to a category and can't be null.");
		checkIfNull("Person", complaint.getPerson(), "Complaint needs to be lodged by a Person and person can't be null.");
		checkIfNull("GeoPoint", complaint.getGeoPoint(), "Complaint needs to have a geopoint and geopoint can't be null");
	}

	public void validateBeforeDelete(Complaint complaint) throws ValidationException {
	}
}
