package com.datacollection.interfaces;

import java.util.Set;

import org.json.JSONObject;

public interface Facebook extends Reader{
	
	/**
	 * Graph API Topic search method
	 * @param Query
	 * @return
	 */
	public Set<JSONObject> getTopics(String Query);
	/**
	 * Search Place by query
	 * @param query
	 * @return
	 */
	public Set<JSONObject> getPlaces(String query);
	/**
	 * Search for page
	 * @param query 
	 * @return JSON object array of found page data
	 */
	public Set<JSONObject> getPage(String query);
	/**
	 * Search for user
	 * @param query 
	 * @return JSON object array of found page data
	 */
	public Set<JSONObject> getUser(String query);
	/**
	 * Get user own posts
	 * @param id of the node (user / page / group ect..)
	 * @return returns the posts created by the user in JSON object array
	 */
	@Deprecated
	public Set<JSONObject> getPosts(String id);
	/**
	 * includes all the things that a user might see on their own profile feed
	 * @param query 
	 * @return returns the feed of the user in JSON object array
	 */
	@Deprecated
	public Set<JSONObject> getFeed(String query);
	/**
	 * Uses app token
	 * @param nodeID Id of user,page,comment,album ect..
	 * @param edge edge name, like posts, feed, tagged ect..
	 * @return return array of the searched edge element
	 */
	public Set<JSONObject> getAppEdge(String nodeID, String edge);
	/**
	 * Uses user token
	 * @param nodeID Id of user,page,comment,album ect..
	 * @param edge edge name, like posts, feed, tagged ect..
	 * @return return array of the searched edge element
	 */
	public Set<JSONObject> getUserEdge(String nodeID, String edge);

}
