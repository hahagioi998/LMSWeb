package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.BookLoans;

public class BookLoansDAO extends BaseDAO<BookLoans> {

	public BookLoansDAO(Connection conn) {
		super(conn);
	}

	public void saveBookLoan(BookLoans bookLoans) throws SQLException {
		save("INSERT INTO tbl_book_loans (bookId, branchId, cardNo, dateOut, dueDate) VALUES (?,?,?,now(),date_add(now(), INTERVAL 1 WEEK))",
				new Object[] { bookLoans.getBookId(), bookLoans.getBranchId(), bookLoans.getCardNo() });
	}

	public void saveBookLoanComplete(BookLoans bookLoans) throws SQLException {
		save("INSERT INTO tbl_book_loans (bookId, branchId, cardNo, dateOut, dueDate, dateIn) VALUES (?,?,?,?,?,?)",
				new Object[] { bookLoans.getBookId(), bookLoans.getBranchId(), bookLoans.getCardNo(),
						bookLoans.getDateOut(), bookLoans.getDueDate(), bookLoans.getDateIn() });
	}

	public void overrideDueDate(BookLoans bookLoans) throws SQLException {
		save("UPDATE tbl_book_loans SET dueDate = date_add(dueDate, INTERVAL 1 week) WHERE bookId = ? and branchId = ? and cardNo = ?",
				new Object[] { bookLoans.getBookId(), bookLoans.getBranchId(), bookLoans.getCardNo() });
	}

	public void updateBookLoanDueDate(BookLoans bookLoans) throws SQLException {
		save("UPDATE tbl_book_loans SET dueDate = ? WHERE bookId = ? and branchId = ? and cardNo = ?", new Object[] {
				bookLoans.getDueDate(), bookLoans.getBookId(), bookLoans.getBranchId(), bookLoans.getCardNo() });
	}

	public void updateBookLoanDateIn(BookLoans bookLoans) throws SQLException {
		save("UPDATE tbl_book_loans SET dateIn = now() WHERE bookId = ? and branchId = ? and cardNo = ?",
				new Object[] { bookLoans.getBookId(), bookLoans.getBranchId(), bookLoans.getCardNo() });
	}

	public void deleteBookLoan(BookLoans bookLoans) throws SQLException {
		save("DELETE FROM tbl_book_loans WHERE bookId = ? and branchId = ? and cardNo = ?",
				new Object[] { bookLoans.getBookId(), bookLoans.getBranchId(), bookLoans.getCardNo() });
	}

	public Integer getBookLoansCount() throws SQLException {
		return getCount("SELECT count(*) as COUNT FROM tbl_book_loans WHERE dateIn IS null", null);
	}

	public Integer getLoanedOutBooksCount(BookLoans bookLoans) throws SQLException {
		return getCount(
				"SELECT count(*) as COUNT FROM tbl_book_loans WHERE bookId = ? AND branchid = ? AND cardNo = ? AND dateIn IS null",
				new Object[] { bookLoans.getBookId(), bookLoans.getBranchId(), bookLoans.getCardNo() });
	}

	public List<BookLoans> readBookLoans(String name) throws SQLException {
		if (name != null && !name.isEmpty()) {
			name = "%" + name + "%";
			return readAll(
					"SELECT * FROM tbl_book_loans, tbl_borrower WHERE tbl_borrower.name = ? and tbl_borrower.cardNo = tbl_book_loans.cardNo",
					new Object[] { name });
		} else {
			return readAll("SELECT * FROM tbl_book_loans", null);
		}

	}

	public List<BookLoans> readAllLoans(String name, Integer pageNo) throws SQLException {
		return readAll("SELECT * FROM tbl_book_loans WHERE dateIn IS null", null);
	}

	@Override
	public List<BookLoans> extractData(ResultSet rs) throws SQLException {
		List<BookLoans> bookLoans = new ArrayList<>();
		while (rs.next()) {
			BookLoans a = new BookLoans();
			a.setBookId(rs.getInt("bookId"));
			a.setBranchId(rs.getInt("branchId"));
			a.setCardNo(rs.getInt("cardNo"));
			a.setDateOut(rs.getString("dateOut"));
			a.setDueDate(rs.getString("dueDate"));
			a.setDateIn(rs.getString("dateIn"));
			bookLoans.add(a);
		}
		return bookLoans;
	}

	@Override
	public List<BookLoans> extractDataFirstLevel(ResultSet rs) throws SQLException {
		List<BookLoans> bookLoans = new ArrayList<>();
		while (rs.next()) {
			BookLoans a = new BookLoans();
			a.setBookId(rs.getInt("bookId"));
			a.setBranchId(rs.getInt("branchId"));
			a.setCardNo(rs.getInt("cardNo"));
			a.setDateOut(rs.getString("dateOut"));
			a.setDueDate(rs.getString("dueDate"));
			a.setDateIn(rs.getString("dateIn"));

			bookLoans.add(a);
		}
		return bookLoans;
	}
}
