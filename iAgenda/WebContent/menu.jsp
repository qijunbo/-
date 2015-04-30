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
		<a href="index.jsp"><img src="images/logo.png" alt="" title="logo"></a>
	</div>
	<div >
		<span>
		<c:if test="${not empty user}">Welcome, <c:out value="${user.firstname}"></c:out> </c:if>
		<c:if test="${empty user}">Welcome !  <c:out value="${user.firstname}"></c:out></c:if>
		</span>
	</div>
	<div class="hdr-nav">
		<ul class="nav">
			<li><a href="index.jsp">Home</a></li>
			<%
				if (session.getAttribute("user") == null) {
			%>
			<li><a class="hsubs" href="user.jsp">SignUp</a></li>
			<%
				} else {
			%>
			<li><a class="hsubs" href="#">Agenda</a>
				<ul class="subs">
					<li><a href="agenda.jsp">Add</a></li>
				</ul></li>
			<li><a class="hsubs" href="#">Me</a>
				<ul class="subs">
					<li><a href="#">MyProfile</a></li>
					<li><a href="logoff">SignOff</a></li>
				</ul></li>
			<%
				}
			%>
			<div id="lavalamp"></div>
		</ul>
	</div>
	<div class="clear"></div>
</div>
