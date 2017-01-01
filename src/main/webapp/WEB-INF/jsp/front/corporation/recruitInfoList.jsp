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
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
</head>
<body style="padding-top: 50px;">

	<jsp:include page="../../common/top.jsp"></jsp:include>

	<%--图片轮播 --%>
	<jsp:include page="../../common/imageCirculation.jsp"></jsp:include>

	<div class="container" style="margin-top: 5px;">
		<div class="row">

			<c:forEach items="${requestScope.recruitInfoList }"
				var="recruitInfoVO">
				<div class="row">
					<a
						href="../corporation/recruitInfo?recruitInfoID=${recruitInfoVO.id }">
						<div class="row" style="margin-top: 10px; background-color: white">
							<div class="col-md-4" style="padding-left: 0px;">
								<img class="img-responsive" style = "border-radius:10px;"
									src="../corporation/getImage?imageUrl=${recrecruitInfoVo.imageUrl}" />
							</div>
							<div class="col-md-8">
								<h4>${recruitInfoVO.title }</h4>
								<p style="word-wrap: break-word;">${recruitInfoVO.content }</p>
							</div>
						</div>
					</a>
				</div>
			</c:forEach>
		</div>
		
		<div class="row text-center">
			<nav>
				<ul class="pagination">
					<c:if test="${requestPage != 1 }">
                    		<li><a href = "../corporation/recruitInfoList?requestPage=${requestPage-1}">上一页</a></li>
                    	</c:if>
					<c:choose>
					<c:when test="${requestPage-5 > 0}">
						<c:forEach begin="${requestPage-5}" end="${requestPage-1}" var="i">
							<li><a
								href="../corporation/recruitInfoList?requestPage=${i}">${i}</a></li>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<c:forEach begin="1" end="${requestPage-1}" var="i">
							<li><a
								href="../corporation/recruitInfoList?requestPage=${i}">${i}</a></li>
						</c:forEach>
					</c:otherwise>
				</c:choose>
				<li><a href = "javascript:void(0)" style = "background-color:#ECF0F1" >${requestPage}</a></li>
				<c:choose>
					<c:when test="${requestPage+5 < count}">
						<c:forEach begin="${requestPage+1}" end="${requestPage+5}" var="i">
							<li><a
								href="../corporation/recruitInfoList?requestPage=${i}\">${i}</a></li>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<c:forEach begin="${requestPage+1}" end="${count}"
							var="i">
							<li><a
								href="../corporation/recruitInfoList?requestPage=${i}">${i}</a></li>
						</c:forEach>
					</c:otherwise>
				</c:choose>
				<c:if test="${requestPage != count}">
					<li><a
						href="../corporation/recruitInfoList?requestPage=${requestPage+1}">下一页</a></li>
				</c:if>
				<li><a href = "javascript:void(0)"  style = "background-color:#ECF0F1">${requestPage}/${count} 页</a></li>
				</ul>
				 
			</nav>


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