package com.revature.models;

import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.dao.Lib_books_DaoClass;
import com.revature.dao.Lib_users_DaoClass;

//This menu class will have a method that displays a menu to the user they can interact with
//Through this menu, the user can give inputs that will interact with the database
public class Menu {
	
	//ALL of the menu display optons and control flow are contained within this method
	public void displayMenu() {
		
		boolean displayMenu = true; //we're going to use this to toggle whether the menu continues after user input
		Scanner scan = new Scanner(System.in);
		Lib_users_DaoClass LuDC = new Lib_users_DaoClass();
		String input;
		
		
		//pretty greeting :)
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Hello! Welcome to 123 Library");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		
		//display the menu as long as the displayMenu boolean is true
		while(displayMenu) {
			
			System.out.println("------------------");
			System.out.println("Log into your account, or sign up");
			System.out.println("------------------");
			
			
			System.out.println("login -> Log into your account with username and password");
			System.out.println("signup -> Create your 123 account today");
			System.out.println("exit -> Exit out of 123 Library application");
			
			//parse user input after they choose a menu
			input = scan.nextLine();
			
			//switch statement that takes the input and delivers the appropiate response
			//you may have a hard time reading this functionality
			//I suggest you look at our dao layer interfaces to see everything the CLI is able to do
			switch(input) {
			case "login":{
				System.out.println("Username");
				String username = scan.nextLine();
				System.out.println("Password");
				String password = scan.nextLine();
				Lib_users lib_user = LuDC.checkLib_password(username,password);
				
				if(lib_user != null) {
					if(lib_user.isManagement()) {
						this._Manager_display(lib_user,scan);
						//input = scan.nextLine();
					} else {
						this._Casual_display(lib_user,scan);
					}
				}

				break;
			}
			case "signup":{
				
				System.out.println("Username");
				String username = scan.nextLine();
				System.out.println("Password");
				String password = scan.nextLine();
				System.out.println("First name");
				String f_name = scan.nextLine();
				System.out.println("Last name");
				String l_name = scan.nextLine();
				System.out.println("Type YES to confirm");
				System.out.println("username: " + username + " | password: " + password + " | First Name: " + f_name + " | Last Name: " + l_name);
				String confirm_info = scan.nextLine();
				
				if(confirm_info.equals("YES")){
					Lib_users lib_user = new Lib_users(username, password, f_name, l_name);
					System.out.println(lib_user.isManagement());
					LuDC.addLib_user(lib_user);
				} else {
					System.out.println("Signup has been cancelled");
				}

				break;
			}
			case "exit":{
				displayMenu = false;
				break;
			}
			default: {
				System.out.println("What did you say? try again buddy");
				break;
			}
			}
			if(!displayMenu) {
				System.out.println("BYEEEEEEEEEE");
			}
		} //end of while loop
		
		scan.close();
	}
	
	
	//Manager display
	private void _Manager_display(Lib_users lib_user, Scanner scan) {
		
		//Scanner scan1 = new Scanner(System.in);
		boolean displayMenu = true;
		Lib_books_DaoClass LbDC = new Lib_books_DaoClass();
		
		System.out.println("------------------");
		System.out.println("Welcome to the Management Login: " + lib_user.getUser_name());
		System.out.println("------------------");
		
		while(displayMenu) {
			System.out.println("viewAllBooks -> View unchecked books in the database");
			System.out.println("viewBorrowedBooks -> View checked books you have borrowed");
			System.out.println("addBooks -> Add a new book to the database");
			System.out.println("removeBooks -> Remove a book from the database");
			System.out.println("signOut -> Sign out of the account");
			
			String input = scan.nextLine();
			
			switch (input) {
			
			case "viewAllBooks":{
				
				List<Lib_books> all_book_array= LbDC.viewAllBooks();
				System.out.println("------------------------------------");
				for(Lib_books lib_book: all_book_array) {
					System.out.println(lib_book);
					System.out.println("------------------------------------");
				}
				break;
			}
			
			case "viewBorrowedBooks": {
				List<Lib_books> all_book_array = LbDC.viewBooksBorrowed();
				System.out.println("------------------------------------");
				for(Lib_books lib_book: all_book_array) {
					System.out.println("Checked out by: " + lib_book.getUser_name_fk());
					System.out.println(lib_book);
					System.out.println("------------------------------------");
				}
				break;
			}
			case "addBooks":{
				
				System.out.println("------------------------------------");
				System.out.println("Adding a Book");
				System.out.println("Enter Book series: ");
				String book_series = scan.nextLine();
				System.out.println("Enter Book title sequence: ");
				String book_title = scan.nextLine();
				System.out.println("Enter Author's first name: ");
				String author_f_name = scan.nextLine();
				System.out.println("Enter Author's last name: ");
				String author_l_name = scan.nextLine();
				
				Lib_books lib_book = new Lib_books(book_series,book_title,author_f_name,author_l_name,"Management");
				
				LbDC.addBooks(lib_book);
				System.out.println("------------------------------------");
				break;
			}
			case "removeBooks":{
				System.out.println("Enter a bookId");
				int bookId = scan.nextInt();
				scan.nextLine();
				LbDC.deleteBooks(bookId);
				break;
			}
			case "signOut":{
				displayMenu = false;
				break;
			}
			default: {
				System.out.println("" + input + " is not an option in the menu.\n Please Try again.");
				break;
			}
			}
		}
		//scan1.close();
		
	}
	
	//Casual display
	private void _Casual_display(Lib_users lib_user,  Scanner scan) {
		boolean displayMenu = true;
		Lib_books_DaoClass LbDC = new Lib_books_DaoClass();
		
		System.out.println("------------------");
		System.out.println("Welcome to the Library Login: " + lib_user.getUser_name());
		System.out.println("------------------");
		
		while(displayMenu) {
			System.out.println("viewAvailableBooks -> View all available books in the database");
			System.out.println("viewMyBorrowedBooks -> View checked books you have borrowed");
			System.out.println("checkoutBook -> Add a new book to the database");
			System.out.println("returnBook -> Remove a book from the database");
			System.out.println("signOut -> Sign out of the account");
			
			String input = scan.nextLine();
			
			switch (input) {
			
			case "viewAvailableBooks":{
				
				List<Lib_books> all_book_array= LbDC.viewAvailableBooks();
				System.out.println("------------------------------------");
				for(Lib_books lib_book: all_book_array) {
					System.out.println(lib_book);
					System.out.println("------------------------------------");
				}
				
				break;
			}
			case "viewMyBorrowedBooks":{
				List<Lib_books> borrowed_book_array = LbDC.viewBooksBorrowed(lib_user.getUser_name());
				System.out.println("------------------------------------");
				for(Lib_books lib_book: borrowed_book_array) {
					System.out.println(lib_book);
					System.out.println("------------------------------------");
				}
				break;
			}
			case "checkoutBook":{
				System.out.println("Enter a bookId");
				int bookId = scan.nextInt();
				scan.nextLine();
				LbDC.borrowBook(lib_user.getUser_name(),bookId);
				break;

			}
			case "returnBook":{
				System.out.println("Enter a bookId");
				int bookId = scan.nextInt();
				scan.nextLine();
				LbDC.returnBook(lib_user.getUser_name(),bookId);
				break;
			}
			case "signOut":{
				displayMenu = false;
				break;
			}
			default: {
				System.out.println("" + input + " is not an option in the menu.\nPlease Try again.");
				break;
			}
			}
		}
	}
	
}







