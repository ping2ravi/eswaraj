package com.eswaraj.domain.nodes;

import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

import com.eswaraj.domain.base.BaseNode;

/**
 * Location of the complaint
 * @author ravi
 * @date May 30, 2014
 *
 */
@NodeEntity
public class LocationType extends BaseNode {

	@Indexed
	private String name;
	
	@RelatedTo(type="PART_OF")
	private LocationType parentLocationType;
	
	@RelatedTo(type="BELONGS_TO")
	private DataClient dataClient;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocationType getParentLocationType() {
		return parentLocationType;
	}

	public void setParentLocationType(LocationType parentLocationType) {
		this.parentLocationType = parentLocationType;
	}

	public DataClient getDataClient() {
		return dataClient;
	}

	public void setDataClient(DataClient dataClient) {
		this.dataClient = dataClient;
	}

	@Override
	public String toString() {
		return "LocationType [name=" + name + ", parentLocationType=" + parentLocationType + ", dataClient=" + dataClient + ", toString()=" + super.toString()
				+ "]";
	}

	
}
