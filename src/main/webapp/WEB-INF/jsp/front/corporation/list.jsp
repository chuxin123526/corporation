<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<div class="container">
		<div>
			<span style="font-size: 30px;">全部分类</span>
			<c:forEach items="${requestScope.corporationTypeList }"
				var="corporationType">
				<span style="font-size: 20px;"><a href="../search/search?keyword=${keyword}&requestPage=1&typeID=${corporationType.id}">${corporationType.name }</a></span>
			</c:forEach>
		</div>
	</div>
	<%--社团列表 --%>
	<div class="container" style="margin-top: 5px;">

		<div class="row">
			<c:forEach items="${requestScope.corporationList}" var="corporation">
				<div class="col-md-3" style="background-color: white; padding: 5px;">
					<a href="../corporation/get?corporationID=${corporation.id }">
						<img alt="..." class="img-responsive" style = "border-radius:10px;" 
						src="../corporation/getImage?imageUrl=${corporation.imageUrl}"
						width="100%">
						<h3 style="margin-top: 5px;" class="text-center">${corporation.name }</h3>
					</a>
				</div>
			</c:forEach>
		</div>
		<div class="row text-center"></div>


		<div class="row text-center">
			<nav>
				<ul class="pagination">
					<c:if test="${requestPage != 1 }">
                    		<li><a href = "../search/search?keyword=${keyword}&requestPage=${requestPage-1}&typeID=${typeID}">上一页</a></li>
                    	</c:if>
					<c:choose>
					<c:when test="${requestPage-5 > 0}">
						<c:forEach begin="${requestPage-5}" end="${requestPage-1}" var="i">
							<li><a
								href="../search/search?keyword=${keyword}&requestPage=${i}&typeID=${typeID}">${i}</a></li>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<c:forEach begin="1" end="${requestPage-1}" var="i">
							<li><a
								href="../search/search?keyword=${keyword}&requestPage=${i}&typeID=${typeID}">${i}</a></li>
						</c:forEach>
					</c:otherwise>
				</c:choose>
				<li><a href = "javascript:void(0)" style = "background-color:#ECF0F1" >${requestPage}</a></li>
				<c:choose>
					<c:when test="${requestPage+5 < count}">
						<c:forEach begin="${requestPage+1}" end="${requestPage+5}" var="i">
							<li><a
								href="../search/search?keyword=${keyword}&requestPage=${i}&typeID=${typeID}">${i}</a></li>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<c:forEach begin="${requestPage+1}" end="${count}"
							var="i">
							<li><a
								href="../search/search?keyword=${keyword}&requestPage=${i}&typeID=${typeID}">${i}</a></li>
						</c:forEach>
					</c:otherwise>
				</c:choose>
				<c:if test="${requestPage != count}">
					<li><a
						href="../search/search?keyword=${keyword}&requestPage=${requestPage+1}&typeID=${typeID}">下一页</a></li>
				</c:if>
				<li><a href = "javascript:void(0)"  style = "background-color:#ECF0F1">${requestPage}/${count} 页</a></li>
				</ul>
				 
			</nav>
		</div>
	</div>


	<br />
	<jsp:include page="../../common/bottom.jsp"></jsp:include>
</body>
<script type="text/javascript">
	
</script>
</html>