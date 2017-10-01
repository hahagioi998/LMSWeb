package com.gcit.lms.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookLoans;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Genre;
import com.gcit.lms.entity.Library_Branch;
import com.gcit.lms.entity.Publisher;
import com.gcit.lms.service.AdminService;
import com.gcit.lms.service.BorrowerService;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet({ "/pageBranch", "/validateCard", "/checkOut", "/returnBook", "/checkBookOut", "/returnBookBack" })
public class BorrowerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BorrowerService borrowerService = new BorrowerService();
	
	Integer cardNo = 0;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BorrowerServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String reqUrl = request.getRequestURI().substring(request.getContextPath().length(), request.getRequestURI().length());
		String redirectUrl = "/borrowerfunc.jsp";
		
		
		switch (reqUrl) {		
		case "/pageBranch":
			redirectUrl = pageBranches(request, response, "/borrowerbranch.jsp");
			break;
			
		case "/checkOut":
			redirectUrl = checkOutBook(request, response, "/checkoutbooks.jsp");
			break;
			
		case "/returnBook":
			redirectUrl = returnBook(request, response, "/returnbooks.jsp");
			break;
			
		case "/checkBookOut":
			redirectUrl = checkBookOut(request, response, "/checkoutbooks.jsp");
			break;
			
		case "/returnBookBack":
			redirectUrl = returnBookBack(request, response, "/returnbooks.jsp");
			break;
			
		default:
			break;
		}
		RequestDispatcher rd = request.getRequestDispatcher(redirectUrl);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String reqUrl = request.getRequestURI().substring(request.getContextPath().length(), request.getRequestURI().length());
		String redirectUrl = "/borrowerfunc.jsp";
		switch (reqUrl) {
		case "/validateCard":
			redirectUrl = validateCard(request, "/borrowerbranch.jsp");
			break;
			
		default:
			break;
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(redirectUrl);
		rd.forward(request, response);
		
	}
	
	private String validateCard(HttpServletRequest request, String redirectUrl) {
	Borrower borrower = new Borrower();
	String message = "Here I am";
	if (request.getParameter("cardNo") != null && !request.getParameter("cardNo").isEmpty()) {
		
			borrower.setCardNo(Integer.parseInt(request.getParameter("cardNo")));
			
			try {
				if(borrowerService.validateCard(borrower)) {
					message = "Card No is valid!";
					cardNo = borrower.getCardNo();
				}
				else {
					message = "False Card Number";
					redirectUrl = "/borrowerfunc.jsp";
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}else{
		message = "Card No cannot be Empty";
		redirectUrl = "/borrowerfunc.jsp";
	}
	request.setAttribute("cardNo", cardNo);
	request.setAttribute("statusMessage", message);
	return redirectUrl;
}

	private String pageBranches(HttpServletRequest request, HttpServletResponse response, String redirectUrl)
			throws ServletException, IOException {
		request.setAttribute("cardNo", cardNo);
		if(request.getParameter("pageNo")!=null){
			Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
			try {
				request.setAttribute("branches", borrowerService.readLibraryBranch(null, pageNo));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return redirectUrl;
		}
		return null;
	}
	
	private String checkOutBook(HttpServletRequest request, HttpServletResponse response, String redirectUrl)
			throws ServletException, IOException {
		if(request.getParameter("cardNo")!=null && request.getParameter("branchId")!=null){
			Integer cardNo = Integer.parseInt(request.getParameter("cardNo"));
			Integer branchId = Integer.parseInt(request.getParameter("branchId"));
			Integer pageNo = 1;
			if(request.getParameter("pageNo")!=null) {
				pageNo = Integer.parseInt(request.getParameter("pageNo"));
			}
			request.setAttribute("cardNo", cardNo);
			request.setAttribute("branchId", branchId);
			try {
				request.setAttribute("books", borrowerService.readBooksCheckOut(borrowerService.readBranchByPK(branchId),pageNo));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return redirectUrl;
		}
		return null;
	}
	
	private String returnBook(HttpServletRequest request, HttpServletResponse response, String redirectUrl)
			throws ServletException, IOException {
		if(request.getParameter("cardNo")!=null && request.getParameter("branchId")!=null){
			Integer cardNo = Integer.parseInt(request.getParameter("cardNo"));
			Integer branchId = Integer.parseInt(request.getParameter("branchId"));
			request.setAttribute("cardNo", cardNo);
			request.setAttribute("branchId", branchId);
			Integer pageNo = 1;
			if(request.getParameter("pageNo")!=null) {
				pageNo = Integer.parseInt(request.getParameter("pageNo"));
			}
			try {
				request.setAttribute("books", borrowerService.readBooksReturn(borrowerService.readBranchByPK(branchId), borrowerService.readBorrowerByPK(cardNo), pageNo));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return redirectUrl;
		}
		return null;
	}
	
	private String checkBookOut(HttpServletRequest request, HttpServletResponse response, String redirectUrl)
			throws ServletException, IOException {
		if(request.getParameter("cardNo")!=null && request.getParameter("branchId")!=null){
			Integer cardNo = Integer.parseInt(request.getParameter("cardNo"));
			Integer branchId = Integer.parseInt(request.getParameter("branchId"));
			Integer pageNo = 1;
			if(request.getParameter("pageNo")!=null) {
				pageNo = Integer.parseInt(request.getParameter("pageNo"));
			}
			Integer bookId = Integer.parseInt(request.getParameter("bookId"));
			request.setAttribute("cardNo", cardNo);
			request.setAttribute("branchId", branchId);
			
			try {
				//add to tbl loans
				borrowerService.checkOutBook(borrowerService.getBookLoans(branchId, bookId, cardNo), borrowerService.getBookCopies(branchId, bookId));
				//reduce number of copies
				request.setAttribute("books", borrowerService.readBooksCheckOut(borrowerService.readBranchByPK(branchId),pageNo));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return redirectUrl;
		}
		return null;
	}
	
	private String returnBookBack(HttpServletRequest request, HttpServletResponse response, String redirectUrl)
			throws ServletException, IOException {
		if(request.getParameter("cardNo")!=null && request.getParameter("branchId")!=null){
			Integer cardNo = Integer.parseInt(request.getParameter("cardNo"));
			Integer branchId = Integer.parseInt(request.getParameter("branchId"));
			Integer pageNo = 1;
			if(request.getParameter("pageNo")!=null) {
				pageNo = Integer.parseInt(request.getParameter("pageNo"));
			}
			Integer bookId = Integer.parseInt(request.getParameter("bookId"));
			request.setAttribute("cardNo", cardNo);
			request.setAttribute("branchId", branchId);
			
			try {
				//add to tbl loans
				borrowerService.returnBook(borrowerService.getBookLoans(branchId, bookId, cardNo), borrowerService.getBookCopies(branchId, bookId));
				//reduce number of copies
				request.setAttribute("books", borrowerService.readBooksReturn(borrowerService.readBranchByPK(branchId),borrowerService.readBorrowerByPK(cardNo),pageNo));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return redirectUrl;
		}
		return null;
	}
	
}
