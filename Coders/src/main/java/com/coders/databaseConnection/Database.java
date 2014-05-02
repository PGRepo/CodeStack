package com.coders.databaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database 
{
	Connection conn = null;
	  String userName = "root";
      String password = "";
      String url = "jdbc:mysql://localhost:3306/LoginDb";
	
	public Connection connectToDatabase()
	
	{   
		
        try {
          
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url, userName, password);
            // System.out.println("Database connection established");

            if (conn != null) 
            {
                Statement s = conn.createStatement();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
       
    }
	
	public void closeConnection()
	{
		if (conn != null) 
        {
         try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        }
	}
	
	
	
	
	
	
	
	
	
	
	
}
