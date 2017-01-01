<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<jsp:include page="../../common/common.jsp"></jsp:include>
</head>
<style>
</style>
<body style="padding-top: 50px;">
	<jsp:include page="../../common/top.jsp"></jsp:include>
	
	<%--社团列表 --%>
	<div class="container" style="margin-top: 5px;">
		<div class="row">

			<c:forEach items="${corporationList}" var="corporation">

				<div class="col-md-3" style="background-color: white; padding: 5px;">
					<a href="../corporation/get?corporationID=${corporation.id}"> <img alt="..."
						src="../corporation/getImage?imageUrl=${corporation.imageUrl }"
						width="100%">
						<h3 style="margin-top: 5px;" class="text-center">${corporation.name}</h3>
					</a>
				</div>

			</c:forEach>

		</div>
		 <hr/>
		<div class = "row">
			<a style = "font-size : 20px;" href = "../apply/applyCreateCorporationUI" class = "glyphicon glyphicon-plus">申请创建社团</a>
		</div>




	</div>
	<br />
	<jsp:include page="../../common/bottom.jsp"></jsp:include>
</body>
<script type="text/javascript">
	
</script>
</html>