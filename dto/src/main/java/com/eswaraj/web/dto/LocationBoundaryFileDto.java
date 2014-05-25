package com.eswaraj.web.dto;

import java.util.Date;

/**
 * Boundary file of a Location
 * @author ravi
 * @date May 25, 2014
 *
 */
public class LocationBoundaryFileDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fileNameAndPath;
	private Long locationId;
	private Date uploadDate;
	private String status;
	
	public String getFileNameAndPath() {
		return fileNameAndPath;
	}
	public void setFileNameAndPath(String fileNameAndPath) {
		this.fileNameAndPath = fileNameAndPath;
	}
	public Long getLocationId() {
		return locationId;
	}
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
	public Date getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
