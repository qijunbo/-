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
<link href="./css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="./js/jquery-1.11.2.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		//alert($("input[name='save']").val());
		//alert($("a[href='save']"));

		//	alert($("input[name='save']").val()); 
		$("a[rel='save']").click(function() {

			$("input[name='save']").val("save");
			//alert($("input[name='save']").val());
			$("form")[0].submit();
		});
	});

	function doDelete(start, i) {
		$(i)[0].remove();
		$.ajax({
			url : 'item?start=' + start,
			type : 'delete',
			success : function(result) {
				alert('result:' + result);
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
		<div class="content abt">
			<div class="section group">
				<div class="cont span_2_of_3">
					<h3>Agenda</h3>
					<img src="./images/pic6.jpg" />
					<c:if test="${empty agenda }">
						<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit,
							sed do eiusmod tempor incididunt ut labore et dolore magna
							aliqua. Ut enim ad minim veniam, quis nostrud exercitation
							ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis
							aute irure dolorLorem ipsum dolor sit amet, consectetur
							adipisicing elit, sed do eiusmod tempor incididunt ut labore et
							dolore.</p>
					</c:if>
					<c:if test="${not empty agenda }">
						<p>
						<h2>${agenda.title }</h2>
						<strong>On: ${agenda.date }</strong>
						<br />
						 ${agenda.detail } 
					</c:if>
					<div class="clear"></div>
					<div id="itemadd" class="contact-form">
						<form action="item" method="post">
							<input name="save" value="!" type="hidden" /> <input
								name="key" value="!" type="hidden" />
							<div>
								<span><label>Start</label></span> <span><input
									name="start" type="text" value=""></span>
							</div>
							<div>
								<span><label>Duration</label></span> <span><input
									name="duration" type="text" value=""></span>
							</div>
							<div>
								<span><label>Speaker</label></span> <span><input
									name="speaker" type="text" value=""></span>
							</div>
							<div>
								<span><label>Room</label></span> <span><input name="room"
									type="text" value=""></span>
							</div>
							<div>
								<span><label>Topic</label></span> <span><input
									name="topic" type="text" value=""></span>
							</div>
							<div>
								<span><label>Image</label></span> <span><input
									name="image" type="text" value=""></span>
							</div>
							<div>
								<span><label>Breakout</label></span> <span><input
									name="breakout" type="text" value=""></span>
							</div>
							<div>
								<span><label>Detail</label></span> <span><textarea
										name="detail"> </textarea></span>
							</div>
							<div>
								<span><input type="submit" value="Submit"></span>
							</div>
							<div class="btn">
								<a class="link" rel="save"><span><span>Save
											&amp; Close</span></span></a>
							</div>
						</form>
					</div>
				</div>
				<div class="rsidebar span_1_of_3">
					<h3>Agenda Items</h3>
					<c:if test="${not empty agenda }">
						<c:forEach items="${agenda.items}" var="item" varStatus="count">
							<div id="item${count.index}">
								<p class="top">
									<a href=""><img src="./images/art-pic1.jpg" alt="" /></a> <br>speaker:
									${item.speaker } <br>start: ${item.start } <br>duration:
									${item.duration } <br>room: ${item.room } <br>topic:
									${item.topic } <br>image: ${item.image } <br>breakout:
									${item.breakout } <br>detail: ${item.detail }
								</p>
								<div class="btn">
									<a class="link"
										onclick="doDelete('${item.start }', '#item${count.index}')"><span><span>Delete</span></span></a>
								</div>
							</div>
						</c:forEach>
					</c:if>
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