package com.eswaraj.domain.nodes.division;

import java.util.List;

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

	
	private List<GeoPoint> geoPoints;

	public List<GeoPoint> getGeoPoints() {
		return geoPoints;
	}

	public void setGeoPoints(List<GeoPoint> geoPoints) {
		this.geoPoints = geoPoints;
	} 
	
}
