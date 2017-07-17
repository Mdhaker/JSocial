package com.datacollection.services;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;
import com.datacollection.config.Config;
import com.datacollection.interfaces.Twitter;
import com.datacollection.utils.Auth;
import com.datacollection.utils.Pagination;
import com.datacollection.utils.Parser;
import com.datacollection.utils.SearchFilter;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth10aService;

public class TwitterAPI implements Twitter{

	private OAuthRequest request;
	private OAuth10aService service;
	private Map<String,String> params;
	
	/**
	 * Default Constructor
	 */
	private TwitterAPI()
	{
		this.service = (OAuth10aService) Auth.getTwitterInstance().getService();
		this.params = new HashMap<String,String>();
	}
	
	/**
	 * build for each action, Reader
	 * @return
	 */
	public static Twitter build()
	{
		return  new TwitterAPI();
	}

	/**
	 * get Json list of tweets by query
	 */
	public Set<JSONObject> getTweets(String query) 
	{
		this.request = new OAuthRequest(Verb.GET,Config.getTwitterSearch_ENDPOINT(), this.service);
		this.request.addParameter("q", query);
		this.request.addParameter("count", "30");
		for(Entry<String,String> entry : params.entrySet())
		{
			this.request.addParameter(entry.getKey(), entry.getValue());
		}
		this.service.signRequest((OAuth1AccessToken) Auth.getTwitterInstance().getAppAccessToken(), request); 
		Pagination.initPaginator("twitter");
		Pagination.buildPaginator(this.service).twitterPaginator(this.request);
		return Pagination.gettwitterData();
		
	}
	/**
	 * get Json list of tweets by filter
	 */
	public Set<JSONObject> getTweets(SearchFilter.TwitterFilter filter) 
	{
		String query=filter.getExactPhrase();
		// Query have one of those word operator "space"
		for(String word:filter.getAllWords())
		{
			query=query.concat(" "+word);
		}
		// Query have one of those words operator "OR"
		Iterator<String> it = filter.getOneOfWords().iterator();
		for(String word:filter.getOneOfWords())
		{
			query=query.concat(" "+word);
			it.next();
			if(it.hasNext())
				query=query.concat(" OR");
		}
		// Query don't have these words
		for(String word : filter.getNoneWords())
		{
			query=query.concat(" -"+word);
		}
		// Query have these hashtags
		for(String hashtag:filter.getHashtags())
		{
			if(!hashtag.isEmpty()&&!(hashtag.charAt(0)=='#'))
				hashtag="#"+hashtag;
			query = query.concat(" "+hashtag);
		}
		// Query have these mentioned accounts 
		for(String account:filter.getMentionAccount())
		{
			query = query.concat(" @"+account);
		}
		// Query sent from below accounts
		for(String account: filter.getSentFrom())
		{
			query = query.concat(" from:"+account);
		}
		// Query sent to below accounts
		for(String account: filter.getSentTo())
		{
			query = query.concat(" to:"+account);
		}
		// Query having below word in url
		for(String word: filter.getUrlWords())
		{
			query = query.concat(" url:"+word);
		}
		// Query sent since the below date
		if(filter.getSince()!=null&&filter.getSince().length()>0)	
			query = query.concat(" since:"+filter.getSince());
		
		// Query sent before the below date
		if(filter.getSince()!=null&&filter.getUntil().length()>0)	
			query = query.concat(" until:"+filter.getUntil());
		
		// Query with postive attitude
		if(filter.isPositiveAttitude())
			query = query.concat(" :)");

		// Query with negative attitude
		if(filter.isNegativeAttitude())
			query = query.concat(" :(");
		
		// Query asking question
		if(filter.isQuestion())
			query = query.concat(" ?");
		
		// Query with image links
		if(filter.isImageLinks())
			query = query.concat(" filter:images");
		
		// Query with native videos
		if(filter.isNativeVideo())
			query = query.concat(" filter:native_video");
		
		//Query with vine
		if(filter.isVineMedia())
			query = query.concat(" filter:vine");
		
		//Query with media (video / photo)
		if(filter.isMedia())
			query = query.concat(" filter:media");
		
		//Query with periscope video
		if(filter.isPeriscopeVideo())
			query = query.concat(" filter:periscope");
		
		//Query safe from senstive content
		if(filter.isSafe())
			query = query.concat(" filter:safe");
		//Query with link
		if(filter.isLink())
			query = query.concat(" filter:links");
		
		return this.getTweets(query);
	}
	
	public TwitterAPI setGeo(float longitude, float latitude, float radius) 
	{
		this.request.addParameter("geocode", String.valueOf(longitude)+","
				+String.valueOf(latitude)+","+String.valueOf(radius)+"km");		
		return this;
	}
	
	public TwitterAPI setLang(String lang)
	{
		this.params.put("lang",lang);
		return this;
	}
	public TwitterAPI setCount(int count) 
	{
		this.params.put("count",Integer.toString(count));	
		return this;
	}
	public TwitterAPI setEntity(boolean include) 
	{
		this.params.put("include_entities",Boolean.toString(include));
		return this;
	}
	
	public TwitterAPI setResultType(String result_type)
	{
		if(result_type.equals("recent")||result_type.equals("popular"))
			this.params.put("result_type",result_type);
		return this;
	}
	public JSONObject getUser() 
	{
		this.request = new OAuthRequest(Verb.GET, Config.getTwitterUserInfo_ENDPOINT(), this.service);
		this.service.signRequest((OAuth1AccessToken) Auth.getTwitterInstance().getUserAccessToken(), request); 
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
	
	public Set<JSONObject> getPlaces(String query) 
	{
		this.request = new OAuthRequest(Verb.GET, Config.getTwitterPlaceInfo_ENDPOINT(), this.service);
		this.request.addParameter("query", query);
		this.service.signRequest((OAuth1AccessToken) Auth.getTwitterInstance().getAppAccessToken(), request); 
		Response response = request.send();
		return Parser.parseArray(response, "result","places");
	}
	
	
}
