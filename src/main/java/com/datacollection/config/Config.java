package com.datacollection.config;

import java.io.PrintStream;

import com.datacollection.utils.HideStream;

public class Config 
{
	public static String CALLBACK_URL="https://localhost:9000/";
	public static String SSL_KEYSOTRE_FILE="/home/dhaker/certificate.jks";
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
	public static String getGooglePublicActivity_ENDPOINT(){return "https://www.googleapis.com/plus/v1/activities";}
	public static String getGooglePublicComments_ENDPOINT(){return "https://www.googleapis.com/plus/v1/comments";}
	public static PrintStream showStream = System.out;
	public static PrintStream hideStream    = new PrintStream(new HideStream());
	public static boolean Debug = false ;
	
	//Flickler Cred
	
	public static String FILCKER_CONSUMER_ID="63025183effe6a604cf3079f10a06bd2";
	public static String FILCKER_CONSUMER_SECRET="dee0e8d68955b338";
	public static String FILCKER_BASE_URL = "https://api.flickr.com/services/rest/";
	
	// Instagram Cred
	public static String INSTAGRAM_APP_ID ="d854ac99b3134f9eb79ef8d268211587";
	public static String INSTAGRAM_APP_SECRET ="f4c766a5fbf04ca5b300a75dc731dd2b";
	public static String getInstagram_BASEURL(){return "https://graph.facebook.com";}
	public static String getInstagramUserInfo_ENDPOINT(){return "https://api.instagram.com/v1/users/self/";}
	public static String getInstagramUserSearch_ENDPOINT(){return "https://api.instagram.com/v1/users/search";}
	public static String getInstagramSearchTopic_ENDPOINT(){return "https://graph.facebook.com/v2.9/search?type=topic";}
	
	// Youtube creds
	public static String YOUTUBE_API_KEY ="AIzaSyB4B8WXf69W7IBHkHDNbLqByisTDJBmuDs";
	public static String Youtube_Search_ENDPOINT(){return "https://www.googleapis.com/youtube/v3/search";}
	public static String Youtube_Playlist_ENDPOINT(){return "https://www.googleapis.com/youtube/v3/playlists";}
	public static String Youtube_Activities_ENDPOINT(){return "https://www.googleapis.com/youtube/v3/activities";}
	public static String Youtube_Channel_ENDPOINT(){return "https://www.googleapis.com/youtube/v3/channels";}
	public static String Youtube_Video_ENDPOINT(){return "https://www.googleapis.com/youtube/v3/videos";}
	public static String Youtube_rating_ENDPOINT(){return "https://www.googleapis.com/youtube/v3/getRating";}
	public static String Youtube_channelSection_ENDPOINT(){return "https://www.googleapis.com/youtube/v3/channelSections";}
	public static String Youtube_captionTrack_ENDPOINT(){return "https://www.googleapis.com/youtube/v3/captions";}
	public static String Youtube_comments_ENDPOINT(){return "https://www.googleapis.com/youtube/v3/commentThreads";}
	public static String Youtube_subscriptions_ENDPOINT(){return "https://www.googleapis.com/youtube/v3/subscriptions";}
	
	//Tumblr creds
	public static String Tumblr_CONSUMER_ID ="hA6c5hJ2NG7nL1BSO0YgOXnUDU6lINe8wXAL7yA9ywCFoGE3YV";
	public static String Tumblr_CONSUMER_SECRET ="3ZDxSvIaa4xW5EkI0Qz9qawtWGb7yBNuKUFXDROlN8llHSK1WG";
	public static String Tumblr_tagged_ENDPOINT(){return "https://api.tumblr.com/v2/tagged";}
	public static String Tumblr_blog_likes_ENDPOINT(String blog){return "https://api.tumblr.com/v2/blog/"+blog+"/likes";}
	public static String Tumblr_blog_info_ENDPOINT(String blog){return "https://api.tumblr.com/v2/blog/"+blog+"/info";}
	public static String Tumblr_blog_following_ENDPOINT(String blog){return "https://api.tumblr.com/v2/blog/"+blog+"/following";}
	public static String Tumblr_blog_followers_ENDPOINT(String blog){return "https://api.tumblr.com/v2/blog/"+blog+"/followers";}
	public static String Tumblr_blog_posts_ENDPOINT(String blog){return "https://api.tumblr.com/v2/blog/"+blog+"/posts";}
	
	
	
}
