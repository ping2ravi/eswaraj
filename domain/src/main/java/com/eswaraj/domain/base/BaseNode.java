package com.eswaraj.domain.base;

import java.util.Date;

import org.springframework.data.neo4j.annotation.GraphId;

public class BaseNode {

	@GraphId
	protected Long id;
	protected Date dateCreated;
	protected Date dateModified;
	protected Long creatorId;
	protected Long modifierId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public Date getDateModified() {
		return dateModified;
	}
	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}
	public Long getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}
	public Long getModifierId() {
		return modifierId;
	}
	public void setModifierId(Long modifierId) {
		this.modifierId = modifierId;
	}
	@Override
	public String toString() {
		return "BaseNode [id=" + id + ", dateCreated=" + dateCreated + ", dateModified=" + dateModified + ", creatorId=" + creatorId + ", modifierId="
				+ modifierId + "]";
	}


}
