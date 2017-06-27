package com.datacollection.services;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

import com.datacollection.config.Config;
import com.datacollection.interfaces.Tumblr;
import com.datacollection.interfaces.Twitter;
import com.datacollection.utils.Auth;
import com.datacollection.utils.Parser;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth10aService;

public class TumblrAPI implements Tumblr{

	private OAuthRequest request;
	private OAuth10aService service;
	
	/**
	 * Default Constructor
	 */
	private TumblrAPI()
	{
		this.service = (OAuth10aService) Auth.getTwitterInstance().getService();
	}
	/**
	 * build for each action, Reader
	 * @return
	 */
	public static Tumblr build()
	{
		return  new TumblrAPI();
	}
	@Override
	public Set<JSONObject> findPosts(String tags) {
		this.request = new OAuthRequest(Verb.GET, Config.Tumblr_tagged_ENDPOINT(), this.service);
		this.request.addParameter("tag", tags);
		this.request.addParameter("api_key", Config.Tumblr_CONSUMER_ID);
		Response response = request.send();
		
		return Parser.parseArray(response, "response");
	}

}
