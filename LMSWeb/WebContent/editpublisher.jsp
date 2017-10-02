<%@page import="com.gcit.lms.entity.Publisher"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@include file="includeadmin.html"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.Book"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%AdminService service = new AdminService();
Publisher pub = service.readPublisherByPK(Integer.parseInt(request.getParameter("publisherId")));
%>

<div class = "container">
	<h1>Welcome to GCIT Library Management System!</h1>
	<h2>Edit Publisher</h2>
	<form method="post" action="editPublisher">
		${statusMessage}
		<br/>Enter Publisher Name to Edit: <input type="text" name="publisherName" value="<%=pub.getPublisherName()%>"><br />
		<input type="hidden" name="publisherId" value="<%=pub.getPublisherId()%>"><br />
		<br/>Enter Publisher Address to Edit: <input type="text" name="publisherAddress" value="<%=pub.getPublisherAddress()%>"><br />
		<br/>Enter Publisher Phone to Edit: <input type="text" name="publisherPhone" value="<%=pub.getPublisherPhone()%>"><br />
		<br/>
		<button type="submit">Update Publisher</button>
	</form>
</div>