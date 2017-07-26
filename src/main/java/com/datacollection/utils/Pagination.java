package com.datacollection.utils;

import java.io.IOException;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.omg.CORBA.portable.OutputStream;

import com.datacollection.config.Config;
import com.github.scribejava.core.exceptions.OAuthConnectionException;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Parameter;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth10aService;
import com.github.scribejava.core.oauth.OAuthService;

public class Pagination {
	private OAuthService service ;
	private static int pagecount;
	private static Set<JSONObject> facebookData = new HashSet<JSONObject>();
	private static Set<JSONObject> twitterData = new HashSet<JSONObject>();
	private static Set<JSONObject> googleData = new HashSet<JSONObject>();
	private static boolean googleFirstPage;
	private static boolean twitterFirstPage;
	private static boolean facebookFirstPage;
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
	public static void initPaginator(String paginator)
	{
		if(paginator.equalsIgnoreCase("facebook"))
		{
			pagecount=0;
			facebookData.clear();
			facebookFirstPage = true ;
		}
		else if (paginator.equalsIgnoreCase("google"))
		{
			pagecount=0;
			googleData.clear();
			googleFirstPage = true ;
		}
		else if (paginator.equalsIgnoreCase("twitter"))
		{
			pagecount=0;
			twitterData.clear();
			twitterFirstPage = true ;
		}
	}
	public static Pagination buildPaginator(OAuthService service)
	{
		Pagination paginator = new Pagination();
		paginator.service=service ;
		Pagination.pagecount =0;
		return paginator;
	}
	/**
	 * Paginate facebook
	 * @param response
	 */
	public void facebookPaginator(OAuthRequest request)
	{
		String resp="";
		try 
		{
			Response response = request.send();
			resp=response.getBody();
			JSONObject jsonresult = new JSONObject(resp);
			if(facebookFirstPage)
			{
				facebookFirstPage = false ;
			}
			facebookData.addAll(Parser.parseArray(response,"data"));
			if(jsonresult.has("paging"))
			{
				Config.showDebug();
				float percent = ((((float)pagecount/80))*100);
				if(pagecount % 2 == 0)
				{
					//System.out.print("/");
				}
				else
					//System.out.print("\\");
				if(pagecount % 10 == 0&& (percent <100))
					System.out.print("["+(int)percent+"%] ");
								
				
				Config.hideDebug();
				this.pagecount++;
				//System.out.println("Calling page number "+this.pagecount);
				String nextPage = jsonresult.getJSONObject("paging").getString("next");
				facebookPaginator(new OAuthRequest(Verb.GET, nextPage, this.service));
			}
			else
			{
			Config.showDebug();
			System.out.println("[100%]");
			Config.hideDebug();
			}
			
		} 
		catch (JSONException e) {
			System.out.println(e.getMessage());
			System.out.println(resp);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}		
		catch(OAuthConnectionException e)
		{
			System.err.println("Check your internet connection...");
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
				Config.showDebug();
				float percent = ((((float)pagecount/200))*100);
				if(pagecount % 2 == 0)
				{
					System.out.print("/");
				}
				else
					System.out.print("\\");
				if((pagecount % 10 == 0)&&percent<100)
					
					System.out.println("["+percent+"%] ");
				Config.hideDebug();
				String nextPage = Config.getTwitterSearch_ENDPOINT()+jsonresult.getJSONObject("search_metadata").getString("next_results");
				this.pagecount++;
				System.out.println("Calling page number "+this.pagecount);
				OAuthRequest req = new OAuthRequest(Verb.GET, nextPage, (OAuth10aService)this.service);
				((OAuth10aService) service).signRequest((OAuth1AccessToken) Auth.getTwitterInstance().getAppAccessToken(), req); 
				twitterPaginator(req);
			}
			else{
			Config.showDebug();
				System.out.println("[100%]");
			Config.hideDebug();
			}
		} 
		catch (JSONException e) {
			System.out.println(resp);
			if(e.getMessage().contains("not found"))
			{
				JSONObject error = new JSONObject(resp);
				int code =error.getJSONArray("errors").getJSONObject(0).getInt("code");
				if(code == 88)
				{
					Config.showDebug();System.err.println("Twitter rate limit exceeded please try again after 15 min");
				}
			}		
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	private int i=1;
	int numberofpages = 0;
	public void googlePaginator(OAuthRequest request,String endpoint)
	{
		if(Config.Debug)
			Config.showDebug();
		Response response = request.send();
		String resp="";
				try 
		{
			resp=response.getBody();
			
			JSONObject jsonresult = new JSONObject(resp);
			
			if(googleFirstPage)
			{
				//googleData.clear();				
			}			
			googleData.addAll(Parser.parseArray(response,"items"));
			if(jsonresult.has("nextPageToken"))
			{
				
				OAuthRequest req = new OAuthRequest(Verb.GET,endpoint,this.service);
				req.addParameter("pageToken", jsonresult.getString("nextPageToken"));
				for(Parameter param :request.getQueryStringParams().getParams())
				{
					req.addParameter(param.getKey(), param.getValue());
				}				
				System.out.println("calling page : " +i);
				Config.showDebug();
				float percent = ((((float)i/198))*100);
				if(i % 2 == 0)
				{
					System.out.print("/");
				}
				else
					System.out.print("\\");
				if(i % 10 == 0&& percent < 100)
					System.out.println("["+(int)percent+"%] ");
								
				
				Config.hideDebug();
				i++;
				this.pagecount++;
				googlePaginator(req,endpoint);
			}
			else
			{
			Config.showDebug();
			System.out.println("[100%]");
			Config.hideDebug();
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
