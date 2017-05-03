package com.datacollection.config;


import java.util.Iterator;

import org.json.JSONObject;

import com.datacollection.services.FacebookAPI;
import com.datacollection.services.TwitterApi;
public class App 
{ 
    public static void main( String[] args )
    {
    	FacebookAPI.buildReader().getUser();
    }
}
