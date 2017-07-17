package com.datacollection.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

import com.datacollection.config.Config;
import com.datacollection.interfaces.Google;
import com.datacollection.utils.Auth;
import com.datacollection.utils.Pagination;
import com.datacollection.utils.Parser;
import com.datacollection.utils.SearchFilter.GoogleFilter;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;

public class GoogleAPI implements Google{

	private OAuthRequest request;
	private OAuth20Service service ;
	
	
	public GoogleAPI() 
	{
		this.service = (OAuth20Service)Auth.getGoogleInstance().getService();
	}
	public static Google build()
	{
		return  new GoogleAPI();
	}
	public JSONObject getUser() {
		this.request = new OAuthRequest(Verb.GET, Config.getGoogleUserInfo_ENDPOINT(), this.service);
		this.service.signRequest((OAuth2AccessToken) Auth.getGoogleInstance().getUserAccessToken(), request); 
		Response response = request.send();
		
		JSONObject result=new JSONObject("{'Error':'insidecode'}");
		try {
			System.out.println(response.getBody().toString());
			result = new JSONObject(response.getBody().toString());
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public Set<JSONObject> getProfiles(String query) 
	{
		this.request = new OAuthRequest(Verb.GET, Config.getGooglePublicUser_ENDPOINT(), this.service);
		this.request.addParameter("query", query);
		this.request.addParameter("key", Config.GOOGLE_API_KEY);
		Pagination.initPaginator("google");
		Pagination.buildPaginator(this.service).googlePaginator(this.request,Config.getGooglePublicUser_ENDPOINT());
		return Pagination.getgoogleData();
	}
	
	@Override
	public Set<JSONObject> getActivities(String query) {
		this.request = new OAuthRequest(Verb.GET, Config.getGooglePublicActivity_ENDPOINT(), this.service);
		this.request.addParameter("query", query);
		this.request.addParameter("key", Config.GOOGLE_API_KEY);
		Pagination.initPaginator("google");
		Pagination.buildPaginator(this.service).googlePaginator(this.request,Config.getGooglePublicActivity_ENDPOINT());
		return Pagination.getgoogleData();
	}
	@Override
	public Set<JSONObject> getActivities(GoogleFilter filter) {
		this.request = new OAuthRequest(Verb.GET, Config.getGooglePublicActivity_ENDPOINT(), this.service);
		if(filter.getQuery() != null)
			this.request.addParameter("query", filter.getQuery());
		if(filter.getLanguage() != null)
			this.request.addParameter("language", filter.getLanguage());
		if(filter.getOrderBy() != null)
			this.request.addParameter("orderBy", filter.getOrderBy());
		this.request.addParameter("key", Config.GOOGLE_API_KEY);
		Pagination.initPaginator("google");
		Pagination.buildPaginator(this.service).googlePaginator(this.request,Config.getGooglePublicActivity_ENDPOINT());
		return Pagination.getgoogleData();
	}
	@Override
	public Set<JSONObject> getComments(String activityId) {
		this.request = new OAuthRequest(Verb.GET, Config.getGooglePublicActivity_ENDPOINT()+"/"+activityId+"/comments", this.service);
		
		this.request.addParameter("key", Config.GOOGLE_API_KEY);
		Pagination.initPaginator("google");
		Pagination.buildPaginator(this.service).googlePaginator(this.request,Config.getGooglePublicActivity_ENDPOINT()+"/"+activityId+"/comments");
		return Pagination.getgoogleData();
	}

}
