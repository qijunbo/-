<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(document).ready(function() {
		$("a.logoff").click(function() {
			alert("I\'m clicked!");

			$.ajax({
				url : "logoff",
				data : {
					zipcode : 97201
				},
				success : function(data) {
					alert(data);
					$("a.logoff").html("Login");
				}
			});
		});
	});
</script>
<div class="header">
	<div class="logo">
		<h1>
			<a href="index.jsp"><img src="./images/logo.png" /></a>
		</h1>
	</div>
	<div class="logo1">
		<span> <c:if test="${not empty user}">Welcome, <c:out
					value="${user.firstname}"></c:out>!
			</c:if> 
			<c:if test="${empty user}">Welcome to iAgenda! </c:if>
		</span>
	</div>
	<div class="hdr-nav">
		<ul class="nav">
			<li><a href="index.jsp">Home</a></li>
			<%
				if (session.getAttribute("user") == null) {
			%>
			<li><a  href="user.jsp">SignUp</a></li>
			<%
				} else {
			%>
			<li><a href="profile.jsp">MyProfile</a></li>
			<li><a href="logoff">Logout</a></li>
			<%
				}
			%>
			<li><a  href="dev.jsp">Developer's</a></li>
			<div id="lavalamp"></div>
		</ul>
	</div>
	<div class="clear"></div>
</div>
