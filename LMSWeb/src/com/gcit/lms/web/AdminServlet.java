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

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet({ "/addAuthor", "/deleteAuthor", "/editAuthor", "/pageAuthors", "/addGenre", "/deleteGenre", "/editGenre",
		"/pageGenres", "/addPublisher", "/deletePublisher", "/editPublisher", "/pagePublishers", "/addBorrower",
		"/deleteBorrower", "/editBorrower", "/pageBorrowers", "/addBranch",
		"/deleteBranch", "/editBranch", "/pageBranches", "/addBook",
		"/deleteBook", "/editBook", "/pageBooks", "/overrideDueDate", "/pageBookLoans" })
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AdminService adminService = new AdminService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String reqUrl = request.getRequestURI().substring(request.getContextPath().length(), request.getRequestURI().length());
		String redirectUrl = "/admin.jsp";
		
		
		switch (reqUrl) {
		case "/overrideDueDate":
			redirectUrl = overrideDueDate(request,response,"/override.jsp");
			break;
		
		case "/deleteAuthor":
			redirectUrl = deleteAuthor(request, response, redirectUrl);
			break;
			
		case "/deleteGenre":
			redirectUrl = deleteGenre(request, response, "/viewgenres.jsp");
			break;
		
		case "/deletePublisher":
			redirectUrl = deletePublisher(request, response, "/viewpublisher.jsp");
			break;
			
		case "/deleteBorrower":
			redirectUrl = deleteBorrower(request, response, "/viewborrower.jsp");
			break;
			
		case "/deleteBranch":
			redirectUrl = deleteBranch(request, response, "/viewbranch.jsp");
			break;
			
		case "/deleteBook":
			redirectUrl = deleteBook(request, response, "/viewbooks.jsp");
			break;
			
		case "/pageAuthors":
			redirectUrl = pageAuthors(request, response, redirectUrl);
			break;

		case "/pageGenres":
			redirectUrl = pageGenres(request, response, "/viewgenres.jsp");
			break;
		
		case "/pagePublishers":
			redirectUrl = pagePublishers(request, response, "/viewpublisher.jsp");
			break;
			
		case "/pageBorrowers":
			redirectUrl = pageBorrowers(request, response, "/viewborrower.jsp");
			break;
		
		case "/pageBranches":
			redirectUrl = pageBranches(request, response, "/viewbranch.jsp");
			break;
			
		case "/pageBooks":
			redirectUrl = pageBooks(request, response, "/viewbooks.jsp");
			break;
			
		case "/pageBookLoans":
			redirectUrl = pageBookLoans(request, response, "/override.jsp");
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
		case "/addAuthor":
			redirectUrl = addAuthor(request, redirectUrl, false);
			break;
			
		case "/editAuthor":
			redirectUrl = addAuthor(request, redirectUrl, true);
			break;

		case "/editGenre":	
			redirectUrl = addGenre(request, "/viewgenres.jsp", true);
			break;
			
		case "/addGenre":	
			redirectUrl = addGenre(request, "/viewgenres.jsp", false);
			break;
		
		case "/editPublisher":	
			redirectUrl = addPublisher(request, "/viewpublisher.jsp", true);
			break;
			
		case "/addPublisher":	
			redirectUrl = addPublisher(request, "/viewpublisher.jsp", false);
			break;
			
		case "/editBorrower":	
			redirectUrl = addBorrower(request, "/viewborrower.jsp", true);
			break;
			
		case "/addBorrower":	
			redirectUrl = addBorrower(request, "/viewborrower.jsp", false);
			break;
		
		case "/editBranch":	
			redirectUrl = addBranch(request, "/viewbranch.jsp", true);
			break;
			
		case "/addBranch":	
			redirectUrl = addBranch(request, "/viewbranch.jsp", false);
			break;
			
		case "/editBook":	
			redirectUrl = addBook(request, "/viewbooks.jsp", true);
			break;
			
		case "/addBook":	
			redirectUrl = addBook(request, "/viewbooks.jsp", false);
			break;
			
		default:
			break;
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(redirectUrl);
		rd.forward(request, response);
		
	}

	private String addAuthor(HttpServletRequest request, String redirectUrl, Boolean editMode) {
		Author author = new Author();
		String message = "Author added Sucessfully";
		
		if (request.getParameter("authorName") != null && !request.getParameter("authorName").isEmpty()) {
			if(request.getParameter("authorName").length() > 45){
				message = "Author Name cannot be more than 45 chars";
				redirectUrl = "/addauthor.jsp";
			}else{
				author.setAuthorName(request.getParameter("authorName"));
				String[] bookIds = request.getParameterValues("bookIds");
				List<Book> books = new ArrayList<>();
				if(bookIds != null) {
				for(String b: bookIds) {
					Book temp = new Book();
//					System.out.println(b);
					temp.setBookId(Integer.parseInt(b));
					books.add(temp);
				}

				author.setBooks(books);	
				}
				if(editMode){
					author.setAuthorId(Integer.parseInt(request.getParameter("authorId")));
				}
				try {
					adminService.saveAuthor(author);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}else{
			message = "Author Name cannot be Empty";
			redirectUrl = "/addauthor.jsp";
		}
		request.setAttribute("statusMessage", message);
		return redirectUrl;
	}
	
	private String addGenre(HttpServletRequest request, String redirectUrl, Boolean editMode) {
		Genre genre = new Genre();
		String message = "Genre added Sucessfully";
		
		if (request.getParameter("genreName") != null && !request.getParameter("genreName").isEmpty()) {
			if(request.getParameter("genreName").length() > 45){
				message = "Genre Name cannot be more than 45 chars";
				redirectUrl = "/addgenre.jsp";
			}else{
				genre.setGenreName(request.getParameter("genreName"));
				String[] bookIds = request.getParameterValues("bookIds");
				List<Book> books = new ArrayList<>();
				if(bookIds != null) {
				for(String b: bookIds) {
					Book temp = new Book();
					temp.setBookId(Integer.parseInt(b));
					books.add(temp);
				}

				genre.setBooks(books);	
				}
				if(editMode){
					genre.setGenreId(Integer.parseInt(request.getParameter("genreId")));
				}
				try {
					adminService.saveGenre(genre);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}else{
			message = "Genre Name cannot be Empty";
			redirectUrl = "/addgenre.jsp";
		}
		request.setAttribute("statusMessage", message);
		return redirectUrl;
	}
	
	private String addPublisher(HttpServletRequest request, String redirectUrl, Boolean editMode) {
		Publisher publisher = new Publisher();
		String message = "Publisher added Sucessfully";
		
		if (request.getParameter("publisherName") != null && !request.getParameter("publisherName").isEmpty()) {
			if(request.getParameter("publisherName").length() > 45 || request.getParameter("publisherAddress").length() > 45 || request.getParameter("publisherPhone").length() > 45){
				message = "Each publisher detail cannot be more than 45 chars";
				redirectUrl = "/addpublisher.jsp";
			}else{
				publisher.setPublisherName(request.getParameter("publisherName"));
				publisher.setPublisherAddress(request.getParameter("publisherAddress"));
				publisher.setPublisherPhone(request.getParameter("publisherPhone"));
				}
				if(editMode){
					publisher.setPublisherId(Integer.parseInt(request.getParameter("publisherId")));
				}
				try {
					adminService.savePublisher(publisher);
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}else{
			message = "Publisher Name cannot be Empty";
			redirectUrl = "/addpublisher.jsp";
		}
		request.setAttribute("statusMessage", message);
		return redirectUrl;
	}
	
	private String addBorrower(HttpServletRequest request, String redirectUrl, Boolean editMode) {
		Borrower borrower = new Borrower();
		String message = "Borrower added Sucessfully";
		
		if (request.getParameter("borrowerName") != null && !request.getParameter("borrowerName").isEmpty()) {
			if(request.getParameter("borrowerName").length() > 45 || request.getParameter("borrowerAddress").length() > 45 || request.getParameter("borrowerPhone").length() > 45){
				message = "Each borrower detail cannot be more than 45 chars";
				redirectUrl = "/addborrower.jsp";
			}else{
				borrower.setName(request.getParameter("borrowerName"));
				borrower.setAddress(request.getParameter("borrowerAddress"));
				borrower.setPhone(request.getParameter("borrowerPhone"));
				}
				if(editMode){
					borrower.setCardNo(Integer.parseInt(request.getParameter("borrowerId")));
				}
				try {
					adminService.saveBorrower(borrower);
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}else{
			message = "Borrower Name cannot be Empty";
			redirectUrl = "/addborrower.jsp";
		}
		request.setAttribute("statusMessage", message);
		return redirectUrl;
	}
	
	private String addBranch(HttpServletRequest request, String redirectUrl, Boolean editMode) {
		Library_Branch libraryBranch = new Library_Branch();
		String message = "Branch added Sucessfully";
		
		if (request.getParameter("branchName") != null && !request.getParameter("branchName").isEmpty()) {
			if(request.getParameter("branchName").length() > 45 || request.getParameter("branchAddress").length() > 45){
				message = "Each branch detail cannot be more than 45 chars";
				redirectUrl = "/addbranch.jsp";
			}else{
				libraryBranch.setBranchName(request.getParameter("branchName"));
				libraryBranch.setBranchAddress(request.getParameter("branchAddress"));
				}
				if(editMode){
					libraryBranch.setBranchId(Integer.parseInt(request.getParameter("branchId")));
				}
				try {
					int id = adminService.saveLibraryBranch(libraryBranch);
					if(!editMode) {
						String[] bookIds = request.getParameterValues("bookIds");
						for(String b: bookIds)
						{
							BookCopies temp = adminService.getBookCopies(id, Integer.parseInt(b));
							adminService.saveBookCopies(temp);
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}else{
			message = "Branch Name cannot be Empty";
			redirectUrl = "/addbranch.jsp";
		}
		request.setAttribute("statusMessage", message);
		return redirectUrl;
	}
	
	private String addBook(HttpServletRequest request, String redirectUrl, Boolean editMode) {
		Book book = new Book();
		String message = "Book added Sucessfully";
		
		if (request.getParameter("bookName") != null && !request.getParameter("bookName").isEmpty()) {
			if(request.getParameter("bookName").length() > 45){
				message = "Book Name cannot be more than 45 chars";
				redirectUrl = "/addbook.jsp";
			}else{
				book.setTitle(request.getParameter("bookName"));
				String[] authorIds = request.getParameterValues("authorIds");
				List<Author> authors = new ArrayList<>();
				if(authorIds != null) {
				for(String a: authorIds) {
					Author temp = new Author();
					temp.setAuthorId(Integer.parseInt(a));
					authors.add(temp);
				}
				book.setAuthors(authors);

				}
				if(editMode){
					book.setBookId(Integer.parseInt(request.getParameter("bookId")));
				}
				try {
					book.setPublisher(adminService.readPublisherByPK(Integer.parseInt(request.getParameter("publisherIds"))));
					int bookId = adminService.saveBook(book);
					if(!editMode) {
						String[] branchIds = request.getParameterValues("branchIds");
						for(String bc: branchIds)
						{
							BookCopies temp = adminService.getBookCopies(Integer.parseInt(bc), bookId);
							adminService.saveBookCopies(temp);
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}else{
			message = "Book Name cannot be Empty";
			redirectUrl = "/addbook.jsp";
		}
		request.setAttribute("statusMessage", message);
		return redirectUrl;
	}
	
	private String deleteBook(HttpServletRequest request, HttpServletResponse response, String redirectUrl)
			throws ServletException, IOException {
		String message = "Book deleted Sucessfully";
		if(request.getParameter("bookId")!=null){
			Integer bookId = Integer.parseInt(request.getParameter("bookId"));
			Book book = new Book();
			book.setBookId(bookId);
			try {
				adminService.deleteBook(book);
			} catch (SQLException e) {
				e.printStackTrace();
				message = "Book delete failed. Contact Admin";
			}
			request.setAttribute("statusMessage", message);
			return redirectUrl;
		}
		
		return null;
	}
	
	private String deleteAuthor(HttpServletRequest request, HttpServletResponse response, String redirectUrl)
			throws ServletException, IOException {
		String message = "Author deleted Sucessfully";
		if(request.getParameter("authorId")!=null){
			Integer authorId = Integer.parseInt(request.getParameter("authorId"));
			Author author = new Author();
			author.setAuthorId(authorId);
			try {
				adminService.deleteAuthor(author);
			} catch (SQLException e) {
				e.printStackTrace();
				message = "Author deleted failed. Contact Admin";
			}
			request.setAttribute("statusMessage", message);
			return redirectUrl;
		}
		
		return null;
	}
	
	private String overrideDueDate(HttpServletRequest request, HttpServletResponse response, String redirectUrl)
			throws ServletException, IOException {
		String message = "BookLoan Due Date pushed by a week";
		if(request.getParameter("bookId")!=null && request.getParameter("branchId")!=null && request.getParameter("cardNo")!=null){
			Integer bookId = Integer.parseInt(request.getParameter("bookId"));
			Integer branchId = Integer.parseInt(request.getParameter("branchId"));
			Integer cardNo = Integer.parseInt(request.getParameter("cardNo"));
			BookLoans bookLoans = new BookLoans();
			bookLoans.setBookId(bookId);
			bookLoans.setBranchId(branchId);
			bookLoans.setCardNo(cardNo);
			try {
				adminService.overrideBookLoanDueDate(bookLoans);;
			} catch (SQLException e) {
				e.printStackTrace();
				message = "Due Date Override failed. Contact Admin";
			}
			request.setAttribute("statusMessage", message);
			return redirectUrl;
		}
		return null;
	}
	
	private String deleteGenre(HttpServletRequest request, HttpServletResponse response, String redirectUrl)
			throws ServletException, IOException {
		String message = "Genre deleted Sucessfully";
		if(request.getParameter("genreId")!=null){
			Integer genreId = Integer.parseInt(request.getParameter("genreId"));
			Genre genre = new Genre();
			genre.setGenreId(genreId);
			try {
				adminService.deleteGenre(genre);
			} catch (SQLException e) {
				e.printStackTrace();
				message = "Genre delete failed. Contact Admin";
			}
			request.setAttribute("statusMessage", message);
			return redirectUrl;
		}
		
		return null;
	}
	
	private String deletePublisher(HttpServletRequest request, HttpServletResponse response, String redirectUrl)
			throws ServletException, IOException {
		String message = "Publisher deleted Sucessfully";
		if(request.getParameter("publisherId")!=null){
			Integer publisherId = Integer.parseInt(request.getParameter("publisherId"));
			Publisher publisher = new Publisher();
			publisher.setPublisherId(publisherId);
			try {
				adminService.deletePublisher(publisher);
			} catch (SQLException e) {
				e.printStackTrace();
				message = "Publisher delete failed. Contact Admin";
			}
			request.setAttribute("statusMessage", message);
			return redirectUrl;
		}
		
		return null;
	}
	
	private String deleteBorrower(HttpServletRequest request, HttpServletResponse response, String redirectUrl)
			throws ServletException, IOException {
		String message = "Borrower deleted Sucessfully";
		if(request.getParameter("borrowerId")!=null){
			Integer borrowerId = Integer.parseInt(request.getParameter("borrowerId"));
			Borrower borrower = new Borrower();
			borrower.setCardNo(borrowerId);
			try {
				adminService.deleteBorrower(borrower);
			} catch (SQLException e) {
				e.printStackTrace();
				message = "Borrower delete failed. Contact Admin";
			}
			request.setAttribute("statusMessage", message);
			return redirectUrl;
		}
		
		return null;
	}
	
	private String deleteBranch(HttpServletRequest request, HttpServletResponse response, String redirectUrl)
			throws ServletException, IOException {
		String message = "Branch deleted Sucessfully";
		if(request.getParameter("branchId")!=null){
			Integer branchId = Integer.parseInt(request.getParameter("branchId"));
			Library_Branch libraryBranch = new Library_Branch();
			libraryBranch.setBranchId(branchId);
			try {
				adminService.deleteLibraryBranch(libraryBranch);
			} catch (SQLException e) {
				e.printStackTrace();
				message = "Branch delete failed. Contact Admin";
			}
			request.setAttribute("statusMessage", message);
			return redirectUrl;
		}
		return null;
	}
	
	private String pageBookLoans(HttpServletRequest request, HttpServletResponse response, String redirectUrl)
			throws ServletException, IOException {
		if(request.getParameter("pageNo")!=null){
			Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
			try {
				request.setAttribute("books", adminService.readAllLoans(null, pageNo));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return redirectUrl;
		}
		
		return null;
	}
	
	private String pageBooks(HttpServletRequest request, HttpServletResponse response, String redirectUrl)
			throws ServletException, IOException {
		if(request.getParameter("pageNo")!=null){
			Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
			try {
				request.setAttribute("books", adminService.readBooks(null, pageNo));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return redirectUrl;
		}
		
		return null;
	}
	
	private String pageAuthors(HttpServletRequest request, HttpServletResponse response, String redirectUrl)
			throws ServletException, IOException {
		if(request.getParameter("pageNo")!=null){
			Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
			try {
				request.setAttribute("authors", adminService.readAuthors(null, pageNo));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return redirectUrl;
		}
		
		return null;
	}
	
	private String pageGenres(HttpServletRequest request, HttpServletResponse response, String redirectUrl)
			throws ServletException, IOException {
		if(request.getParameter("pageNo")!=null){
			Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
			try {
				request.setAttribute("genres", adminService.readGenres(null, pageNo));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return redirectUrl;
		}
		
		return null;
	}
	
	private String pagePublishers(HttpServletRequest request, HttpServletResponse response, String redirectUrl)
			throws ServletException, IOException {
		if(request.getParameter("pageNo")!=null){
			Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
			try {
				request.setAttribute("publishers", adminService.readPublishers(null, pageNo));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return redirectUrl;
		}
		
		return null;
	}
	
	private String pageBorrowers(HttpServletRequest request, HttpServletResponse response, String redirectUrl)
			throws ServletException, IOException {
		if(request.getParameter("pageNo")!=null){
			Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
			try {
				request.setAttribute("borrowers", adminService.readBorrowers(null, pageNo));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return redirectUrl;
		}
		
		return null;
	}
	
	private String pageBranches(HttpServletRequest request, HttpServletResponse response, String redirectUrl)
			throws ServletException, IOException {
		if(request.getParameter("pageNo")!=null){
			Integer pageNo = Integer.parseInt(request.getParameter("pageNo"));
			try {
				request.setAttribute("branches", adminService.readLibraryBranch(null, pageNo));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return redirectUrl;
		}
		return null;
	}
	
}
