package com.datacollection.twitter.TwitterSearchClient;


import org.json.JSONObject;
public class App 
{
    public static void main( String[] args )
    {
    	SearchFilter filter = SearchFilter.build().havingAllWords("chahed","youssef","beji").havingImageLinks(true).havingNoneOf("nahda");
    	java.util.Iterator<JSONObject> tweetIt = TwitterApi.init().getTweets(filter).iterator();
    	while(tweetIt.hasNext())
    	{
    		System.out.println(tweetIt.next().get("text"));
    	}
    }
}
