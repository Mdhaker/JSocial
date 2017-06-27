package com.datacollection.interfaces;

import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import com.github.scribejava.core.model.Parameter;

public interface Facebook extends Reader{
	
	/**
	 * Graph API Topic search method
	 * @param Query
	 * @return
	 */
	public Set<JSONObject> searchTopics(String Query);
	/**
	 * Search Place by query
	 * @param query
	 * @return
	 */
	public Set<JSONObject> searchPlaces(String query);
	/**
	 * Search for page
	 * @param query 
	 * @return JSON object array of found page data
	 */
	public Set<JSONObject> searchPages(String query);
	/**
	 * Search for user
	 * @param query 
	 * @return JSON object array of found page data
	 */
	public Set<JSONObject> searchUsers(String query);
	/**
	 * Search for a Facebook event
	 * @param query 
	 * @return JSON object array of found page data
	 */
	public Set<JSONObject> searchEvents(String query);
	/**
	 * Search for a Facebook event
	 * @param id of the node (user / page / group ect..)
	 * @return returns the posts created by the user in JSON object array
	 */
	public Set<JSONObject> searchGroups(String query);
	/**
	 * Search for a Facebook Groups
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
	public Set<JSONObject> getFeed(String id);
	/**
	 * Uses app token
	 * @param nodeID Id of user,page,comment,album ect..
	 * @param edge edge name, like posts, feed, tagged ect..
	 * @return return array of the searched edge element
	 */
	public Set<JSONObject> getAppEdge(String nodeID, String edge,Parameter... params);
	/**
	 * Uses user token
	 * @param nodeID Id of user,page,comment,album ect..
	 * @param edge edge name, like posts, feed, tagged ect..
	 * @return return array of the searched edge element
	 */
	public Set<JSONObject> getUserEdge(String nodeID, String edge,Parameter... params);

}
