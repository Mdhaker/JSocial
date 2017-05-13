package com.datacollection.config;

public class Config 
{
	public static String CALLBACK_URL="https://localhost:9000/";
	public static String SSL_KEYSOTRE_FILE="/home/dhaker/key.jks";
	public static String KEYSOTRE_PASSWORD="123456789";
	public static String SSL_CA_File="/home/dhaker/Downloads/cacert.pem";
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
	public static String FACEBOOK_APP_SECRET ="b8948bb3c57f35a94afee5d5869854b7";
	
	public static String getFacebook_BASEURL(){return "https://graph.facebook.com";}
	public static String getFacebookUserInfo_ENDPOINT(){return "https://graph.facebook.com/me/";}
	public static String getFacebookSearch_ENDPOINT(){return "https://graph.facebook.com/search";}
	public static String getFacebookSearchTopic_ENDPOINT(){return "https://graph.facebook.com/v2.9/search?type=topic";}
	
	//LinkedIn Cred
	
	public static String LINKEDIN_APP_ID="77hzzfjwqlgxu9";
	public static String LINKEDIN_APP_SECRET="3qZHHnrqwR0VATAC";
	public static String getLinkedINUserInfo_ENDPOINT(){return "https://api.linkedin.com/v1/people/~?format=json";}
	
	//Google Creds
	
	public static String GOOGLE_APP_ID="986721521349-9ee3rb2q7rru4r43p8jd4rb0is1nd93k.apps.googleusercontent.com";
	public static String GOOGLE_APP_SECRET="ERZlnuybbVPN7o9Zqwq605ve";
	public static String GOOGLE_API_KEY ="AIzaSyB4B8WXf69W7IBHkHDNbLqByisTDJBmuDs";
	public static String getGoogleUserInfo_ENDPOINT(){return "https://www.googleapis.com/plus/v1/people/me";}
	public static String getGooglePublicUser_ENDPOINT(){return "https://www.googleapis.com/plus/v1/people";}
	
	
	//Flickler Cred
	
	public static String FILCKER_APP_ID="a8a3eca8e81064cfdac2baf7c445e569";
	public static String FILCKER_APP_SECRET="c2abea55d4932e70";
	public static String FILCKER_BASE_URL = "https://api.flickr.com/services/rest/";
}
