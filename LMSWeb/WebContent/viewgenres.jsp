<%@include file="include.html"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.entity.Genre"%>
<%
	AdminService service = new AdminService();
	Integer totalCount = service.getAuthorsCount();
	int numOfPages = 0;
	if(totalCount%10 > 0){
		numOfPages = totalCount/10 +1;
	}else{
		numOfPages = totalCount/10;
	}
	List<Genre> genres = new ArrayList<>();
	if(request.getAttribute("genres")!=null){
		genres = (List<Genre>)request.getAttribute("genres");
	}else{
		genres = service.readGenres(null, 1);
	}
%>
<%
	if (request.getAttribute("statusMessage") != null) {
		out.println(request.getAttribute("statusMessage"));
	}
%>
<div class="container">
	<h1>List of Genres in LMS&nbsp;&nbsp;&nbsp;&nbsp; Total Genres in LMS: <%=totalCount%> Genres</h1>
	<nav aria-label="Page navigation example">
		<ul class="pagination">
			<li class="page-item"><a class="page-link" href="#"
				aria-label="Previous"> <span aria-hidden="true">&laquo;</span> <span
					class="sr-only">Previous</span>
			</a></li>
			<%for(int i=1; i<=numOfPages; i++){ %>
			<li class="page-item"><a class="page-link" href="pageGenres?pageNo=<%=i%>"><%=i%></a></li>
			<%} %>
			<li class="page-item"><a class="page-link" href="#"
				aria-label="Next"> <span aria-hidden="true">&raquo;</span> <span
					class="sr-only">Next</span>
			</a></li>
		</ul>
	</nav>
	<table class="table table-striped">
		<tr>
			<th>#</th>
			<th>Genre Name</th>
			<th>Books of the Type</th>
			<th>Edit Genre</th>
			<th>Delete Genre</th>
		</tr>
		<%
			for (Genre a : genres) {
		%>
		<tr>
			<td><%=genres.indexOf(a) + 1%></td>
			<td><%=a.getGenreName()%></td>
			<td>
				<%
					for (Book b : a.getBooks()) {
							out.println(b.getTitle() + "|");
						}
				%>
			</td>
			<td><button type="button"
					onclick="javascript:location.href='editgenre.jsp?genreId=<%=a.getGenreId()%>'"
					class="btn btn-primary btn-sm">Edit</button></td>
			<td><button type="button"
					onclick="javascript:location.href='deletegenre?genreId=<%=a.getGenreId()%>'"
					class="btn btn-danger btn-sm">Delete</button></td>
		</tr>
		<%
			}
		%>
	</table>
</div>