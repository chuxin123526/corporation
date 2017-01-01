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
	<div class="container" style="margin-top: 10px;">

		<div class="row" style="margin-top: 10px;">

			<div class="col-md-12" style="background-color: white;">
				<div class="row" style="padding: 20px;">
					<h2 class="text-center" style="color: green;">我的申请</h2>
				</div>

				<hr />

				<%--成员列表 --%>
				<table class="table table-striped">
					<caption>我申请加入的社团</caption>
					<thead>
						<tr>
							<th>社团名称</th>
							<th>申请时间</th>
							<th>状态</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${corporationJoinApplyList}" var="corporationJoinApply">
							<tr>
								<td>${corporationJoinApply.corporationName }</td>
								<td><fmt:formatDate value="${corporationJoinApply.applyTime }" pattern="yyyy-MM-dd HH:mm"/></td>
								<td>${corporationJoinApply.status }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

				<%--分页信息 --%>
				<div class="row text-center">
					<nav>
						<ul class="pagination">
							<c:if test="${requestPage != 1 }">
								<li><a
									href="../apply/myApplyJoinCorporationList?requestPage=${requestPage-1}">上一页</a></li>
							</c:if>
							<c:choose>
								<c:when test="${requestPage-5 > 0}">
									<c:forEach begin="${requestPage-5}" end="${requestPage-1}"
										var="i">
										<li><a
											href="../apply/myApplyJoinCorporationList?requestPage=${i}">${i}</a></li>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<c:forEach begin="1" end="${requestPage-1}" var="i">
										<li><a
											href="../apply/myApplyJoinCorporationList?requestPage=${i}">${i}</a></li>
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
											href="../apply/myApplyJoinCorporationList?requestPage=${i}">${i}</a></li>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<c:forEach begin="${requestPage+1}" end="${count}" var="i">
										<li><a
											href="../apply/myApplyJoinCorporationList?requestPage=${i}">${i}</a></li>
									</c:forEach>
								</c:otherwise>
							</c:choose>
							<c:if test="${requestPage != count}">
								<li><a
									href="../apply/myApplyJoinCorporationList?requestPage=${requestPage+1}">下一页</a></li>
							</c:if>
							<li><a href="javascript:void(0)"
								style="background-color: #ECF0F1">${requestPage}/${count} 页</a></li>
						</ul>

					</nav>
				</div>
			</div>
	</div>

	<br />
	<jsp:include page="../../common/bottom.jsp"></jsp:include>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$("#logoutCorporation").click(function() {
			if (confirm("确定要注销该社团吗，这将会删除该社团的所有信息")) {
				$.ajax({
					url : "../corporation/logoutCorporation",
					type : "post",
					dataType : "json",
					data : {
						"corporationID" : corporationID
					},
					success : function(result) {
						switch (result.responseCode) {
						case '1':
							history.go(-1);
							break;
						case '0':
							alert('注销失败，系统异常');
							break;
						}
					}
				});
			}
		});
	});
</script>
</html>