package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.models.Lib_books;
import com.revature.utils.ConnectionUtil;

public class Lib_books_DaoClass implements Lib_books_DaoInterface{

	@Override
	public List<Lib_books> viewAllBooks() {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			ResultSet rs = null;
			
			String sql = "select * from lib_books";
			
			Statement s = conn.createStatement();
			
			rs = s.executeQuery(sql);
			
			List<Lib_books> lib_books_array = new ArrayList<>();
			
			Lib_books lib_book = null;
			
			while(rs.next()) {
				
				lib_book = new Lib_books(
						rs.getInt("book_id"),
						rs.getString("book_series"),
						rs.getString("book_title"),
						rs.getString("author_l_name"),
						rs.getString("author_f_name"),
						rs.getString("user_name_fk"));
				
				lib_books_array.add(lib_book);
			}
			
			return lib_books_array;
			
			
		} catch(SQLException e) {
			System.out.println("Something went wrong with your checkusername database");
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Lib_books> viewAvailableBooks() {
		try(Connection conn = ConnectionUtil.getConnection()){
			
			ResultSet rs = null;
			
			String sql = "select * from lib_books join lib_users on user_name_fk = user_name where management = true";
			
			Statement s = conn.createStatement();
			
			rs = s.executeQuery(sql);
			
			List<Lib_books> lib_books_array = new ArrayList<>();
			
			Lib_books lib_book = null;
			
			while(rs.next()) {
				
				lib_book = new Lib_books(
						rs.getInt("book_id"),
						rs.getString("book_series"),
						rs.getString("book_title"),
						rs.getString("author_l_name"),
						rs.getString("author_f_name"),
						rs.getString("user_name_fk"));
				
				lib_books_array.add(lib_book);
			}
			
			return lib_books_array;
			
			
		} catch(SQLException e) {
			System.out.println("Something went wrong with your checkusername database");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void addBooks(Lib_books lib_book) {
		
		try (Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "insert into lib_books (book_series, book_title, author_l_name, author_f_name, user_name_fk) " +
			"Values (?,?,?,?,?)";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, lib_book.getBook_series());
			ps.setString(2, lib_book.getBook_title());
			ps.setString(3, lib_book.getAuthor_l_name());
			ps.setString(4, lib_book.getAuthor_f_name());
			ps.setString(5, lib_book.getUser_name_fk());
			
			ps.executeUpdate();
			
			System.out.println("Book Added Successfully: " + lib_book.getBook_series() + lib_book.getBook_title());
			
		} catch(SQLException e) {
			System.out.println("Something went wrong with your checkusername database");
			e.printStackTrace();
		}
		return;
		
	}

	@Override
	public void deleteBooks(int bookId) {
		try(Connection conn = ConnectionUtil.getConnection()){
			
			ResultSet rs = null;
			String sql;
			PreparedStatement ps;
			
			sql = "select * from lib_books WHERE book_id = ? and user_name_fk = 'Management';";
			
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, bookId);
			
			rs = ps.executeQuery();
			if(!(rs.next())) {
				System.out.println("Book Id is invalid or User currently borrowing Book");
				return;
			}
			
			sql = "delete from Lib_books where book_id = ?";
			
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, bookId);
			
			ps.executeUpdate();
			
			System.out.println("Removed Book Successfully: " + bookId);
			
		} catch (SQLException e) {
			System.out.println("Something went wrong with your database");
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Lib_books> viewBooksBorrowed() {
		try(Connection conn = ConnectionUtil.getConnection()){
			
			ResultSet rs = null;
			
			String sql = "select * from lib_books where user_name_fk != 'Management'";
			
			Statement s = conn.createStatement();
			
			rs = s.executeQuery(sql);
			
			List<Lib_books> lib_books_array = new ArrayList<>();
			
			Lib_books lib_book = null;
			
			while(rs.next()) {
				
				lib_book = new Lib_books(
						rs.getInt("book_id"),
						rs.getString("book_series"),
						rs.getString("book_title"),
						rs.getString("author_l_name"),
						rs.getString("author_f_name"),
						rs.getString("user_name_fk"));
				
				lib_books_array.add(lib_book);
			}
			
			return lib_books_array;
			
		} catch(SQLException e) {
			System.out.println("Something went wrong with your checkusername database");
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Lib_books> viewBooksBorrowed(String username) {
		try(Connection conn = ConnectionUtil.getConnection()){
			
			ResultSet rs = null;
			
			String sql = "select * from lib_books where user_name_fk = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, username);
			
			rs = ps.executeQuery();
			
			List<Lib_books> lib_books_array = new ArrayList<>();
			
			Lib_books lib_book = null;
			
			while(rs.next()) {
				
				lib_book = new Lib_books(
						rs.getInt("book_id"),
						rs.getString("book_series"),
						rs.getString("book_title"),
						rs.getString("author_l_name"),
						rs.getString("author_f_name"),
						rs.getString("user_name_fk"));
				
				lib_books_array.add(lib_book);
			}
			
			return lib_books_array;
			
			
		} catch(SQLException e) {
			System.out.println("Something went wrong with your checkusername database");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void borrowBook(String username, int bookId) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			ResultSet rs = null;
			String sql;
			PreparedStatement ps;
			Logger logDao = LogManager.getLogger(Lib_books_DaoClass.class);
			
			sql = "select * from lib_books WHERE book_id = ?;";
			
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, bookId);
			
			rs = ps.executeQuery();
			if(!(rs.next())) {
				System.out.println("Book Id is invalid");
				return;
			}
			//CHECK WHETHER YOU ALREADY HAVE THAT BOOK
			
			sql = "select * from lib_books WHERE user_name_fk = 'Management' AND book_id = ?;";
			
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, bookId);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				
				sql = "update lib_books set user_name_fk = ? where book_id = ?";
				
				ps = conn.prepareStatement(sql);
				
				ps.setString(1, username);
				ps.setInt(2, bookId);
				
				ps.executeUpdate();
				
				System.out.println("Successfully borrowed book: " + bookId);
				logDao.info(username + " SUCCESSFULLY BORROWED BOOK " + bookId);
				
				return;
			}
			
			System.out.println("Someone has already checked out this book!");
			return;
			
			
		} catch(SQLException e) {
			System.out.println("Something went wrong with your checkusername database");
			e.printStackTrace();
		}
		
	}

	@Override
	public void returnBook(String username, int bookId) {

		try(Connection conn = ConnectionUtil.getConnection()){
			
			ResultSet rs = null;
			String sql;
			PreparedStatement ps;
			Logger logDao = LogManager.getLogger(Lib_books_DaoClass.class);
			
			sql = "select * from lib_books WHERE book_id = ?;";
			
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, bookId);
			
			rs = ps.executeQuery();
			if(!(rs.next())) {
				System.out.println("Book Id is invalid");
				return;
			}
			
			//CHECK WHETHER YOU ALREADY HAVE THAT BOOK
			sql = "select * from lib_books WHERE user_name_fk = ? AND book_id = ?;";
			
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, username);
			ps.setInt(2, bookId);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				sql = "update lib_books set user_name_fk = 'Management' where book_id = ?";
				
				ps = conn.prepareStatement(sql);
				
				ps.setInt(1, bookId);
				
				ps.executeUpdate();
				
				System.out.println("Successfully borrowed book: " + bookId);
				logDao.info(username + " SUCCESSFULLY RETURNED BOOK " + bookId);
			} else {
				System.out.println("You have do not have this book checked out");
			}
			
			return;
			
			
		} catch(SQLException e) {
			System.out.println("Something went wrong with your checkusername database");
			e.printStackTrace();
		}
		
	}

	
	@Override
	public List<Lib_books> vBT(String series) {
		try(Connection conn = ConnectionUtil.getConnection()){
			
			ResultSet rs = null;
			
			String sql = "select * from lib_books where lower(book_series) like lower(?)";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1,"%" + series + "%");
			
			rs = ps.executeQuery();
			
			List<Lib_books> lib_books_array = new ArrayList<>();
			
			Lib_books lib_book = null;
			
			while(rs.next()) {
				
				lib_book = new Lib_books(
						rs.getInt("book_id"),
						rs.getString("book_series"),
						rs.getString("book_title"),
						rs.getString("author_l_name"),
						rs.getString("author_f_name"),
						rs.getString("user_name_fk"));
				
				lib_books_array.add(lib_book);
			}
			
			return lib_books_array;
			
			
		} catch(SQLException e) {
			System.out.println("Something went wrong with your checkusername database");
			e.printStackTrace();
		}
		
		return null;
	}
	

	@Override
	public List<Lib_books> vBA(String author_l_name) {
		try(Connection conn = ConnectionUtil.getConnection()){
			
			ResultSet rs = null;
			
			String sql = "select * from lib_books where lower(author_l_name) like lower(?)";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1,author_l_name);
			
			rs = ps.executeQuery();
			
			List<Lib_books> lib_books_array = new ArrayList<>();
			
			Lib_books lib_book = null;
			
			while(rs.next()) {
				
				lib_book = new Lib_books(
						rs.getInt("book_id"),
						rs.getString("book_series"),
						rs.getString("book_title"),
						rs.getString("author_l_name"),
						rs.getString("author_f_name"),
						rs.getString("user_name_fk"));
				
				lib_books_array.add(lib_book);
			}
			
			return lib_books_array;
			
			
		} catch(SQLException e) {
			System.out.println("Something went wrong with your checkusername database");
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Lib_books> vBTAvailable(String series) {
		try(Connection conn = ConnectionUtil.getConnection()){
			
			ResultSet rs = null;
			
			String sql = "select * from lib_books where lower(book_series) like lower(?) and user_name_fk = 'Management'";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1,"%" + series + "%");
			
			rs = ps.executeQuery();
			
			List<Lib_books> lib_books_array = new ArrayList<>();
			
			Lib_books lib_book = null;
			
			while(rs.next()) {
				
				lib_book = new Lib_books(
						rs.getInt("book_id"),
						rs.getString("book_series"),
						rs.getString("book_title"),
						rs.getString("author_l_name"),
						rs.getString("author_f_name"),
						rs.getString("user_name_fk"));
				
				lib_books_array.add(lib_book);
			}
			
			return lib_books_array;
			
			
		} catch(SQLException e) {
			System.out.println("Something went wrong with your checkusername database");
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Lib_books> vBAAvailable(String author_l_name) {
		try(Connection conn = ConnectionUtil.getConnection()){
			
			ResultSet rs = null;
			
			String sql = "select * from lib_books where lower(author_l_name) like lower(?) and user_name_fk = 'Management'";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1,author_l_name);
			
			rs = ps.executeQuery();
			
			List<Lib_books> lib_books_array = new ArrayList<>();
			
			Lib_books lib_book = null;
			
			while(rs.next()) {
				
				lib_book = new Lib_books(
						rs.getInt("book_id"),
						rs.getString("book_series"),
						rs.getString("book_title"),
						rs.getString("author_l_name"),
						rs.getString("author_f_name"),
						rs.getString("user_name_fk"));
				
				lib_books_array.add(lib_book);
			}
			
			return lib_books_array;
			
			
		} catch(SQLException e) {
			System.out.println("Something went wrong with your checkusername database");
			e.printStackTrace();
		}
		
		return null;
	}



}
