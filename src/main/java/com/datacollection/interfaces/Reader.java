package com.datacollection.interfaces;

import java.util.Set;

import org.json.JSONObject;

public interface Reader 
{
	/**
	 * Get authenticated user Data
	 * @return
	 */
	public JSONObject getUser();
	
	/**
	 * Search Place by query
	 * @param query
	 * @return
	 */
	public Set<JSONObject> getPlace(String query);
	

}
