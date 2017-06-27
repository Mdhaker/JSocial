package com.datacollection.interfaces;

import java.util.Set;

import org.json.JSONObject;

public interface Instagram extends Reader{
	
	/**
	 * Search for user
	 * @param query 
	 * @return JSON object array of found page data
	 */
	public Set<JSONObject> getUser(String query);

}
