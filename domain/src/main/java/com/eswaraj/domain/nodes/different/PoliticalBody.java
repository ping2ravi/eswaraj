package com.eswaraj.domain.nodes.different;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.eswaraj.domain.base.BaseNode;

/**
 * Represents a political body like a political party
 * @author anuj
 * @date Jan 28, 2014
 *
 */

@NodeEntity
public class PoliticalBody extends BaseNode {

	private String name;
	
}
