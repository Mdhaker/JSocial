package com.datacollection.utils;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;
import java.util.Scanner;

import com.datacollection.config.Config;
import com.datacollection.services.InstagramAPI;
import com.github.scribejava.apis.FacebookApi;
import com.github.scribejava.apis.FlickrApi;
import com.github.scribejava.apis.GoogleApi20;
import com.github.scribejava.apis.LinkedInApi20;
import com.github.scribejava.apis.TumblrApi;
import com.github.scribejava.apis.TwitterApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.oauth.OAuth10aService;
import com.github.scribejava.core.oauth.OAuth20Service;


public class Auth {
	private Object service;
	private static Auth instance = null;
	private final Scanner keyboard = new Scanner(System.in);
	private String currentNetwork;
	
	/**
	 * OAuth provider for twitter
	 * @return
	 */
	public static Auth getTwitterInstance()
	{
        instance = new Auth((OAuth10aService) new ServiceBuilder()
                .apiKey(Config.TWITTER_CONSUMER_ID)
                .apiSecret(Config.TWITTER_CONSUMER_SECRET)
                .callback(Config.CALLBACK_URL)
                .build(TwitterApi.instance()));
       instance.setCurrentNetwork("TWITTER");
        return instance;
    }
	/**
	 * OAuth provider for Flickr
	 * @return
	 */
	public static Auth getFlickrInstance() {
		 instance = new Auth((OAuth10aService) new ServiceBuilder()
	                .apiKey(Config.FILCKER_CONSUMER_ID)
	                .apiSecret(Config.FILCKER_CONSUMER_SECRET)
	                .callback(Config.CALLBACK_URL)
	                .build(FlickrApi.instance()));
	       instance.setCurrentNetwork("FLICKR");
	        return instance;
	}
	/**
	 * OAuth provider for facebook
	 * @return
	 */
	public static Auth getFacebookInstance()
	{
		instance = new Auth((OAuth20Service) new ServiceBuilder()
                .apiKey(Config.FACEBOOK_APP_ID)
                .apiSecret(Config.FACEBOOK_APP_SECRET)
                .state("secret" + new Random().nextInt(999999))
                .callback(Config.CALLBACK_URL)
                .build(FacebookApi.instance()));
		instance.setCurrentNetwork("FACEBOOK");
		return instance;
	}
	/**
	 * OAuth provider for Linkedin
	 */
	public static Auth getLinkedinInstance()
	{
		instance = new Auth((OAuth20Service) new ServiceBuilder()
				.apiKey(Config.LINKEDIN_APP_ID)
				.apiSecret(Config.LINKEDIN_APP_SECRET)
				.scope("r_basicprofile r_emailaddress") // replace with desired scope
				.callback(Config.CALLBACK_URL)
				.state("secret" + new Random().nextInt(999999))
				.build(LinkedInApi20.instance()));
		instance.setCurrentNetwork("LNIKEDIN");
		return instance;
	}
	/**
	 * OAuth provider for Google
	 * @return
	 */
	public static Auth getGoogleInstance()
	{
		instance = new Auth((OAuth20Service) new ServiceBuilder()
				.apiKey(Config.GOOGLE_APP_ID)
				.apiSecret(Config.GOOGLE_APP_SECRET)
				.scope("profile") // replace with desired scope
				.callback(Config.CALLBACK_URL)
				.state("secret" + new Random().nextInt(999999))
				.build(GoogleApi20.instance()));
		instance.setCurrentNetwork("G+");
		return instance;
	}
	
	/**
	 * OAuth provider for Instagram
	 * @return
	 */
	public static Auth getInstagramInstance()
	{
		instance = new Auth((OAuth20Service) new ServiceBuilder()
				.apiKey(Config.INSTAGRAM_APP_ID)
				.apiSecret(Config.INSTAGRAM_APP_SECRET)
				.scope("public_content basic") // replace with desired scope
				.callback(Config.CALLBACK_URL)
				.state("secret" + new Random().nextInt(999999))
				.build(InstagramApi20.instance()));
		instance.setCurrentNetwork("Instagram");
		return instance;
	}
	
	/**
	 * OAuth provider for Tumblr
	 * @return
	 */
	public static Auth getTumblrInstance()
	{
		instance = new Auth((OAuth10aService) new ServiceBuilder()
                .apiKey(Config.Tumblr_CONSUMER_ID)
                .apiSecret(Config.Tumblr_CONSUMER_SECRET)
                .callback(Config.CALLBACK_URL)
                .build(TumblrApi.instance()));
       instance.setCurrentNetwork("Tumblr");
        return instance;
	}
	
	
	/**
	 * constructor
	 * @param service
	 * @param accessT
	 * @param accessTS
	 */
	private Auth(Object service)
	{
		this.service = service ;		
	}
	/**
	 * This is a string generated token for App based calls
	 * @return
	 */
	public Object getAppAccessToken()
	{
		if(this.currentNetwork.equals("TWITTER"))
			return new OAuth1AccessToken(Config.TWITTER_ACCESS_TOKEN,Config.TWITTER_ACCESS_TOKEN_SECRET);
		else if(this.currentNetwork.equals("FACEBOOK"))
			return new OAuth2AccessToken(Config.FACEBOOK_APP_ID+"|"+Config.FACEBOOK_APP_SECRET);
		else if(this.currentNetwork.equals("G+"))
			return new OAuth2AccessToken(Config.GOOGLE_API_KEY);
		else if(this.currentNetwork.equals("Instagram"))
			return new OAuth2AccessToken(Config.INSTAGRAM_APP_ID);
		
		else
			return null;
	}
	/**
	 * User generated token
	 * @return
	 */
	public Object getUserAccessToken()
	{
		String authUrl;
		AuthServer server = AuthServer.start();
		try 
		{
			// Oauth 1 providers
			if(this.currentNetwork.equals("TWITTER")||this.currentNetwork.equals("FLICKR")||this.currentNetwork.equalsIgnoreCase("Tumblr"))
			{
			OAuth1RequestToken requestToken = ((OAuth10aService) service).getRequestToken();
			 authUrl= ((OAuth10aService) service).getAuthorizationUrl(requestToken);
			 AuthServer.openBrowser(authUrl);
			 
			 int i=0;
				while(server.getCode().equals(""))
				 {
					//waiting loop
					if(i==0)
					 System.out.println("waiting authentification ...");
					i++;
				 }	    
			 
			 
			 String code = server.getCode();
			 System.out.println("this is the code result"+code);
			 return ((OAuth10aService) service).getAccessToken(requestToken, code);
			}
			
			// Oauth 2.0 providers
			else if(this.currentNetwork.equalsIgnoreCase("FACEBOOK")||this.currentNetwork.equalsIgnoreCase("LNIKEDIN")||this.currentNetwork.equalsIgnoreCase("G+")||this.currentNetwork.equalsIgnoreCase("Instagram"))
			{
				authUrl = ((OAuth20Service)this.service).getAuthorizationUrl();
				AuthServer.openBrowser(authUrl);
				// for console display
				int i=0;
				while(server.getCode().equals(""))
				 {
					//waiting loop
					if(i==0)
					 System.out.println("waiting authentification ...");
					i++;
				 }
				System.out.println("this is my code : "+server.getCode());
		        return ((OAuth20Service)service).getAccessToken(server.getCode());
		        
			}
			else return null;
			 
		} catch (IOException e) 
		{
			authUrl ="http://localhost";
			e.printStackTrace();
			return null;
			
		}
		
	}
	
	public Object getService()
	{
		if(this.currentNetwork.equalsIgnoreCase("TWITTER")||this.currentNetwork.equalsIgnoreCase("FLICKR")||this.currentNetwork.equalsIgnoreCase("Tumblr"))
			return (OAuth10aService) this.service;
		else if(this.currentNetwork.equalsIgnoreCase("FACEBOOK")||this.currentNetwork.equalsIgnoreCase("LNIKEDIN")||this.currentNetwork.equalsIgnoreCase("G+")||this.currentNetwork.equalsIgnoreCase("Instagram"))
			return (OAuth20Service) this.service;
		
		else return null;
	}
	
	// Getter & Setter 
	public String getCurrentNetwork() {
		return currentNetwork;
	}
	public void setCurrentNetwork(String currentNetwork) {
		this.currentNetwork = currentNetwork;
	}
	
	
}

