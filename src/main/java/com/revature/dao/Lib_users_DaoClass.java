package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.Lib_users;
import com.revature.utils.ConnectionUtil;

public class Lib_users_DaoClass implements Lib_users_DaoInterface{

	@Override
	public boolean checkLib_user(String username) {
		try (Connection conn = ConnectionUtil.getConnection()){
			
			//Result Set
			ResultSet rs = null;
			
			//SQL string
			String sql = "select * from lib_users where user_name = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, username);
			
			rs = ps.executeQuery();
			
			if(rs.next() == false) {
				return false;
			}
			
			return true;
			
		} catch(SQLException e) {
			System.out.println("Something went wrong with your checkusername database");
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void addLib_user(Lib_users lib_user) {
		
		try (Connection conn = ConnectionUtil.getConnection()){
			
			if(this.checkLib_user(lib_user.getUser_name())){
				System.out.println("This username is already in use.");
				return;
			}
			
			String sql = "insert into lib_users (user_name, user_pass, f_name, l_name) " +
			"Values (?,?,?,?)";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, lib_user.getUser_name());
			ps.setString(2, lib_user.getUser_pass());
			ps.setString(3, lib_user.getF_name());
			ps.setString(4, lib_user.getL_name());
			
			ps.executeUpdate();
			
			System.out.println("Welcome aboard " + lib_user.getUser_name());
			
		} catch(SQLException e) {
			System.out.println("Something went wrong with your checkusername database");
			e.printStackTrace();
		}
		return;
		
		
	}

	//Function to take in username, and check whether the password corresponds to the username's
	@Override
	public Lib_users checkLib_password(String username, String password) { 
		try (Connection conn = ConnectionUtil.getConnection()){
			
			//Result Set
			ResultSet rs = null;
			
			//SQL string
			String sql = "select * from lib_users where user_name = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, username);
			
			rs = ps.executeQuery();
			
			if(rs.next() == false) {
				return null;
			}
			
			//System.out.println(rs.getString("user_pass"));
			if(rs.getString("user_pass").equals(password)) {
				Lib_users lib_user = new Lib_users(rs.getString("user_name"), rs.getString("user_pass"), rs.getString("f_name"), rs.getString("l_name"), rs.getBoolean("management"));
				return lib_user;
			}
			
			return null;
			
		} catch(SQLException e) {
			System.out.println("Something went wrong with your checkusername database");
			e.printStackTrace();
		}
		return null;
	}
	

}
