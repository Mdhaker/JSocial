package com.datacollection.services;

import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

import com.datacollection.config.Auth;
import com.datacollection.config.Config;
import com.datacollection.interfaces.Linkedin;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;

public class LinkedinAPI implements Linkedin {
	
	private OAuthRequest request;
	private OAuth20Service service ;
	
	
	public LinkedinAPI() 
	{
		this.service = (OAuth20Service)Auth.getLinkedinInstance().getService();
	}

	/**
	 * build for each action, Reader
	 * @return
	 */
	public static Linkedin build()
	{
		return  new LinkedinAPI();
	}

	public JSONObject getUser() {
		
		this.request = new OAuthRequest(Verb.GET, Config.getLinkedINUserInfo_ENDPOINT(), this.service);
		this.request.addHeader("x-li-format", "json");
		this.service.signRequest((OAuth2AccessToken) Auth.getLinkedinInstance().getUserAccessToken(), request); 
		Response response = request.send();
		
		JSONObject result=new JSONObject("{'Error':'insidecode'}");
		try {
			result = new JSONObject(response.getBody().toString());
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	

}
