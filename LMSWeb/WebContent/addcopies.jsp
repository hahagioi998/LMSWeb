<%@include file="include.html"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.Author"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.LibrarianService"%>
<%LibrarianService service = new LibrarianService();
Integer bookId = Integer.parseInt(request.getParameter("bookId"));
Integer branchId = Integer.parseInt(request.getParameter("branchId"));
Book book = service.readBookByPK(bookId);
%>
<div class="container">
	<h2>Add New Book</h2>
	<form method="post" action="copiesAdded">
	${statusMessage}
		<br/>Enter Book Name: <%=book.getTitle()%><br />
		<br/>Number of Copies : <%=(service.getBooksBrCount(bookId, branchId))%><br />
		<br/>Enter new Number of Copies : <input type="text" name = "bbcopies" value="<%=(service.getBooksBrCount(bookId, branchId))%>"><br />
		<input type="hidden" name = "bookId" value="<%=bookId%>">
		<input type="hidden" name = "branchId" value="<%=branchId%>">
		<button type="submit" class="btn btn-primary btn-md">Save Book</button>
	</form>
</div>