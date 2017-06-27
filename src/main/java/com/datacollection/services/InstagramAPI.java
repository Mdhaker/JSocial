package com.datacollection.services;

import java.io.IOException;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

import com.datacollection.config.Config;
import com.datacollection.interfaces.Facebook;
import com.datacollection.interfaces.Instagram;
import com.datacollection.utils.Auth;
import com.datacollection.utils.Parser;
import com.github.scribejava.core.builder.api.DefaultApi20;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;

public class InstagramAPI implements Instagram{

	private OAuthRequest request;
	private OAuth20Service service ;	
	public InstagramAPI() 
	{
		this.service= (OAuth20Service)Auth.getInstagramInstance().getService();		
	}
		
	/**
	 * Factory method
	 * @return
	 */
	public static Instagram build()
	{
		return  new InstagramAPI();
	}
	@Override
	public JSONObject getUser() {
		this.request = new OAuthRequest(Verb.GET, Config.getInstagramUserInfo_ENDPOINT(), this.service);
		this.service.signRequest((OAuth2AccessToken) Auth.getInstagramInstance().getUserAccessToken(), request); 
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

	@Override
	public Set<JSONObject> getUser(String query) 
	{
		this.request = new OAuthRequest(Verb.GET, Config.getInstagramUserSearch_ENDPOINT(), this.service);
		this.request.addParameter("q", query);
		this.service.signRequest((OAuth2AccessToken) Auth.getInstagramInstance().getUserAccessToken(), request); 
		Response response = request.send();
		System.out.println(this.request.getCompleteUrl());
		return Parser.parseArray(response,"data");
	}
	
}
