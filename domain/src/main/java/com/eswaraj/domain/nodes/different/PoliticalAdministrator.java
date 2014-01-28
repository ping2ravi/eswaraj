package com.eswaraj.domain.nodes.different;

import org.springframework.data.neo4j.annotation.RelatedTo;

/**
 * Responsible person for a execitve body
 * @author anuj
 * @date Jan 18, 2014
 *
 */
public class PoliticalAdministrator extends Administrator {
	
	@RelatedTo(type="MEMBER_OF")
	private PoliticalBody politicalBody;
	
	public PoliticalBody getPoliticalBody() {
		return politicalBody;
	}
	public void setPoliticalBody(PoliticalBody politicalBody) {
		this.politicalBody = politicalBody;
	}
	public PoliticalBodyAdministrator memberOf(Post post) {
		PoliticalBodyAdministrator politicalBodyAdministrator = new PoliticalBodyAdministrator(politicalBody, this , post);
		return politicalBodyAdministrator;
	}
}
