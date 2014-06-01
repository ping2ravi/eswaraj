package com.eswaraj.domain.nodes.division;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.eswaraj.domain.base.BaseNode;

/**
 * Boundary that a location belongs to
 * @author anuj
 * @date Jan 18, 2014
 *
 */
@NodeEntity
public class Boundary extends BaseNode{

	
	private GeoPoint topLeft;
	private GeoPoint bottomRight;

	public GeoPoint getTopLeft() {
		return topLeft;
	}

	public void setTopLeft(GeoPoint topLeft) {
		this.topLeft = topLeft;
	}

	public GeoPoint getBottomRight() {
		return bottomRight;
	}

	public void setBottomRight(GeoPoint bottomRight) {
		this.bottomRight = bottomRight;
	}

}
