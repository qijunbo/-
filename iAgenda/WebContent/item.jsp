<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Item</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1">
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />

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
					<%@include file="left.jsp"%>
				</div>
				<div class="col span_2_of_3">
					<h3>Title of the agenda</h3>
					<div id="itemadd" class="contact-form">
						<div class="btn">
							<a href="#" class="link"><span><span>Edit</span></span></a> <a
								href="#" class="link"><span><span>Add item</span></span></a>
						</div>
						<form action="user" method="post">
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
						</form>
					</div>
					<p class="top">
						<a href=""><img src="./images/art-pic1.jpg" alt="" /> Ut enim
							ad minim veniam, quis nostrud exercitation ullamco laboris nisi
							ut aliquip ex ea commodo consequat.</a>
					</p>
					<p class="top">
						<a href=""><img src="./images/art-pic4.jpg" alt="" /> Ut enim
							ad minim veniam, quis nostrud exercitation ullamco laboris nisi
							ut aliquip ex ea commodo consequat.</a>
					</p>
					<p class="top">
						<a href=""><img src="./images/art-pic3.jpg" alt="" /> Ut enim
							ad minim veniam, quis nostrud exercitation ullamco laboris nisi
							ut aliquip ex ea commodo consequat.</a>
					</p>
					<p class="top">
						<a href=""><img src="./images/art-pic1.jpg" alt="" /> Ut enim
							ad minim veniam, quis nostrud exercitation ullamco laboris nisi
							ut aliquip ex ea commodo consequat.</a>
					</p>
					<p class="top">
						<a href=""><img src="./images/art-pic4.jpg" alt="" /> Ut enim
							ad minim veniam, quis nostrud exercitation ullamco laboris nisi
							ut aliquip ex ea commodo consequat.</a>
					</p>
					<p class="top">
						<a href=""><img src="./images/art-pic3.jpg" alt="" /> Ut enim
							ad minim veniam, quis nostrud exercitation ullamco laboris nisi
							ut aliquip ex ea commodo consequat.</a>
					</p>
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