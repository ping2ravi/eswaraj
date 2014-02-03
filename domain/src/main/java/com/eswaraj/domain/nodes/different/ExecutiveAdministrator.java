package com.eswaraj.domain.nodes.different;

import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

/**
 * Responsible person for a execitve body
 * @author anuj
 * @date Jan 18, 2014
 *
 */
@NodeEntity
public class ExecutiveAdministrator extends Administrator {

	@RelatedTo(type="WORKS_FOR")
	private ExecutiveBody executiveBody;

	public ExecutiveBody getExecutiveBody() {
		return executiveBody;
	}
	public void setExecutiveBody(ExecutiveBody executiveBody) {
		this.executiveBody = executiveBody;
	}
}
