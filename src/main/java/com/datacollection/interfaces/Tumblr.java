package com.datacollection.interfaces;

import java.util.Set;

import org.json.JSONObject;

public interface Tumblr {
	
	/**
	 * Find posts by tags
	 * @param search tags
	 * @return JSON object array
	 */
	public Set<JSONObject> findPosts(String tags);

}
