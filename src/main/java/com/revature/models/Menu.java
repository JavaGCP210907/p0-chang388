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
		Logger log = LogManager.getLogger(Menu.class);
		
		
		//pretty greeting :)
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Hello! Welcome to Debora Library application");
		//System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		
		//display the menu as long as the displayMenu boolean is true
		while(displayMenu) {
			
			System.out.println("\n***************************************************");
			System.out.println("Log into your account, or sign up");
			System.out.println("***************************************************");
			
			
			System.out.println("login  -> Log into your account with username and password");
			System.out.println("signup -> Create your free Debora account today");
			System.out.println("exit   -> Exit");
			System.out.println("---------------------------------------------------");
			
			//parse user input after they choose a menu
			input = scan.nextLine();
			
			//switch statement that takes the input and delivers the appropiate response
			//you may have a hard time reading this functionality
			//I suggest you look at our dao layer interfaces to see everything the CLI is able to do
			switch(input) {
			case "login":{
				System.out.println("\n---------------------------------------------------");
				System.out.println("Please enter your username: ");
				String username = scan.nextLine();
				System.out.println("---------------------------------------------------");
				System.out.println("Please enter you password: ");
				String password = scan.nextLine();
				Lib_users lib_user = LuDC.checkLib_password(username,password);
				
				if(lib_user != null) {
					log.info("USER LOGGIN ACCOUNT SUCCESSFUL: " + username);
					if(lib_user.isManagement()) {
						this._Manager_display(lib_user,scan);
						//input = scan.nextLine();
					} else {
						this._Casual_display(lib_user,scan);
					}
				} else {
					System.out.println("\n---------------------------------------------------");
					System.out.println("Username or Password is incorrect,\nPlease try again");
					System.out.println("---------------------------------------------------");
					log.error("USER LOGGIN ACCOUNT FAILED: " + username);
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
				System.out.println("\n------------------------------------");
				System.out.println("" + input + " is not an option in the menu.\nPlease Try again.");
				break;
			}
			}
			if(!displayMenu) {
				System.out.println("Thank you for using the Debora Library application\nHave a wonderful day");
			}
		} //end of while loop
		
		scan.close();
	}
	
	
	//Manager display
	private void _Manager_display(Lib_users lib_user, Scanner scan) {
		
		//Scanner scan1 = new Scanner(System.in);
		boolean displayMenu = true;
		Lib_books_DaoClass LbDC = new Lib_books_DaoClass();

		while(displayMenu) {
			System.out.println("\n***************************************************");
			System.out.println("Welcome to the Management Portal: " + lib_user.getUser_name());
			System.out.println("***************************************************");
			System.out.println("viewAllBooks -> View unchecked books in the database");
			System.out.println("	vBT -> View Books by title");
			System.out.println("	vBA -> View Books by Author's Last Name");
			System.out.println("viewBorrowedBooks -> View checked books you have borrowed");
			System.out.println("addBook -> Add a new book to the database");
			System.out.println("removeBook -> Remove a book from the database");
			System.out.println("signOut -> Sign out of the account");
			
			String input = scan.nextLine();
			
			switch (input) {
			
			case "viewAllBooks":{
				
				List<Lib_books> all_book_array= LbDC.viewAllBooks();
				System.out.println("\nAll Books");
				System.out.println("------------------------------------");
				for(Lib_books lib_book: all_book_array) {
					System.out.println(lib_book);
					System.out.println("------------------------------------");
				}
				break;
			}
			
			case "vBT":{
				System.out.println("Enter word to search Book Series:");
				String book_series = scan.nextLine();
				List<Lib_books> all_book_array = LbDC.vBT(book_series);
				System.out.println("\nSearch Book Series that include word: " + book_series);
				System.out.println("------------------------------------");
				for(Lib_books lib_book: all_book_array) {
					System.out.println(lib_book);
					System.out.println("------------------------------------");
				}
				break;
			}
			case "vBA":{
				System.out.println("Search Book Through Author:");
				String book_author = scan.nextLine();
				List<Lib_books> all_book_array = LbDC.vBA(book_author);
				System.out.println("\nSearch Book Through Author: " + book_author);
				System.out.println("------------------------------------");
				for(Lib_books lib_book: all_book_array) {
					System.out.println(lib_book);
					System.out.println("------------------------------------");
				}
				break;
			}
			
			case "viewBorrowedBooks": {
				List<Lib_books> all_book_array = LbDC.viewBooksBorrowed();
				System.out.println("\nChecked out books");
				System.out.println("------------------------------------");
				for(Lib_books lib_book: all_book_array) {
					System.out.println("Checked out by: " + lib_book.getUser_name_fk());
					System.out.println(lib_book);
					System.out.println("------------------------------------");
				}
				break;
			}
			case "addBook":{
				System.out.println("\nAdd books to the library");
				System.out.println("------------------------------------");
				System.out.println("Enter Book series: ");
				String book_series = scan.nextLine();
				System.out.println("Enter Book title sequence: ");
				String book_title = scan.nextLine();
				System.out.println("Enter Author's first name: ");
				String author_f_name = scan.nextLine();
				System.out.println("Enter Author's last name: ");
				String author_l_name = scan.nextLine();
				
				Lib_books lib_book = new Lib_books(book_series,book_title,author_l_name,author_f_name,"Management");
				
				LbDC.addBooks(lib_book);
				System.out.println("------------------------------------");
				break;
			}
			case "removeBook":{
				System.out.println("\nRemove a Library book");
				System.out.println("------------------------------------");
				System.out.println("Enter a book Id to remove from Debora Library: ");
				int bookId = scan.nextInt();
				scan.nextLine();
				LbDC.deleteBooks(bookId);
				System.out.println("------------------------------------");
				break;
			}
			case "signOut":{
				displayMenu = false;
				System.out.println("\n------------------------------------");
				System.out.println("Signing out of: " + lib_user.getUser_name());
				System.out.println("------------------------------------");
				break;
			}
			default: {
				System.out.println("\n------------------------------------");
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
		
		while(displayMenu) {
			System.out.println("\n***************************************************");
			System.out.println("Welcome to the User Portal: " + lib_user.getF_name() + " " + lib_user.getL_name());
			System.out.println("Select an action from the Menu below");
			System.out.println("***************************************************");
			System.out.println("viewAvailableBooks -> View all available books in the database");
			System.out.println("	vABT -> View Available Books by title");
			System.out.println("	vABA -> View Available Books by Author's Last Name");
			System.out.println("viewMyBorrowedBooks -> View checked books you have borrowed");
			System.out.println("checkoutBook -> Add a new book to the database");
			System.out.println("returnBook -> Remove a book from the database");
			System.out.println("signOut -> Sign out of the account");
			System.out.println("---------------------------------------------------");
			
			String input = scan.nextLine();
			
			switch (input) {
			
			case "viewAvailableBooks":{
				
				List<Lib_books> all_book_array= LbDC.viewAvailableBooks();
				System.out.println("\nAvailable Books for checkout");
				System.out.println("------------------------------------");
				for(Lib_books lib_book: all_book_array) {
					System.out.println(lib_book);
					System.out.println("------------------------------------");
				}
				
				break;
			}
			case "vABT":{
				System.out.println("Enter word to search Book Series:");
				String book_series = scan.nextLine();
				List<Lib_books> all_book_array = LbDC.vBTAvailable(book_series);
				System.out.println("\nSearch Available Book Series that include word: " + book_series);
				System.out.println("------------------------------------");
				for(Lib_books lib_book: all_book_array) {
					System.out.println(lib_book);
					System.out.println("------------------------------------");
				}
				break;
			}
			case "vABA":{
				System.out.println("Search Available Book Through Author:");
				String book_author = scan.nextLine();
				List<Lib_books> all_book_array = LbDC.vBAAvailable(book_author);
				System.out.println("\nSearch Book Through Author: " + book_author);
				System.out.println("------------------------------------");
				for(Lib_books lib_book: all_book_array) {
					System.out.println(lib_book);
					System.out.println("------------------------------------");
				}
				break;
			}
			case "viewMyBorrowedBooks":{
				List<Lib_books> borrowed_book_array = LbDC.viewBooksBorrowed(lib_user.getUser_name());
				System.out.println("\nMy checked out books");
				System.out.println("------------------------------------");
				for(Lib_books lib_book: borrowed_book_array) {
					System.out.println(lib_book);
					System.out.println("------------------------------------");
				}
				break;
			}
			case "checkoutBook":{
				System.out.println("\n------------------------------------");
				System.out.println("Enter the bookId for checkout: ");
				int bookId = scan.nextInt();
				scan.nextLine();
				LbDC.borrowBook(lib_user.getUser_name(),bookId);
				break;

			}
			case "returnBook":{
				System.out.println("\n------------------------------------");
				System.out.println("Enter the bookId to return: ");
				int bookId = scan.nextInt();
				scan.nextLine();
				LbDC.returnBook(lib_user.getUser_name(),bookId);
				break;
			}
			case "signOut":{
				displayMenu = false;
				System.out.println("\n------------------------------------");
				System.out.println("Signing out of: " + lib_user.getUser_name());
				System.out.println("------------------------------------");
				break;
			}
			default: {
				System.out.println("\n------------------------------------");
				System.out.println("" + input + " is not an option in the menu.\nPlease Try again.");
				break;
			}
			}
		}
	}
	
}







