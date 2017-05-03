package com.datacollection.config;

public class Config 
{
	// Twitter Creds
	public static String TWITTER_CONSUMER_ID ="EFupjPFqv2ItkznGT4dQS9Ep0";
	public static String TWITTER_CONSUMER_SECRET ="WLJ7sPeBYvWhJfLVPN9GqkJbpRGBcrkkm6a9yJBccB2nppPiPM";
	public static String TWITTER_ACCESS_TOKEN = "836229387704012800-kFREblD1urnsp5NtxQq0XOT9Dwy4CQu";
	public static String TWITTER_ACCESS_TOKEN_SECRET = "paUB6xoqqjpu02i5uZdFfj5HwiB9sQppilARbWW9XtP7n";
	
	public static String getTwitterSearch_ENDPOINT(){return "https://api.twitter.com/1.1/search/tweets.json";}
	public static String getTwitterUserInfo_ENDPOINT(){return "https://api.twitter.com/1.1/account/verify_credentials.json";}
	public static String getTwitterPlaceInfo_ENDPOINT(){return "https://api.twitter.com/1.1/geo/search.json";}
	
	// Facebook Cred
	
	public static String FACEBOOK_APP_ID ="166536670200421";
	public static String FACEBOOK_SECRET_ID ="b8948bb3c57f35a94afee5d5869854b7";
	
	public static String getFacebookPlaceInfo_ENDPOINT(){return "https://graph.facebook.com/search?type=place";}
	public static String getFacebookUserInfo_ENDPOINT(){return "https://graph.facebook.com/me/";}
	
}
