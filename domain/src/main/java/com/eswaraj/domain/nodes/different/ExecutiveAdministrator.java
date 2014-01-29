package com.eswaraj.domain.nodes.different;

import org.springframework.data.neo4j.annotation.RelatedTo;

/**
 * Responsible person for a execitve body
 * @author anuj
 * @date Jan 18, 2014
 *
 */
public class ExecutiveAdministrator extends Administrator {
	
	@RelatedTo(type="WORKS_FOR")
	private ExecutiveBody executiveBody;
	
	public ExecutiveBody getExecutiveBody() {
		return executiveBody;
	}
	public void setExecutiveBody(ExecutiveBody executiveBody) {
		this.executiveBody = executiveBody;
	}
	public ExecutiveBodyAdministrator worksFor(Post post) {
		ExecutiveBodyAdministrator executiveBodyAdministrator = new ExecutiveBodyAdministrator(executiveBody, this , post);
		return executiveBodyAdministrator;
	}
}
