/**
 * 
 */
package com.gcit.lms.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author admin
 *
 */
public class Library_Branch implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4187390478843232840L;
	
	private Integer branchId;
	private String branchName;
	private String branchAddress;
	private List<BookCopies> bookCopies;
	
	public Integer getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getBranchAddress() {
		return branchAddress;
	}
	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}
	public List<BookCopies> getBookCopies() {
		return bookCopies;
	}
	public void setBookCopies(List<BookCopies> bookCopies) {
		this.bookCopies = bookCopies;
	}
	
	public int getBookCopies(Integer bookId)
	{
		for(BookCopies b: bookCopies)
		{
			if(bookId.equals(b.getBookId()))
				return b.getNoOfCopies();
		}
		
		return 0;
	}

}


