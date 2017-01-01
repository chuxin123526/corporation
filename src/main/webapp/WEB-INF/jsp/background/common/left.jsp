<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<div class="sidebar-nav">
		<a href="#dashboard-menu" class="nav-header" data-toggle="collapse"><i
			class="icon-dashboard"></i>系统管理</a>
		<ul id="dashboard-menu" class="nav nav-list collapse in">
		<c:forEach items = "${topPrivilegeMap }" var = "map">
			<li><a href="${map.value }">${map.key }</a></li>
		</c:forEach>
		</ul>
	</div>