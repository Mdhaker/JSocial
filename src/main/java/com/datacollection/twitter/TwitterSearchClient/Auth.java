package com.datacollection.twitter.TwitterSearchClient;

import com.github.scribejava.apis.TwitterApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.oauth.OAuth10aService;

public class Auth {
	private OAuth10aService service;
	private OAuth1AccessToken accessToken;
	private static Auth instance = null;
	public static Auth getInstance()
	{
		if(instance == null) 
		{
        instance = new Auth();
		}
     return instance;
    }
	private Auth()
	{
		this.service = new ServiceBuilder()
                .apiKey(Config.CONSUMER_ID)
                .apiSecret(Config.CONSUMER_SECRET)
                .build(TwitterApi.instance());		
	}
	public OAuth1AccessToken getAppAccessToken()
	{
		return new OAuth1AccessToken(Config.ACCESS_TOKEN,Config.ACCESS_TOKEN_SECRET);
	}
	public OAuth10aService getService()
	{
		return this.service;
	}
	
}

