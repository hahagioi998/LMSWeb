<%@include file="include.html"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%AdminService service = new AdminService();
%>
<div class="container">
	<h2>Enter Card No</h2>
	<form method="post" action="validateCard">
		${statusMessage}
		<br/><input type="text" name="cardNo" value=""><br /><br />
		<button type="submit" class="btn btn-primary btn-md">Proceed</button>
	</form>
</div>