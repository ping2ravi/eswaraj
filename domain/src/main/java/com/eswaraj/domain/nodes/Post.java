package com.eswaraj.domain.nodes;

import java.util.Date;


/**
 * Post of the administrator
 * @author anuj
 * @date Jan 18, 2014
 *
 */
public class Post {

	private String title;
	private Date joined;
	private Date left;
	
	public Post() {}
	
	public Post(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getJoined() {
		return joined;
	}

	public void setJoined(Date joined) {
		this.joined = joined;
	}

	public Date getLeft() {
		return left;
	}

	public void setLeft(Date left) {
		this.left = left;
	}
}
