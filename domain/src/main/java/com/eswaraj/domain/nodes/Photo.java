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

	private String url;
}
