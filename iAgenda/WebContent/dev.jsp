<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Developer's Tool</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href='css/style.css' rel='stylesheet' type='text/css'>
<script type="text/javascript" src="./js/jquery-1.11.2.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {

		//	alert($("a.link"));

		// Preventing a link from being followed
		$("a.link").click(function(eventObject) {

			$.ajax({
				
				//url: "5.10.70.180:1188/iAgenda/rest/agenda?email=junboqi@cn.ibm.com&password=123456",
				url : "rest/agenda?email=junboqi@foxmail.com&password=123456",
				//type : 'delete',
				data : {
					"email" : "junboqi@foxmail.com",
					"password" : "123456"
				},
				success : function(data) {

					var json = JSON.parse(data);

					$("textarea[name='result']").val(data);
					
					$("textarea[name='firstname']").val(json.firstname);

				}
			});

			eventObject.preventDefault();
			// var elem = $( this );
			// if ( elem.attr( "href" ).match( /evil/ ) ) {

			//   elem.addClass( "evil" );
			//}
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
						<h4>Method 1:</h4>
						<p>Send requst like the following example with Firfox
							RestClient you will get a response in JSON format.</p>
						<form action="" method="post">
							<div>
								<span><label>METHOD</label></span> <span><input
									name="method" type="text" value="POST" disabled="disabled"></span>
							</div>
							<div>
								<span><label>URL</label></span> <span><input name="url"
									type="text" value="http://5.10.70.180:1188/iAgenda/rest/agenda"></span>
							</div>
							<div>
								<span><label>Request Body</label></span> <span><textarea
										name="request">{"email":"junboqi@cn.ibm.com","password":"123456"}</textarea></span>
							</div>
							<div class="clear"></div>
						</form>
					</div>
					<h4>Method 2:</h4>
					<p>
						<a target="_blank"
							href="http://5.10.70.180:1188/iAgenda/rest/agenda?email=junboqi@cn.ibm.com&password=123456">
							http://5.10.70.180:1188/iAgenda/rest/agenda?email=junboqi@cn.ibm.com&amp;password=123456</a>
					<p>
					<div class="btn">
						<a class="link"><span><span>Get</span></span></a>
					</div>
					<div class="clear"></div>
					<div>
						<span><label>Request Body</label></span> <span><textarea
								name="result"></textarea></span>
					</div>
					<div>
						<span><label>Request Body</label></span> <span><textarea
								name="firstname"></textarea></span>
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