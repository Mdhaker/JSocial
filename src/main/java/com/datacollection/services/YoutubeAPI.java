package com.datacollection.services;

import java.util.Set;

import org.json.JSONObject;

import com.datacollection.config.Config;
import com.datacollection.interfaces.Youtube;
import com.datacollection.utils.Auth;
import com.datacollection.utils.Pagination;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;

public class YoutubeAPI implements Youtube{

	private OAuthRequest request;
	private OAuth20Service service ;
	
	public YoutubeAPI() 
	{
		this.service = (OAuth20Service)Auth.getGoogleInstance().getService();
	}
	public static Youtube build()
	{
		return  new YoutubeAPI();
	}
	
	@Override
	public Set<JSONObject> findVideos(String query) {
		this.request = new OAuthRequest(Verb.GET, Config.Youtube_Search_ENDPOINT(), this.service);
		this.request.addParameter("query", query);
		this.request.addParameter("part", "snippet");
		this.request.addParameter("type", "video");
		this.request.addParameter("key", Config.GOOGLE_API_KEY);
		Pagination.buildPaginator(this.service).googlePaginator(this.request,Config.Youtube_Search_ENDPOINT());
		return Pagination.getgoogleData();
	}
	@Override
	public Set<JSONObject> findChannel(String query) {
		this.request = new OAuthRequest(Verb.GET, Config.Youtube_Search_ENDPOINT(), this.service);
		this.request.addParameter("query", query);
		this.request.addParameter("part", "snippet");
		this.request.addParameter("type", "channel");
		this.request.addParameter("key", Config.GOOGLE_API_KEY);
		System.out.println(this.request.getCompleteUrl());
		Pagination.buildPaginator(this.service).googlePaginator(this.request,Config.Youtube_Search_ENDPOINT());
		return Pagination.getgoogleData();
	}
	@Override
	public Set<JSONObject> findPlaylist(String query) {
		this.request = new OAuthRequest(Verb.GET, Config.Youtube_Search_ENDPOINT(), this.service);
		this.request.addParameter("query", query);
		this.request.addParameter("part", "snippet");
		this.request.addParameter("type", "playlist");
		this.request.addParameter("key", Config.GOOGLE_API_KEY);
		Pagination.buildPaginator(this.service).googlePaginator(this.request,Config.Youtube_Search_ENDPOINT());
		return Pagination.getgoogleData();
	}
	@Override
	public Set<JSONObject> getChannelActivities(String channelID) {
		this.request = new OAuthRequest(Verb.GET, Config.Youtube_Search_ENDPOINT(), this.service);
		this.request.addParameter("channelId", channelID);
		this.request.addParameter("part", "snippet");
		this.request.addParameter("key", Config.GOOGLE_API_KEY);
		Pagination.buildPaginator(this.service).googlePaginator(this.request,Config.Youtube_Activities_ENDPOINT());
		return Pagination.getgoogleData();
	}
	@Override
	public Set<JSONObject> getChannel(String channelID) {
		this.request = new OAuthRequest(Verb.GET, Config.Youtube_Search_ENDPOINT(), this.service);
		this.request.addParameter("id", channelID);
		this.request.addParameter("part", "snippet");
		this.request.addParameter("key", Config.GOOGLE_API_KEY);
		Pagination.buildPaginator(this.service).googlePaginator(this.request,Config.Youtube_Channel_ENDPOINT());
		return Pagination.getgoogleData();
	}
	@Override
	public Set<JSONObject> getVideo(String videoID) {
		this.request = new OAuthRequest(Verb.GET, Config.Youtube_Search_ENDPOINT(), this.service);
		this.request.addParameter("id", videoID);
		this.request.addParameter("part", "snippet");
		this.request.addParameter("key", Config.GOOGLE_API_KEY);
		Pagination.buildPaginator(this.service).googlePaginator(this.request,Config.Youtube_Video_ENDPOINT());
		return Pagination.getgoogleData();
	}
	@Override
	public Set<JSONObject> getPlaylist(String playlistID) {
		this.request = new OAuthRequest(Verb.GET, Config.Youtube_Search_ENDPOINT(), this.service);
		this.request.addParameter("channelId", playlistID);
		this.request.addParameter("part", "snippet");
		this.request.addParameter("key", Config.GOOGLE_API_KEY);
		Pagination.buildPaginator(this.service).googlePaginator(this.request,Config.Youtube_Video_ENDPOINT());
		return Pagination.getgoogleData();
	}
}
