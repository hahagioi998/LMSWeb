<%@include file="include.html"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%AdminService service = new AdminService();
%>
<div class="container">
	<h2>Add New Branch</h2>
	<form method="post" action="addBranch">
	${statusMessage}
		${statusMessage}
		<br/>Enter Branch Name to Edit: <input type="text" name="branchName" value=""><br />
		<br/>Enter Branch Address to Edit: <input type="text" name="branchAddress" value=""><br />
		<br/>
		<button type="submit" class="btn btn-primary btn-md">Save Branch</button>
	</form>
</div>