package com.eswaraj.domain.nodes;

import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;

import com.eswaraj.domain.base.BaseNode;

/**
 * Location of the complaint
 * @author ravi
 * @date May 30, 2014
 *
 */
@NodeEntity
public class DataClient extends BaseNode {

	@Indexed
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "DataClient [name=" + name + ", super=" + super.toString() + "]";
	}

	
}
