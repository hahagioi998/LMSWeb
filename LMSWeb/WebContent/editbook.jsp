<%@page import="com.gcit.lms.entity.Author"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@include file="includeadmin.html"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="com.gcit.lms.entity.Publisher"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%AdminService service = new AdminService();
Book book = service.readBookByPK(Integer.parseInt(request.getParameter("bookId")));
List<Author> authors = service.readAuthors();
List<Publisher> publishers = service.readPublishers();
%>

<div class = "container">
	<h1>Welcome to GCIT Library Management System!</h1>
	<h2>Edit Book</h2>
	<form method="post" action="editBook">
		${statusMessage}
		<br/>Enter Book Name to Edit: <input type="text" name="bookName" value="<%=book.getTitle()%>"><br />
		<input type="hidden" name="bookId" value="<%=book.getBookId()%>"><br />
		Select Authors from list Below: <br/>
		<select multiple="multiple" size="10" name="authorIds">
			<%for(Author a: authors) {%>
			<option value=<%=a.getAuthorId()%> <%if((a.getBooks()).contains(book)){%>selected<%}%>><%=a.getAuthorName() %></option>
			<%} %>
		</select>
		<br/>
		Select Publisher from list Below: <br/>
		<select size="10" name="publisherIds">
			<%for(Publisher p: publishers) {%>
			<option value=<%=p.getPublisherId()%> <%if((p.getBooks()).contains(book)){%>selected<%}%>><%=p.getPublisherName() %></option>
			<%} %>
		</select>
		<button type="submit">Update Book</button>
	</form>
</div>