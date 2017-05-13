package com.datacollection.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.github.scribejava.core.model.Response;



public class Parser {

	public static Set<JSONObject> facebookParser(Response response)
	{
		Set<JSONObject> result = new HashSet<JSONObject>();
		System.out.println("Response before parsing .. "+response.getMessage());
		
		String resp="";
		try {
			resp=response.getBody();
			System.out.println("Response before parsing : "+resp);
			JSONArray elements = new JSONObject(resp).getJSONArray("data");
			
			for(int i=0;i<elements.length();i++)
			{
				result.add(elements.getJSONObject(i));
			}
		} catch (JSONException e) {
			System.out.println(e.getMessage());
			System.out.println(resp);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
		
	}
	public static Set<JSONObject> googleParser(Response response)
	{
		Set<JSONObject> result = new HashSet<JSONObject>();
		
		String resp="";
		try {
			resp=response.getBody();
			JSONArray elements = new JSONObject(resp).getJSONArray("items");
			
			for(int i=0;i<elements.length();i++)
			{
				result.add(elements.getJSONObject(i));
			}
		} catch (JSONException e) {
			System.out.println(e.getMessage());
			System.out.println(resp);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
		
	}
	public static void saveTofile(Set<JSONObject> json,String filename)
	{
		FileWriter filew;
		File file = new File("/home/dhaker/Desktop/"+filename+".json");
		
		try {
			file.createNewFile();
			System.out.println(file.getAbsolutePath());
			filew = new FileWriter(file);
			Iterator<JSONObject> it=json.iterator();
			while(it.hasNext())
			{
				filew.append(it.next().toString(2));
			}
			filew.close();
			System.out.println("JSON Object appended to file");
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
	}
}
