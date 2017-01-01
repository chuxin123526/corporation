<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Bootstrap Admin</title>
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<jsp:include page="../common/common.jsp"></jsp:include>

<!-- Demo page code -->

<style type="text/css">
#line-chart {
	height: 300px;
	width: 800px;
	margin: 0px auto;
	margin-top: 1em;
}

.brand {
	font-family: georgia, serif;
}

.brand .first {
	color: #ccc;
	font-style: italic;
}

.brand .second {
	color: #fff;
	font-weight: bold;
}
</style>

<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="lib/html5.js"></script>
    <![endif]-->

<!-- Le fav and touch icons -->
<link rel="shortcut icon" href="../assets/ico/favicon.ico">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="../assets/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="../assets/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="../assets/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="../assets/ico/apple-touch-icon-57-precomposed.png">
</head>

<!--[if lt IE 7 ]> <body class="ie ie6"> <![endif]-->
<!--[if IE 7 ]> <body class="ie ie7 "> <![endif]-->
<!--[if IE 8 ]> <body class="ie ie8 "> <![endif]-->
<!--[if IE 9 ]> <body class="ie ie9 "> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<body class="">
	<!--<![endif]-->

	<%--nav --%>
	<jsp:include page="../common/top.jsp"></jsp:include>
	
	<%--left --%>
	<jsp:include page="../common/left.jsp"></jsp:include>



	<div class="content">

		 

		<ul class="breadcrumb">
			<li><a href="index.html">首页</a> <span class="divider">/</span></li>
			<li class="active">Dashboard</li>
		</ul>

		<div class="container-fluid">
			<div class="row-fluid">


				<div class="row-fluid">

					<div class="alert alert-info">
						<button type="button" class="close" data-dismiss="alert">×</button>
						<strong> 欢迎来到社团网申系统后台管理</strong>
					</div>

					<div class="block">
						<a href="#page-stats" class="block-heading" data-toggle="collapse">状态
							</a>
						<div id="page-stats" class="block-body collapse in">

							<div class="stat-widget-container">
								<div class="stat-widget">
									<div class="stat-button">
										<p class="title">${requestScope.corporationCount}</p>
										<p class="detail">社团</p>
									</div>
								</div>
 
								<div class="stat-widget">
									<div class="stat-button">
										<p class="title">${requestScope.userCount}</p>
										<p class="detail">用户</p>
									</div>
								</div>

								<div class="stat-widget">
									<div class="stat-button">
										<p class="title">${requestScope.recruitInfoCount}</p>
										<p class="detail">招募令</p>
									</div>
								</div>

							

							</div>
						</div>
					</div>
				</div>

				<footer>
				
				</footer>

			</div>
		</div>
	</div>

	<script type="text/javascript">
		$("[rel=tooltip]").tooltip();
		$(function() {
			$('.demo-cancel-click').click(function() {
				return false;
			});
		});
	</script>

</body>
</html>


