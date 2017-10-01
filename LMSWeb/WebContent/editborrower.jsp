<%@page import="com.gcit.lms.entity.Borrower"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@include file="include.html"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%AdminService service = new AdminService();
Borrower borrower = service.readBorrowerByPK(Integer.parseInt(request.getParameter("borrowerId")));
%>

<div class = "container">
	<h1>Welcome to GCIT Library Management System!</h1>
	<h2>Edit Borrower</h2>
	<form method="post" action="editBorrower">
		${statusMessage}
		<br/>Enter Borrower Name to Edit: <input type="text" name="borrowerName" value="<%=borrower.getName()%>"><br />
		<input type="hidden" name="borrowerId" value="<%=borrower.getCardNo()%>"><br />
		<br/>Enter Borrower Address to Edit: <input type="text" name="borrowerAddress" value="<%=borrower.getAddress()%>"><br />
		<br/>Enter Borrower Phone to Edit: <input type="text" name="borrowerPhone" value="<%=borrower.getPhone()%>"><br />
		<br/>
		<button type="submit">Update Borrower</button>
	</form>
</div>