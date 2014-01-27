package com.eswaraj.domain.nodes;

import org.springframework.data.neo4j.annotation.NodeEntity;

/**
 * Highest responsible person for an executive body
 * @author anuj
 * @date Jan 26, 2014
 *
 */
@NodeEntity
public class ExecutiveAdministrator extends Administrator {

	private ExecutiveBody executiveBody;

	public ExecutiveBody getExecutiveBody() {
		return executiveBody;
	}

	public void setExecutiveBody(ExecutiveBody executiveBody) {
		this.executiveBody = executiveBody;
	}
}
