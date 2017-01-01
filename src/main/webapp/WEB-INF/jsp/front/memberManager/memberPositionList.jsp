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
					<h2 class="text-center">成员职位</h2>
				</div>

				<jsp:include page="../corporation/commonInfo.jsp"></jsp:include>
				<hr />
				<!-- 模态框（Modal） -->
				<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<form action="../memberManager/addMemberPosition" method="post">
							<input type="hidden" name="corporationID"
								value="${requestScope.corporation.id }" />
							<div class="modal-content">
								<div class="modal-header"> 
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">&times;</button>
									<h4 class="modal-title" id="myModalLabel">请输入职位名称</h4>
								</div>
								<div class="modal-body">
									<input name="memberPositionName" type="text" size="30" required/>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">关闭</button>
									<button type="submit" class="btn btn-primary">提交更改</button>
								</div>
							</div>
						</form>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal -->
				</div>

				<%--职位列表 --%>
				<table class="table table-striped">
					<caption>
						<a href="#" class="glyphicon glyphicon-plus" data-toggle="modal"
							data-target="#myModal">添加职位</a>
					</caption>

					<thead>
						<tr>
							<th>职位名称</th>
							<th class="text-center">操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${memberPositionList}" var="memberPosition">
							<tr>
								<td>${memberPosition.name}</td>
								<td class="text-center"><a onclick = "return confirm('确定要删除该职位吗')"
									href="../memberManager/deleteMemberPosition?memberPositionID=${memberPosition.id }&corporationID=${requestScope.corporation.id}">删除</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>


			</div>


		</div>


	</div>

	<br />
	<jsp:include page="../../common/bottom.jsp"></jsp:include>
</body>
<script type="text/javascript">
	
</script>
</html>