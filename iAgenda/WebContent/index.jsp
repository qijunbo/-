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
<link href="./css/style.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
	function doDelete(title, i) {
		$(i)[0].remove();
		$.ajax({
			url : 'agenda?title=' + title,
			type : 'delete',
			success : function(result) {

			}
		});
	}
</script>
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
						<div class="contact-form">
							<h3>Create Agenda</h3>
							<div class="contact-form">
							<form action="agenda" method="post">
								<div>
									<span><label>Title</label></span> <span><input
										name="title" type="text" value=""></span>
								</div>
								<div>
									<span><label>Date</label></span> <span><input
										name="date" type="text" value="2015-05-12"></span>
								</div>
								<div>
									<span><label>Detail</label></span> <span><textarea
											name="detail"> </textarea></span>
								</div>
								<div>
									<span><input type="submit" value="Submit"></span>
								</div>
							</form>
							</div>
						</div>
						<c:if test="${not empty user.agendas}">
							<h3>Agendas Created By ${user.firstname}</h3>
							<c:forEach items="${user.agendas}" var="e" varStatus="count">
								<div id="item${count.index}">
									<p class="top">
										<strong>${e.title}</strong> <img src="./images/art-pic3.jpg"
											alt="" /><br> <a href="agenda?title=${e.title}">${e.detail}</a>
									</p>
									<div class="btn">
										<a class="link"
											onclick="doDelete('${e.title }', '#item${count.index}')"><span><span>Delete</span></span></a>
									</div>
								</div>
							</c:forEach>
						</c:if>
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