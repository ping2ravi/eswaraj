package com.eswaraj.domain.nodes.different;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

import com.eswaraj.domain.base.BaseRelationship;

/**
 * Administrators working in an executive body
 * @author anuj
 * @data Jan 28, 2014
 */

@RelationshipEntity(type="WORKS_FOR")
public class ExecutiveBodyAdministrator extends BaseRelationship {

	@StartNode ExecutiveBody executiveBody;
	@EndNode ExecutiveAdministrator executiveAdministrator;
	private Post post;

	public ExecutiveBodyAdministrator() {}
	
	public ExecutiveBodyAdministrator(ExecutiveBody executiveBody, ExecutiveAdministrator executiveAdministrator, Post post) {
		this.executiveBody = executiveBody;
		this.executiveAdministrator = executiveAdministrator;
		this.post = post;
	}
	public ExecutiveBody getExecutiveBody() {
		return executiveBody;
	}
	public void setExecutiveBody(ExecutiveBody executiveBody) {
		this.executiveBody = executiveBody;
	}
	public ExecutiveAdministrator getExecutiveAdministrator() {
		return executiveAdministrator;
	}
	public void setExecutiveAdministrator(
			ExecutiveAdministrator executiveAdministrator) {
		this.executiveAdministrator = executiveAdministrator;
	}
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
}
