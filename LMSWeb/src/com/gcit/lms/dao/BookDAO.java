package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Library_Branch;
import com.gcit.lms.entity.Publisher;

public class BookDAO extends BaseDAO<Book> {

	public BookDAO(Connection conn) {
		super(conn);
	}

	public void saveBook(Book book) throws SQLException {
		save("INSERT INTO tbl_book (title,pubId) VALUES (?,?)", new Object[] { book.getTitle(), book.getPublisher().getPublisherId() });
	}

	public void saveBookAuthor(Book book) throws SQLException {
		for (Author a : book.getAuthors()) {
			save("INSERT INTO tbl_book_authors VALUES (?, ?)", new Object[] { book.getBookId(), a.getAuthorId() });
		}
	}
	
	public void deleteBookAuthor(Book book) throws SQLException {
		save("DELETE FROM tbl_book_authors WHERE bookId = ?", new Object[] { book.getBookId() });
	}

	public Integer saveBookID(Book book) throws SQLException {
		return saveWithID("INSERT INTO tbl_book (title) VALUES (?)", new Object[] { book.getTitle() });
	}

	public void updateBook(Book book) throws SQLException {
		save("UPDATE tbl_book SET title = ?, pubId = ? WHERE bookId = ?", new Object[] { book.getTitle(), book.getPublisher().getPublisherId(), book.getBookId() });
	}

	public void deleteBook(Book book) throws SQLException {
		save("DELETE FROM tbl_book WHERE bookId = ?", new Object[] { book.getBookId() });
	}

	public List<Book> readBooksByBranch(Library_Branch libraryBranch) throws SQLException {
		return readAll("SELECT * FROM tbl_book WHERE bookId IN (SELECT bookId FROM tbl_book_copies WHERE branchId = ?)",
				new Object[] { libraryBranch.getBranchId() });
	}

	public List<Book> readBooksByBorrower(Library_Branch libraryBranch, Borrower borrower) throws SQLException {
		return readAll(
				"SELECT * FROM tbl_book WHERE bookId IN (SELECT bookId FROM tbl_book_loans WHERE branchId = ? AND cardNo = ?)",
				new Object[] { libraryBranch.getBranchId(), borrower.getCardNo() });
	}

	public List<Book> readAllBooks() throws SQLException {
		return readAll("SELECT * FROM tbl_book", null);
	}

	public List<Book> readBooksByTitle(String bookTitle) throws SQLException {
		bookTitle = "%" + bookTitle + "%";
		return readAll("SELECT * FROM tbl_book WHERE title like ?", new Object[] { bookTitle });
	}

	public Integer getBooksCount() throws SQLException {
		return getCount("SELECT count(*) as COUNT FROM tbl_book", null);
	}
	
	public List<Book> readBooks(String bookName, Integer pageNo) throws SQLException {
		setPageNo(pageNo);
		if(bookName !=null && !bookName.isEmpty()){
			bookName = "%"+bookName+"%";
			return readAll("SELECT * FROM tbl_book WHERE title like ?", new Object[]{bookName});
		}else{
			return readAll("SELECT * FROM tbl_book", null);
		}
		
	}
	
	public Book readBookByPK(Integer bookId) throws SQLException {
		List<Book> books = readAll("SELECT * FROM tbl_book WHERE bookId = ?", new Object[]{bookId});
		if(books!=null){
			return books.get(0);
		}
		return null;
	}
	
	
	@Override
	public List<Book> extractData(ResultSet rs) throws SQLException {
		AuthorDAO adao = new AuthorDAO(conn);
		GenreDAO gdao = new GenreDAO(conn);
		PublisherDAO pdao = new PublisherDAO(conn);
		List<Book> books = new ArrayList<>();
		while (rs.next()) {
			Book b = new Book();
			b.setBookId(rs.getInt("bookId"));
			b.setTitle(rs.getString("title"));
			b.setAuthors(adao.readAllFirstLevel(
					"SELECT * FROM tbl_author WHERE authorId IN (SELECT authorId FROM tbl_book_authors WHERE bookId = ?)",
					new Object[] { b.getBookId() }));
			b.setGenres(gdao.readAllFirstLevel(
					"SELECT * FROM tbl_genre WHERE genre_id IN (SELECT genre_id FROM tbl_book_genres WHERE bookId = ?)",
					new Object[] { b.getBookId() }));
			List<Publisher> pub = pdao.readAllFirstLevel(
					"SELECT * FROM tbl_publisher WHERE publisherId IN (SELECT pubId FROM tbl_book WHERE bookId = ?)", 
					new Object[] {b.getBookId()});
			if(pub != null && !pub.isEmpty()) {
			b.setPublisher(pub.get(0));
			}
			books.add(b);
		}
		return books;
	}

	@Override
	public List<Book> extractDataFirstLevel(ResultSet rs) throws SQLException {
		List<Book> books = new ArrayList<>();
		while (rs.next()) {
			Book b = new Book();
			b.setBookId(rs.getInt("bookId"));
			b.setTitle(rs.getString("title"));
			books.add(b);
		}
		return books;
	}
}
