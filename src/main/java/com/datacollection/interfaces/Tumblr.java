package com.datacollection.interfaces;

import java.util.Set;

import org.json.JSONObject;

public interface Tumblr {
	
	/**
	 * Find posts by tags
	 * @param search tags
	 * @return list of json objects
	 */
	public Set<JSONObject> findPosts(String tags);
	/**
	 * This method returns general information about the blog, such as the title, number of posts, and other high-level data.
	 * @param blog hostname
	 * @return single json object
	 */
	public JSONObject getBlogInfo(String blog);
	/**
	 * This method can be used to retrieve the publicly exposed likes from a blog.
	 * @param blog hostname
	 * @return list of json objects
	 */
	public Set<JSONObject> getBlogLikes(String blog);
	/**
	 * This method can be used to retrieve the publicly exposed list of blogs that a blog follows, in order from most recently-followed to first.
	 * @param blog blog hostname
	 * @return list of json objects
	 */
	public Set<JSONObject> getBlogFollowing(String blog);
	/**
	 * Retrieve a Blog's Followers
	 * @param blog blog hostname
	 * @return list of json objects
	 */
	public Set<JSONObject> getBlogFollowers(String blog);
	/**
	 * Retrieve Published Posts
	  * @param blog blog hostname
	 * @return list of json objects
	 */
	public Set<JSONObject> getBlogPosts(String blog);
	

}
