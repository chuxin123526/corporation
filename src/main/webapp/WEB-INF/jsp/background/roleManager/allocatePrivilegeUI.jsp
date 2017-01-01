<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<%@ page import="com.corporation.po.Privilege" %>
<%@ page import="com.corporation.po.Role_Privilege" %>
<!DOCTYPE html>

<html lang="en">
<head>
<meta charset="utf-8">
<title>Bootstrap Admin</title>
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<jsp:include page="../common/common.jsp"></jsp:include>

<!-- Demo page code -->

<style type="text/css">
#line-chart {
	height: 300px;
	width: 800px;
	margin: 0px auto;
	margin-top: 1em;
}

.brand {
	font-family: georgia, serif;
}

.brand .first {
	color: #ccc;
	font-style: italic;
}

.brand .second {
	color: #fff;
	font-weight: bold;
}
</style>

<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="lib/html5.js"></script>
    <![endif]-->

<!-- Le fav and touch icons -->
<link rel="shortcut icon" href="../assets/ico/favicon.ico">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="../assets/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="../assets/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="../assets/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="../assets/ico/apple-touch-icon-57-precomposed.png">
</head>

<!--[if lt IE 7 ]> <body class="ie ie6"> <![endif]-->
<!--[if IE 7 ]> <body class="ie ie7 "> <![endif]-->
<!--[if IE 8 ]> <body class="ie ie8 "> <![endif]-->
<!--[if IE 9 ]> <body class="ie ie9 "> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<body class="">
	<!--<![endif]-->

	<jsp:include page="../common/top.jsp"></jsp:include>
	<jsp:include page="../common/left.jsp"></jsp:include>



	<div class="content">

		<div class="header">

			<h1 class="page-title">正在给${requestScope.role.name }分配权限</h1>
		</div>

		<hr />


		<div class="container-fluid">
			<div class="row-fluid">
				<div class="well">
					<form id="managerForm" method="post" action="allocatePrivilege">
						<input type="hidden" name="roleID"
							value="${requestScope.role.id }" />
							<!-- 
						<c:forEach items="${topPrivilegeList }" var="privilege">
							<c:forEach items="${role_privilegeList }" var="role_privilege">

								<c:if test="${role_privilege.privilegeID == privilege.id }">
									<div class="form-group">
										<input type="checkbox" name="privilegeIDS" checked
											value="${privilege.id}" /> ${privilege.name}
									</div>
								</c:if>
							</c:forEach>
							<div class="form-group">
								<input type="checkbox" name="privilegeIDS"
									value="${privilege.id}" /> ${privilege.name}
							</div>
						</c:forEach>
						 -->
						<% 
							List<Privilege> topPrivilegelist =(List) request.getAttribute("topPrivilegeList") ; 
							List<Role_Privilege> role_privilegeList = (List)request.getAttribute("role_privilegeList") ; 
							for(Privilege privilege : topPrivilegelist)
							{int flag = 0 ; 
								for(Role_Privilege role_Privilege : role_privilegeList)
								{
									
									if(privilege.getId() == role_Privilege.getPrivilegeID())
									{
										flag = 1 ; 
										
								%>
								<div class="form-group">
										<input type="checkbox" name="privilegeIDS" checked
											value="<%=privilege.getId() %>" /><%=privilege.getName() %> 
									</div>
								
								
								<%
								break ; 
									}
									
								}
								if(flag == 0)
								{
									%>
									
									<div class="form-group">
										<input type="checkbox" name="privilegeIDS" 
											value="<%=privilege.getId() %>" /><%=privilege.getName() %> 
									</div>
									<%
								}
							}
						%>
						
						

						<br />

						<button type="submit" class="btn btn-default">提交</button>
					</form>

				</div>



				<footer>
					<hr>


				</footer>

			</div>
		</div>
	</div>


</body>
</html>


