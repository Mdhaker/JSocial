package com.datacollection.interfaces;

import java.util.Set;

import org.json.JSONObject;

public interface Youtube {

	/**
	 * Search for public youtube videos by keywrod
	 * @param query search query
	 * @return
	 */
	public Set<JSONObject> findVideos(String query);
	/**
	 * Search for public youtube channels by keyword
	 * @param query search query
	 * @return
	 */
	public Set<JSONObject> findChannel(String query);
	/**
	 * search for public youtube playlists by keyword
	 * @param query search query
	 * @return
	 */
	public Set<JSONObject> findPlaylist(String query);	
	/**
	 * load channel data by id
	 * @param channelID channel id to load
	 * @return
	 */
	public Set<JSONObject> getChannelActivities(String channelID);
	/**
	 * load channel activities
	 * @param channelID 
	 * @return
	 */
	public Set<JSONObject> getChannel(String channelID);
	/**
	 * load video data by id
	 * @param videoID
	 * @return
	 */
	public Set<JSONObject> getVideo(String videoID);
	/**
	 * load playlist data by id
	 * @param playlistID
	 * @return
	 */
	public Set<JSONObject> getPlaylist(String playlistID);
}
