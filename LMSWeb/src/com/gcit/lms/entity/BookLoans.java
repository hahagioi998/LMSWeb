/**
 * 
 */
package com.gcit.lms.entity;

import java.io.Serializable;

/**
 * @author admin
 *
 */
public class BookLoans implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1218121805471027343L;
	
	private int branchId;
	private int bookId;
	private int cardNo;
	private String dateOut;
	private String dueDate;
	private String dateIn;
	/**
	 * @return the branchId
	 */
	public int getBranchId() {
		return branchId;
	}
	/**
	 * @param branchId the branchId to set
	 */
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	/**
	 * @return the bookId
	 */
	public int getBookId() {
		return bookId;
	}
	/**
	 * @param bookId the bookId to set
	 */
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	/**
	 * @return the cardNo
	 */
	public int getCardNo() {
		return cardNo;
	}
	/**
	 * @param cardNo the cardNo to set
	 */
	public void setCardNo(int cardNo) {
		this.cardNo = cardNo;
	}
	/**
	 * @return the dateOut
	 */
	public String getDateOut() {
		return dateOut;
	}
	/**
	 * @param i the dateOut to set
	 */
	public void setDateOut(String i) {
		this.dateOut = i;
	}
	/**
	 * @return the dueDate
	 */
	public String getDueDate() {
		return dueDate;
	}
	/**
	 * @param dueDate the dueDate to set
	 */
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	/**
	 * @return the dateIn
	 */
	public String getDateIn() {
		return dateIn;
	}
	/**
	 * @param dateIn the dateIn to set
	 */
	public void setDateIn(String dateIn) {
		this.dateIn = dateIn;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bookId;
		result = prime * result + branchId;
		result = prime * result + cardNo;
		result = prime * result + ((dateOut == null) ? 0 : dateOut.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		BookLoans other = (BookLoans) obj;
		if (bookId != other.bookId) {
			return false;
		}
		if (branchId != other.branchId) {
			return false;
		}
		if (cardNo != other.cardNo) {
			return false;
		}
		if (dateOut == null) {
			if (other.dateOut != null) {
				return false;
			}
		} else if (!dateOut.equals(other.dateOut)) {
			return false;
		}
		return true;
	}
	
	
	
}
