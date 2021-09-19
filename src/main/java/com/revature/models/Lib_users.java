package com.revature.models;

public class Lib_users {
	
	//fields
	private String user_name;
	private String user_pass;
	private String f_name;
	private String l_name;
	private boolean management;
	
	//Constructors
	public Lib_users() {
		super();
	}
	
	public Lib_users(String user_name, String user_pass, String f_name, String l_name) {
		super();
		this.user_name = user_name;
		this.user_pass = user_pass;
		this.f_name = f_name;
		this.l_name = l_name;
	}

	public Lib_users(String user_name, String user_pass, String f_name, String l_name, boolean management) {
		super();
		this.user_name = user_name;
		this.user_pass = user_pass;
		this.f_name = f_name;
		this.l_name = l_name;
		this.management = management;
	}

	//Getters and setters
	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_pass() {
		return user_pass;
	}

	public void setUser_pass(String user_pass) {
		this.user_pass = user_pass;
	}

	public String getF_name() {
		return f_name;
	}

	public void setF_name(String f_name) {
		this.f_name = f_name;
	}

	public String getL_name() {
		return l_name;
	}

	public void setL_name(String l_name) {
		this.l_name = l_name;
	}

	public boolean isManagement() {
		return management;
	}

	public void setManagement(boolean management) {
		this.management = management;
	}

	//ToString
	@Override
	public String toString() {
		return "Lib_users [user_name=" + user_name + ", user_pass=" + user_pass + ", f_name=" + f_name + ", l_name="
				+ l_name + ", management=" + management + "]";
	}

	//Hashcode and equals
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((f_name == null) ? 0 : f_name.hashCode());
		result = prime * result + ((l_name == null) ? 0 : l_name.hashCode());
		result = prime * result + (management ? 1231 : 1237);
		result = prime * result + ((user_name == null) ? 0 : user_name.hashCode());
		result = prime * result + ((user_pass == null) ? 0 : user_pass.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lib_users other = (Lib_users) obj;
		if (f_name == null) {
			if (other.f_name != null)
				return false;
		} else if (!f_name.equals(other.f_name))
			return false;
		if (l_name == null) {
			if (other.l_name != null)
				return false;
		} else if (!l_name.equals(other.l_name))
			return false;
		if (management != other.management)
			return false;
		if (user_name == null) {
			if (other.user_name != null)
				return false;
		} else if (!user_name.equals(other.user_name))
			return false;
		if (user_pass == null) {
			if (other.user_pass != null)
				return false;
		} else if (!user_pass.equals(other.user_pass))
			return false;
		return true;
	}
	
}
