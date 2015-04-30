<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>User</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<link href='css/style.css' rel='stylesheet' type='text/css'>
</head>
<body>
	<div class="header-bg">
		<div class="wrap">
			<%@include file="menu.jsp"%>
		</div>
	</div>
	<div class="wrap">
		<div class="content abt">
			<div class="section group">
				<div class="col span_1_of_3">
					<%@include file="left.jsp" %>
				</div>
				<div class="col span_2_of_3">
					<div class="contact-form">
						<h3>Sign Up</h3>
						<form action="user" method="post">
							<div>
								<span><label>FIRST NAME</label></span> <span><input
									name="firstname" type="text" value=""></span>
							</div>
							<div>
								<span><label>SURNAME</label></span> <span><input
									name="surname" type="text" value=""></span>
							</div>
							<div>
								<span><label>E-MAIL</label></span> <span><input
									name="email" type="text" value=""></span>
							</div>
							<div>
								<span><label>MOBILE</label></span> <span><input
									name="mobile" type="text" value=""></span>
							</div>
							<div>
								<span><label>DEPARTMENT</label></span> <span><input
									name="department" type="text" value=""></span>
							</div>
							<div>
								<span><label>PASSWORD</label></span> <span><input
									name="password" type="password" value=""></span>
							</div>
							<div>
								<span><label>REPEAT</label></span> <span><input
									name="password" type="password" value=""></span>
							</div>
							<div>
								<span><input type="submit" value="Submit"></span>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="footer-bg">
		<div class="wrap">
			<%@include file="footer.jsp"%>
		</div>
	</div>
	<div class="footer1-bg">
		<div class="wrap">
			<%@include file="footer1.jsp"%>
		</div>
	</div>
</body>
</html>