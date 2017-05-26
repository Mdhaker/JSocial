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
import com.datacollection.utils.SearchFilter.GoogleFilter;

public class App 
{ 
    public static void main( String[] args )
    {
    	
    	System.out.println(Provider.FLICKR.searchPhoto("ghandi"));
    	
    }
}
