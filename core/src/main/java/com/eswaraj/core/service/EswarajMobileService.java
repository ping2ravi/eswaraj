package com.eswaraj.core.service;

public interface EswarajMobileService {

	/**
	 * This service returns all location info which may include all or few of following
	 * State,District,Assembly Constituency, Parliament Constituency, City/Village, Ward, Colony etc
	 * 
	 * and there will be cases too when we may find two states or two district etc, in that case we will return all such locations
	 * 
	 * @param lattitude
	 * @param longitude
	 * @return
	 */
	LocationInfo getLocationInfo(Double lattitude, Double longitude);
	
	
	
	/**
	 * This service will create a new complaint and will return the complete complaint data
	 * which may have all or some of following data
	 * Location : State,District,Assembly Constituency, Parliament Constituency, City/Village, Ward, Colony etc
	 * Official Responsible : Official person responsible for the problem , one official
	 * Politician Responsible : List of all politician responsible for complaint
	 * Photos, videos and other complaint attributes
	 * 
	 * People who endorsed this complaint may not be returned by this, for that their will be different API
	 * 
	 * @param complaint
	 * @return
	 */
	ComplaintInfo saveComplaint(Complaint complaint);
}
