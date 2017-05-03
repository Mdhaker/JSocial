package com.datacollection.services;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.datacollection.config.Auth;
import com.datacollection.config.Config;
import com.datacollection.interfaces.Reader;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.github.scribejava.core.oauth.OAuthService;

public class FacebookAPI implements Reader {
	
	private OAuthRequest request;
	private OAuth20Service service = (OAuth20Service)Auth.getFacebookInstance().getService();;
	
	
	public FacebookAPI() 
	{
		
	}

	/**
	 * build for each action, Reader
	 * @return
	 */
	public static Reader buildReader()
	{
		return  new FacebookAPI();
	}

	public JSONObject getUser() {
		
		this.request = new OAuthRequest(Verb.GET, Config.getFacebookUserInfo_ENDPOINT(), this.service);
		this.service.signRequest((OAuth2AccessToken) Auth.getFacebookInstance().getUserAccessToken(), request); 
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

	public Set<JSONObject> getPlace(String query) {
		Set<JSONObject> result = new HashSet<JSONObject>();
		this.request = new OAuthRequest(Verb.GET, Config.getFacebookPlaceInfo_ENDPOINT(), this.service);
		this.request.addParameter("q", query);
		this.service.signRequest((OAuth2AccessToken) Auth.getFacebookInstance().getAppAccessToken(), request); 
		Response response = request.send();
		
		try {
			JSONArray places = new JSONObject(response.getBody()).getJSONArray("data");
			
			for(int i=0;i<places.length();i++)
			{
				result.add(places.getJSONObject(i));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

}
