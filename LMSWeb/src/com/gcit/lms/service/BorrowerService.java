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
	
	public List<Book> readBooksCheckOut(Library_Branch libraryBranch, Integer pageNo) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookDAO bdao = new BookDAO(conn);
			return bdao.readBooksByBranch(libraryBranch, pageNo);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		return null;
	}
	
	public List<Book> readBooksReturn(Library_Branch libraryBranch, Borrower borrower, Integer pageNo) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookDAO bdao = new BookDAO(conn);
			return bdao.readBooksByBorrower(libraryBranch, borrower, pageNo);
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
	
	public Integer getBranchesCount() throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			Library_BranchDAO bdao = new Library_BranchDAO(conn);
			return bdao.getBranchesCount();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		return null;
	}

	public Library_Branch readBranchByPK(Integer branchId) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			Library_BranchDAO bdao = new Library_BranchDAO(conn);
			return bdao.readBranchByPK(branchId);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		
		return null;
	}

	public Borrower readBorrowerByPK(Integer borrowerId) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			BorrowerDAO bdao = new BorrowerDAO(conn);
			return bdao.readBorrowerByPK(borrowerId);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		
		return null;
	}
	
	public Integer getCheckOutBooksCount(Integer branchId) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookCopiesDAO bcdao = new BookCopiesDAO(conn);
			return bcdao.getCheckOutBooksCount(branchId);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		
		return 0;
	}
	
	public Integer getLoanedOutBooksCount(Integer bookId, Integer cardNo, Integer branchId) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookLoansDAO bldao = new BookLoansDAO(conn);
			return bldao.getLoanedOutBooksCount(getBookLoans(branchId, bookId, cardNo));
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		
		return 0;
	}
	
	public BookLoans getBookLoans(Integer branchId, Integer bookId, Integer cardNo) throws SQLException{
		BookLoans bookLoans = new BookLoans();
		bookLoans.setBookId(bookId);
		bookLoans.setBranchId(branchId);
		bookLoans.setCardNo(cardNo);
		return bookLoans;
	}
	
	public BookCopies getBookCopies(Integer branchId, Integer bookId) throws SQLException{
		BookCopies bookCopies = new BookCopies();
		bookCopies.setBookId(bookId);
		bookCopies.setBranchId(branchId);
		return bookCopies;
	}
	
}
