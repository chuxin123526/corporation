<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<jsp:include page="../../common/common.jsp"></jsp:include>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
</head>
<style>
</style>
<body style="padding-top: 50px;">
	<jsp:include page="../../common/top.jsp"></jsp:include>
	<jsp:include page="../../common/imageCirculation.jsp"></jsp:include>
	<div class="container" style="margin-top: 10px;background-color : white ; border-radius:30px;">

		<div class="row" style="margin-top: 10px; "> 

			<div class="col-md-12" style="background-color: white;">
				<div class="row" style="padding: 20px;">
					<h2 class="text-center">${requestScope.corporation.name}</h2>
				</div>
				
				<jsp:include page="commonInfo.jsp"></jsp:include>
				
				<hr />

				<div class="row" style="padding: 20px;">
					<img class="img-responsive"
						src="../corporation/getImage?imageUrl=${corporation.imageUrl }" />
				</div>

				<div class="row" style="padding: 20px; word-wrap: break-word;">
					简介：${requestScope.corporation.introduction }</div>
				<div class="row" style="padding: 20px; word-wrap: break-word;">
					地址： ${requestScope.corporation.address }</div>

				<div class="row" style="padding: 20px; word-wrap: break-word;">
					联系电话： ${requestScope.corporation.phoneNumber }</div>

				<div class="row" style="padding: 20px; word-wrap: break-word;">
					电子邮箱： ${requestScope.corporation.email }</div>
			</div>

	</div>
</div>
	<br />
	<jsp:include page="../../common/bottom.jsp"></jsp:include>
</body>

</html>