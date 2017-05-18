package com.datacollection.interfaces;

import java.util.Set;

import org.json.JSONObject;

import com.datacollection.services.FlickrAPI;

public interface Flickr {

	/**
	 * 
	 * search people by email
	 * @param query email
	 * @return
	 */
	public JSONObject findUserByMail(String email);
	/**
	 * 
	 *   search people by user name
	 * @param query Username
	 * @return
	 */
	public JSONObject findUserByUserName(String username);
	/**
	 * 
	 *   get user photo
	 * @param id user id
	 * @return
	 */
	public JSONObject getUserPhotos(String id);
	/**
	 * 
	 *  get user groups
	 * @param id user id
	 * @return
	 */
	public JSONObject getUserGroups(String id);
	/**
	 * 
	 * get user info
	 * @param id user id
	 * @return
	 */
	public JSONObject getUserInfo(String id);
	/**
	 * 
	 * search photos
	 * @param id user id
	 * @return
	 */
	public JSONObject searchPhoto(String text);
	/**
	 * 
	 * get photo info
	 * @param id photo id
	 * @return
	 */
	public JSONObject getPhotoInfo(String id);
	/**
	 * 
	 * get photo comments
	 * @param id photo id
	 * @return
	 */
	public JSONObject getPhotoComments(String id);
	/**
	 * 
	 * get photo location coordinates
	 * @param id photo id
	 * @return
	 */
	public JSONObject getPhotoGeoLocation(String id);
	/**
	 * 
	 * search for places
	 * @param query query for the search
	 * @return
	 */
	public JSONObject getPlaces(String query);
	/**
	 * 
	 * get specified place by longitude and latitude
	 * @param long Longitude	
	 * @param lat Latitude	
	 * @param acc Accuracy	
	 * @return 
	 */
	public JSONObject getPlacesByLatLong(String lat,String lon,String acc);
	/**
	 * 
	 * get specified place info
	 * @param id of the place	
	 * @return 
	 */
	public JSONObject getPlaceInfo(String query);
	/**
	 * search place by flickr url found in result of long lat search place
	 * @param url flickr url of the place
	 * @return
	 */
	public JSONObject getPlaceByURL(String url);
	
}
