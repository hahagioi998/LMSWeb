<%@include file="includeadmin.html"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%AdminService service = new AdminService();
%>
<div class="container">
	<h2>Add New Borrower</h2>
	<form method="post" action="addBorrower">
	${statusMessage}
		${statusMessage}
		<br/>Enter Borrower Name to Edit: <input type="text" name="borrowerName" value=""><br />
		<br/>Enter Borrower Address to Edit: <input type="text" name="borrowerAddress" value=""><br />
		<br/>Enter Borrower Phone to Edit: <input type="text" name="borrowerPhone" value=""><br />
		<br/>
		<button type="submit" class="btn btn-primary btn-md">Save Borrower</button>
	</form>
</div>