package com.eswaraj.domain.nodes.different;

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
}
