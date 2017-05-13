package com.datacollection.interfaces;

import java.util.Set;

import org.json.JSONObject;

public interface Google extends Reader{

	/**
	 * Get all public profile
	 * @param query get profile by query
	 * @return JSON object array
	 */
	public Set<JSONObject> getProfiles(String query);
}
