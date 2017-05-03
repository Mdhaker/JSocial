package com.datacollection.config;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;
import java.util.Scanner;

import com.github.scribejava.apis.FacebookApi;
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
                .build(TwitterApi.instance()));
       instance.setCurrentNetwork("TWITTER");
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
                .apiSecret(Config.FACEBOOK_SECRET_ID)
                .state("secret" + new Random().nextInt(999999))
                .callback("http://murmuring-tundra-1599.herokuapp.com/oauth_callback/")
                .build(FacebookApi.instance()));
		instance.setCurrentNetwork("FACEBOOK");
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
			return new OAuth2AccessToken(Config.FACEBOOK_APP_ID+"|"+Config.FACEBOOK_SECRET_ID);
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
		try 
		{
			if(this.currentNetwork.equals("TWITTER"))
			{
			OAuth1RequestToken requestToken = ((OAuth10aService) service).getRequestToken();
			 authUrl= ((OAuth10aService) service).getAuthorizationUrl(requestToken);
			 this.openBrowser(authUrl);
			 System.out.println(">>");
			 System.out.println(">>");
			 System.out.print(">>");
			 String oauthVerifier = keyboard.nextLine();
			 return ((OAuth10aService) service).getAccessToken(requestToken, oauthVerifier);
			}
			else if(this.currentNetwork.equals("FACEBOOK"))
			{
				authUrl = ((OAuth20Service)this.service).getAuthorizationUrl();
				this.openBrowser(authUrl);
				System.out.println("paste the authorization code here");
		        System.out.print(">>");
		        final String code = keyboard.nextLine();
		        return ((OAuth20Service)service).getAccessToken(code);
		        
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
		if(this.currentNetwork.equals("TWITTER"))
			return (OAuth10aService) this.service;
		else if(this.currentNetwork.equals("FACEBOOK"))
			return (OAuth20Service) this.service;
		else return null;
	}
	/**
	 * simple method to open browser with a target url (authorization) url, in manuel oauth test
	 * @param url
	 */
	private void openBrowser(String url) 
	{
        if(Desktop.isDesktopSupported()){
            Desktop desktop = Desktop.getDesktop();
          
                try {
					desktop.browse(new URI(url));
				} catch (IOException e) {
					e.printStackTrace();
				} catch (URISyntaxException e) {
					e.printStackTrace();
				}
           
        }else{
            Runtime runtime = Runtime.getRuntime();
            try {
                runtime.exec("xdg-open " + url);
                Runtime.getRuntime().exec("cls");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
	// Getter & Setter 
	public String getCurrentNetwork() {
		return currentNetwork;
	}
	public void setCurrentNetwork(String currentNetwork) {
		this.currentNetwork = currentNetwork;
	}
	
}

