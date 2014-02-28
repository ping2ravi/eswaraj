package com.eswaraj.domain.base;

import com.eswaraj.domain.nodes.division.AssemblyConstituency;
import com.eswaraj.domain.nodes.division.CityVillage;
import com.eswaraj.domain.nodes.division.ColonyLocalArea;
import com.eswaraj.domain.nodes.division.Country;
import com.eswaraj.domain.nodes.division.District;
import com.eswaraj.domain.nodes.division.ParliamentConstituency;
import com.eswaraj.domain.nodes.division.State;
import com.eswaraj.domain.nodes.division.Ward;

public class BaseLocationNode extends BaseNode {

	protected ColonyLocalArea colonyLocalArea;
	protected Ward ward;
	protected CityVillage cityVillage;
	protected AssemblyConstituency assemblyConstituency;
	protected District district;
	protected ParliamentConstituency parliamentConstituency;
	protected State state;
	protected Country country;

}
