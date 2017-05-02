package com.datacollection.config;


import java.util.Iterator;

import org.json.JSONObject;

import com.datacollection.services.TwitterApi;
public class App 
{ 
    public static void main( String[] args )
    {
    	Iterator<JSONObject> it =TwitterApi.buildReader().getPlace("Sidi").iterator();
    	while(it.hasNext())
    	{
    		System.out.println(":==) >> "+it.next().get("country"));
    	}
    }
}
