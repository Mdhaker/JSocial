package com.datacollection.storage;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import oadd.org.apache.commons.lang3.StringUtils;


public class DrillConnector {
	static final String JDBC_DRIVER = "org.apache.drill.jdbc.Driver";
    static final String DB_URL = "jdbc:drill:zk=localhost:2181";
    static final String USER = "admin";
    static final String PASS = "admin";
    private static DrillConnector instance=null;
    private Connection connection;
    private String filepath;
    public DrillConnector() throws ClassNotFoundException, SQLException
    {
        Class.forName(JDBC_DRIVER);
        this.connection =DriverManager.getConnection(DB_URL,USER,PASS);
        

    }
    public static DrillConnector getConnection()
    {
    	if(instance == null)
    	{
    		try {
				instance = new DrillConnector();
			} catch (ClassNotFoundException e) {
				System.out.println("Error while loading drill Driver");
				System.out.println(e.getMessage());
			} catch (SQLException e) {
				System.out.println("Error while creating connection");
				System.out.println(e.getMessage());
			}
    	}
    	return instance ;
    }
    public DrillConnector setFilePath(String filepath)
    {
    	if(new File(filepath).exists())
    		this.filepath = filepath;
    	else
    		System.out.println("File don't exist : "+filepath);
    	
    	return this ;
    }
    public List<String> selectItem(boolean flatten,boolean where,String... items)
    {
    	List<String> selectResult = new ArrayList<String>();
    	Statement stmt;
    	String query ="";
    	String alias="";
    	int itemStartindex =0;
    		if(where)
    			itemStartindex=+1;
		try {
			String columns="";
			for(int i=itemStartindex;i<items.length;i++)
			{
				alias=items[i].split(" as ")[1].trim();
				if(flatten) //to access root data with alias
				{
					String target ="flatten";
					String targetCols ="";
					try // alias using key word as is mandatory 
					{
						targetCols = items[i].split(" as ")[0].trim();
						alias =items[i].split(" as ")[1].trim();
					}
					catch(Exception e)
					{
						if(items[i].contains("."))
							alias = items[i].split("\\.")[items[i].split("\\.").length-1];
						else
							alias= items[i];
						// unaccepted keys
						String[] forbiddenkeys = {"from","user","language","time"};
						if(Arrays.asList(forbiddenkeys).contains(alias))
							alias ="item";
					}
					
					
					// if columns contains nested elements
					if(targetCols.contains("."))
					{
						for(String item:targetCols.split("\\."))
						{
							target.trim();
									
							if(StringUtils.isNumeric(item))
								target = target+"["+item+"]";
							else
								target = target+"['"+item+"']";	
						}
					}
					else
						target = target+"['"+targetCols+"']";
					target = target+" as "+alias;
					
					columns = columns.concat(",rootdoc."+target);
				}
				else
					columns=columns.concat(","+items[i]);
			}
			columns = columns.substring(1);
			// default query without flatten, substring() to remove the first comma
			query = "select "+columns+" FROM dfs.`"+this.filepath+"` root" ;
			// if flatten we use a nested query to flatten the root table data
			if(flatten)
			{
				query="select "+columns+" FROM (select FLATTEN(root.data) as flatten FROM dfs.`"+this.filepath+"` root) rootdoc";
			}
			
			if(where)
			{
				if(flatten)
					query = query+ " WHERE rootdoc.flatten."+items[0] ;
				else
					query = query+ " WHERE rootdoc."+items[0] ;
			}
			
				
			stmt = this.connection.createStatement();			
			 System.out.println(query);
		     ResultSet rs = stmt.executeQuery(query);
		     while(rs.next()) 
		     	{      
		    	 	for(String item : items)
		    	 	{	
		    	 			selectResult.add(rs.getString(alias));
		    	 	}
	            }
		     	rs.close();
	            stmt.close();
		} catch (SQLException e)
		{
			System.out.println(e.getMessage());
		}
        /* Perform a select on data in the classpath storage plugin. */ 
    	return selectResult;
    }
    
    
    
    public void sqlQuery(String query)
    {
    	
    	Statement stmt;
		try {
			String columns="";
			
			stmt = this.connection.createStatement();
			 String sql = query;
		     ResultSet rs = stmt.executeQuery(sql);
		     while(rs.next()) 
		     	{      
		    	 	for(int i=0;i<rs.getMetaData().getColumnCount();i++)
		    	 	{
		    	 		String colname = rs.getMetaData().getColumnName(i);
		    	 		System.out.println("String "+colname+" :" + rs.getString(colname));
		    	 	}
	            }
		     	rs.close();
	            stmt.close();
		} catch (SQLException e)
		{
			System.out.println(e.getMessage());
		}
    }
    
    
    public int getCount(String element,boolean flatten,String arraycol)
    {
    	Statement stmt;
		try {
			stmt = this.connection.createStatement();
			 String sql = "select count(root."+element+") as total FROM dfs.`"+this.filepath+"` root" ;
			 if(flatten)
				 sql="select count("+element+") as total from (select FLATTEN(root."+arraycol+")  FROM dfs.`"+this.filepath+"` root)";
		     ResultSet rs = stmt.executeQuery(sql);
		     while(rs.next()) {
	                return (rs.getInt("total"));
	            }

	            rs.close();
	            stmt.close();
		     return -1;
		     
		} catch (SQLException e)
		{
			System.out.println(e.getMessage());
			return -1;
		}
		
    }
   
}
