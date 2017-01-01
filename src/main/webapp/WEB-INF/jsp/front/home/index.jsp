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
<body style="padding-top: 50px;">

	<jsp:include page="../../common/top.jsp"></jsp:include>

	<%--图片轮播 --%>
	<jsp:include page="../../common/imageCirculation.jsp"></jsp:include>

	<div class="container" style="margin-top: 5px;">
		<div class="row">
			<div class="col-md-9">
				<c:forEach items="${requestScope.corporationList }"
					var="corporation">
				
					<a href="../corporation/get?corporationID=${corporation.id}">
						<div class="row" style="margin-top: 10px; background-color: white" ;>
							<div class="col-md-4" style="padding-left: 0px;">
								<img class="img-responsive" style = "border-radius:10px;"
									src="../corporation/getImage?imageUrl=${corporation.imageUrl}" />
							</div>
							<div class="col-md-8">
								<h4>${corporation.name }</h4>
								<p style = "word-wrap: break-word;">${corporation.introduction }</p>
							</div>
						</div>
					</a>

				</c:forEach>




			</div>
			<div class="col-md-3" style="margin-top: 10px">
				<div class="row" style="padding-left: 10px;">
					<h2 style="margin: 0px; background-color: white;padding-bottom : 15px ;" 
						class="text-center">招募令</h2>
					<ul
						style="padding: 10px; list-style: none; background-color: white;">
						<c:forEach items="${recruitInfoList }" var="recruitInfo">
							<li class = "text-left"><a href="../corporation/recruitInfo?recruitInfoID=${recruitInfo.id }">${recruitInfo.title }</a></li> 
						</c:forEach>
					</ul>
				
				</div>

				<div class="row" style="padding-left: 10px; margin-top: 5px;">
					<h2 style="margin: 0px; background-color: white;"
						class="text-center">关于我们</h2>
					<p
						style="padding-top: 10px; background-color: white; font-size: 16px; padding-left: 5px;">
						社团网申系统主要为学生提供在线申请创建社团，申请加入社团，管理者可管理社团信息，成员。 <br /> <br /> <br />
					</p>
				</div>

				<div class="row" style="padding-left: 10px; margin-top: 5px;">
					<h2 style="margin: 0px; background-color: white;"
						class="text-center">联系我们</h2>
					<ul
						style="padding: 10px;padding-bottom : 25px ; list-style: none; background-color: white;">
						<li>地址 ：广东海洋大学</li>
			
						<li>email ：XXXXXXXX@gmail.com</li>
		
						<li>电话 ：00000000000</li>
			
						<li>传真：00000000000</li>
					</ul>
				</div>
			</div>
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