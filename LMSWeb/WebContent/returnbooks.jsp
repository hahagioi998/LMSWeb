<%@include file="includeborrower.html"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="com.gcit.lms.entity.BookCopies"%>
<%@page import="com.gcit.lms.service.BorrowerService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.entity.Author"%>
<%@page import="com.gcit.lms.entity.Publisher"%>
<%
	BorrowerService service = new BorrowerService();
	
	List<Book> books = new ArrayList<>();
	books = (List<Book>)request.getAttribute("books");

	Integer cardNo = (Integer)request.getAttribute("cardNo");
	Integer branchId = (Integer)request.getAttribute("branchId");
	Integer noOfCopies = 0;
	Integer totalCount = service.getCheckOutBooksCount(branchId);
	int numOfPages = 0;
	if(totalCount%10 > 0){
		numOfPages = totalCount/10 +1;
	}else{
		numOfPages = totalCount/10;
	}
%>
<%
	if (request.getAttribute("statusMessage") != null) {
		out.println(request.getAttribute("statusMessage"));
	}
	
%>
<div class="container">
	<h1>List of Books in LMS&nbsp;&nbsp;&nbsp;&nbsp; Total Books in LMS: <%=totalCount%> Books</h1>
	<nav aria-label="Page navigation example">
		<ul class="pagination">
			<li class="page-item"><a class="page-link" href="#"
				aria-label="Previous"> <span aria-hidden="true">&laquo;</span> <span
					class="sr-only">Previous</span>
			</a></li>
			<%for(int i=1; i<=numOfPages; i++){ %>
			<li class="page-item"><a class="page-link" href="returnBook?pageNo=<%=i%>&cardNo=<%=cardNo%>&branchId=<%=branchId%>"><%=i%></a></li>
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
			<th>Book Title</th>
			<th>Authors</th>
			<th>No of Copies</th>
			<th>Return</th>
		</tr>
		<%
			for (Book b : books) {
		%>
		<tr>
			<td><%=books.indexOf(b) + 1%></td>
			<td><%=b.getTitle()%></td>
			<td>
				<%
					for (Author a : b.getAuthors()) {
							out.println(a.getAuthorName() + "|");
						}
				%>
			</td>
			<td>
				<%
					//display no of books that user has
					noOfCopies = service.getLoanedOutBooksCount(b.getBookId(),cardNo,branchId);
					out.println(noOfCopies);
				%>
			</td>
			<td><button type="button"
					onclick="javascript:location.href='returnBookBack?bookId=<%=b.getBookId()%>&cardNo=<%=cardNo%>&branchId=<%=branchId%>'"
					class="btn btn-primary btn-sm"
					<%if(noOfCopies<=0){ %>disabled<%} %>>Return</button></td>
		</tr>
		<%
			}
		%>
	</table>
</div>