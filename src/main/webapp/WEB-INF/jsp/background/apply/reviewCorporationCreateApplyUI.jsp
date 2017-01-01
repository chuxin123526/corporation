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
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/messages_zh.min.js"></script>

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

	<jsp:include page="../common/top.jsp"></jsp:include>
	<jsp:include page="../common/left.jsp"></jsp:include>



	<div class="content">

		<div class="header">

			<h1 class="page-title">审核创建社团申请</h1>
		</div>

		<hr />


		<div class="container-fluid">
			<div class="row-fluid">


				<div class="well">


					<form id="managerForm" method="post" action="reviewCorporationCreateApply">
						<input type="hidden" name="requestPage"
							value="${requestScope.requestPage }" /> <input type="hidden"
							name="corporationCreateApplyID"
							value="${requestScope.corporationCreateApply.id }" />
						<div class="form-group">
							<label for="userName">用户名</label> <input type="text"
								class="form-control" name="userName" readonly
								value="${requestScope.corporationCreateApply.userName }">
						</div>
						<div class="form-group">
							<label for="corporationName">社团名称</label> <input type="text"
								class="form-control" name="corporationName" readonly
								value="${requestScope.corporationCreateApply.name }">
						</div>
						<div class="form-group">
							<label for="corporationTypeName">类型</label> <input type="text"
								class="form-control" name="corporationTypeName" readonly
								value="${requestScope.corporationCreateApply.corporationTypeName }">
						</div>
						<div class="form-group">
							<label for="address">社团地址</label> <input type="text"
								class="form-control" name="address" readonly
								value="${requestScope.corporationCreateApply.address}">
						</div>
						<div class="form-group">
							<label for="phoneNumber">联系电话</label> <input type="text"
								class="form-control" name="phoneNumber" readonly
								value="${requestScope.corporationCreateApply.phoneNumber}">
						</div>
						<div class="form-group">
							<label for="email">电子邮箱</label> <input type="text"
								class="form-control" name="email" readonly
								value="${requestScope.corporationCreateApply.email}">
						</div>
						<div class="form-group">
							<label for="applyTime">申请时间</label> <input type="text"
								class="form-control" name="applyTime" readonly
								value="<fmt:formatDate value="${requestScope.corporationCreateApply.applyTime}"
											pattern="yyyy-MM-dd  HH:mm:ss" />">
						</div>
						<div class="form-group">
							<label for="status">状态</label> <input type="text"
								class="form-control" name="status" readonly
								value="${requestScope.corporationCreateApply.status}">
						</div>
						
						<div class="form-group">
							<label for="introduction">简介</label> 
							<textarea class = "form-control" rows = "10" cols = "20" name = "introduction" readonly>${requestScope.corporationCreateApply.introduction}</textarea>
							
						</div>

						<div class="radio">
							<label> <input type="radio" name="handle"
								id="optionsRadios2" value="通过" checked> 通过
							</label>
						</div>
						<div class="radio">
							<label> <input type="radio" name="handle"
								id="optionsRadios3" value="不通过"> 不通过
							</label>
						</div>
						<br />
						<button type="submit" class="btn btn-default">提交</button>
					</form>

				</div>



				<footer>
					<hr>


				</footer>

			</div>
		</div>
	</div>

	<script type="text/javascript">
		
	</script>

</body>
</html>


