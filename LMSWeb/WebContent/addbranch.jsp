<%@include file="includeadmin.html"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%AdminService service = new AdminService();
List<Book> book = service.readBooks();
%>
<div class="container">
	<h2>Add New Branch</h2>
	<form method="post" action="addBranch">
	${statusMessage}
		${statusMessage}
		<br/>Enter Branch Name to Edit: <input type="text" name="branchName" value=""><br />
		<br/>Enter Branch Address to Edit: <input type="text" name="branchAddress" value=""><br />
		<br/>
		Select Books to add to this Branch: 
		<select multiple="multiple" size="10" name="bookIds">
			<%for(Book b: book) {%>
			<option value=<%=b.getBookId()%>><%=b.getTitle() %></option>
			<%} %>
		</select>
		<button type="submit" class="btn btn-primary btn-md">Save Branch</button>
	</form>
</div>