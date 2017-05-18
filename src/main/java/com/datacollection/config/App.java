package com.datacollection.config;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.json.JSONObject;

import com.datacollection.services.FlickrAPI;
import com.datacollection.services.Provider;
import com.datacollection.utils.AuthServer;
import com.datacollection.utils.Parser;
import com.datacollection.utils.SearchFilter;

public class App 
{ 
    public static void main( String[] args )
    {
    	
    	//System.out.println(Provider.FLICKR.getUserGroups(Provider.FLICKR.findUserByMail("michel@gmail.com").getString("id")));
    	System.out.println(Provider.FLICKR.getPlacesByLatLong("37.76513627957266", "-122.42020770907402","16"));
    	
    }
}
