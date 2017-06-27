package com.datacollection.utils;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

import com.datacollection.config.Config;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Parameter;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth10aService;
import com.github.scribejava.core.oauth.OAuthService;

public class Pagination {
	private OAuthService service ;
	private int pagecount;
	private static Set<JSONObject> facebookData = new HashSet<JSONObject>();
	private static Set<JSONObject> twitterData = new HashSet<JSONObject>();
	private static Set<JSONObject> googleData = new HashSet<JSONObject>();
	public static Set<JSONObject> getFacebookData()
	{
		return facebookData;
	}
	public static Set<JSONObject> gettwitterData()
	{
		return twitterData;
	}
	public static Set<JSONObject> getgoogleData()
	{
		return googleData;
	}
	public static Pagination buildPaginator(OAuthService service)
	{
		Pagination paginator = new Pagination();
		paginator.service=service ;
		paginator.pagecount =0;
		return paginator;
	}
	/**
	 * Paginate facebook
	 * @param response
	 */
	public void facebookPaginator(OAuthRequest request)
	{
		Response response = request.send();
		String resp="";
		try 
		{
			resp=response.getBody();
			JSONObject jsonresult = new JSONObject(resp);
			facebookData.addAll(Parser.parseArray(response,"data"));
			if(jsonresult.has("paging"))
			{
				this.pagecount++;
				System.out.println("Calling page number "+this.pagecount);
				String nextPage = jsonresult.getJSONObject("paging").getString("next");
				facebookPaginator(new OAuthRequest(Verb.GET, nextPage, this.service));
			}
			
		} 
		catch (JSONException e) {
			System.out.println(e.getMessage());
			System.out.println(resp);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	/**
	 * paginate twitter
	 * @param response
	 */
	public void twitterPaginator(OAuthRequest request)
	{
		Response response = request.send();
		String resp="";
		try 
		{
			resp=response.getBody();
			JSONObject jsonresult = new JSONObject(resp);
			twitterData.addAll(Parser.parseArray(response,"statuses"));
			if(jsonresult.getJSONObject("search_metadata").has("next_results"))
			{
				String nextPage = Config.getTwitterSearch_ENDPOINT()+jsonresult.getJSONObject("search_metadata").getString("next_results");
				this.pagecount++;
				System.out.println("Calling page number "+this.pagecount);
				OAuthRequest req = new OAuthRequest(Verb.GET, nextPage, (OAuth10aService)this.service);
				((OAuth10aService) service).signRequest((OAuth1AccessToken) Auth.getTwitterInstance().getAppAccessToken(), req); 
				twitterPaginator(req);
			}
		} 
		catch (JSONException e) {
			System.out.println(e.getMessage());
			System.out.println(resp);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public void googlePaginator(OAuthRequest request,String endpoint)
	{
		Response response = request.send();
		String resp="";
		try 
		{
			resp=response.getBody();
			System.out.println(resp);
			JSONObject jsonresult = new JSONObject(resp);
			googleData.addAll(Parser.parseArray(response,"items"));
			if(jsonresult.has("nextPageToken"))
			{
				System.out.println("Calling next page :"+this.pagecount);
				OAuthRequest req = new OAuthRequest(Verb.GET,endpoint,this.service);
				req.addParameter("pageToken", jsonresult.getString("nextPageToken"));
				for(Parameter param :request.getQueryStringParams().getParams())
				{
					req.addParameter(param.getKey(), param.getValue());
				}
				System.out.println("Next page url : " +req.getCompleteUrl());
				this.pagecount++;
				twitterPaginator(req);
			}
		} 
		catch (JSONException e) {
			System.out.println(e.getMessage());
			System.out.println(resp);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

}
