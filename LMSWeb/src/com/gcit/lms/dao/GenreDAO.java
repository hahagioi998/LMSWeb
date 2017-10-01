package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Genre;

@SuppressWarnings({ })
public class GenreDAO extends BaseDAO <Genre>{

	public GenreDAO(Connection conn) {
		super(conn);
	}

	public void saveGenre(Genre genre) throws SQLException {
		save("INSERT INTO tbl_genre (genre_name) VALUES (?)", new Object[] { genre.getGenreName() });
	}
	
	public void saveBookGenre(Genre genre) throws SQLException {
		for(Book b: genre.getBooks()){
			save("INSERT INTO tbl_book_genres VALUES (?,?)", new Object[] { genre.getGenreId(), b.getBookId()});
		}
	}
	
	public void deleteBookGenre(Genre genre) throws SQLException {
		save("DELETE FROM tbl_book_genres WHERE genre_id = ?", new Object[] {genre.getGenreId()});
	}
	
	public Integer saveGenreWithID(Genre genre) throws SQLException {
		return saveWithID("INSERT INTO tbl_genre (genre_name) VALUES (?)", new Object[] { genre.getGenreName() });
	}

	public void updateGenre(Genre genre) throws SQLException {
		save("UPDATE tbl_genre SET genre_name = ? WHERE genre_id = ?",
				new Object[] { genre.getGenreName(), genre.getGenreId() });
	}

	public void deleteGenre(Genre genre) throws SQLException {
		save("DELETE FROM tbl_genre WHERE genre_id = ?", new Object[] { genre.getGenreId() });
	}
	
	
	public Genre readGenreByPK(Integer genreId) throws SQLException {
		List<Genre> genres = readAll("SELECT * FROM tbl_genre WHERE genre_id = ?", new Object[]{genreId});
		if(genres!=null){
			return genres.get(0);
		}
		return null;
	}
	
	public List<Genre> readGenres(String genreName) throws SQLException {
		if(genreName !=null && !genreName.isEmpty()){
			genreName = "%"+genreName+"%";
			return readAll("SELECT * FROM tbl_genre WHERE genre_name like ?", new Object[]{genreName});
		}else{
			return readAll("SELECT * FROM tbl_genre", null);
		}
		
	}
	
	public List<Genre> readGenres(String genreName, Integer pageNo) throws SQLException {
		setPageNo(pageNo);
		if(genreName !=null && !genreName.isEmpty()){
			genreName = "%"+genreName+"%";
			return readAll("SELECT * FROM tbl_genre WHERE genreName like ?", new Object[]{genreName});
		}else{
			return readAll("SELECT * FROM tbl_genre", null);
		}
		
	}

	public Integer getGenresCount() throws SQLException {
		return getCount("SELECT count(*) as COUNT FROM tbl_genre", null);
	}
	
	@Override
	public List<Genre> extractData(ResultSet rs) throws SQLException {
		List<Genre> genres = new ArrayList<>();
		BookDAO bdao = new BookDAO(conn);
		while(rs.next()){
			Genre a = new Genre();
			a.setGenreId(rs.getInt("genre_id"));
			a.setGenreName(rs.getString("genre_name"));
			a.setBooks(bdao.readAllFirstLevel("SELECT * FROM tbl_book WHERE bookId IN (SELECT bookId FROM tbl_book_genres WHERE genre_id = ?)", new Object[]{a.getGenreId()}));
			genres.add(a);
		}
		
		return genres;
	}
	
	@Override
	public List<Genre> extractDataFirstLevel(ResultSet rs) throws SQLException {
		List<Genre> genres = new ArrayList<>();
		while(rs.next()){
			Genre a = new Genre();
			a.setGenreId(rs.getInt("genre_id"));
			a.setGenreName(rs.getString("genre_name"));
			genres.add(a);
		}
		
		return genres;
	}
}
