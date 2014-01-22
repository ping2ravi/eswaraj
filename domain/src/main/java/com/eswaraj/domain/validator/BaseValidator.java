package com.eswaraj.domain.validator;

import com.eswaraj.domain.validator.exception.ValidationException;

public abstract class BaseValidator<T> {

	public BaseValidator(Class<T> type, ValidationManager validationManager){
		validationManager.registerValidator(type, this);
	}
	
	public abstract void validateBeforeSave(T entity) throws ValidationException;
	
	public abstract void validateBeforeDelete(T entity) throws ValidationException;
	
}
