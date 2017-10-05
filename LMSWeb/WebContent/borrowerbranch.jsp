<%@include file="includeborrower.html"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="com.gcit.lms.service.BorrowerService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.entity.Library_Branch"%>
<%@page import="com.gcit.lms.entity.Borrower"%>
<%
	BorrowerService service = new BorrowerService();
	Integer totalCount = service.getBranchesCount();
	int numOfPages = 0;
	if(totalCount%10 > 0){
		numOfPages = totalCount/10 +1;
	}else{
		numOfPages = totalCount/10;
	}
	List<Library_Branch> branches = new ArrayList<>();
	if(request.getAttribute("branches")!=null){
		branches = (List<Library_Branch>)request.getAttribute("branches");
	}else{
		branches = service.readLibraryBranch(null, 1);
	}
	
	Borrower borrower = new Borrower();
	borrower.setCardNo((Integer)request.getAttribute("cardNo"));
%>
<%
// 	if (request.getAttribute("statusMessage") != null) {
// 		out.println(request.getAttribute("statusMessage"));
// 	}
%>
<div class="container">
  <div class="alert alert-success alert-dismissable fade in">
    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
    <strong><%out.println(request.getAttribute("statusMessage"));%></strong>
  </div>
	<h2>Card Number: <%=request.getAttribute("cardNo")%></h2>
	<nav aria-label="Page navigation example">
		<ul class="pagination">
			<li class="page-item"><a class="page-link" href="#"
				aria-label="Previous"> <span aria-hidden="true">&laquo;</span> <span
					class="sr-only">Previous</span>
			</a></li>
			<%for(int i=1; i<=numOfPages; i++){ %>
			<li class="page-item"><a class="page-link" href="pageBranch?pageNo=<%=i%>"><%=i%></a></li>
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
			<th>Branches Name</th>
			<th>Address</th>
			<th>Check Book Out</th>
			<th>Return a Book</th>
		</tr>
		<%
			for (Library_Branch a : branches) {
		%>
		<tr>
			<td><%=branches.indexOf(a) + 1%></td>
			<td><%=a.getBranchName()%></td>
			<td>
				<%= a.getBranchAddress()	%>
			</td>
			<td><button type="button"
					onclick="javascript:location.href='checkOut?branchId=<%=a.getBranchId()%>&cardNo=<%=request.getAttribute("cardNo")%>'"
					class="btn btn-primary btn-sm">CheckOut</button></td>
			<td><button type="button"
					onclick="javascript:location.href='returnBook?branchId=<%=a.getBranchId()%>&cardNo=<%=request.getAttribute("cardNo")%>'"
					class="btn btn-danger btn-sm"
					<%if((service.readBooksReturn(a,borrower,1)).isEmpty()){ %>disabled<%} %>>Return</button></td>
		</tr>
		<%
			}
		%>
	</table>
</div>