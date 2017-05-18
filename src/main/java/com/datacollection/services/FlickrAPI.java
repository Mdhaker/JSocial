package com.datacollection.services;

import java.util.Set;

import org.json.JSONObject;

import com.datacollection.config.Config;
import com.datacollection.interfaces.Flickr;
import com.datacollection.interfaces.Twitter;
import com.datacollection.utils.Auth;
import com.datacollection.utils.Parser;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth10aService;

public class FlickrAPI implements Flickr{
	
	private OAuthRequest request;
	private OAuth10aService service;
	public static enum methodsPeople{
		findByEmail,findByUsername,getGroups,
		getInfo,getLimits,getPhotos,getPhotosOf,
		getPublicGroups,getPublicPhotos,getUploadStatus};
		
	public static enum methodsGroup{browse,getInfo,search};
			
	public static enum methodsTopic{add,getInfo,getList};
			
	public static enum methodsPhoto{
		addTags,delete,getAllContexts,getContactsPhotos,getContactsPublicPhotos,
		getContext,getCounts,getExif,getFavorites,getInfo,getNotInSet,
		getPerms,getPopular,getRecent,getSizes,getUntagged,
		getWithGeoData,getWithoutGeoData,recentlyUpdated,
		search};
		
	public static enum methodsPlace{
			find,findByLatLon,getChildrenWithPhotosPublic,getInfo,
			getInfoByUrl,getPlaceTypes,getShapeHistory,getTopPlacesList,
			placesForBoundingBox,placesForContacts,placesForTags,
			placesForUser,resolvePlaceId,resolvePlaceURL,tagsForPlace}
	public static enum methodsGallery{getInfo,getList,getPhotos,getListForPhoto}	
	public static enum methodsPhotoComment{getList,getRecentForContacts}	
	public static enum methodsPhotoGeo{getLocation}	
	public static enum methodsPhotoPeople{getList}	
	public static enum methodsProfile{getProfile}
		
	
	/**
	 * Default Constructor
	 */
	private FlickrAPI()
	{
		this.service = (OAuth10aService) Auth.getFlickrInstance().getService();
	}
	
	/**
	 * build for each action, Reader
	 * @return
	 */
	public static Flickr build()
	{
		return  new FlickrAPI();
	}

	@Override
	public JSONObject findUserByMail(String query) {
		this.request = new OAuthRequest(Verb.GET, Config.FILCKER_BASE_URL, this.service);
		
		this.request.addParameter("api_key", Config.FILCKER_APP_ID);
		this.request.addParameter("method", "flickr.people."+FlickrAPI.methodsPeople.findByEmail.name());
		this.request.addParameter("find_email", query);
		this.request.addParameter("format", "json");
		Response response = request.send();
		System.out.println(request.getCompleteUrl());
		return Parser.parseObject(response,"flickr","user");
	}

	

	@Override
	public JSONObject findUserByUserName(String username) {
		this.request = new OAuthRequest(Verb.GET, Config.FILCKER_BASE_URL, this.service);
		this.request.addParameter("api_key", Config.FILCKER_APP_ID);
		this.request.addParameter("method", "flickr.people."+FlickrAPI.methodsPeople.findByUsername.name());
		this.request.addParameter("username", username);
		this.request.addParameter("format", "json");
		Response response = request.send();
		System.out.println(request.getCompleteUrl());
		return Parser.parseObject(response,"flickr","user");
	}

	@Override
	public JSONObject getUserPhotos(String id) {
		this.request = new OAuthRequest(Verb.GET, Config.FILCKER_BASE_URL, this.service);
		this.request.addParameter("api_key", Config.FILCKER_APP_ID);
		this.request.addParameter("method", "flickr.people."+FlickrAPI.methodsPeople.getPhotos.name());
		this.request.addParameter("user_id", id);
		this.request.addParameter("format", "json");
		Response response = request.send();
		System.out.println(request.getCompleteUrl());
		return Parser.parseObject(response,"flickr","photos");
	}

	@Override
	public JSONObject getUserInfo(String id) {
		this.request = new OAuthRequest(Verb.GET, Config.FILCKER_BASE_URL, this.service);
		this.request.addParameter("api_key", Config.FILCKER_APP_ID);
		this.request.addParameter("method", "flickr.people."+FlickrAPI.methodsPeople.getInfo.name());
		this.request.addParameter("user_id", id);
		this.request.addParameter("format", "json");
		Response response = request.send();
		System.out.println(request.getCompleteUrl());
		return Parser.parseObject(response,"flickr","person");
	}

	@Override
	public JSONObject getUserGroups(String id) {
		this.request = new OAuthRequest(Verb.GET, Config.FILCKER_BASE_URL, this.service);
		this.request.addParameter("api_key", Config.FILCKER_APP_ID);
		this.request.addParameter("method", "flickr.people."+FlickrAPI.methodsPeople.getPublicGroups.name());
		this.request.addParameter("user_id", id);
		this.request.addParameter("format", "json");
		Response response = request.send();
		System.out.println(request.getCompleteUrl());
		return Parser.parseObject(response,"flickr","groups");
	}
	
	@Override
	public JSONObject searchPhoto(String text) {
		this.request = new OAuthRequest(Verb.GET, Config.FILCKER_BASE_URL, this.service);
		this.request.addParameter("api_key", Config.FILCKER_APP_ID);
		this.request.addParameter("method", "flickr.photos."+FlickrAPI.methodsPhoto.search.name());
		this.request.addParameter("text", text);
		this.request.addParameter("format", "json");
		Response response = request.send();
		System.out.println(request.getCompleteUrl());
		return Parser.parseObject(response,"flickr","photos");
	}

	@Override
	public JSONObject getPhotoInfo(String id) {
		this.request = new OAuthRequest(Verb.GET, Config.FILCKER_BASE_URL, this.service);
		this.request.addParameter("api_key", Config.FILCKER_APP_ID);
		this.request.addParameter("method", "flickr.photos."+FlickrAPI.methodsPhoto.getInfo.name());
		this.request.addParameter("photo_id", id);
		this.request.addParameter("format", "json");
		Response response = request.send();
		System.out.println(request.getCompleteUrl());
		return Parser.parseObject(response,"flickr","photo");
	}

	@Override
	public JSONObject getPhotoComments(String id) {
		this.request = new OAuthRequest(Verb.GET, Config.FILCKER_BASE_URL, this.service);
		this.request.addParameter("api_key", Config.FILCKER_APP_ID);
		this.request.addParameter("method", "flickr.photos.comments."+FlickrAPI.methodsPhotoComment.getList.name());
		this.request.addParameter("photo_id", id);
		this.request.addParameter("format", "json");
		Response response = request.send();
		System.out.println(request.getCompleteUrl());
		return Parser.parseObject(response,"flickr","comments");
	}

	@Override
	public JSONObject getPhotoGeoLocation(String id) {
		this.request = new OAuthRequest(Verb.GET, Config.FILCKER_BASE_URL, this.service);
		this.request.addParameter("api_key", Config.FILCKER_APP_ID);
		this.request.addParameter("method", "flickr.photos.geo."+FlickrAPI.methodsPhotoGeo.getLocation.name());
		this.request.addParameter("photo_id", id);
		this.request.addParameter("format", "json");
		Response response = request.send();
		System.out.println(request.getCompleteUrl());
		return Parser.parseObject(response,"flickr","photo");
	}

	@Override
	public JSONObject getPlaces(String query) {
		this.request = new OAuthRequest(Verb.GET, Config.FILCKER_BASE_URL, this.service);
		this.request.addParameter("api_key", Config.FILCKER_APP_ID);
		this.request.addParameter("method", "flickr.places."+FlickrAPI.methodsPlace.find.name());
		this.request.addParameter("query", query);
		this.request.addParameter("format", "json");
		Response response = request.send();
		System.out.println(request.getCompleteUrl());
		return Parser.parseObject(response,"flickr","places");
	}

	@Override
	public JSONObject getPlaceInfo(String id) {
		this.request = new OAuthRequest(Verb.GET, Config.FILCKER_BASE_URL, this.service);
		this.request.addParameter("api_key", Config.FILCKER_APP_ID);
		this.request.addParameter("method", "flickr.places."+FlickrAPI.methodsPlace.getInfo.name());
		this.request.addParameter("place_id", id);
		this.request.addParameter("format", "json");
		Response response = request.send();
		System.out.println(request.getCompleteUrl());
		return Parser.parseObject(response,"flickr","place");
	}

	@Override
	public JSONObject getPlacesByLatLong(String lat, String lon,String acc) {
		this.request = new OAuthRequest(Verb.GET, Config.FILCKER_BASE_URL, this.service);
		this.request.addParameter("api_key", Config.FILCKER_APP_ID);
		this.request.addParameter("method", "flickr.places."+FlickrAPI.methodsPlace.findByLatLon.name());
		this.request.addParameter("lat", lat);
		this.request.addParameter("lon", lon);
		this.request.addParameter("accurcy", acc);
		this.request.addParameter("format", "json");
		Response response = request.send();
		System.out.println(request.getCompleteUrl());
		return Parser.parseObject(response,"flickr","places");
	}
	@Override
	public JSONObject getPlaceByURL(String url) {
		this.request = new OAuthRequest(Verb.GET, Config.FILCKER_BASE_URL, this.service);
		this.request.addParameter("api_key", Config.FILCKER_APP_ID);
		this.request.addParameter("method", "flickr.places."+FlickrAPI.methodsPlace.getInfoByUrl.name());
		this.request.addParameter("url", url);
		this.request.addParameter("format", "json");
		Response response = request.send();
		System.out.println(request.getCompleteUrl());
		return Parser.parseObject(response,"flickr","places");
	}
	
	
}
