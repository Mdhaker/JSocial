package com.datacollection.config;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

import com.github.scribejava.apis.TwitterApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.oauth.OAuth10aService;

public class Auth {
	private OAuth10aService service;
	private OAuth1AccessToken accessToken;
	private static Auth instance = null;
	private String appAccessToken,appAcessTokenSecret;
	private final Scanner keyboard = new Scanner(System.in);
	
	/**
	 * OAuth privider for twitter
	 * @return
	 */
	public static Auth getTwitterInstance()
	{
        instance = new Auth(new ServiceBuilder()
                .apiKey(Config.TWITTER_CONSUMER_ID)
                .apiSecret(Config.TWITTER_CONSUMER_SECRET)
                .build(TwitterApi.instance()),Config.TWITTER_ACCESS_TOKEN,Config.TWITTER_ACCESS_TOKEN_SECRET);
       return instance;
    }
	private Auth(OAuth10aService service,String accessT,String accessTS)
	{
		this.appAccessToken = accessT;
		this.appAcessTokenSecret = accessTS;
		this.service = service ;		
	}
	/**
	 * This is a string generated token for App based calls
	 * @return
	 */
	public OAuth1AccessToken getAppAccessToken()
	{
		return new OAuth1AccessToken(this.appAccessToken,this.appAcessTokenSecret);
	}
	/**
	 * User generated token
	 * @return
	 */
	public OAuth1AccessToken getUserAccessToken()
	{
		String authUrl;
		try 
		{
			OAuth1RequestToken requestToken = service.getRequestToken();
			 authUrl= service.getAuthorizationUrl(requestToken);
			 this.openBrowser(authUrl);
			 System.out.println(">>");
			 System.out.println(">>");
			 System.out.print(">>");
			 String oauthVerifier = keyboard.nextLine();
			 return service.getAccessToken(requestToken, oauthVerifier);
			 
		} catch (IOException e) 
		{
			authUrl ="http://localhost";
			e.printStackTrace();
			return null;
			
		}
		
	}
	
	public OAuth10aService getService()
	{
		return this.service;
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
	
}

