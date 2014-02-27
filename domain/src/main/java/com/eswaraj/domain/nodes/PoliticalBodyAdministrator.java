package com.eswaraj.domain.nodes;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

import com.eswaraj.domain.base.BaseRelationship;

/**
 * Administrators working in a political body
 * @author anuj
 * @data Jan 28, 2014
 */

@RelationshipEntity(type="MEMBER_OF")
public class PoliticalBodyAdministrator extends BaseRelationship {

	@StartNode PoliticalBody politicalBody;
	@EndNode PoliticalAdministrator politicalAdministrator;
	private Post post;
	public PoliticalBodyAdministrator() {}
	
	public PoliticalBodyAdministrator(PoliticalBody executiveBody,PoliticalAdministrator politicalAdministrator, Post post) {
		this.politicalBody = executiveBody;
		this.politicalAdministrator = politicalAdministrator;
		this.post = post;
	}

	public PoliticalBody getPoliticalBody() {
		return politicalBody;
	}

	public void setPoliticalBody(PoliticalBody politicalBody) {
		this.politicalBody = politicalBody;
	}

	public PoliticalAdministrator getPoliticalAdministrator() {
		return politicalAdministrator;
	}

	public void setPoliticalAdministrator(
			PoliticalAdministrator politicalAdministrator) {
		this.politicalAdministrator = politicalAdministrator;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}
}
