<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<script type="text/javascript"
		src="<%=request.getContextPath()%>/js/jquery.validate.min.js"></script>
		<script type="text/javascript"
		src="<%=request.getContextPath()%>/js/messages_zh.min.js"></script>

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

			<h1 class="page-title">添加角色</h1>
		</div>

		<hr />


		<div class="container-fluid">
			<div class="row-fluid">


				<div class="well">


					<form role="roleForm" id="roleForm" method="post" action="add">
						<div class="form-group">
							<label for="roleName">名称</label> <input type="text"
								class="form-control" id="exampleInputEmail1" name = "roleName"
								placeholder="请输入角色名称" required maxlength="20">
						</div>

						<button type="submit" class="btn btn-default">提交</button>
					</form>

				</div>



				<footer>
					<hr>


				</footer>

			</div>
		</div>
	</div>

	<script type="text/javascript">
	
	$(document).ready(function(){
		$("#roleForm").validate({
			submitHandler:function(form) {
				form.submit() ;	
			}
		}) ; 
	}) ;
	
	</script>

</body>
</html>


