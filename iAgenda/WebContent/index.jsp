<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<title>iAgenda</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<script type="text/javascript" src="./js/jquery-1.11.2.min.js"></script>
<link href="./css/style.css" rel="stylesheet" type="text/css"
	media="all" />
</head>
<body>
	<div class="header-bg">
		<div class="wrap">
			<jsp:include page="menu.jsp"></jsp:include>
		</div>
	</div>
	<div class="wrap">
		<c:if test="${empty user}">
			<%@include file="login.jsp"%>
		</c:if>
		<c:if test="${not empty user}">
			<div class="content abt">
				<div class="section group">
					<div class="col span_1_of_3">
						<%@include file="left.jsp"%>
					</div>
					<div class="col span_2_of_3">
						<h3>Agendas Created By ${user.firstname}</h3>
						<c:forEach items="${agendas}" var="e">
							<p class="top">
								<a href=""><img src="./images/art-pic3.jpg" alt="" />${e.title }</a>
							</p>
						</c:forEach>
					</div>
				</div>
			</div>
		</c:if>
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