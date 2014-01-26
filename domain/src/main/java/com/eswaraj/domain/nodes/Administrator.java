package com.eswaraj.domain.nodes;

import com.eswaraj.domain.base.BaseNode;

/**
 * Highest responsible person for that particular division
 * @author anuj
 * @date Jan 18, 2014
 *
 */
public class Administrator extends BaseNode {
	
	private Person person;
	private Post post;
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
