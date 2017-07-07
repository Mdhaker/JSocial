package com.datacollection.interfaces;

import java.util.Set;

import org.json.JSONObject;

public interface Youtube {

	/**
	 * Search for public youtube videos by keywrod
	 * @param query search query
	 * @return list of json objects
	 */ 
	public Set<JSONObject> findVideos(String query);
	/**
	 * Search for public youtube channels by keyword
	 * @param query search query
	 * @return list of json objects
	 */
	public Set<JSONObject> findChannel(String query);
	/**
	 * search for public youtube playlists by keyword
	 * @param query search query
	 * @return list of json objects
	 */
	public Set<JSONObject> findPlaylist(String query);	
	/**
	 * Search all youtube element matching the provided query
	 * @param query
	 * @return list of json objects
	 */
	public Set<JSONObject> searchYoutube(String query);	
	
	/**
	 * load channel data by id
	 * @param channelID channel id to load
	 * @return list of json objects
	 */
	public Set<JSONObject> getChannelActivities(String channelID);
	/**
	 * load channel activities
	 * @param channelID 
	 * @return list of json objects
	 */
	public Set<JSONObject> getChannel(String channelID);
	/**
	 * load video data by id
	 * @param videoID
	 * @return list of json objects
	 */
	public Set<JSONObject> getVideo(String videoID);
	/**
	 * load playlist data by channel id
	 * @param playlistID
	 * @return list of json objects
	 */
	public Set<JSONObject> getPlaylist(String channelID);
	/**
	 * list a youtube video ranking with a given ID
	 * @param VideoID
	 * @return list of json objects
	 */
	public String getVideoRating(String VideoID);
	/**
	 * contains information about a set of videos that a channel has chosen to feature. For example, a section could feature a channel's latest uploads, most popular uploads, or videos from one or more playlists.
	 * @param channelID channel id to fetch
	 * @return list of json objects
	 */
	public Set<JSONObject> getChannelSection(String channelID);
	/**
	 * get comment thread of a video (comments and replies)
	 * @param VideoID video id to fetch the comments
	 * @return list of json objects
	 */
	public Set<JSONObject> getcommentThread(String VideoID);
	/**
	 *  represents a YouTube caption track. A caption track is associated with exactly one YouTube video.
	 * @param VideoID
	 * @return list of json objects
	 */
	public Set<JSONObject> getcaptionTrack(String VideoID);
	/**
	 * fetch information about a YouTube user subscription. A subscription notifies a user when new videos are added to a channel or when another user takes one of several actions on YouTube, such as uploading a video, rating a video, or commenting on a video.
	 * @param channelID
	 * @return list of json objects
	 */
	public Set<JSONObject> getSubscriptions(String channelID);
}
