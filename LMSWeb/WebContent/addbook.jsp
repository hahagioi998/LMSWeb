<%@include file="include.html"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.Author"%>
<%@page import="com.gcit.lms.entity.Publisher"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%AdminService service = new AdminService();
List<Author> authors = service.readAuthors();
List<Publisher> publishers = service.readPublishers();
%>
<div class="container">
	<h2>Add New Book</h2>
	<form method="post" action="addBook">
	${statusMessage}
		<br/>Enter Book Name: <input type="text" name="bookName"><br />
		Select Authors from list Below: <br/>
		<select multiple="multiple" size="10" name="authorIds">
			<%for(Author a: authors) {%>
			<option value=<%=a.getAuthorId()%>><%=a.getAuthorName() %></option>
			<%} %>
		</select>
		<br/>
		Select Publisher from list Below: <br/>
		<select size="10" name="publisherIds">
			<%for(Publisher p: publishers) {%>
			<option value=<%=p.getPublisherId()%>><%=p.getPublisherName() %></option>
			<%} %>
		</select>
		<br/>
		<button type="submit" class="btn btn-primary btn-md">Save Book</button>
	</form>
</div>