package com.gcit.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookCopiesDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BookLoansDAO;
import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.dao.GenreDAO;
import com.gcit.lms.dao.Library_BranchDAO;
import com.gcit.lms.dao.PublisherDAO;
import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookCopies;
import com.gcit.lms.entity.BookLoans;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Genre;
import com.gcit.lms.entity.Library_Branch;
import com.gcit.lms.entity.Publisher;

public class AdminService {
	
	public Utilities util = new Utilities();
	
	public void saveAuthor(Author author) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			if(author.getAuthorId()!=null){
				adao.updateAuthor(author);
				adao.deleteBookAuthor(author);
				adao.saveBookAuthor(author);
			}else{
				int id = adao.saveAuthorWithID(author);
				author.setAuthorId(id);
				adao.saveBookAuthor(author);
			}
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
	}
	
	public void deleteAuthor(Author author) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			adao.deleteAuthor(author);
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
	}
	
	public List<Author> readAuthors(String searchString) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			return adao.readAuthors(searchString);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		
		return null;
	}
	
	public List<Author> readAuthors(String searchString, Integer pageNo) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			return adao.readAuthors(searchString, pageNo);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		
		return null;
	}
	
	public List<Genre> readGenres(String searchString, Integer pageNo) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			GenreDAO gdao = new GenreDAO(conn);
			return gdao.readGenres(searchString, pageNo);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		
		return null;
	}
	
	public List<Publisher> readPublishers(String searchString, Integer pageNo) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			PublisherDAO pdao = new PublisherDAO(conn);
			return pdao.readPublishers(searchString, pageNo);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		
		return null;
	}
	
	public List<Borrower> readBorrowers(String searchString, Integer pageNo) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			BorrowerDAO bdao = new BorrowerDAO(conn);
			return bdao.readBorrowers(searchString, pageNo);
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
	
	public List<Book> readBooks(String searchString, Integer pageNo) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookDAO bdao = new BookDAO(conn);
			return bdao.readBooks(searchString, pageNo);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		
		return null;
	}
	
	public List<BookLoans> readAllLoans(String searchString, Integer pageNo) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookLoansDAO bdao = new BookLoansDAO(conn);
			return bdao.readAllLoans(searchString, pageNo);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		
		return null;
	}
	
	public Author readAuthorByPK(Integer authorId) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			return adao.readAuthorByPK(authorId);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		
		return null;
	}
	
	public Genre readGenreByPK(Integer genreId) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			GenreDAO gdao = new GenreDAO(conn);
			return gdao.readGenreByPK(genreId);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		
		return null;
	}
	
	public Publisher readPublisherByPK(Integer publisherId) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			PublisherDAO pdao = new PublisherDAO(conn);
			return pdao.readPublisherByPK(publisherId);
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
	
	public Book readBookByPK(Integer bookId) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookDAO bdao = new BookDAO(conn);
			return bdao.readBookByPK(bookId);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		
		return null;
	}
	
	public Integer getAuthorsCount() throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			AuthorDAO adao = new AuthorDAO(conn);
			return adao.getAuthorsCount();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		
		return null;
	}
	
	public Integer getGenresCount() throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			GenreDAO gdao = new GenreDAO(conn);
			return gdao.getGenresCount();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		
		return null;
	}
	
	public Integer getPublishersCount() throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			PublisherDAO pdao = new PublisherDAO(conn);
			return pdao.getPublishersCount();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		
		return null;
	}

	public Integer getBorrowersCount() throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			BorrowerDAO bdao = new BorrowerDAO(conn);
			return bdao.getBorrowersCount();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		
		return null;
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
	
	public Integer getBooksCount() throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookDAO bdao = new BookDAO(conn);
			return bdao.getBooksCount();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		return null;
	}
	
	public Integer getBookLoansCount() throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookLoansDAO bldao = new BookLoansDAO(conn);
			return bldao.getBookLoansCount();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		return null;
	}
	
	public Integer saveBook(Book book) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookDAO bdao = new BookDAO(conn);
			int id = 0;
			if(book.getBookId()!=null){
				bdao.updateBook(book);
				bdao.deleteBookAuthor(book);
				bdao.saveBookAuthor(book);
			}else{
				id = bdao.saveBookID(book);
				book.setBookId(id);
				bdao.saveBookAuthor(book);
			}
			conn.commit();
			return id;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		
		return 0;
	}
	
	public void deleteBook(Book book) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookDAO bdao = new BookDAO(conn);
			bdao.deleteBook(book);
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
	}
	
	public List<Book> readBooks(String searchString) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookDAO bdao = new BookDAO(conn);
			return bdao.readBooksByTitle(searchString);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}

		return null;
	}

	public List<Book> readBooks() throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookDAO bdao = new BookDAO(conn);
			return bdao.readAllBooks();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}

		return null;
	}
	
	public List<Author> readAuthors() throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			AuthorDAO bdao = new AuthorDAO(conn);
			return bdao.readAuthors(null);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}

		return null;
	}
	
	public List<Publisher> readPublishers() throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			PublisherDAO pdao = new PublisherDAO(conn);
			return pdao.readPublishers(null);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}

		return null;
	}
	
	public List<Genre> readGenres() throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			GenreDAO pdao = new GenreDAO(conn);
			return pdao.readGenres(null);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}

		return null;
	}
	
	public List<Library_Branch> readBranches() throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			Library_BranchDAO lbdao = new Library_BranchDAO(conn);
			return lbdao.readBranch(null);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}

		return null;
	}
	
	public Integer saveLibraryBranch(Library_Branch libraryBranch) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			Integer id = 0;
			Library_BranchDAO ldao = new Library_BranchDAO(conn);
			if(libraryBranch.getBranchId() != null){
				ldao.updateBranch(libraryBranch);
			}else{
				id = ldao.saveLibraryBranchWithID(libraryBranch);
			}
			conn.commit();
			return id;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		return 0;
	}
	
	public BookCopies getBookCopies(Integer branchId, Integer bookId) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookCopiesDAO bcdao = new BookCopiesDAO(conn);
			BookCopies temp = bcdao.getBookCopies(bookId, branchId);
			return temp;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		
		return null;
	}
	
	public void saveBookCopies(BookCopies bookCopies) throws SQLException {
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookCopiesDAO bcdao = new BookCopiesDAO(conn);
			bcdao.saveBC(bookCopies);
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
	}
	
	public void deleteLibraryBranch(Library_Branch libraryBranch) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			Library_BranchDAO ldao = new Library_BranchDAO(conn);
			ldao.deleteLibraryBranch(libraryBranch);
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
	}
	
	public List<Library_Branch> readLibraryBranch(String searchString) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			Library_BranchDAO ldao = new Library_BranchDAO(conn);
			return ldao.readBranch(searchString);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		return null;
	}

	public void savePublisher(Publisher publisher) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			PublisherDAO pdao = new PublisherDAO(conn);
			if(publisher.getPublisherId() != null){
				pdao.updatePublisher(publisher);
			}else{
				pdao.savePublisher(publisher);
			}
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
	}
	
	public void deletePublisher(Publisher publisher) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			PublisherDAO pdao = new PublisherDAO(conn);
			pdao.deletePublisher(publisher);
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
	}
	
	public List<Publisher> readPublisher(String searchString) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			PublisherDAO pdao = new PublisherDAO(conn);
			return pdao.readPublishers(searchString);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		return null;
	}
	
	public void saveGenre(Genre genre) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			GenreDAO ldao = new GenreDAO(conn);
			if(genre.getGenreId() != null){
				ldao.updateGenre(genre);
				ldao.deleteBookGenre(genre);
				ldao.saveBookGenre(genre);
			}else{
				int id = ldao.saveGenreWithID(genre);
				genre.setGenreId(id);
				ldao.saveBookGenre(genre);
			}
			
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
	}
	
	public void deleteGenre(Genre genre) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			GenreDAO gdao = new GenreDAO(conn);
			gdao.deleteGenre(genre);
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
	}
	
	public List<Genre> readGenre(String searchString) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			GenreDAO gdao = new GenreDAO(conn);
			return gdao.readGenres(searchString);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		return null;
	}
	
	public void saveBorrower(Borrower borrower) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			BorrowerDAO bdao = new BorrowerDAO(conn);
			if(borrower.getCardNo() != null){
				bdao.updateBorrower(borrower);
			}else{
				bdao.saveBorrower(borrower);
			}
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
	}
	
	public void deleteBorrower(Borrower borrower) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			BorrowerDAO bdao = new BorrowerDAO(conn);
			bdao.deleteBorrower(borrower);
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
	}
	
	public List<Borrower> readBorrower(String searchString) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			BorrowerDAO gdao = new BorrowerDAO(conn);
			return gdao.readBorrowers(searchString);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
		return null;
	}
	
	public void saveBookLoans(BookLoans bookLoans) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookLoansDAO bdao = new BookLoansDAO(conn);
			if(bookLoans.getDateIn() != null) {
				bdao.saveBookLoanComplete(bookLoans);
			}else {
				bdao.saveBookLoan(bookLoans);
			}
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
	}
	
	public void deleteBookLoans(BookLoans bookLoans) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookLoansDAO bdao = new BookLoansDAO(conn);
			bdao.deleteBookLoan(bookLoans);
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
	}
	
	public void overrideBookLoanDueDate(BookLoans bookLoans) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookLoansDAO bdao = new BookLoansDAO(conn);
			bdao.overrideDueDate(bookLoans);
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
	}
	
	public void updateBookLoanDueDate(BookLoans bookLoans) throws SQLException{
		Connection conn = null;
		try {
			conn = util.getConnection();
			BookLoansDAO bdao = new BookLoansDAO(conn);
			bdao.updateBookLoanDueDate(bookLoans);
			conn.commit();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			conn.rollback();
		} finally{
			if(conn!=null){
				conn.close();
			}
		}
	}
	
}
