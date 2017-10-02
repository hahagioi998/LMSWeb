<%@page import="com.gcit.lms.entity.Genre"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@include file="includeadmin.html"%>l"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%AdminService service = new AdminService();
Genre genre = service.readGenreByPK(Integer.parseInt(request.getParameter("genreId")));
List<Book> books = service.readBooks();
%>

<div class = "container">
	<h1>Welcome to GCIT Library Management System!</h1>
	<h2>Edit Genre</h2>
	<form method="post" action="editGenre">
		${statusMessage}
		<br/>Enter Genre Name to Edit: <input type="text" name="genreName" value="<%=genre.getGenreName()%>"><br />
		<input type="hidden" name="genreId" value="<%=genre.getGenreId()%>"><br />
		Select Books from list Below: <br/>
		<select multiple="multiple" size="10" name="bookIds">
			<option value="">Select Book to associate</option>
			<%for(Book b: books) {%>
			<option value=<%=b.getBookId()%>
			 <%if(b.getGenres() != null){ if((b.getGenres()).contains(genre)){%>
			 selected<%}}%>>
			<%=b.getTitle()%>
			</option>
			<%} %>
		</select>
		<br/>
		<button type="submit">Update Genre</button>
	</form>
</div>