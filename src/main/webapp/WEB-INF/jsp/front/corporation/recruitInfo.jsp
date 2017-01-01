<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%> 
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="zh-CN">
<jsp:include page="../../common/common.jsp"></jsp:include>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<head>
</head>
<body style="padding-top: 50px;">

	<jsp:include page="../../common/top.jsp"></jsp:include>

	<%--图片轮播 --%>
	<jsp:include page="../../common/imageCirculation.jsp"></jsp:include>

	<div class="container" style="margin-top: 5px;">
		<div class="row">
			<div class="col-md-12"
				style="background-color: white; margin-top: 10px;">
				<h2 class="text-center" style = "color : green ; ">${requestScope.recruitInfo.title }</h2>

				<div class="row" style="padding: 20px;">
					<a href="../corporation/get?corporationID=${requestScope.recruitInfo.corporationID }" class="glyphicon glyphicon-list">${requestScope.recruitInfo.corporationName }</a>&nbsp;&nbsp;&nbsp;&nbsp;
					<span class="glyphicon glyphicon-time">开始时间:<fmt:formatDate value="${requestScope.recruitInfo.beginTime}" pattern="yyyy-MM-dd"/></span>&nbsp;&nbsp;&nbsp;&nbsp;
					<span class="glyphicon glyphicon-time">结束时间:<fmt:formatDate value="${requestScope.recruitInfo.endTime }" pattern="yyyy-MM-dd"/></span>&nbsp;&nbsp;&nbsp;&nbsp;
					<span class="glyphicon glyphicon-user">招募人数:${requestScope.recruitInfo.amount }</span>&nbsp;&nbsp;&nbsp;&nbsp;
					<span class="glyphicon glyphicon-usd">入团费用:${requestScope.recruitInfo.cost }</span>&nbsp;&nbsp;&nbsp;&nbsp;
				
				</div>
				<hr/>
				<div class = "row" style = "padding : 20px ; ">
				<p  style = "word-wrap:break-word;">
					${requestScope.recruitInfo.content }
				</p>
					
					<br/>
					<a href="../apply/applyJoinCorporationUI?corporationID=${recruitInfo.corporationID}" class="glyphicon glyphicon-plus" style = "font-size : 20px ;">加入我们</a>
				</div>

			</div>
			
		</div>
	</div>
	<jsp:include page="../../common/bottom.jsp"></jsp:include>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$('.carousel').carousel({
			interval : 2000
		});
	});
</script>
</html>