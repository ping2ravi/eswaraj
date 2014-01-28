package com.eswaraj.domain.nodes.relationships;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

import com.eswaraj.domain.nodes.StateExecutiveBody;
import com.eswaraj.domain.nodes.division.State;

@RelationshipEntity(type="BELONGS_TO")
public class StateExecutiveBodyState {

	@StartNode State state;
	@EndNode StateExecutiveBody stateExecutiveBody;
	
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public StateExecutiveBody getStateExecutiveBody() {
		return stateExecutiveBody;
	}
	public void setStateExecutiveBody(StateExecutiveBody stateExecutiveBody) {
		this.stateExecutiveBody = stateExecutiveBody;
	}
	
}
