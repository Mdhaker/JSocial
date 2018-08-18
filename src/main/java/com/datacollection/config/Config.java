package com.datacollection.config;

import java.io.PrintStream;

import com.datacollection.utils.HideStream;
public class Config 
{
	public static String CALLBACK_URL="https://localhost:9000/";
	public static String SSL_KEYSOTRE_FILE="/cert/certificate.jks";
	public static String KEYSOTRE_PASSWORD="123456789";
	public static String SSL_CA_File="/home/dhaker/Downloads/cacert.pem";
	// Twitter Creds
	public static String TWITTER_CONSUMER_ID ="xxxx";
	public static String TWITTER_CONSUMER_SECRET ="xxx";
	
	public static String TWITTER_ACCESS_TOKEN = "xxx";
	public static String TWITTER_ACCESS_TOKEN_SECRET = "xxx";
	
	public static String getTwitterSearch_ENDPOINT(){return "https://api.twitter.com/1.1/search/tweets.json";}
	public static String getTwitterUserInfo_ENDPOINT(){return "https://api.twitter.com/1.1/account/verify_credentials.json";}
	public static String getTwitterPlaceInfo_ENDPOINT(){return "https://api.twitter.com/1.1/geo/search.json";}
	
	// Facebook Cred
	
	public static String FACEBOOK_APP_ID ="xxx";
	public static String FACEBOOK_APP_SECRET ="xxx";
	
	public static String getFacebook_BASEURL(){return "https://graph.facebook.com";}
	public static String getFacebookUserInfo_ENDPOINT(){return "https://graph.facebook.com/me/";}
	public static String getFacebookSearch_ENDPOINT(){return "https://graph.facebook.com/search";}
	public static String getFacebookSearchTopic_ENDPOINT(){return "https://graph.facebook.com/v2.9/search?type=topic";}
	
	//LinkedIn Cred
	
	public static String LINKEDIN_APP_ID="xxx";
	public static String LINKEDIN_APP_SECRET="xxx";
	public static String getLinkedINUserInfo_ENDPOINT(){return "https://api.linkedin.com/v1/people/~?format=json";}
	
	//Google Creds
	
	public static String GOOGLE_APP_ID="xxx.apps.googleusercontent.com";
	public static String GOOGLE_APP_SECRET="xxx";
	public static String GOOGLE_API_KEY ="xxx";
	public static String getGoogleUserInfo_ENDPOINT(){return "https://www.googleapis.com/plus/v1/people/me";}
	public static String getGooglePublicUser_ENDPOINT(){return "https://www.googleapis.com/plus/v1/people";}
	public static String getGooglePublicActivity_ENDPOINT(){return "https://www.googleapis.com/plus/v1/activities";}
	public static String getGooglePublicComments_ENDPOINT(){return "https://www.googleapis.com/plus/v1/comments";}
	private static PrintStream showStream = System.out;
	private static PrintStream hideStream    = new PrintStream(new HideStream());
	public static boolean Debug = false ;
	public static void showDebug()
	{
			System.setOut(showStream);			
	}
	public static void hideDebug()
	{
		System.setOut(hideStream);
	}
	
	
	
	//Flickler Cred
	
	public static String FILCKER_CONSUMER_ID="xxx";
	public static String FILCKER_CONSUMER_SECRET="xxx";
	public static String FILCKER_BASE_URL = "https://api.flickr.com/services/rest/";
	
	// Instagram Cred
	public static String INSTAGRAM_APP_ID ="xxx";
	public static String INSTAGRAM_APP_SECRET ="xxx";
	public static String getInstagram_BASEURL(){return "https://graph.facebook.com";}
	public static String getInstagramUserInfo_ENDPOINT(){return "https://api.instagram.com/v1/users/self/";}
	public static String getInstagramUserSearch_ENDPOINT(){return "https://api.instagram.com/v1/users/search";}
	public static String getInstagramSearchTopic_ENDPOINT(){return "https://graph.facebook.com/v2.9/search?type=topic";}
	
	// Youtube creds
	public static String YOUTUBE_API_KEY ="xxx";
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
	public static String Tumblr_CONSUMER_ID ="xx";
	public static String Tumblr_CONSUMER_SECRET ="xxx";
	public static String Tumblr_tagged_ENDPOINT(){return "https://api.tumblr.com/v2/tagged";}
	public static String Tumblr_blog_likes_ENDPOINT(String blog){return "https://api.tumblr.com/v2/blog/"+blog+"/likes";}
	public static String Tumblr_blog_info_ENDPOINT(String blog){return "https://api.tumblr.com/v2/blog/"+blog+"/info";}
	public static String Tumblr_blog_following_ENDPOINT(String blog){return "https://api.tumblr.com/v2/blog/"+blog+"/following";}
	public static String Tumblr_blog_followers_ENDPOINT(String blog){return "https://api.tumblr.com/v2/blog/"+blog+"/followers";}
	public static String Tumblr_blog_posts_ENDPOINT(String blog){return "https://api.tumblr.com/v2/blog/"+blog+"/posts";}
	
	public static boolean isWindows()
	{
		return (System.getProperty("os.name").equalsIgnoreCase("Windows"));
	}
	
	public static boolean isLinux()
	{
		return (System.getProperty("os.name").equalsIgnoreCase("linux"));
	}
	
}
