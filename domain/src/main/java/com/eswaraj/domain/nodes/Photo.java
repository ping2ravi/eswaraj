package com.eswaraj.domain.nodes;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.eswaraj.domain.base.BaseNode;

/**
 * Photo of the complaint and/or location
 * @author anuj
 * @date Jan 18, 2014
 *
 */
@NodeEntity
public class Photo extends BaseNode {

	private String orgUrl;
	private int orgWidth;
	private int orgHeight;
	
	private String squareUrl;
	private String smallUrl;
	private String mediumUrl;
	private String largeUrl;
	public String getOrgUrl() {
		return orgUrl;
	}
	public void setOrgUrl(String orgUrl) {
		this.orgUrl = orgUrl;
	}
	public int getOrgWidth() {
		return orgWidth;
	}
	public void setOrgWidth(int orgWidth) {
		this.orgWidth = orgWidth;
	}
	public int getOrgHeight() {
		return orgHeight;
	}
	public void setOrgHeight(int orgHeight) {
		this.orgHeight = orgHeight;
	}
	public String getSquareUrl() {
		return squareUrl;
	}
	public void setSquareUrl(String squareUrl) {
		this.squareUrl = squareUrl;
	}
	public String getSmallUrl() {
		return smallUrl;
	}
	public void setSmallUrl(String smallUrl) {
		this.smallUrl = smallUrl;
	}
	public String getMediumUrl() {
		return mediumUrl;
	}
	public void setMediumUrl(String mediumUrl) {
		this.mediumUrl = mediumUrl;
	}
	public String getLargeUrl() {
		return largeUrl;
	}
	public void setLargeUrl(String largeUrl) {
		this.largeUrl = largeUrl;
	}
	@Override
	public String toString() {
		return "Photo [orgUrl=" + orgUrl + "]";
	}
}
