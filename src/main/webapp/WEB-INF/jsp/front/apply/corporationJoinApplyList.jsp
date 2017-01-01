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
					<h2 class="text-center" style="color: green;">处理申请</h2>
				</div>
				<input type="hidden" id="corporationID" name="corporationID"
						value="${requestScope.corporation.id }" />
						
						
				<jsp:include page="../corporation/commonInfo.jsp"></jsp:include>
				<hr />

				<%--成员列表 --%>
				<table class="table table-striped">
					<caption>申请列表</caption>
					<thead>
						<tr>
							<th>用户名</th>
							<th>真实姓名</th>
							<th>性别</th>
							<th>学院</th>
							<th>专业</th>
							<th>年级</th>
							<th>电话号码</th>
							<th>电子邮箱</th>
							<th>申请时间</th>
							<th>状态</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${corporationJoinApplyList}" var="corporationJoinApply">
							<tr>
								<td>${corporationJoinApply.userName }</td>
								<td>${corporationJoinApply.realName }</td>
								<td>${corporationJoinApply.sex }</td>
								<td>${corporationJoinApply.college }</td>
								<td>${corporationJoinApply.major }</td>
								<td>${corporationJoinApply.period }</td>
								<td>${corporationJoinApply.phoneNumber }</td>
								<td>${corporationJoinApply.email }</td>
								<td><fmt:formatDate value="${corporationJoinApply.applyTime }" pattern="yyyy-MM-dd HH:mm"/></td>
								<td>${corporationJoinApply.status }</td>
								<td>
								<c:if test="${corporationJoinApply.status == '未审核' }">
									<a href = "../apply/reviewJoinCorporationApplyUI?corporationJoinApplyID=${corporationJoinApply.id}&corporationID=${requestScope.corporation.id}&requestPage=${requestScope.requestPage}">审核</a>
								</c:if>
								</td>
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
									href="../apply/corporationJoinApplyList?requestPage=${requestPage-1}&corporationID=${requestScope.corporation.id}">上一页</a></li>
							</c:if>
							<c:choose>
								<c:when test="${requestPage-5 > 0}">
									<c:forEach begin="${requestPage-5}" end="${requestPage-1}"
										var="i">
										<li><a
											href="../apply/corporationJoinApplyList?requestPage=${i}&corporationID=${requestScope.corporation.id}">${i}</a></li>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<c:forEach begin="1" end="${requestPage-1}" var="i">
										<li><a
											href="../apply/corporationJoinApplyList?requestPage=${i}&corporationID=${requestScope.corporation.id}">${i}</a></li>
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
											href="../apply/corporationJoinApplyList?requestPage=${i}&corporationID=${requestScope.corporation.id}">${i}</a></li>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<c:forEach begin="${requestPage+1}" end="${count}" var="i">
										<li><a
											href="../apply/corporationJoinApplyList?requestPage=${i}&corporationID=${requestScope.corporation.id}">${i}</a></li>
									</c:forEach>
								</c:otherwise>
							</c:choose>
							<c:if test="${requestPage != count}">
								<li><a
									href="../apply/corporationJoinApplyList?requestPage=${requestPage+1}&corporationID=${requestScope.corporation.id}">下一页</a></li>
							</c:if>
							<li><a href="javascript:void(0)"
								style="background-color: #ECF0F1">${requestPage}/${count} 页</a></li>
						</ul>

					</nav>
				</div>
			</div>
	
		</div>


	</div>

	<br />
	<jsp:include page="../../common/bottom.jsp"></jsp:include>
</body>
<script type="text/javascript">
	
</script>
</html>