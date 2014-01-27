package com.eswaraj.domain.nodes;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedToVia;

import com.eswaraj.domain.base.BaseNode;

/**
 * Highest responsible person for that particular division
 * @author anuj
 * @date Jan 18, 2014
 *
 */
@NodeEntity
public class Administrator extends BaseNode {
	
	private Person person;
	private Post post;
	@RelatedToVia(type="SERVED_BY", direction=Direction.INCOMING)
	private Iterable<Complaint> complaints;
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
}
