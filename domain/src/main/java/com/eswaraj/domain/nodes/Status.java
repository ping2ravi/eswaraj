package com.eswaraj.domain.nodes;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.eswaraj.domain.base.BaseNode;

/**
 * Status of the complaint
 * @author anuj
 * @date Jan 18, 2014
 *
 */

@NodeEntity
public class Status extends BaseNode {

	public enum Mode {
		PENDING("Complaint has not been looked upon by an Administrator yet"), 
		ACKNOWLEDGED("Complaint has been looked upon and understood by an Administrator"), 
		QUERY("An Administrator has a query about this complaint"), 
		DUPLICATE("Administrator thinks this is a duplicate of another complaint"), 
		ASSIGNED("Administrator has assigned a task force to this complaint"), 
		IN_PROGRESS("Your complaint is being worked upon"), 
		IN_REVIEW("Your complaint has been worked upon waiting for your review."), 
		DONE("This complaint has been resolved!"), 
		UNFINISHED("This complaint has been neglected far too long. Name and shame time!"),
		ESCLATED("Your complaint has been escalated");
		
		private String description;
		
		Mode(String description) {
			this.description = description;
		}

		public String getDescription() {
			return description;
		}
	}
	
	private Mode mode;

	public Status(){}
	
	public Status(Mode mode) {
		this.mode = mode;
	}

	public Mode getMode() {
		return mode;
	}

	public void setMode(Mode mode) {
		this.mode = mode;
	}
}
