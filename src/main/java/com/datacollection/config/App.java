package com.datacollection.config;
import com.datacollection.services.FacebookAPI;
public class App 
{ 
    public static void main( String[] args )
    {
    	System.out.println(FacebookAPI.buildReader().getPlace("Medina"));
    }
}
