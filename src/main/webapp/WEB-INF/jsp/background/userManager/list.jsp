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

			<h1 class="page-title">Users</h1>
		</div>


		<div class="container-fluid">
			<div class="row-fluid">

				<hr/>
				<div class="well">
					<table class="table" style="font-size: 12px;">
						<thead>
							<tr>
								<th>编号</th>
								<th>用户名</th>
								<th>真实姓名</th>
								<th>性别</th>
								<th>手机号码</th>
								<th>电子邮箱</th>
								<th>学院</th>
								<th>专业</th>
								<th>年级</th>
								<th>状态</th>
								<th>注册时间</th>
								<th>操作</th>
								<th style="width: 26px;"></th>
							</tr>
						</thead>
						<tbody>

							<c:forEach items="${userList}" var="user">
								<tr>
									<td>${user.id}</td>
									<td>${user.userName}</td>
									<td>${user.realName}</td>
									<td>${user.sex}</td>
									<td>${user.phoneNumber}</td>
									<td>${user.email}</td>
									<td>${user.college}</td>
									<td>${user.major}</td>
									<td>${user.period}</td>
									<td>${user.status}</td>
									<td><fmt:formatDate value="${user.registerTime}"
											pattern="yyyy-MM-dd" /></td>
									<td><a href="delete?userID=${user.id }&requestPage=${requestPage}" onclick = "return confirm('确定要删除该用户吗，将会删除该用户的所有信息且不可恢复')">删除</a>
										
									</td>
								</tr>
							</c:forEach>


						</tbody>
					</table>
				</div>
				<div class="pagination">
					<ul>
						<c:if test="${requestPage != 1 }">
								<li><a
									href="list?requestPage=${requestPage-1}">上一页</a></li>
							</c:if>
							<c:choose>
								<c:when test="${requestPage-5 > 0}">
									<c:forEach begin="${requestPage-5}" end="${requestPage-1}"
										var="i">
										<li><a
											href="list?requestPage=${i}">${i}</a></li>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<c:forEach begin="1" end="${requestPage-1}" var="i">
										<li><a
											href="list?requestPage=${i}">${i}</a></li>
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
											href="list?requestPage=${i}">${i}</a></li>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<c:forEach begin="${requestPage+1}" end="${count}" var="i">
										<li><a
											href="list?requestPage=${i}">${i}</a></li>
									</c:forEach>
								</c:otherwise>
							</c:choose>
							<c:if test="${requestPage != count}">
								<li><a
									href="list?requestPage=${requestPage+1}">下一页</a></li>
							</c:if>
							<li><a href="javascript:void(0)"
								style="background-color: #ECF0F1">${requestPage}/${count} 页</a></li>
					</ul>
				</div>

				<div class="modal small hide fade" id="myModal" tabindex="-1"
					role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">×</button>
						<h3 id="myModalLabel">Delete Confirmation</h3>
					</div>
					<div class="modal-body">
						<p class="error-text">
							<i class="icon-warning-sign modal-icon"></i>Are you sure you want
							to delete the user?
						</p>
					</div>
					<div class="modal-footer">
						<button class="btn" data-dismiss="modal" aria-hidden="true">Cancel</button>
						<button class="btn btn-danger" data-dismiss="modal">Delete</button>
					</div>
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


