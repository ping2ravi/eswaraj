package com.eswaraj.web.dto;

import java.util.List;


/**
 * Location of the complaint
 * @author ravi
 * @date May 30, 2014
 *
 */
public class LocationTypeJsonDto extends LocationTypeDto {

	private static final long serialVersionUID = 1L;
	List<LocationTypeJsonDto> children;
	public List<LocationTypeJsonDto> getChildren() {
		return children;
	}
	public void setChildren(List<LocationTypeJsonDto> children) {
		this.children = children;
	}
	@Override
	public String toString() {
		return "LocationTypeJsonDto [children=" + children + ", toString()=" + super.toString() + "]";
	}
	
	
}
