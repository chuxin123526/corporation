<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<div class="navbar">
	<div class="navbar-inner">
		<ul class="nav pull-right">

			<c:choose>
				<c:when test="${sessionScope.manager != null }">


					<li id="fat-menu" class="dropdown"><a href="#" role="button"
						class="dropdown-toggle" data-toggle="dropdown"> <i
							class="icon-user"></i>${sessionScope.manager.name}<i
							class="icon-caret-down"></i>
					</a>

						<ul class="dropdown-menu">
							<li><a tabindex="-1"
								href="../manager/updatePasswordUI?managerID=${sessionScope.manager.id }">修改密码</a></li>
							<li><a tabindex="-1" href="../manager/exit">退出</a></li>
						</ul></li>

				</c:when>
				<c:otherwise>
						<a href = "../manager/loginUI" class = "btn">登录</a>
				</c:otherwise>
			</c:choose>


		</ul>
		<a class="brand" href="../backgroundHome/index"><span
			class="first">社团网申系统后台管理</span> </a>
	</div>
</div>