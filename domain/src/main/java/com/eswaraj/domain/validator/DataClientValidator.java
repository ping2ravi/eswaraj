package com.eswaraj.domain.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eswaraj.domain.nodes.DataClient;
import com.eswaraj.domain.validator.exception.ValidationException;
import com.google.gdata.util.common.base.StringUtil;

@Component
public class DataClientValidator extends BaseValidator<DataClient>{

	@Autowired
	public DataClientValidator(ValidationManager validationManager) {
		super(DataClient.class, validationManager);
	}

	public void validateBeforeSave(DataClient dataClient) throws ValidationException {
		if(StringUtil.isEmpty(dataClient.getName())){
			throw new ValidationException("Data Client Name can not be null or empty");
		}
	}

	public void validateBeforeDelete(DataClient locationType) throws ValidationException {
		
	}
}
