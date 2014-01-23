package com.eswaraj.domain.nodes;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.eswaraj.domain.base.BaseNode;

/**
 * Video of the complaint and/or location
 * @author anuj
 * @date Jan 18, 2014
 *
 */
@NodeEntity
public class Video extends BaseNode {
	private String url;
}
