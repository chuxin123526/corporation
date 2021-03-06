<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<jsp:include page="common.jsp"></jsp:include>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
</head>
<body>
	<div class="container">
		<div class="row" style="margin-top: 200px;">
			<div class="col-md-3"> </div>
			<div class="col-md-6" style="background-color: white;">
				<c:if test="${requestScope.errorMessage != null }"> 
					<h1 class="text-center"><%=request.getAttribute("errorMessage")%></h1>
				</c:if>
				<hr/>
				<div class = "text-center" >
				<a class = "glyphicon glyphicon-home" href = "../home/index">返回首页</a>&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;   &nbsp;&nbsp;&nbsp; 
				<a class = "glyphicon glyphicon-user" href = "../user/loginUI">登录</a>
				<hr/>
				</div>
			</div>
			<div class="col-md-3"> </div>
		</div>
	</div>
</body>
</html>