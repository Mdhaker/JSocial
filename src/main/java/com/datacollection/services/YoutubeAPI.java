package com.datacollection.services;

import java.util.Set;

import org.json.JSONObject;

import com.datacollection.config.Config;
import com.datacollection.interfaces.Youtube;
import com.datacollection.utils.Auth;
import com.datacollection.utils.Pagination;
import com.datacollection.utils.Parser;
import com.github.scribejava.core.model.OAuth2AccessToken;
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
		this.request.addParameter("q", query);
		this.request.addParameter("part", "snippet");
		this.request.addParameter("type", "video");
		this.request.addParameter("key", Config.GOOGLE_API_KEY);
		Pagination.buildPaginator(this.service).googlePaginator(this.request,Config.Youtube_Search_ENDPOINT());
		return Pagination.getgoogleData();
	}
	@Override
	public Set<JSONObject> findChannel(String query) {
		this.request = new OAuthRequest(Verb.GET, Config.Youtube_Search_ENDPOINT(), this.service);
		this.request.addParameter("q", query);
		this.request.addParameter("part", "snippet");
		this.request.addParameter("type", "channel");
		this.request.addParameter("key", Config.GOOGLE_API_KEY);
		Pagination.buildPaginator(this.service).googlePaginator(this.request,Config.Youtube_Search_ENDPOINT());
		return Pagination.getgoogleData();
	}
	@Override
	public Set<JSONObject> findPlaylist(String query) 
	{
		this.request = new OAuthRequest(Verb.GET, Config.Youtube_Search_ENDPOINT(), this.service);
		this.request.addParameter("q", query);
		this.request.addParameter("part", "snippet,statistics");
		this.request.addParameter("type", "playlist");
		this.request.addParameter("key", Config.GOOGLE_API_KEY);
		Pagination.buildPaginator(this.service).googlePaginator(this.request,Config.Youtube_Search_ENDPOINT());
		return Pagination.getgoogleData();
	}
	@Override
	public Set<JSONObject> getChannelActivities(String channelID) {
		this.request = new OAuthRequest(Verb.GET, Config.Youtube_Activities_ENDPOINT(), this.service);
		this.request.addParameter("channelId", channelID);
		this.request.addParameter("part", "snippet");
		this.request.addParameter("key", Config.GOOGLE_API_KEY);
		Pagination.buildPaginator(this.service).googlePaginator(this.request,Config.Youtube_Activities_ENDPOINT());
		return Pagination.getgoogleData();
	}
	@Override
	public Set<JSONObject> getChannel(String channelID) {
		this.request = new OAuthRequest(Verb.GET, Config.Youtube_Channel_ENDPOINT(), this.service);
		this.request.addParameter("id", channelID);
		this.request.addParameter("part", "snippet,statistics");
		this.request.addParameter("key", Config.GOOGLE_API_KEY);
		return Parser.parseArray(this.request.send(), "items");
	}
	@Override
	public Set<JSONObject> getVideo(String videoID) {
		this.request = new OAuthRequest(Verb.GET, Config.Youtube_Video_ENDPOINT(), this.service);
		this.request.addParameter("id", videoID);
		this.request.addParameter("part", "snippet,statistics");
		this.request.addParameter("key", Config.GOOGLE_API_KEY);
		return Parser.parseArray(this.request.send(), "items");
	}
	@Override
	public Set<JSONObject> getPlaylist(String playlistID) {
		this.request = new OAuthRequest(Verb.GET, Config.Youtube_Playlist_ENDPOINT(), this.service);
		this.request.addParameter("channelId", playlistID);
		this.request.addParameter("part", "snippet");
		this.request.addParameter("key", Config.GOOGLE_API_KEY);
		return Parser.parseArray(this.request.send(), "items");
	}
	@Override
	public String getVideoRating(String videoID) {
		this.request = new OAuthRequest(Verb.GET, Config.Youtube_rating_ENDPOINT(), this.service);
		this.request.addParameter("id", videoID);
		this.service.signRequest((OAuth2AccessToken) Auth.getGoogleInstance().getUserAccessToken(), request);
		return Parser.parseArray(this.request.send(), "items").toString();
	}
	@Override
	public Set<JSONObject> searchYoutube(String query) {
		this.request = new OAuthRequest(Verb.GET, Config.Youtube_Search_ENDPOINT(), this.service);
		this.request.addParameter("q", query);
		this.request.addParameter("part", "snippet");
		this.request.addParameter("key", Config.GOOGLE_API_KEY);
		Pagination.buildPaginator(this.service).googlePaginator(this.request,Config.Youtube_Search_ENDPOINT());
		return Pagination.getgoogleData();
	}
	@Override
	public Set<JSONObject> getChannelSection(String channelID) 
	{
		this.request = new OAuthRequest(Verb.GET, Config.Youtube_channelSection_ENDPOINT(), this.service);
		this.request.addParameter("channelId", channelID);
		this.request.addParameter("key", Config.GOOGLE_API_KEY);
		this.request.addParameter("part", "snippet");
		return Parser.parseArray(this.request.send(), "items");
	}
	@Override
	public Set<JSONObject> getcommentThread(String VideoID) 
	{
		this.request = new OAuthRequest(Verb.GET, Config.Youtube_comments_ENDPOINT(), this.service);
		this.request.addParameter("videoId", VideoID);
		this.request.addParameter("part", "snippet");
		this.request.addParameter("key", Config.GOOGLE_API_KEY);
		Pagination.buildPaginator(this.service).googlePaginator(this.request,Config.Youtube_comments_ENDPOINT());
		return Pagination.getgoogleData();
	}
	@Override
	public Set<JSONObject> getcaptionTrack(String VideoID) 
	{
		this.request = new OAuthRequest(Verb.GET, Config.Youtube_captionTrack_ENDPOINT(), this.service);
		this.request.addParameter("videoId", VideoID);
		this.request.addParameter("part", "snippet");
		this.request.addParameter("key", Config.GOOGLE_API_KEY);
		return Parser.parseArray(this.request.send(), "items");
	}
	@Override
	public Set<JSONObject> getSubscriptions(String channelID) {
		this.request = new OAuthRequest(Verb.GET, Config.Youtube_subscriptions_ENDPOINT(), this.service);
		this.request.addParameter("channelId", channelID);
		this.request.addParameter("part", "snippet");
		this.request.addParameter("key", Config.GOOGLE_API_KEY);
		Pagination.buildPaginator(this.service).googlePaginator(this.request,Config.Youtube_subscriptions_ENDPOINT());
		return Pagination.getgoogleData();
	}
}
