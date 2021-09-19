package com.revature.models;

public class Lib_books {
	

	private int book_id;
	private String book_series;
	private String book_title;
	private String author_l_name;
	private String author_f_name;
	private String user_name_fk;
	
	
	//Constructors
	public Lib_books() {
		super();
	}
	public Lib_books(int book_id, String book_series, String book_title, String author_l_name, String aufthor_f_name, String user_name_fk) {
		super();
		this.book_id = book_id;
		this.book_series = book_series;
		this.book_title = book_title;
		this.author_l_name = author_l_name;
		this.author_f_name = aufthor_f_name;
		this.user_name_fk = user_name_fk;
	}
	public Lib_books(String book_series, String book_title, String author_l_name, String aufthor_f_name, String user_name_fk) {
		super();
		this.book_series = book_series;
		this.book_title = book_title;
		this.author_l_name = author_l_name;
		this.author_f_name = aufthor_f_name;
		this.user_name_fk = user_name_fk;
	}
	
	//Getters and Setters
	public int getBook_id() {
		return book_id;
	}
	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}
	public String getBook_series() {
		return book_series;
	}
	public void setBook_series(String book_series) {
		this.book_series = book_series;
	}
	public String getBook_title() {
		return book_title;
	}
	public void setBook_title(String book_title) {
		this.book_title = book_title;
	}
	public String getAuthor_l_name() {
		return author_l_name;
	}
	public void setAuthor_l_name(String author_l_name) {
		this.author_l_name = author_l_name;
	}
	public String getAuthor_f_name() {
		return author_f_name;
	}
	public void setAuthor_f_name(String aufthor_f_name) {
		this.author_f_name = aufthor_f_name;
	}
	public String getUser_name_fk() {
		return user_name_fk;
	}
	public void setUser_name_fk(String user_name_fk) {
		this.user_name_fk = user_name_fk;
	}
	
	//hashcode and equals
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author_f_name == null) ? 0 : author_f_name.hashCode());
		result = prime * result + ((author_l_name == null) ? 0 : author_l_name.hashCode());
		result = prime * result + book_id;
		result = prime * result + ((book_series == null) ? 0 : book_series.hashCode());
		result = prime * result + ((book_title == null) ? 0 : book_title.hashCode());
		result = prime * result + ((user_name_fk == null) ? 0 : user_name_fk.hashCode());
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
		Lib_books other = (Lib_books) obj;
		if (author_f_name == null) {
			if (other.author_f_name != null)
				return false;
		} else if (!author_f_name.equals(other.author_f_name))
			return false;
		if (author_l_name == null) {
			if (other.author_l_name != null)
				return false;
		} else if (!author_l_name.equals(other.author_l_name))
			return false;
		if (book_id != other.book_id)
			return false;
		if (book_series == null) {
			if (other.book_series != null)
				return false;
		} else if (!book_series.equals(other.book_series))
			return false;
		if (book_title == null) {
			if (other.book_title != null)
				return false;
		} else if (!book_title.equals(other.book_title))
			return false;
		if (user_name_fk == null) {
			if (other.user_name_fk != null)
				return false;
		} else if (!user_name_fk.equals(other.user_name_fk))
			return false;
		return true;
	}
	
	//toString method
	@Override
	public String toString() {
		return "Book id: " + book_id + "\nBook Series: " + book_series + " - " + book_title
				+ "\nAuthor Name=" + author_l_name + ", " + author_f_name; // + "\nChecked out by:" + user_name_fk;
	}
	
	
	
	
	

}
