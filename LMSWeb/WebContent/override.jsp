<%@include file="includeadmin.html"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.entity.Library_Branch"%>
<%@page import="com.gcit.lms.entity.Borrower"%>
<%@page import="com.gcit.lms.entity.BookLoans"%>
<%
	AdminService service = new AdminService();
	Integer totalCount = service.getBookLoansCount();
	int numOfPages = 0;
	if(totalCount%10 > 0){
		numOfPages = totalCount/10 +1;
	}else{
		numOfPages = totalCount/10;
	}
	
	List<BookLoans> bookLoans = new ArrayList<>();
	
	if(request.getAttribute("books")!=null){
		bookLoans = (List<BookLoans>)request.getAttribute("bookLoans");
	}else{
		bookLoans = service.readAllLoans(null,1);
	}
	
	
%>
<%
	if (request.getAttribute("statusMessage") != null) {
		out.println(request.getAttribute("statusMessage"));
	}
%>
<div class="container">
	<h1>List of Book Loans in LMS&nbsp;&nbsp;&nbsp;&nbsp; Total Book Loans in LMS: <%=totalCount%> </h1>
	<nav aria-label="Page navigation example">
		<ul class="pagination">
			<li class="page-item"><a class="page-link" href="#"
				aria-label="Previous"> <span aria-hidden="true">&laquo;</span> <span
					class="sr-only">Previous</span>
			</a></li>
			<%for(int i=1; i<=numOfPages; i++){ %>
			<li class="page-item"><a class="page-link" href="pageBookLoans?pageNo=<%=i%>"><%=i%></a></li>
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
			<th>Book Name</th>
			<th>Branch Name</th>
			<th>Borrower's Name</th>
			<th>Date Out</th>
			<th>Due Date</th>
			<th>Override Due Date</th>
		</tr>
		<%
			for (BookLoans bl : bookLoans) {
				if(bl.getDateIn()==null){
		%>
		<tr>
			<td><%=bookLoans.indexOf(bl) + 1%></td>
			<td><%
				Book tempBook = service.readBookByPK(bl.getBookId());
				if(tempBook!=null){
					out.println(tempBook.getTitle());
				}
				%>
			</td>
			<td><%
				Library_Branch tempBranch = service.readBranchByPK(bl.getBranchId());
				if(tempBranch!=null){
					out.println(tempBranch.getBranchName());
				}
				%>
			</td>
			<td><%
				Borrower tempBorrower = service.readBorrowerByPK(bl.getCardNo());
				if(tempBorrower!=null){
					out.println(tempBorrower.getName());
				}
				%>
			</td>
			<td><%=bl.getDateOut()%></td>
			<td><%=bl.getDueDate()%></td>
			<td><button type="button"
					onclick="javascript:location.href='overrideDueDate?bookId=<%=bl.getBookId()%>&branchId=<%=bl.getBranchId()%>&cardNo=<%=bl.getCardNo()%>'"
					class="btn btn-primary btn-sm">Override</button>
			</td>
		</tr>
		<%
			}}
		%>
	</table>
</div>