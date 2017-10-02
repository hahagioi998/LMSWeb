<%@page import="com.gcit.lms.entity.Library_Branch"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@include file="includeadmin.html"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%AdminService service = new AdminService();
Library_Branch libraryBranch = service.readBranchByPK(Integer.parseInt(request.getParameter("branchId")));
%>

<div class = "container">
	<h1>Welcome to GCIT Library Management System!</h1>
	<h2>Edit Branch</h2>
	<form method="post" action="editBranch">
		${statusMessage}
		<br/>Enter Branch Name to Edit: <input type="text" name="branchName" value="<%=libraryBranch.getBranchName()%>"><br />
		<input type="hidden" name="branchId" value="<%=libraryBranch.getBranchId()%>"><br />
		<br/>Enter Branch Address to Edit: <input type="text" name="branchAddress" value="<%=libraryBranch.getBranchAddress()%>"><br />
		<br/>
		<button type="submit">Update Branch</button>
	</form>
</div>