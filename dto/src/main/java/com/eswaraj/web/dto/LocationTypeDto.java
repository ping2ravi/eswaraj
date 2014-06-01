package com.eswaraj.web.dto;


/**
 * Location of the complaint
 * @author ravi
 * @date May 30, 2014
 *
 */
public class LocationTypeDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private Long parentLocationTypeId;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getParentLocationTypeId() {
		return parentLocationTypeId;
	}

	public void setParentLocationTypeId(Long parentLocationTypeId) {
		this.parentLocationTypeId = parentLocationTypeId;
	}

	@Override
	public String toString() {
		return "LocationTypeDto [name=" + name + ", parentLocationTypeId=" + parentLocationTypeId + ", getId()=" + getId() + "]";
	}

	
}
