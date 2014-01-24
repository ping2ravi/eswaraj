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
public class ColonyLocalArea extends BaseNode{
	//Colony Local Area will be linked to ward as well as CityVillage, because it may be possible 
	//that Ward is no their at all in a city or village but Colony/Local areas are there
	private String name;
	private CityVillage cityVillage;
	private Ward ward;
	
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
	public Ward getWard() {
		return ward;
	}
	public void setWard(Ward ward) {
		this.ward = ward;
	}
	
}
