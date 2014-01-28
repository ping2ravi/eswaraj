package com.eswaraj.domain.nodes;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.eswaraj.domain.base.BaseNode;
import com.eswaraj.domain.nodes.division.State;

@NodeEntity
public class StateDepartment extends BaseNode{

	
	private String name;
	private State state;
	private Department department;
	
}
