package com.gcit.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.gcit.lms.dao.BookCopiesDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BookLoansDAO;
import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.dao.Library_BranchDAO;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookCopies;
import com.gcit.lms.entity.BookLoans;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Library_Branch;

public class BorrowerService {
	
	public Utilities util = new Utilities();
	
	public List<Library_Branch> readBranch(String searchString) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			Library_BranchDAO bdao = new Library_BranchDAO(conn);
			return bdao.readBranch(searchString);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		return null;
	}
	
	public List<Book> readBooksCheckOut(Library_Branch libraryBranch) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookDAO bdao = new BookDAO(conn);
			return bdao.readBooksByBranch(libraryBranch);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		return null;
	}
	
	public List<Book> readBooksReturn (Library_Branch libraryBranch, Borrower borrower) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookDAO bdao = new BookDAO(conn);
			return bdao.readBooksByBorrower(libraryBranch, borrower);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		return null;
	}
	
	public void checkOutBook(BookLoans bookLoans, BookCopies bookCopies) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookCopiesDAO bcdao = new BookCopiesDAO(conn);
			BookLoansDAO bldao = new BookLoansDAO(conn);
			
			bldao.saveBookLoan(bookLoans);
			bcdao.checkOutBookCopies(bookCopies);
			
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
	}
	
	public void returnBook(BookLoans bookLoans, BookCopies bookCopies) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookCopiesDAO bcdao = new BookCopiesDAO(conn);
			BookLoansDAO bldao = new BookLoansDAO(conn);
			
			bldao.updateBookLoanDateIn(bookLoans);
			bcdao.returnBookCopies(bookCopies);
			
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
	}
	
	public List<Library_Branch> readBranches(Integer pageNo) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			Library_BranchDAO lbdao = new Library_BranchDAO(conn);
			
			return lbdao.readBranches(null, pageNo);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		return null;
	}
	
	public List<Library_Branch> readLibraryBranch(String searchString, Integer pageNo) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			Library_BranchDAO bdao = new Library_BranchDAO(conn);
			return bdao.readBranches(searchString, pageNo);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		
		return null;
	}
	
	public boolean validateCard(Borrower borrower) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			BorrowerDAO bdao = new BorrowerDAO(conn);
			return bdao.validateCard(borrower);
		}catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		return false;
	}

}
