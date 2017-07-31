package com.datacollection.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.datacollection.config.Config;
import com.github.scribejava.core.model.Response;



public class Parser {

	/**
	 * Parse request reponse to JSON array
	 * @param response respone to parse
	 * @param root root block to excract the result
	 * @return
	 */
	public static Set<JSONObject> parseArray(Response response,String... root)
	{
		Set<JSONObject> result = new HashSet<JSONObject>();
		//System.out.println("Response before parsing .. "+response.getMessage());
		PrintStream showStream = System.out;
		PrintStream hideStream    = new PrintStream(new HideStream());
		String resp="";
		try {
			System.setOut(hideStream);
			resp=response.getBody();
			System.setOut(showStream);
			//System.out.println("Response before parsing : "+resp);
			JSONObject rootObject= new JSONObject(resp);
			JSONArray elements = null;
			if(rootObject.get(root[0]) instanceof JSONArray)
				elements = rootObject.getJSONArray(root[0]);
			else
				elements = rootObject.getJSONObject(root[0]).getJSONArray(root[1]);
			
			for(int i=0;i<elements.length();i++)
			{
				result.add(elements.getJSONObject(i));
			}
		} catch (JSONException e) {
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static JSONObject parseObject(Response response,String network,String root)
	{
		Set<JSONObject> result = new HashSet<JSONObject>();
		String resp="";
		try {
			resp=response.getBody();
			if(network.equalsIgnoreCase("flickr"))
			{
				resp = resp.substring(14, resp.length()-1);				
			}
			System.out.println("Response before parsing : "+resp);
		} catch (JSONException | IOException e) {
			System.out.println(e.getMessage());
			System.out.println(resp);
		} 
		return new JSONObject(resp).getJSONObject(root);
	}
	public static String PathToSave="/home/dhaker/Desktop/";
	
	
	/**
	 * Save json object into json file with data array key root
	 * @param json
	 * @param filename
	 */
	public static void saveTofile(Set<JSONObject> json,String filename)
	{
		if(!Config.Debug)
		{
			Config.hideDebug();
		}
		
		mkdir(PathToSave);
		System.out.println(Parser.PathToSave+filename+".json");
		File file = new File(Parser.PathToSave+filename+".json");
		
		try 
		{
			file.createNewFile();
			System.out.println(file.getAbsolutePath());
			OutputStreamWriter filew = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);
			Iterator<JSONObject> it=json.iterator();
			filew.append("{");
			filew.append(System.lineSeparator());
			filew.append("\"data\" : [");
			filew.append(System.lineSeparator());
			int i=1;
			while(it.hasNext())
			{
				filew.append(it.next().toString(10));				
				if(i<json.size())
					filew.append(",");
				filew.append(System.lineSeparator());
				i++;
			}
			filew.append("]}");
			filew.close();
			System.out.println("JSON Object appended to file");
			Config.hideDebug();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	
	
	public static void mkdir(String path)
	{
		File theDir = new File(path);

		// if the directory does not exist, create it
		if (!theDir.exists()) {
		    boolean result = false;
		    try
		    {
		        theDir.mkdirs();
		        result = true;
		    } 
		    catch(SecurityException se)
		    {
		        //handle it
		    }        
		    if(result) 
		    {    
		        System.out.println("Creating directory...");  
		    }
		}
		else
		{
		}
	}
	
	public static String formatQueryCols(String item)
	{
		String foramttedcol ="";
		return foramttedcol;
	}
}
