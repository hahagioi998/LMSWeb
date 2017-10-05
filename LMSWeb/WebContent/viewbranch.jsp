<%@include file="includeadmin.html"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.entity.Library_Branch"%>
<%
	AdminService service = new AdminService();
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
	Integer pageNo = 1;
	Boolean first = false, last = false;
	if(request.getAttribute("pageNo")!=null){
		pageNo = (Integer)request.getAttribute("pageNo");
	} else {
		pageNo = 1;
	}
	
	if(pageNo == numOfPages){
		last = true;
	}
	
	if(pageNo == 1){
		first = true;
	}
%>
<%
	if (request.getAttribute("statusMessage") != null) {
		out.println(request.getAttribute("statusMessage"));
	}
%>
<div class="container">
	<h1>List of Branches in LMS&nbsp;&nbsp;&nbsp;&nbsp; Total Branches in LMS: <%=totalCount%> Branches</h1>
	<nav aria-label="Page navigation example">
		<ul class="pagination">
			<li class="page-item<% if(first==true){%> disabled<%}%>"><a class="page-link" href="pageBranches?pageNo=<%=pageNo-1%>"
				aria-label="Previous"> <span aria-hidden="true">&laquo;</span> <span
					class="sr-only">Previous</span>
			</a></li>
			<%for(int i=1; i<=numOfPages; i++){ %>
			<li class="page-item"><a class="page-link" href="pageBranches?pageNo=<%=i%>"><%=i%></a></li>
			<%} %>
			<li class="page-item<% if(last==true){%> disabled<%}%>"><a class="page-link" href="pageBranches?pageNo=<%=pageNo+1%>"
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
			<th>Edit Branch</th>
			<th>Delete Branch</th>
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
					onclick="javascript:location.href='editbranch.jsp?branchId=<%=a.getBranchId()%>'"
					class="btn btn-primary btn-sm">Edit</button></td>
			<td><button type="button"
					onclick="javascript:location.href='deleteBranch?branchId=<%=a.getBranchId()%>'"
					class="btn btn-danger btn-sm">Delete</button></td>
		</tr>
		<%
			}
		%>
	</table>
</div>