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
    	r.setDebug(false);
    	r.count(path+"channels.json", "*", true, "data");
    }
}
