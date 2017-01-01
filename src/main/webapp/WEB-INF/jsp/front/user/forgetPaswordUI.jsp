<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<jsp:include page="../../common/common.jsp"></jsp:include>
</head>
<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="cache-control" content="no-cache"> 
<meta http-equiv="expires" content="0">   
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
.checkSuccess
{
	background-image: url('../../images/checkSuccess.png') ;
	width : 15px ;
	height : 16px ;
	margin-top: 5px ;
}
-->  
</style>  
<body style="padding-top: 50px; 
 background-image: url('<%=request.getContextPath()%>/images/registerBackground.jpg') ;
background-repeat:no-repeat;">

	<jsp:include page="../../common/top.jsp"></jsp:include>
	<div class="row" style="margin-top: 50px;">
		<div class="col-md-4"></div>
		<div class="col-md-3"></div>
		<div class="container col-md-4" style="background-color: white;opacity:0.9;border:0px solid;
border-radius:13px;box-shadow: 5px 5px 5px #D0DFEF;">
			<h2 class="text-center" style  = "font-family:monaco;color : green ;">忘记密码</h2>
			<form method = "post" action = "../user/forgetPassword" id = "userForm" class="form-horizontal" role="form">
				
				<div class="form-group">
					<label for="email" class="col-md-3 control-label">邮箱</label>
					<div class="col-md-8">
						<input type="email" name = "email" class="form-control" id="email" required 
							placeholder="请输入邮箱地址">
					</div>
				</div>
				<div class="form-group">
					<label for="securityCode" class="col-md-3 control-label">验证码</label>
					<div class="col-md-8">
						<input type="text" name = "securityCode" class="form-control" id="securityCode" required 
							placeholder="请输入验证码"><label id = "securityCodeMessage" style = "color: #CC0000;"> </label>
						<div class = "text-center" style = "margin-top : 10px ;">
							<img id="codeImage" src="<%=request.getContextPath()%>/manager/util/securityCode" />
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10 text-center">
						<button type="submit" class="btn btn-primary  ">提交</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="btn btn-primary ">清除</button>
					</div>
				</div>

			</form>

		</div>
		<div class="col-md-1"></div>
	</div>
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<jsp:include page="../../common/bottom.jsp"></jsp:include>
</body>
<script type="text/javascript">
$(document).ready(function() {
	
	//刷新验证码
	$("#codeImage").click(function() {
		var i = Math.random();
		$("#codeImage").attr("src", "../util/securityCode?i=" + i);
	});
	
	$("#userForm").validate({
		submitHandler:function(form){
	
            //检查验证码
            $.ajax({ 
            	url: "../util/checkSecurityCode",
                type: "post",
                dataType: "json",
                data : {"securityCode":$("#securityCode").val()},
            	error : function(result)
            	{
            		
            	} , 
            	success : function(result)
            	{
            		if(result.responseCode == 1)
            			{
            				//提交表单
            				form.submit() ;
            			}
            		else
            			{
            				//提示验证码错误
            				$("#securityCodeMessage").text("验证码错误") ;
            				//刷新验证码
            				var i = Math.random();
							$("#codeImage").attr("src", "../util/securityCode?i=" + i);
							//验证码输入框获取焦点时清空提示信息
            				$("#securityCode").focus(function(){
            					$("#securityCodeMessage").text("") ;
            				}) ;
            			}
            	}
            }) ; 
			//form.submit();
        },
        focusCleanup : true
      
	//,
        //success: function(label) {
        	//label.addClass("checkSuccess").text("&nbsp;") ;
        	//label.html("").addClass("checkSuccess");
        //}
       
    });
});

</script>

</html>