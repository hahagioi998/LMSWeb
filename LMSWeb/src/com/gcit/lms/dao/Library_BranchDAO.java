package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.Library_Branch;
import com.gcit.lms.entity.BookCopies;

@SuppressWarnings({ })
public class Library_BranchDAO extends BaseDAO<Library_Branch> {

	public Library_BranchDAO(Connection conn) {
		super(conn);
	}

	public void saveLibraryBranch(Library_Branch library_Branch) throws SQLException {
		save("INSERT INTO tbl_library_branch (branchName,branchAddress) VALUES (?,?)",
				new Object[] { library_Branch.getBranchName(), library_Branch.getBranchAddress() });
	}

	public void saveBookLibraryBranch(Library_Branch branch) throws SQLException {
		for (BookCopies b : branch.getBookCopies()) {
			save("INSERT INTO tbl_book_copies VALUES (?,?,?)",
					new Object[] { b.getBookId(), branch.getBranchId(), b.getNoOfCopies() });
		}
	}

	public Integer saveLibraryBranchWithID(Library_Branch library_Branch) throws SQLException {
		return saveWithID("INSERT INTO tbl_library_Branch (branchName, branchAddress) VALUES (?,?)",
				new Object[] { library_Branch.getBranchName(), library_Branch.getBranchAddress() });
	}

	public void updateBranch(Library_Branch library_Branch) throws SQLException {
		save("UPDATE tbl_library_branch SET branchName = ?, branchAddress = ? WHERE branchId = ?",
				new Object[] { library_Branch.getBranchName(), library_Branch.getBranchAddress(), library_Branch.getBranchId() });
	}

	public void deleteLibraryBranch(Library_Branch library_Branch) throws SQLException {
		save("DELETE FROM tbl_library_Branch WHERE BranchId = ?", new Object[] { library_Branch.getBranchId() });
	}

	public List<Library_Branch> readBranch(String branchName) throws SQLException {
		if (branchName != null && !branchName.isEmpty()) {
			branchName = "%" + branchName + "%";
			return readAll("SELECT * FROM tbl_library_branch WHERE branchName like ?", new Object[] { branchName });
		} else {
			return readAll("SELECT * FROM tbl_library_branch", null);
		}

	}

	public List<Library_Branch> readBranches(String branchName, Integer pageNo) throws SQLException {
		setPageNo(pageNo);
		if(branchName !=null && !branchName.isEmpty()){
			branchName = "%"+branchName+"%";
			return readAll("SELECT * FROM tbl_library_branch WHERE branchName like ?", new Object[]{branchName});
		}else{
			return readAll("SELECT * FROM tbl_library_branch", null);
		}
		
	}

	public Integer getBranchesCount() throws SQLException {
		return getCount("SELECT count(*) as COUNT FROM tbl_library_branch", null);
	}
	
	public Library_Branch readBranchByPK(Integer branchId) throws SQLException {
		List<Library_Branch> branches = readAll("SELECT * FROM tbl_library_branch WHERE branchId = ?", new Object[]{branchId});
		if(branches!=null){
			return branches.get(0);
		}
		return null;
	}	
	
	@Override
	public List<Library_Branch> extractData(ResultSet rs) throws SQLException {
		List<Library_Branch> branchs = new ArrayList<>();
		BookCopiesDAO bcdao = new BookCopiesDAO(conn);
		while (rs.next()) {
			Library_Branch a = new Library_Branch();
			a.setBranchId(rs.getInt("branchId"));
			a.setBranchName(rs.getString("branchName"));
			a.setBranchAddress(rs.getString("branchAddress"));
			a.setBookCopies(bcdao.readAllFirstLevel("SELECT * FROM tbl_book_copies WHERE branchId = ?",
					new Object[] { a.getBranchId() }));
			branchs.add(a);
		}

		return branchs;
	}

	@Override
	public List<Library_Branch> extractDataFirstLevel(ResultSet rs) throws SQLException {
		List<Library_Branch> branchs = new ArrayList<>();
		while (rs.next()) {
			Library_Branch a = new Library_Branch();
			a.setBranchId(rs.getInt("branchId"));
			a.setBranchName(rs.getString("branchName"));
			a.setBranchAddress(rs.getString("branchAddress"));
			branchs.add(a);
		}

		return branchs;
	}
}
