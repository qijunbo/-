<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Agenda</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<link href='css/jquery-ui.min.css' rel='stylesheet' type='text/css'>
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />

<script type="text/javascript" src="./js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="./js/jquery-ui.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
	 
		$( "#datepicker" ).datepicker();
		$( "input[name='date']" ).click(function(){
			alert('run');
			$(".datepicker_container").class=".datepicker_container_open";
		});
	});
</script>
 
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
					<div class="contact-form">
						<h3>Sign Up</h3>
						<form action="agenda" method="post">
							<div>
								<span><label>Title</label></span> <span><input
									name="title" type="text" value=""></span>
							</div>
							<div>
								<span><label>Date</label></span> <span><input name="date"
									type="text" value="yyyy-MM-dd"></span> 
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
	
	<div class="datepicker_container" ><div id="datepicker"></div> </div>
</body>
</html>