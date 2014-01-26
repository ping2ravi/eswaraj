package com.eswaraj.domain.nodes;

import org.springframework.data.neo4j.annotation.NodeEntity;

/**
 * Political administrator who has a party affiliation
 * @author anuj
 * @date Jan 18, 2014
 *
 */
@NodeEntity
public class PoliticalAdministrator extends Administrator {
	
	private Party party;

	public Party getParty() {
		return party;
	}

	public void setParty(Party party) {
		this.party = party;
	}
}
