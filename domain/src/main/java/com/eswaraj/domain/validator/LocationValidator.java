package com.eswaraj.domain.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eswaraj.domain.nodes.Location;
import com.eswaraj.domain.validator.exception.ValidationException;
import com.eswaraj.web.dto.LocationType;
import com.google.gdata.util.common.base.StringUtil;

@Component
public class LocationValidator extends BaseValidator<Location>{

	@Autowired
	public LocationValidator(ValidationManager validationManager) {
		super(Location.class, validationManager);
	}

	public void validateBeforeSave(Location location) throws ValidationException {
		if(StringUtil.isEmpty(location.getName())){
			throw new ValidationException("Location Name can not be null");
		}
		if(location.getLocationType() == null){
			throw new ValidationException("Location Type can not be null");
		}
		if(location.getParentLocation() == null && location.getLocationType() != LocationType.COUNTRY){
			throw new ValidationException("Location must have a parent, only country can be created without parent location");
		}
		if(location.getLocationType() == LocationType.STATE && location.getParentLocation().getLocationType() != LocationType.COUNTRY){
			throw new ValidationException("State can be created under a country only");
		}
		if(location.getLocationType() == LocationType.DISTRICT && location.getParentLocation().getLocationType() != LocationType.STATE){
			throw new ValidationException("District can be created under a State only");
		}
		if(location.getLocationType() == LocationType.PARLIAMENT_CONSTITUENCY && location.getParentLocation().getLocationType() != LocationType.STATE){
			throw new ValidationException("Parliament Constituency can be created under a State only");
		}
		if(location.getLocationType() == LocationType.ASSEMBLY_CONSTITUENCY && location.getParentLocation().getLocationType() != LocationType.PARLIAMENT_CONSTITUENCY){
			throw new ValidationException("Assembly Constituency can be created under a District only");
		}
		if(location.getLocationType() == LocationType.CITY && location.getParentLocation().getLocationType() != LocationType.DISTRICT){
			throw new ValidationException("City can be created under a District only");
		}
		if(location.getLocationType() == LocationType.MUNCIPLE && location.getParentLocation().getLocationType() != LocationType.ASSEMBLY_CONSTITUENCY){
			throw new ValidationException("Munciple can be created under a Assembly Constituency only");
		}
		if(location.getLocationType() == LocationType.WARD && location.getParentLocation().getLocationType() != LocationType.MUNCIPLE){
			throw new ValidationException("Ward can be created under a Munciple only");
		}
	}

	public void validateBeforeDelete(Location location) throws ValidationException {
		
	}
}
