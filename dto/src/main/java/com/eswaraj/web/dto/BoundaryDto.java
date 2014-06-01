package com.eswaraj.web.dto;

import java.util.List;

/**
 * Boundary that a location belongs to
 * @author ravi
 * @date May 29, 2014
 *
 */
public class BoundaryDto extends BaseDto{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GeoPointDto topLeft;
	private GeoPointDto bottomRight;
	private List<GeoPointDto> geoPoints;

	public GeoPointDto getTopLeft() {
		return topLeft;
	}

	public void setTopLeft(GeoPointDto topLeft) {
		this.topLeft = topLeft;
	}

	public GeoPointDto getBottomRight() {
		return bottomRight;
	}

	public void setBottomRight(GeoPointDto bottomRight) {
		this.bottomRight = bottomRight;
	}

	public List<GeoPointDto> getGeoPoints() {
		return geoPoints;
	}

	public void setGeoPoints(List<GeoPointDto> geoPoints) {
		this.geoPoints = geoPoints;
	} 
	
}
