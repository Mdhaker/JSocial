package com.datacollection.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.json.JSONObject;

import com.datacollection.config.Config;
import com.datacollection.services.Provider;
import com.datacollection.storage.DrillConnector;
import com.github.scribejava.core.model.Parameter;
/**
 * This class is made to connect with R package using r-javas
 * @author dhaker
 *
 */
public class Rinterface {
	
	public void setDebug(boolean mode)
	{
		Config.Debug = mode ;
	}
	//Get topics from google+ and Twitter
	public void getTopics(String path,String query)
	{
		Parser.PathToSave =path;
		Parser.saveTofile(Provider.GOOGLE.getActivities(query),"googleActivities");
		Set<JSONObject> ActivtiesComments = new HashSet();
		for(String id:DrillConnector.getConnection().setFilePath(path+"/googleActivities.json").selectItem(true,false,"id"))
		{
			System.out.println(id);
			ActivtiesComments.addAll(new HashSet(Provider.GOOGLE.getComments(id)));
		}
		System.out.println(ActivtiesComments.toString());
		Parser.saveTofile(ActivtiesComments, "/googleActivitiesComments");
		Parser.saveTofile(Provider.TWITTER.getTweets(query), "twitterTweets");
	}
	//get tweets with filter
	public String getTweets(String path,String lang,String exact,String allword,String hashtags,String noneWords,String oneOf,
			String accounts,boolean attitude,boolean question)
	{
		Parser.PathToSave =path;
		SearchFilter.TwitterFilter filter = SearchFilter.buildTwitter().havingExactPhrase(exact).havingAllWords(allword.split("\\s"))
				.havingHashtags(hashtags.split("\\s")).havingNoneOf(noneWords.split("\\s")).havingOneOf(oneOf.split("\\s"))
				.sentFrom(accounts.split("\\s")).havingPositiveAttitude(attitude);
		Parser.saveTofile(Provider.TWITTER.setLang(lang).getTweets(filter), "twitterTweets");
		return path+"twitterTweets.json";
	}
	//get tweets with query
	public String getTweets(String path,String query)
	{
		Parser.PathToSave =path;
		Parser.saveTofile(Provider.TWITTER.getTweets(query), "twitterTweets");
		return path+"twitterTweets.json";
	}
	//get google + activities
	public String getActivities(String path,String query,String lang)
	{
		Parser.PathToSave =path;
		SearchFilter.GoogleFilter filter = SearchFilter.buildGoogle().setLang(lang).setQuery(query);
		Parser.saveTofile(Provider.GOOGLE.getActivities(filter), "googleActivities");
		return path+"googleActivities.json";
	}
	// search a youtube element
	public String searchYoutube(String path,String type,String query)
	{
		Parser.PathToSave =path;
		if(type.equalsIgnoreCase("channel"))
		{
			Parser.saveTofile(Provider.YOUTUBE.findChannel(query), "channels");
			return path+"channels.json";
		}
		else if(type.equalsIgnoreCase("video"))
		{
			Parser.saveTofile(Provider.YOUTUBE.findVideos(query), "videos");
			return path+"videos.json";
		}
		else if(type.equalsIgnoreCase("playlist"))
		{
			Parser.saveTofile(Provider.YOUTUBE.findPlaylist(query), "playlists");
			return path+"playlists.json";
		}
		
		else
		{
		Parser.saveTofile(Provider.YOUTUBE.searchYoutube(query), "searchResult");
		return path+"searchResult.json";
		}
	}
	
	// Get youtube elements info
	public String getYoutube(String path,String type,String ID)
	{
		Parser.PathToSave =path+"/"+ID+"/";
		if(type.equalsIgnoreCase("channel"))
		{
			Parser.saveTofile(Provider.YOUTUBE.getChannel(ID), "channel");
			return Parser.PathToSave+"channel"+".json";
		}
		else if(type.equalsIgnoreCase("video"))
		{
			Parser.saveTofile(Provider.YOUTUBE.getVideo(ID), "videos");
			return Parser.PathToSave+"videos"+".json";
		}
		else if(type.equalsIgnoreCase("playlist"))
		{
			Parser.saveTofile(Provider.YOUTUBE.getPlaylist(ID), "playlists");
			return Parser.PathToSave+"playlists"+".json";
		}
		else if (type.equalsIgnoreCase("activity"))
		{
			Parser.saveTofile(Provider.YOUTUBE.getChannelActivities(ID), "channelActivities");
			return Parser.PathToSave+"channelActivities"+".json";
		}
		else if (type.equalsIgnoreCase("subscription"))
		{
			Parser.saveTofile(Provider.YOUTUBE.getSubscriptions(ID), "subscriptions");
			return Parser.PathToSave+"subscriptions"+".json";
		}
		else if (type.equalsIgnoreCase("caption"))
		{
			Parser.saveTofile(Provider.YOUTUBE.getcaptionTrack(ID), "captionsTrack");
			return Parser.PathToSave+"captionsTrack"+".json";
		}
		else if (type.equalsIgnoreCase("section"))
		{
			Parser.saveTofile(Provider.YOUTUBE.getChannelSection(ID), "channelSections");
			return Parser.PathToSave+"channelSections"+".json";
		}
		else if (type.equalsIgnoreCase("comment"))
		{
			Parser.saveTofile(Provider.YOUTUBE.getcommentThread(ID), "commentThread");
			return Parser.PathToSave+"commentThread"+".json";
		}
		return "";
	}
	
	// get channel and video from a list of IDs
	public static String getYouTubeList(String path,String type,String... IDs)
	{
		Parser.PathToSave =path+"/"+type+"/";
		if(type.equalsIgnoreCase("channel"))
		{
			Set<JSONObject> channels = new HashSet<JSONObject>() ;
			for(int i=0;i<IDs.length;i++)
			{
				System.out.print("*");
				channels.addAll(Provider.YOUTUBE.getChannel(IDs[i]));
			}
			Parser.saveTofile(channels, "ListOf#channels");
			return Parser.PathToSave+"ListOf#channels"+".json";
		}
		
		else if(type.equalsIgnoreCase("video"))
		{
			Set<JSONObject> videos = new HashSet<JSONObject>() ;
			for(int i=0;i<IDs.length;i++)
			{
				videos.addAll(Provider.YOUTUBE.getVideo(IDs[i]));
			}
			Parser.saveTofile(videos, "ListOf#videos");
			return Parser.PathToSave+"ListOf#videos"+".json";
		}		
		
		return "";
	}
		
	//params key and value in same string splitted by space
	public String getFacebookEdge(String path,String nodeid,String edge,boolean user,String... params)
	{
		Parser.PathToSave =path;
		Set<Parameter> parameters = new HashSet<Parameter>();
		for(String param : params)
			{
			if(!param.isEmpty()&&param.contains(" "))
				parameters.add(new Parameter(param.split("\\s")[0],param.split("\\s")[1]));
			}
		if(user)
			Parser.saveTofile(Provider.FACEBOOK.getUserEdge(nodeid, edge,parameters.toArray(new Parameter[parameters.size()])), "/"+nodeid+edge);
		else
			Parser.saveTofile(Provider.FACEBOOK.getAppEdge(nodeid, edge,parameters.toArray(new Parameter[parameters.size()])), "/"+nodeid+edge);
		
		return path+"/"+nodeid+edge+".json";
	}
	
	//Search a facebook node including page, group, event
	public String searchFacebookEdge(String path,String nodeType,String query)
	{
		Parser.PathToSave =path;
		if(nodeType.equalsIgnoreCase("user"))
		{
			Parser.saveTofile(Provider.FACEBOOK.searchUsers(query), "/"+nodeType);
		}
		if(nodeType.equalsIgnoreCase("event"))
		{
			Parser.saveTofile(Provider.FACEBOOK.searchEvents(query), "/"+nodeType);
		}
		if(nodeType.equalsIgnoreCase("page"))
		{
			Parser.saveTofile(Provider.FACEBOOK.searchPages(query), "/"+nodeType);
		}
		if(nodeType.equalsIgnoreCase("group"))
		{
			Parser.saveTofile(Provider.FACEBOOK.searchGroups(query), "/"+nodeType);
		}
		return path+"/"+nodeType+".json";
	}
	
	public void getUsers(String path,String query)
	{
		Parser.PathToSave =path;
		Parser.saveTofile(Provider.GOOGLE.getProfiles(query),"googleUsers");
		Parser.saveTofile(Provider.FACEBOOK.searchUsers(query), "twitterUsers");
	}
	
	public String[] selectFrom(String path,String item,String where, String savedfile,boolean append,boolean flatten)
	{
		boolean whereC = !where.isEmpty();
		List<String> selectedItems;
		if(whereC)
			 selectedItems =DrillConnector.getConnection().setFilePath(path).selectItem(flatten,whereC,where,item);
		else
			selectedItems =DrillConnector.getConnection().setFilePath(path).selectItem(flatten,whereC,item);

		if(!savedfile.isEmpty())
		{
			FileWriter filew;			
			File file = new File(path.substring(0, path.lastIndexOf("/"))+"/"+savedfile);
			try {
				file.createNewFile();
				filew = new FileWriter(file,append);
				for(String selecteditem:selectedItems)
				{
					filew.append(selecteditem);
					filew.append(System.lineSeparator());
					filew.append(System.lineSeparator());
				}
				filew.close();
				System.out.println("Column "+item+" saved into "+file.getAbsolutePath());
				return new String[]{file.getAbsolutePath()};
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}		
		}
			return selectedItems.toArray(new String[selectedItems.size()]);
	}
	

	
	public int count(String path,String element,boolean flatten,String array)
	{
		return DrillConnector.getConnection().setFilePath(path).getCount(element,flatten,array);		
	}


}
