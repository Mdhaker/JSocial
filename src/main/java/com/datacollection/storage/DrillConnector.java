package com.datacollection.storage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DrillConnector {
	static final String JDBC_DRIVER = "org.apache.drill.jdbc.Driver";
    static final String DB_URL = "jdbc:drill:zk=localhost:2181;schema=dfs";

    static final String USER = "admin";
    static final String PASS = "admin";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try{
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.createStatement();
            /* Perform a select on data in the classpath storage plugin. */
            String sql = "select * FROM `/home/dhaker/Desktop/tunisiana.json` " ;
            ResultSet rs = stmt.executeQuery(sql);
            
            System.out.println("-------------------------------------------------------------");
            while(rs.next()) {
                              
                System.out.println(rs.getString("link"));
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch(SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch(Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            try{
                if(stmt!=null)
                    stmt.close();
            } catch(SQLException se2) {
            }
            try {
                if(conn!=null)
                    conn.close();
            } catch(SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
