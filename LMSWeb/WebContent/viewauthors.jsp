<%@include file="includeadmin.html"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.entity.Author"%>
<%
	AdminService service = new AdminService();
	Integer aId = 1;
	List<Book> books = service.readBooks();
	Integer totalCount = service.getAuthorsCount();
	int numOfPages = 0;
	if (totalCount % 10 > 0) {
		numOfPages = totalCount / 10 + 1;
	} else {
		numOfPages = totalCount / 10;
	}
	List<Author> authors = new ArrayList<>();
	if (request.getAttribute("authors") != null) {
		authors = (List<Author>) request.getAttribute("authors");
	} else {
		authors = service.readAuthors(null, 1);
	}
	Integer pageNo = 1;
	Boolean first = false, last = false;
	if (request.getAttribute("pageNo") != null) {
		pageNo = (Integer) request.getAttribute("pageNo");
	} else {
		pageNo = 1;
	}

	if (pageNo == numOfPages) {
		last = true;
	}

	if (pageNo == 1) {
		first = true;
	}
%>
<%
	if (request.getAttribute("statusMessage") != null) {
		out.println(request.getAttribute("statusMessage"));
	}
%>

<!-- Modal -->
<!-- <div class="modal fade" id="myModal" tabindex="-1" role="dialog" -->
<!-- 	aria-labelledby="exampleModalLabel" aria-hidden="true"> -->
<!-- 	<div class="modal-dialog" role="document"> -->
<!-- 		<div class="modal-content"> -->
<!-- 			<div class="modal-header"> -->
<!-- 				<h5 class="modal-title" id="exampleModalLabel">Modal title</h5> -->
<!-- 				<button type="button" class="close" data-dismiss="modal" -->
<!-- 					aria-label="Close"> -->
<!-- 					<span aria-hidden="true">&times;</span> -->
<!-- 				</button> -->
<!-- 			</div> -->
<!-- 			<div class="modal-body"></div> -->
<!-- 			<div class="modal-footer"> -->
<!-- 				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button> -->
<!-- 				<button type="button" class="btn btn-primary">Save changes</button> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</div> -->
<!-- </div> -->

<div class="container">
	<h1>
		List of Authors in LMS&nbsp;&nbsp;&nbsp;&nbsp; Total Authors in LMS:
		<%=totalCount%>
		Authors
	</h1>
	<!-- Button trigger modal -->
	<!-- 	<button type="button" class="btn btn-primary" data-toggle="modal" -->
	<!-- 		data-target="#myModal">Launch demo modal</button> -->
	<nav aria-label="Page navigation example">
		<ul class="pagination">
			<li class="page-item<% if(first==true){%> disabled<%}%>"><a
				class="page-link" href="pageAuthors?pageNo=<%=pageNo-1%>"
				aria-label="Previous"> <span aria-hidden="true">&laquo;</span> <span
					class="sr-only">Previous</span>
			</a></li>
			<%for(int i=1; i<=numOfPages; i++){ %>
			<li class="page-item"><a class="page-link"
				href="pageAuthors?pageNo=<%=i%>"><%=i%></a></li>
			<%} %>
			<li class="page-item<% if(last==true){%> disabled<%}%>"><a
				class="page-link" href="pageAuthors?pageNo=<%=pageNo+1%>"
				aria-label="Next"> <span aria-hidden="true">&raquo;</span> <span
					class="sr-only">Next</span>
			</a></li>
		</ul>
	</nav>
	<table class="table table-striped">
		<tr>
			<th>#</th>
			<th>Author Name</th>
			<th>Books Written</th>
			<th>Edit Author</th>
			<th>Delete Author</th>
		</tr>
		<%
			for (Author a : authors) {
		%>
		<tr>
			<td><%=authors.indexOf(a) + 1%></td>
			<td><%=a.getAuthorName()%></td>
			<td>
				<%
					for (Book b : a.getBooks()) {
							out.println(b.getTitle());%><br />
				<%
						}
					aId = a.getAuthorId();
				%>
			</td>
			<td><button type="button" data-toggle="modal"
					data-target="#myModal"
					data-remote="editauthor.jsp?authorId=<%=a.getAuthorId()%>"
					class="btn btn-primary btn-sm">Edit</button></td>
			<td><button type="button"
					onclick="javascript:location.href='deleteAuthor?authorId=<%=a.getAuthorId()%>'"
					class="btn btn-danger btn-sm">Delete</button></td>
		</tr>

		<%
			}
		%>
	</table>
</div>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Edit Author</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<p></p>
			</div>
		</div>
	</div>
</div>
<script>
		$('body').on('click', '[data-toggle="modal"]',
				function() {
					$($(this).data("target") + ' .modal-body').load($(this).data("remote"));
				});
</script>