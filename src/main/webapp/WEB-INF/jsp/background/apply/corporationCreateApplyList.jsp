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

			<h1 class="page-title">社团创建申请</h1>
		</div>

		<hr />

		<div class="container-fluid">
			<div class="row-fluid">


				<div class="well">
					<table class="table" style="font-size: 12px;">
						<thead>
							<tr>
								<th>用户名</th>
								<th>社团名称</th>
								<th>类型</th>
								<th>社团地址</th>
								<th>联系电话</th>
								<th>联系邮箱</th>
								<th>申请时间</th>
								<th>状态</th>
								<th style="width: 26px;"></th>
							</tr>
						</thead>
						<tbody>

							<c:forEach items="${corporationCreateApplyList}"
								var="corporationCreateApply">
								<tr>
									<td>${corporationCreateApply.userName}</td>
									<td>${corporationCreateApply.name}</td>
									<td>${corporationCreateApply.corporationTypeName}</td>
									<td>${corporationCreateApply.address}</td>
									<td>${corporationCreateApply.phoneNumber}</td>
									<td>${corporationCreateApply.email}</td>
									<td><fmt:formatDate value="${corporationCreateApply.applyTime}"
											pattern="yyyy-MM-dd" /></td>
									<td>${corporationCreateApply.status}</td>

									<td><c:if
											test="${corporationCreateApply.status == '未审核' }">
											<a
												href="reviewCorporationCreateApplyUI?corporationCreateApplyID=${corporationCreateApply.id }&requestPage=${requestPage}">审核</a>
										</c:if></td>
								</tr>
							</c:forEach>


						</tbody>
					</table>
				</div>
				
				
				
				<div class="pagination">
					<ul>
						<c:if test="${requestPage != 1 }">
								<li><a
									href="corporationCreateApplyList?requestPage=${requestPage-1}">上一页</a></li>
							</c:if>
							<c:choose>
								<c:when test="${requestPage-5 > 0}">
									<c:forEach begin="${requestPage-5}" end="${requestPage-1}"
										var="i">
										<li><a
											href="corporationCreateApplyList?requestPage=${i}">${i}</a></li>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<c:forEach begin="1" end="${requestPage-1}" var="i">
										<li><a
											href="corporationCreateApplyList?requestPage=${i}">${i}</a></li>
									</c:forEach>
								</c:otherwise>
							</c:choose>
							<li><a href="javascript:void(0)"
								style="background-color: #ECF0F1">${requestPage}</a></li>
							<c:choose>
								<c:when test="${requestPage+5 < count}">
									<c:forEach begin="${requestPage+1}" end="${requestPage+5}"
										var="i">
										<li><a
											href="corporationCreateApplyList?requestPage=${i}">${i}</a></li>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<c:forEach begin="${requestPage+1}" end="${count}" var="i">
										<li><a
											href="corporationCreateApplyList?requestPage=${i}">${i}</a></li>
									</c:forEach>
								</c:otherwise>
							</c:choose>
							<c:if test="${requestPage != count}">
								<li><a
									href="corporationCreateApplyList?requestPage=${requestPage+1}">下一页</a></li>
							</c:if>
							<li><a href="javascript:void(0)"
								style="background-color: #ECF0F1">${requestPage}/${count} 页</a></li>
					</ul>
				</div>

				<footer> </footer>

			</div>
		</div>
	</div>

	<script type="text/javascript">
		
	</script>

</body>
</html>


