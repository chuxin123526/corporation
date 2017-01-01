<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="cache-control" content="no-cache"> 
<meta http-equiv="expires" content="0">  
<jsp:include page="../../common/common.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/bootstrap-datetimepicker.min.css" />
<script type="text/javascript" src = "<%=request.getContextPath()%>/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src = "<%=request.getContextPath()%>/js/bootstrap-datetimepicker.zh-CN.js"></script>
</head>
<style>  
<!--  
body
{
	color : #555555 ; 
}
/*jquery validate 前端验证提示信息的字体颜色.*/  
label.error
{
	padding-left: 0px;
	color: #CC0000;
	font-size : 16px ;
}

-->  
</style>  
<body style="padding-top: 50px;">

	<jsp:include page="../../common/top.jsp"></jsp:include>

	<%--图片轮播 --%>
	<jsp:include page="../../common/imageCirculation.jsp"></jsp:include>

	<div class="container" style="margin-top: 5px;">
	
		<div class="row">
			<div class="col-md-12"
				style="background-color: white; margin-top: 10px;">
				<h2 class="text-center" style = "color : green ; ">正在为${requestScope.member.userName}设置职位</h2>
				<hr/>
				<jsp:include page="../corporation/commonInfo.jsp"></jsp:include>
				
				<hr/>
				
				<form id = "userForm" class="form-horizontal" method = "post" action = "../memberManager/setMemberPosition" role="form">
					<input type = "hidden" name = "memberID" value = "${requestScope.member.id}" />
					<input type = "hidden" name = "corporationID" value = "${requestScope.corporation.id}" />
					<input type = "hidden" name = "user_memberPositionID" value = "${requestScope.user_memberPosition.id}" />
					
					<div class="form-group">
						<label for="cost" class="col-sm-2 control-label">职位</label>
						<div class="col-sm-10">
							<select name = "memberPositionID" id = "cost" class = "form-control" >
							<option  value = "0">无</option>
							<c:forEach items = "${memberPositionList}" var = "memberPosition">
							<c:choose>
								<c:when test="${user_memberPosition.memberPositionID == memberPosition.id }">
									<option selected value = "${memberPosition.id}">${memberPosition.name }</option>
								</c:when>
								<c:otherwise>
									<option value = "${memberPosition.id}">${memberPosition.name }</option>
								</c:otherwise>
							</c:choose>
							</c:forEach>
							</select>
						</div>
					</div>
					
					<div class="form-group">
						<div class=" text-center">
							<button type="submit" class="btn btn-primary">提交</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="btn btn-primary" onclick = "javascript:history.go(-1)">返回</button>
						</div>
					</div>

				</form>

			</div>
			
		</div>
	</div>
	<jsp:include page="../../common/bottom.jsp"></jsp:include>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		//图片轮播
		$('.carousel').carousel({
			interval : 2000
		});
		

		
	});
</script>
</html>