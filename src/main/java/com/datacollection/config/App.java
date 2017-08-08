package com.datacollection.config;

import java.io.File;
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
    	String path="/home/dhaker/Desktop/ThisDirectoryForTest/";
    	Rinterface r = new Rinterface("/home/dhaker/datacollector/inst");
    	r.setDebug(true);
    	//r.count(path+"channels.json", "*", true, "data");
    	r.getFlickrList(path, "35113313023","36157851831","36067599181","35809496990","36036769822");
    	
    }
}
