<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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

		<div class="header">

			<h1 class="page-title">roles</h1>
		</div>

		<hr/>

		<div class="container-fluid">
			<div class="row-fluid">
				<div class="btn-toolbar"> 
					<a class="btn btn-primary" href = "addUI">
						<i class="icon-plus"></i> 添加角色
					</a>
				</div>
			
				<div class="well">
					<table class="table" style="font-size: 12px;">
						<thead>
							<tr>
								<th>编号</th>
								<th>名称</th>
								<th>操作</th>
								<th style="width: 26px;"></th>
							</tr>
						</thead>
						<tbody>

							<c:forEach items="${roleList}" var="role">
								<tr>
									<td>${role.id}</td>
									<td>${role.name}</td>
									<td><a href="delete?roleID=${role.id }"
										onclick="return confirm('确定要删除该角色吗')">删除</a>
										<a href="allocatePrivilegeUI?roleID=${role.id }">分配权限</a>
										</td>
								</tr>
							</c:forEach>


						</tbody>
					</table>
				</div>

				<footer> </footer>

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


