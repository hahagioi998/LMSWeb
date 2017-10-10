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
import com.gcit.lms.entity.BookCopies;
import com.gcit.lms.entity.BookLoans;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Genre;
import com.gcit.lms.entity.Library_Branch;
import com.gcit.lms.entity.Publisher;
import com.gcit.lms.service.AdminService;
import com.gcit.lms.service.LibrarianService;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet({ "/chooseBranch", "/updateBranch" , "/addCopies", "/branchUpdate", "/copiesAdded" })
public class LibrarianServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private LibrarianService service = new LibrarianService();
	
	Integer branchId = 0;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LibrarianServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String reqUrl = request.getRequestURI().substring(request.getContextPath().length(), request.getRequestURI().length());
		String redirectUrl = "/viewauthors.jsp";
		
		
		switch (reqUrl) {
//		case "/overrideDueDate":
//			redirectUrl = overrideDueDate(request,response,"/override.jsp");
//			break;
//		
//		case "/deleteAuthor":
//			redirectUrl = deleteAuthor(request, response, redirectUrl);
//			break;
//			
//		case "/deleteGenre":
//			redirectUrl = deleteGenre(request, response, "/viewgenres.jsp");
//			break;
//		
//		case "/deletePublisher":
//			redirectUrl = deletePublisher(request, response, "/viewpublisher.jsp");
//			break;
//			
//		case "/deleteBorrower":
//			redirectUrl = deleteBorrower(request, response, "/viewborrower.jsp");
//			break;
//			
//		case "/deleteBranch":
//			redirectUrl = deleteBranch(request, response, "/viewbranch.jsp");
//			break;
//			
//		case "/deleteBook":
//			redirectUrl = deleteBook(request, response, "/viewbooks.jsp");
//			break;
//			
//		case "/pageAuthors":
//			redirectUrl = pageAuthors(request, response, redirectUrl);
//			break;
//
//		case "/pageGenres":
//			redirectUrl = pageGenres(request, response, "/viewgenres.jsp");
//			break;
//		
//		case "/pagePublishers":
//			redirectUrl = pagePublishers(request, response, "/viewpublisher.jsp");
//			break;
//			
//		case "/pageBorrowers":
//			redirectUrl = pageBorrowers(request, response, "/viewborrower.jsp");
//			break;
//		
//		case "/pageBranches":
//			redirectUrl = pageBranches(request, response, "/viewbranch.jsp");
//			break;
//			
//		case "/pageBooks":
//			redirectUrl = pageBooks(request, response, "/viewbooks.jsp");
//			break;
//			
//		case "/pageBookLoans":
//			redirectUrl = pageBookLoans(request, response, "/override.jsp");
//			break;
			
		case "/updateBranch":
			request.setAttribute("branchId", branchId);
			redirectUrl = "/updatebranch.jsp";
			break;
			
		case "/addCopies":
			request.setAttribute("branchId", branchId);
			redirectUrl = addBookCopies(request, response, "/branchbooks.jsp");
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
		String redirectUrl = "/viewauthors.jsp";
		switch (reqUrl) {

		case "/chooseBranch":
			branchId = Integer.parseInt(request.getParameter("branchId"));
			redirectUrl = "/library.jsp";
			break;
		
		case "/copiesAdded":
			redirectUrl = changeCopies(request,"/branchbooks.jsp");
			break;
			
		case "/branchUpdate":
			redirectUrl = addBranch(request, "/library.jsp");
			break;
			
		default:
			break;
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(redirectUrl);
		rd.forward(request, response);
		
	}

	private String addBranch(HttpServletRequest request, String redirectUrl) {
		Library_Branch libraryBranch = new Library_Branch();
		String message = "Updated Branch";

		libraryBranch.setBranchName(request.getParameter("branchName"));
		libraryBranch.setBranchAddress(request.getParameter("branchAddress"));

		libraryBranch.setBranchId(branchId);

		try {
			service.updateBranch(libraryBranch);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		request.setAttribute("statusMessage", message);
		return redirectUrl;
	}
	
	private String changeCopies(HttpServletRequest request, String redirectUrl) {
		BookCopies bookCopies= new BookCopies();
		String message = "Copies Modified";

		bookCopies.setBranchId(Integer.parseInt(request.getParameter("branchId")));
		bookCopies.setBookId(Integer.parseInt(request.getParameter("bookId")));

		bookCopies.setNoOfCopies(Integer.parseInt(request.getParameter("bbcopies")));

		try {
			service.updateNoOfCopies(bookCopies);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("branchId", branchId);
		request.setAttribute("statusMessage", message);
		return redirectUrl;
	}
	private String addBookCopies(HttpServletRequest request, HttpServletResponse response, String redirectUrl)
			throws ServletException, IOException {
		Integer pageNo = 1;
		if(request.getParameter("pageNo")!=null){
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		}
			try {
				request.setAttribute("books", service.readBooks(service.readBranchByPK(branchId), pageNo));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return redirectUrl;
	}
	
	
}
