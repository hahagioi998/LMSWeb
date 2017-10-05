<%@include file="include.html"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%
	AdminService service = new AdminService();
%>
<div class="container">
	<!-- <header class="intro-header"> -->
	<!-- 	<h2>Enter Card No</h2> -->
	<%-- 	${statusMessage} --%>
	<!-- 	<form method="post" action="validateCard"> -->
	<!-- 		<br/><input type="text" name="cardNo" value=""><br /><br /> -->
	<!-- 		<button type="submit" class="btn btn-primary btn-md">Proceed</button> -->
	<!-- 	</form> -->
	<!--   </header> -->
	<header class="intro-header">
		<div class="container">
			<div class="intro-message">
				<h1>GCIT Library Management System</h1>
				<hr class="intro-divider">
				<ul class="list-inline intro-social-buttons">
					<li class="list-inline-item">
						<h2>Enter Card No</h2>
					</li>
					<li class="list-inline-item">
						<form method="post" action="validateCard">
							<input type="text" name="cardNo" value="">
							<button type="submit" class="btn btn-primary btn-md">Proceed</button>
						</form>
					</li>
				</ul>
			</div>
		</div>
	</header>
</div>