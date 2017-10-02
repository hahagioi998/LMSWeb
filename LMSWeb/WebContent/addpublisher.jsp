<%@include file="includeadmin.html"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%AdminService service = new AdminService();
%>
<div class="container">
	<h2>Add New Publisher</h2>
	<form method="post" action="addPublisher">
	${statusMessage}
		${statusMessage}
		<br/>Enter Publisher Name to Edit: <input type="text" name="publisherName" value=""><br />
		<br/>Enter Publisher Address to Edit: <input type="text" name="publisherAddress" value=""><br />
		<br/>Enter Publisher Phone to Edit: <input type="text" name="publisherPhone" value=""><br />
		<br/>
		<button type="submit" class="btn btn-primary btn-md">Save Publisher</button>
	</form>
</div>