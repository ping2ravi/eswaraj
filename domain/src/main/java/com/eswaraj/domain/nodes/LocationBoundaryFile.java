package com.eswaraj.domain.nodes;

import java.util.Date;

import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

import com.eswaraj.domain.base.BaseNode;

/**
 * Boundary file of a Location
 * @author ravi
 * @date May 25, 2014
 *
 */
@NodeEntity
public class LocationBoundaryFile extends BaseNode {

	@Indexed
	private String fileNameAndPath;
	private Location location;
	private Date uploadDate;
	private String status;
	
	public String getFileNameAndPath() {
		return fileNameAndPath;
	}
	public void setFileNameAndPath(String fileNameAndPath) {
		this.fileNameAndPath = fileNameAndPath;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
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
