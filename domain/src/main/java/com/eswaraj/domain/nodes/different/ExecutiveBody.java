package com.eswaraj.domain.nodes.different;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.eswaraj.domain.base.BaseNode;

/**
 * Represents an executive body like Water Department or Fire Department
 * @author anuj
 * @date Jan 28, 2014
 *
 */

@NodeEntity
public class ExecutiveBody extends BaseNode {

	private String name;
	
}
