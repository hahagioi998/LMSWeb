<%@include file="include.html"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%AdminService service = new AdminService();
List<Book> books = service.readBooks();
%>
<div class="container">
	<h2>Add New Genre</h2>
	<form method="post" action="addGenre">
	${statusMessage}
		<br/>Enter Genre Name: <input type="text" name="genreName"><br />
		Select Books from list Below: <br/>
		<select multiple="multiple" size="10" name="bookIds">
			<option value="">Select Book to associate</option>
			<%for(Book b: books) {%>
			<option value=<%=b.getBookId()%>><%=b.getTitle() %></option>
			<%} %>
		</select>
		<br/>
		<button type="submit" class="btn btn-primary btn-md">Save Genre</button>
	</form>
</div>