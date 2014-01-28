package com.eswaraj.domain.nodes;

import java.util.List;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedToVia;

import com.eswaraj.domain.nodes.division.State;

/**
 * Represents an executive body like Water Department or Fire Department
 * @author anuj
 * @date Jan 26, 2014
 *
 */

@NodeEntity
public class StateExecutiveBody extends ExecutiveBody {

	private StateDepartment stateDepartment;
	@RelatedToVia(type="BELONGS_TO", direction=Direction.INCOMING)
	private List<State> states;
	
}
