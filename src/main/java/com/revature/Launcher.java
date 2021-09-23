package com.revature;

import java.sql.Connection;
import java.sql.SQLException;

import com.revature.models.Menu;
import com.revature.utils.ConnectionUtil;

public class Launcher {

	public static void main(String[] args) {
		
		// We are testing whether our Connection (from ConnectionUtil Class) is successful
		//remember - the getConnection() method will return a Connection object if you reach the database successfully
		try(Connection conn = ConnectionUtil.getConnection()){
			//System.out.println("Hello, connection was successful");
		} catch (SQLException e) {
			System.out.println("Connection to Debora Library Database Failed");
			e.printStackTrace();
		}
		
		//Here is the actual functionality of our application-----------------
		
		//Create our menu object
		Menu menu = new Menu();
		
		//Use the menu's displayMenu() method
		menu.displayMenu();

	}

}
