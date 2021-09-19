package com.revature.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	
	public static Connection getConnection() throws SQLException {
	
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		//credentials in Strings, used to connect in the return statement
		String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=p0_library";
		String username = "postgres";
		String password = "password";
		
		//return DriverManager.getConnection(url, username, password);
		
		
		//These variables are hidden in my Environment Variables
		//Run -> Run Configurations -> Environment Variables -> Then create key/value pairs for these credentials
		/*
		String url = System.getenv("URL");
		String username = System.getenv("USERNAME");
		String password = System.getenv("PASSWORD");*/
		
		return DriverManager.getConnection(url, username, password);
	}
	
}
