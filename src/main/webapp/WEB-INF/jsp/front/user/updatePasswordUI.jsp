<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="cache-control" content="no-cache"> 
<meta http-equiv="expires" content="0"> 
<jsp:include page="../../common/common.jsp"></jsp:include>
</head>
<style>  
<!--  
body
{
	color : #555555 ; 
}
/*jquery validate 前端验证提示信息的字体颜色.*/  
label.error
{
	padding-left: 0px;
	color: #CC0000;
	font-size : 16px ;
}

-->  
</style>  
<body style="padding-top: 50px;">

	<jsp:include page="../../common/top.jsp"></jsp:include>

	<%--图片轮播 --%>
	<jsp:include page="../../common/imageCirculation.jsp"></jsp:include>

	<div class="container" style="margin-top: 5px;">
		 
		<div class="row">
			<div class="col-md-12" 
				style="background-color: white; margin-top: 10px;">
				<h2 class="text-center" style = "color : green ; ">修改密码</h2>
				<form method = "post" id = "userForm" class="form-horizontal" action = "updatePassword" role="form">
					<div class="form-group">
						<label for="originPassword" class="col-sm-2 control-label">原密码</label>
						<div class="col-sm-10">
							<input type="password" class="form-control" name = "originPassword" id="originPassword"  required minlength = "6" maxlength = "16"
								placeholder="请输入原密码"><label id = "originPasswordErrorMessage" style = "color : red ; font-size : 16px ;"></label>
							<label style = "color : #999999 ; font-size : 12px ;">6~16个字符</label>
						</div> 
					</div>
					
					<div class="form-group">
						<label for="newPassword" class="col-sm-2 control-label">新密码</label>
						<div class="col-sm-10">
							<input type="password" class="form-control" name = "newPassword" id="newPassword"  required minlength = "6" maxlength = "16"
								placeholder="请输入新密码">
							<label style = "color : #999999 ; font-size : 12px ;">6~16个字符</label>
						</div> 
					</div>
					
					<div class="form-group">
						<label for="twiceNewPassword" class="col-sm-2 control-label">确认新密码</label>
						<div class="col-sm-10">
							<input type="password" class="form-control" name = "twiceNewPassword" id="twiceNewPassword"  required minlength = "6" maxlength = "16" equalTo="#newPassword"
								placeholder="请输入新新密码">
							<label style = "color : #999999 ; font-size : 12px ;">6~16个字符</label>
						</div> 
					</div>
					
					<div class="form-group">
						<div class=" text-center">
							<button type="submit" class="btn btn-primary">提交</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="btn btn-primary" onclick = "javascript:history.go(-1)">返回</button>
						</div>
					</div>

				</form>

			</div>
			
		</div>
	</div>
	<jsp:include page="../../common/bottom.jsp"></jsp:include>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		//图片轮播
		$('.carousel').carousel({
			interval : 2000
		});

		//校验输入
		$("#userForm").validate({
			submitHandler:function(form){
				//异步提交表单
				var originPassword = $("#originPassword").val() ; 
				var newPassword = $("#newPassword").val() ; 
				var url = "../user/updatePassword" ; 
				$.ajax({
					url:url,
					type:"post",
					dataType:"json",	
					data:{"originPassword":originPassword,"newPassword":newPassword},				
					error:function(result)
					{
						 
					},
					success:function(result)
					{
						switch(result.responseCode)
						{
						case '1' : location.href = "../home/index" ; break ; 
						case '0' : $("#originPasswordErrorMessage").text("原密码错误") ; 
							$("#originPassword").focus(function(){
								$("#originPasswordErrorMessage").text("") ; 
							}) ;
						break ; 
						case '2' : alert("服务器异常") ; break ; 
						}
						
					}
				}) ; 
	        },
	        focusCleanup : true 
	    });
		
	});
</script>
</html>