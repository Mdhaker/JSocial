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
    	Rinterface r = new Rinterface();
    	r.getActivities(path+"pizza/", "Pizza","en");
    	r.getActivities(path+"caf/", "CAF match","en");
    	r.getActivities(path+"adis ababa/", "Adis ababa","en");
    	r.getActivities(path+"land berg/", "Land berg","en");
    	r.getActivities(path+"aireplance/", "Aireplanes","en");    	
    	
    }
}
