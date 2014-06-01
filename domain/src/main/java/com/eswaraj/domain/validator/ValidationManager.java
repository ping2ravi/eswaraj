package com.eswaraj.domain.validator;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.lifecycle.BeforeSaveEvent;
import org.springframework.stereotype.Component;

@Component
public class ValidationManager extends Neo4jConfiguration {
   
	//@SuppressWarnings("rawtypes")
	Map<Class, BaseValidator> validators = new HashMap<Class, BaseValidator>(); 
	
	public <T> void registerValidator(Class<T> type, BaseValidator<T> baseValidator){
		//System.out.println("registerValidator = "+baseValidator+", "+baseValidator.getClass());
		validators.put(type, baseValidator);
	}
	
	public <T> void validateBeforeSave(BeforeSaveEvent<T> beforeSaveEvent){
		//System.out.println("ValidationManager.BeforeSaveEvent "+beforeSaveEvent.getEntity());
		BaseValidator validator = validators.get(beforeSaveEvent.getEntity().getClass());
		//System.out.println("validator = "+validator);
		if(validator != null){
			validator.validateBeforeSave(beforeSaveEvent.getEntity());
		}
	}
}
