<%@include file="includeadmin.html"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.Author"%>
<%@page import="com.gcit.lms.entity.Publisher"%>
<%@page import="com.gcit.lms.entity.Library_Branch"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%AdminService service = new AdminService();
List<Author> authors = service.readAuthors();
List<Publisher> publishers = service.readPublishers();
List<Library_Branch> lbranches = service.readBranches();
%>
<div class="container">
	<h2>Add New Book</h2>
	<form method="post" action="addBook">
	${statusMessage}
		<br/>Enter Book Name: <input type="text" name="bookName"><br />
		
		<table class="table table-bordered">
		<tr>
			<th>Select Authors</th>
			<th>Select Publisher</th>
			<th>Select Branches</th>
			
		</tr>

		<tr>
			<td><select multiple="multiple" size="10" name="authorIds">
			<%for(Author a: authors) {%>
			<option value=<%=a.getAuthorId()%>><%=a.getAuthorName() %></option>
			<%} %>
		</select></td>
			<td><select size="10" name="publisherIds">
			<%for(Publisher p: publishers) {%>
			<option value=<%=p.getPublisherId()%>><%=p.getPublisherName() %></option>
			<%} %>
		</select></td>
			<td><select multiple="multiple" size="10" name="branchIds">
			<%for(Library_Branch lb: lbranches) {%>
			<option value=<%=lb.getBranchId()%>><%=lb.getBranchName() %></option>
			<%} %>
		</select></td>
		</tr>
	</table>
	<br/>
		<button type="submit" class="btn btn-primary btn-md">Save Book</button>
	</form>
</div>