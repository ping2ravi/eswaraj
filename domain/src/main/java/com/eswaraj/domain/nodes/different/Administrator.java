package com.eswaraj.domain.nodes.different;

import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.data.neo4j.annotation.RelatedToVia;

import com.eswaraj.domain.base.BaseNode;
import com.eswaraj.domain.nodes.Complaint;
import com.eswaraj.domain.nodes.Person;
import com.eswaraj.domain.nodes.Post;

/**
 * Highest responsible person for that particular division
 * @author anuj
 * @date Jan 18, 2014
 *
 */
@NodeEntity
public class Administrator extends BaseNode {
	
	@RelatedTo(type="REPORTS_TO")
	private Person person;
	
	@RelatedTo(type="REPORTS_TO", direction=Direction.INCOMING)
	private Set<Person> directReports;
	
	@RelatedToVia(type="WORKS_IN")
	private 
}
