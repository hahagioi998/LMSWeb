package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;

public class AuthorDAO extends BaseDAO<Author> {

	public AuthorDAO(Connection conn) {
		super(conn);
	}

	public void saveAuthor(Author author) throws SQLException {
		save("INSERT INTO tbl_author (authorName) VALUES (?)", new Object[] { author.getAuthorName() });
	}
	
	public void saveBookAuthor(Author author) throws SQLException {
		for(Book b: author.getBooks()){
			save("INSERT INTO tbl_book_authors VALUES (?, ?)", new Object[] { b.getBookId(), author.getAuthorId()});
		}
	}
	
	public void deleteBookAuthor(Author author) throws SQLException{
		save("DELETE FROM tbl_book_authors WHERE authorId = ?",new Object[] {author.getAuthorId()});
	}
	
	public Integer saveAuthorWithID(Author author) throws SQLException {
		return saveWithID("INSERT INTO tbl_author (authorName) VALUES (?)", new Object[] { author.getAuthorName() });
	}

	public void updateAuthor(Author author) throws SQLException {
		save("UPDATE tbl_author SET authorName = ? WHERE authorId = ?",
				new Object[] { author.getAuthorName(), author.getAuthorId() });
	}

	public void deleteAuthor(Author author) throws SQLException {
		save("DELETE FROM tbl_author WHERE authorId = ?", new Object[] { author.getAuthorId() });
	}
	
	
	public List<Author> readAuthors(String authorName) throws SQLException {
		if(authorName !=null && !authorName.isEmpty()){
			authorName = "%"+authorName+"%";
			return readAll("SELECT * FROM tbl_author WHERE authorName like ?", new Object[]{authorName});
		}else{
			return readAll("SELECT * FROM tbl_author", null);
		}
		
	}
	
	public Integer getAuthorsCount() throws SQLException {
		return getCount("SELECT count(*) as COUNT FROM tbl_author", null);
	}
	
	public List<Author> readAuthors(String authorName, Integer pageNo) throws SQLException {
		setPageNo(pageNo);
		if(authorName !=null && !authorName.isEmpty()){
			authorName = "%"+authorName+"%";
			return readAll("SELECT * FROM tbl_author WHERE authorName like ?", new Object[]{authorName});
		}else{
			return readAll("SELECT * FROM tbl_author", null);
		}
		
	}
	
	public Author readAuthorByPK(Integer authorId) throws SQLException {
		List<Author> authors = readAll("SELECT * FROM tbl_author WHERE authorId = ?", new Object[]{authorId});
		if(authors!=null){
			return authors.get(0);
		}
		return null;
	}
	
	@Override
	public List<Author> extractData(ResultSet rs) throws SQLException {
		List<Author> authors = new ArrayList<>();
		BookDAO bdao = new BookDAO(conn);
		while(rs.next()){
			Author a = new Author();
			a.setAuthorId(rs.getInt("authorId"));
			a.setAuthorName(rs.getString("authorName"));
			a.setBooks(bdao.readAllFirstLevel("SELECT * FROM tbl_book WHERE bookId IN (SELECT bookId FROM tbl_book_authors WHERE authorId = ?)", new Object[]{a.getAuthorId()}));
			authors.add(a);
		}
		
		return authors;
	}
	
	@Override
	public List<Author> extractDataFirstLevel(ResultSet rs) throws SQLException {
		List<Author> authors = new ArrayList<>();
		while(rs.next()){
			Author a = new Author();
			a.setAuthorId(rs.getInt("authorId"));
			a.setAuthorName(rs.getString("authorName"));
			authors.add(a);
		}
		
		return authors;
	}
}
