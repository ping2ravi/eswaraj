package com.eswaraj.domain.nodes.division;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.eswaraj.domain.base.BaseNode;

/**
 * Village level administrative division
 * @author ravi
 * @date Jan 18, 2014
 *
 */
@NodeEntity
public class Ward extends BaseNode{

	private String name;
	private CityVillage cityVillage;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CityVillage getCityVillage() {
		return cityVillage;
	}
	public void setCityVillage(CityVillage cityVillage) {
		this.cityVillage = cityVillage;
	}
	
}
