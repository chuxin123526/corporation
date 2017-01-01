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

		<div class="row" style="margin-top: 5px;">

			<div class="col-md-12" style="background-color: white;">
				<div class="row" style="padding: 20px;">
					<h2 class="text-center" style="color: green;">${requestScope.corporation.name }</h2>
				</div>

				<jsp:include page="../corporation/commonInfo.jsp"></jsp:include>
				<hr />

				<%--成员列表 --%>
				<table class="table table-striped">
					<caption>成员列表</caption>
					<thead>
						<tr>
							<th>名字</th>
							<th>性别</th>
							<th>真实姓名</th>
							<th>手机号码</th>
							<th>电子邮箱</th>
							<th>学院</th>
							<th>专业</th>
							<th>年级</th>
							<th>职位</th>
							
							<c:if
								test="${sessionScope.user != null && sessionScope.user.id == requestScope.corporation.presidentID}">
								<th class="text-center">操作</th>
							</c:if>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${memberList}" var="member">
							<tr>
								<td>${member.userName }</td>
								<td>${member.sex }</td>
								<td>${member.realName }</td>
								<td>${member.phoneNumber}</td>
								<td>${member.email }</td>
								<td>${member.college }</td>
								<td>${member.major }</td>
								<td>${member.period }</td>
								<td>${member.memberPositionName }</td>
								<c:if
									test="${sessionScope.user != null && sessionScope.user.id == requestScope.corporation.presidentID}">
									<td><a
										href="../memberManager/setMemberPositionUI?corporationID=${corporation.id}&memberID=${member.id}">设置职位</a>
										&nbsp;&nbsp;<a onclick="return confirm('确定要将该成员移出社团吗')"
										href="../memberManager/kick?memberID=${member.id}&corporationID=${requestScope.corporation.id}&requestPage=${requestPage}">移出社团</a>

									</td>
								</c:if>

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
									href="../memberManager/list?requestPage=${requestPage-1}&corporationID=${corporationID}">上一页</a></li>
							</c:if>
							<c:choose>
								<c:when test="${requestPage-5 > 0}">
									<c:forEach begin="${requestPage-5}" end="${requestPage-1}"
										var="i">
										<li><a
											href="../memberManager/list?requestPage=${i}&corporationID=${corporationID}">${i}</a></li>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<c:forEach begin="1" end="${requestPage-1}" var="i">
										<li><a
											href="../memberManager/list?requestPage=${i}&corporationID=${corporationID}">${i}</a></li>
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
											href="../memberManager/list?requestPage=${i}&corporationID=${corporationID}">${i}</a></li>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<c:forEach begin="${requestPage+1}" end="${count}" var="i">
										<li><a
											href="../memberManager/list?requestPage=${i}&corporationID=${corporationID}">${i}</a></li>
									</c:forEach>
								</c:otherwise>
							</c:choose>
							<c:if test="${requestPage != count}">
								<li><a
									href="../memberManager/list?requestPage=${requestPage+1}&corporationID=${corporationID}">下一页</a></li>
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