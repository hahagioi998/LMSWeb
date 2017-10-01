package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.Borrower;

public class BorrowerDAO extends BaseDAO<Borrower> {

	public BorrowerDAO(Connection conn) {
		super(conn);
	}

	public void saveBorrower(Borrower borrower) throws SQLException {
		save("INSERT INTO tbl_borrower (name,address,phone) VALUES (?,?,?)", new Object[] { borrower.getName() , borrower.getAddress(), borrower.getPhone()});
	}
	
	public Integer saveBorrowerWithID(Borrower borrower) throws SQLException {
		return saveWithID("INSERT INTO tbl_borrower (name,address,phone) VALUES (?)", new Object[] { borrower.getName() , borrower.getAddress(), borrower.getPhone()});
	}

	public void updateBorrower(Borrower borrower) throws SQLException {
		save("UPDATE tbl_borrower SET name = ?, address = ?, phone = ? WHERE cardNo = ?",
				new Object[] { borrower.getName(), borrower.getAddress(), borrower.getPhone(), borrower.getCardNo() });
	}

	public void deleteBorrower(Borrower borrower) throws SQLException {
		save("DELETE FROM tbl_borrower WHERE cardNo = ?", new Object[] { borrower.getCardNo() });
	}
	
	
	public List<Borrower> readBorrowers(String name) throws SQLException {
		if(name !=null && !name.isEmpty()){
			name = "%"+name+"%";
			return readAll("SELECT * FROM tbl_borrower WHERE name like ?", new Object[]{name});
		}else{
			return readAll("SELECT * FROM tbl_borrower", null);
		}
		
	}

	public List<Borrower> readBorrowers(String borrowerName, Integer pageNo) throws SQLException {
		setPageNo(pageNo);
		if(borrowerName !=null && !borrowerName.isEmpty()){
			borrowerName = "%"+borrowerName+"%";
			return readAll("SELECT * FROM tbl_borrower WHERE name like ?", new Object[]{borrowerName});
		}else{
			return readAll("SELECT * FROM tbl_borrower", null);
		}
		
	}

	public Integer getBorrowersCount() throws SQLException {
		return getCount("SELECT count(*) as COUNT FROM tbl_borrower", null);
	}
	
	public Borrower readBorrowerByPK(Integer borrowerId) throws SQLException {
		List<Borrower> borrowers = readAll("SELECT * FROM tbl_borrower WHERE cardNo = ?", new Object[]{borrowerId});
		if(borrowers!=null){
			return borrowers.get(0);
		}
		return null;
	}
	
	@Override
	public List<Borrower> extractData(ResultSet rs) throws SQLException {
		List<Borrower> borrowers = new ArrayList<>();
		BookLoansDAO bdao = new BookLoansDAO(conn);
		while(rs.next()){
			Borrower a = new Borrower();
			a.setCardNo(rs.getInt("cardNo"));
			a.setName(rs.getString("name"));
			a.setAddress(rs.getString("address"));
			a.setPhone(rs.getString("phone"));
			a.setBookLoans(bdao.readAllFirstLevel("SELECT * FROM tbl_book_loans WHERE cardNo = ?", new Object[]{a.getCardNo()}));
			borrowers.add(a);
		}
		
		return borrowers;
	}
	
	@Override
	public List<Borrower> extractDataFirstLevel(ResultSet rs) throws SQLException {
		List<Borrower> borrowers = new ArrayList<>();
		while(rs.next()){
			Borrower a = new Borrower();
			a.setCardNo(rs.getInt("cardNo"));
			a.setName(rs.getString("name"));
			a.setAddress(rs.getString("address"));
			a.setPhone(rs.getString("phone"));
			borrowers.add(a);
		}
		
		return borrowers;
	}

	public boolean validateCard(Borrower borrower) throws SQLException {
		List<Borrower> temp = readAll("SELECT * FROM tbl_borrower WHERE cardNo = ?", new Object[]{borrower.getCardNo()});
		if(!temp.isEmpty())
			return true;
		return false;
	}
}
