package com.datacollection.interfaces;

import java.util.Set;

import org.json.JSONObject;

import com.datacollection.utils.SearchFilter;

public interface Google extends Reader{

	/**
	 * Get all public profile
	 * @param query get profile by query
	 * @return JSON object array
	 */
	public Set<JSONObject> getProfiles(String query);
	
	/**
	 *GooglePlus activities equals Tweets and Status 
	 * @param query
	 * @return
	 */
	public Set<JSONObject> getActivities(String query);
	/**
	 * Get comments of an Activity
	 * @param activityId Acitivty Id
	 * @return
	 */
	public Set<JSONObject> getComments(String activityId);
	
	/**
	 * 
	 * @param query
	 * @return
	 */
	public Set<JSONObject> getActivities(SearchFilter.GoogleFilter filter);
	
}
