package com.revature.dao;

import com.revature.models.Lib_users;

public interface Lib_users_DaoInterface {
	
	//for both sign up and login
	public boolean checkLib_user(String username);
	
	//for create account
	public void addLib_user(Lib_users lib_user);
	
	//for sign in account
	public Lib_users checkLib_password(String username, String password);

}
