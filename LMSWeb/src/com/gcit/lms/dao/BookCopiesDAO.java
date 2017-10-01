package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.BookCopies;

public class BookCopiesDAO extends BaseDAO<BookCopies> {

	public BookCopiesDAO(Connection conn) {
		super(conn);
	}

	public void saveBookCopies(BookCopies bookCopies) throws SQLException {
		save("INSERT INTO tbl_book_copies (bookId, branchId, noOfCopies) VALUES (?,?,?)",
				new Object[] { bookCopies.getBookId(), bookCopies.getBranchId(), bookCopies.getNoOfCopies() });
	}

	public void updateBookCopies(BookCopies bookCopies) throws SQLException {
		save("UPDATE tbl_book_copies SET noOfCopies = ? WHERE bookId = ? and branchID = ?",
				new Object[] {bookCopies.getNoOfCopies(), bookCopies.getBookId(), bookCopies.getBranchId() });
	}

	public void deleteAuthor(BookCopies bookCopies) throws SQLException {
		save("DELETE FROM tbl_book_copies WHERE bookId = ? AND branchId = ?", new Object[] { bookCopies.getBookId(), bookCopies.getBranchId() });
	}

	public List<BookCopies> readBooks(String branchName) throws SQLException {
		if (branchName != null && !branchName.isEmpty()) {
			branchName = "%" + branchName + "%";
			return readAll(
					"SELECT * FROM tbl_book_copies, tbl_library_branch, tbl_book WHERE branchName like ? AND tbl_book_copies.bookId = tbl_book.bookId AND tbl_book_copies.branchId = tbl_library_branch.branchId",
					new Object[] { branchName });
		} else {
			return readAll("SELECT * FROM tbl_book_copies", null);
		}

	}
	
	public BookCopies showBookCopies(BookCopies bookCopies) throws SQLException {
		List<BookCopies> books = readAll("SELECT * FROM tbl_book_copies WHERE branchId = ? AND bookId = ?", new Object[] { bookCopies.getBranchId(), bookCopies.getBookId() }) ;
		
		if(books != null)
		{
			return books.get(0);
		}
		return null;
	}
	
	public List<BookCopies> readBranches(String bookName) throws SQLException {
		if (bookName != null && !bookName.isEmpty()) {
			bookName = "%" + bookName + "%";
			return readAll(
					"SELECT * FROM tbl_book_copies, tbl_library_branch, tbl_book WHERE title like ? AND tbl_book_copies.bookId = tbl_book.bookId AND tbl_book_copies.branchId = tbl_library_branch.branchId",
					new Object[] { bookName });
		} else {
			return readAll("SELECT * FROM tbl_book_copies", null);
		}

	}
	
	public void checkOutBookCopies(BookCopies bookCopies) throws SQLException {
		save("UPDATE tbl_book_copies SET noOfCopies = noOfCopies-1 WHERE bookId = ? and branchID = ?",
				new Object[] { bookCopies.getBookId(), bookCopies.getBranchId() });		
	}

	public void returnBookCopies(BookCopies bookCopies) throws SQLException {
		save("UPDATE tbl_book_copies SET noOfCopies = noOfCopies+1 WHERE bookId = ? and branchID = ?",
				new Object[] { bookCopies.getBookId(), bookCopies.getBranchId() });		
	}
	
	@Override
	public List<BookCopies> extractData(ResultSet rs) throws SQLException {
		List<BookCopies> bookCopies = new ArrayList<>();
		while (rs.next()) {
			BookCopies a = new BookCopies();
			a.setBookId(rs.getInt("bookId"));
			a.setBranchId(rs.getInt("branchId"));
			a.setNoOfCopies(rs.getInt("noOfCopies"));
			bookCopies.add(a);
		}

		return bookCopies;
	}

	@Override
	public List<BookCopies> extractDataFirstLevel(ResultSet rs) throws SQLException {
		List<BookCopies> bookCopies = new ArrayList<>();
		while (rs.next()) {
			BookCopies a = new BookCopies();
			a.setBookId(rs.getInt("bookId"));
			a.setBranchId(rs.getInt("branchId"));
			a.setNoOfCopies(rs.getInt("noOfCopies"));
			bookCopies.add(a);
		}

		return bookCopies;
	}
}
