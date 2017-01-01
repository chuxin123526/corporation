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
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/bootstrap-datetimepicker.min.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/bootstrap-datetimepicker.zh-CN.js"></script>
</head>
<style>
<!--
body {
	color: #555555;
}
/*jquery validate 前端验证提示信息的字体颜色.*/
label.error {
	padding-left: 0px;
	color: #CC0000;
	font-size: 16px;
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
				<h2 class="text-center" style="color: green;">审核</h2>

				<jsp:include page="../corporation/commonInfo.jsp"></jsp:include>
				<hr />

				<form id="userForm" class="form-horizontal" method="post"
					action="../apply/reviewJoinCorporationApply" role="form">
					<input type="hidden" id="corporationID" name="corporationID"
						value="${requestScope.corporation.id }" /> <input type="hidden"
						id="userID" name="userID"
						value="${requestScope.corporationJoinApply.userID }" /> <input
						type="hidden" id="requestPage" name="requestPage"
						value="${requestPage}" /> <input type="hidden"
						id="corporationJoinApplyID" name="corporationJoinApplyID"
						value="${requestScope.corporationJoinApply.id}" />

					<div class="form-group">
						<label for="userName" class="col-sm-2 control-label">用户名</label>
						<div class="col-sm-9">
							<input type="text" name="userName"
								value="${requestScope.corporationJoinApply.userName}"
								class="form-control" id="userName" readonly />

						</div>
					</div>
					<div class="form-group">
						<label for="realName" class="col-sm-2 control-label">真实姓名</label>
						<div class="col-sm-9">
							<input type="text" name="realName"
								value="${requestScope.corporationJoinApply.realName}"
								class="form-control" id="realName" readonly />

						</div>
					</div>

					<div class="form-group">
						<label for="sex" class="col-sm-2 control-label">性别</label>
						<div class="col-sm-9">
							<input type="text" name="sex"
								value="${requestScope.corporationJoinApply.sex}"
								class="form-control" id="sex" readonly />

						</div>
					</div>

					<div class="form-group">
						<label for="college" class="col-sm-2 control-label">学院</label>
						<div class="col-sm-9">
							<input type="text" name="college"
								value="${requestScope.corporationJoinApply.college}"
								class="form-control" id="college" readonly />

						</div>
					</div>

					<div class="form-group">
						<label for="major" class="col-sm-2 control-label">专业</label>
						<div class="col-sm-9">
							<input type="text" name="major"
								value="${requestScope.corporationJoinApply.major}"
								class="form-control" id="major" readonly />

						</div>
					</div>

					<div class="form-group">
						<label for="period" class="col-sm-2 control-label">年级</label>
						<div class="col-sm-9">
							<input type="text" name="period"
								value="${requestScope.corporationJoinApply.period}"
								class="form-control" id="period" readonly />

						</div>
					</div>

					<div class="form-group">
						<label for="phoneNumber" class="col-sm-2 control-label">电话号码</label>
						<div class="col-sm-9">
							<input type="text" name="phoneNumber"
								value="${requestScope.corporationJoinApply.phoneNumber}"
								class="form-control" id="phoneNumber" readonly />

						</div>
					</div>

					<div class="form-group">
						<label for="email" class="col-sm-2 control-label">电子邮箱</label>
						<div class="col-sm-9">
							<input type="text" name="email"
								value="${requestScope.corporationJoinApply.email}"
								class="form-control" id="email" readonly />

						</div>
					</div>

					<div class="form-group">
						<label for="applyTime" class="col-sm-2 control-label">申请时间</label>
						<div class="col-sm-9">
							<input type="text" name="applyTime"
								value="<fmt:formatDate value='${corporationJoinApply.applyTime }' pattern='yyyy-MM-dd HH:mm'/>"
								class="form-control" id="applyTime" readonly />

						</div>
					</div>

					<div class="form-group">
						<label for="reason" class="col-sm-2 control-label">理由</label>
						<div class="col-sm-9">
							<textarea name="reason" rows="10" class="form-control"
								id="reason" readonly>${requestScope.corporationJoinApply.reason}</textarea>

						</div>
					</div>

					<div class="form-group">
						<label for="handle" class="col-sm-2 control-label"></label>
						<div class="col-sm-9">
							<input type="radio" name="handle" value="通过" checked />通过
						</div>
					</div>

					<div class="form-group">
						<label for="handle" class="col-sm-2 control-label"></label>
						<div class="col-sm-9">
							<input type="radio" name="handle" value="不通过" />不通过
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