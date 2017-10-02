<%@include file="include.html"%>
<%@page import="com.gcit.lms.entity.Library_Branch"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.LibrarianService"%>
<%LibrarianService service = new LibrarianService();
List<Library_Branch> libraryBranch = service.readBranch("");
%>
<div class="container">
<h2>Choose Your Branch</h2>
	<form method="post" action="chooseBranch">
	${statusMessage}
		Select Branch from list Below: <br/>
		<select size="10" name="branchId">
			<%for(Library_Branch lb: libraryBranch) {%>
			<option value=<%=lb.getBranchId()%>><%=lb.getBranchName()%>&nbsp;@<%=lb.getBranchAddress()%></option>
			<%} %>
		</select>
		<br/><br/>
		<button type="submit" class="btn btn-primary btn-md">Proceed</button>
	</form>
</div>