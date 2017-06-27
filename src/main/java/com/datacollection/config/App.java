package com.datacollection.config;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.json.JSONObject;

import com.datacollection.services.FlickrAPI;
import com.datacollection.services.Provider;
import com.datacollection.storage.DrillConnector;
import com.datacollection.utils.AuthServer;
import com.datacollection.utils.Parser;
import com.datacollection.utils.Rinterface;
import com.datacollection.utils.SearchFilter;

public class App 
{ 
    public static void main( String[] args )
    {    
    	//Parser.saveTofile(Provider.FACEBOOK.getPage("mahatma ghandi"), "ghandi");
    	//System.out.println(DrillConnector.getConnection().setFilePath("/home/dhaker/Desktop/GhandiOffcialPage/Facebookposts.json").selectItem("comments"));
    	//Rinterface r = new Rinterface();
    	//r.getFacebookEdge("FatherofNation","posts","/home/dhaker/Desktop/GhandiOffcialPage");
    	//r.getTweets("/home/dhaker/Desktop/TwitterData/","","","","","","Khlifa soltani", "", true, true);
    	//r.selectFrom("/home/dhaker/Desktop/Ghandi//FatherOfNationfeed.json", "from", "", "", false, true);
    	//Provider.YOUTUBE.findChannel("gandhi");
    	System.out.println(Provider.FACEBOOK.searchEvents("Psychology"));
    }
}
