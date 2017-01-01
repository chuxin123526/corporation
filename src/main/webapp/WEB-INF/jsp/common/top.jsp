<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="cache-control" content="no-cache"> 
<meta http-equiv="expires" content="0"> 
<nav class="navbar navbar-default navbar-fixed-top " role="navigation">
	<div class="container">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="/corporation/manager/home/index">首页</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="../corporation/list">社团</a></li>
				<li><a href="../corporation/recruitInfoList">招募令</a></li> 
			</ul>

			<ul class="nav navbar-nav navbar-right">
				<li><form class="navbar-form navbar-left" role="search" method = "post" action = "../search/search">
						<div class="form-group">
							<div class="input-group">
								<input class="form-control" type="text" name = "keyword"
									placeholder="关键字" required>
							</div>
							<div class="input-group">
								<input class="form-control" type="submit" value="搜索">
							</div>
						</div>

					</form></li>
				<c:if test="${sessionScope.user != null}">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown">${sessionScope.user.userName }<span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
							<li><a href="../user/personalInfoCompleteUI">个人信息</a></li>
							<li><a href="../user/myCorporation">我的社团</a></li>
							<li><a href="../user/myJoinCorporation">我加入的社团</a></li>
							<li><a href="../apply/myApplyJoinCorporationList">我申请加入的社团</a></li>
							<li><a href="../apply/myApplyCreateCorporationList">我申请创建的社团</a></li>
							<li><a href="../user/updatePasswordUI">修改密码</a></li>
							<li><a href="../user/exit">退出</a></li>
						</ul></li>
				</c:if>
				<c:if test="${sessionScope.user == null}">
					<ul class="nav navbar-nav">
						<li><a href="/corporation/manager/user/loginUI">登录</a></li>
						<li><a href="/corporation/manager/user/registerUI">注册</a></li>
					</ul>

				</c:if>

			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid -->
</nav>