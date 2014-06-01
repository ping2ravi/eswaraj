package com.eswaraj.domain.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eswaraj.domain.nodes.division.GeoPoint;
import com.eswaraj.domain.validator.exception.ValidationException;

@Component
public class GeoPointValidator extends BaseValidator<GeoPoint>{

	@Autowired
	public GeoPointValidator(ValidationManager validationManager) {
		super(GeoPoint.class, validationManager);
	}

	public void validateBeforeSave(GeoPoint geoPoint) throws ValidationException {
		if(geoPoint.getBoundary() == null){
			throw new ValidationException("GeoPoint can not be saved without a boundary");
		}
		if(geoPoint.getLattitude() > 90 || geoPoint.getLattitude() < -90){
			throw new ValidationException("GeoPoint Latitude must be between 90 and -90");
		}
		if(geoPoint.getLongitude() > 180 || geoPoint.getLongitude() < -180){
			throw new ValidationException("GeoPoint Longitude must be between 180 and -180");
		}
	}

	public void validateBeforeDelete(GeoPoint geoPoint) throws ValidationException {
		
	}
}
