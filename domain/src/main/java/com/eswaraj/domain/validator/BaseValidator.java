package com.eswaraj.domain.validator;

import com.eswaraj.domain.validator.exception.ValidationException;
import com.google.gdata.util.common.base.StringUtil;

public abstract class BaseValidator<T> {

	public BaseValidator(Class<T> type, ValidationManager validationManager){
		validationManager.registerValidator(type, this);
	}
	
	public abstract void validateBeforeSave(T entity) throws ValidationException;
	
	public abstract void validateBeforeDelete(T entity) throws ValidationException;
	
	protected void checkIfEmpty(String fieldName, String fieldValue) throws ValidationException{
		checkIfEmpty(fieldName, fieldValue, fieldName +" can not be null or empty");
	}
	protected void checkIfEmpty(String fieldName, String fieldValue, String validationMessage) throws ValidationException{
		if(StringUtil.isEmpty(fieldValue)){
			throw new ValidationException(validationMessage);
		}
	}
	protected void checkIfNull(String fieldName, Object fieldValue) throws ValidationException{
		checkIfNull(fieldName, fieldValue, fieldName +" can not be null");
	}
	protected void checkIfNull(String fieldName, Object fieldValue, String validationMessage) throws ValidationException{
		if(fieldValue == null){
			throw new ValidationException(validationMessage);
		}
	}
	
	protected void checkLength(String fieldValue, String validationMessage, int min, int max) throws ValidationException {
		if(fieldValue.length() < min || fieldValue.length() > max)
			throw new ValidationException(validationMessage);
	}
	
	protected void checkAcceptedCharacters(String fieldValue, String validationMessage, String regex) throws ValidationException {
		if(!fieldValue.matches(regex))
			throw new ValidationException(validationMessage);
	}
}
