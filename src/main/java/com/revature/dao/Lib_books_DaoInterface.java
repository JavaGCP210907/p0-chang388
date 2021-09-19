package com.revature.dao;

import java.util.List;

import com.revature.models.Lib_books;

public interface Lib_books_DaoInterface {
	
	public List<Lib_books> viewAllBooks();

	public List<Lib_books> viewAvailableBooks(); //view all available books
	
	public void addBooks(Lib_books lib_book); //add books to the database
	
	public void deleteBooks(int bookId); //remove books from the database
	
	public List<Lib_books> viewBooksBorrowed();
	
	public List<Lib_books> viewBooksBorrowed(String username);
	
	public void borrowBook(String username, int bookId);
	
	public void returnBook(String username, int bookId);
}
