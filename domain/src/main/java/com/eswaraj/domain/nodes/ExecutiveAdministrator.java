package com.eswaraj.domain.nodes;

import org.springframework.data.neo4j.annotation.NodeEntity;

import com.eswaraj.domain.base.BaseNode;

/**
 * Highest responsible person for an executive body
 * @author anuj
 * @date Jan 26, 2014
 *
 */
@NodeEntity
public class ExecutiveAdministrator extends BaseNode {

	private ExecutiveBody executiveBody;

	public ExecutiveBody getExecutiveBody() {
		return executiveBody;
	}

	public void setExecutiveBody(ExecutiveBody executiveBody) {
		this.executiveBody = executiveBody;
	}
}
