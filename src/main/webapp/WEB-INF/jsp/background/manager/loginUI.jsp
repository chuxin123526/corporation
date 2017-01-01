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

	<div class="row avgrund-popup" id = "mainDiv" style="margin-top: 50px;">
		<div class="col-md-4"></div>
		<div class="col-md-3"></div>
		<div class="container col-md-4" style="background-color: white;opacity:0.9;border:0px solid;
border-radius:13px;box-shadow: 5px 5px 5px #D0DFEF;">
			<h2 class="text-center" style  = "font-family:monaco;color : green ;">&nbsp;社团网申系统后台管理</h2>
			<form method = "post" action = "../manager/login" id = "userForm" class="form-horizontal" role="form">
				<div class="form-group">
					<label for="managerName" class="col-md-3 control-label">用户名</label>
					<div class="col-md-8">
						<input type="text" class="form-control" value = "administrator" name = "managerName" id="managerName" required minlength = "6" maxlength = "16"
							placeholder="请输入用户名">
							<label style = "color : #999999 ; font-size : 12px ;">6~16个字符，可使用字母、数字、下划线，需以字母开头</label>
					</div>
				</div>
				<div class="form-group">
					<label for="password" class="col-md-3 control-label">密码</label>
					<div class="col-md-8">
						<input type="password" name = "password" value = "administrator" class="form-control" id="password"  required minlength = "6" maxlength = "16"
							placeholder="请输入密码">
							<label style = "color : #999999 ; font-size : 12px ;">6~16个字符，区分大小写</label>
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
						<button type="submit" class="btn btn-primary  ">登录</button>
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
	<br />
	<br />
	<br /> 
	<jsp:include page="../../common/bottom.jsp"></jsp:include>
</body>
<script type="text/javascript">
$(document).ready(function() {
	//validate自定义方法:校验用户名 是否含有中文
	jQuery.validator.addMethod("checkChinese", function(value, element, param) {
	    var length = value.length;
	    for(var i = 0; i < value.length; i++){
	        if(value.charCodeAt(i) > 127){
	            length++;
	        }
	    }
	  return this.optional(element) || ( length >= param[0] && length <= param[1] );   
	}, "包含中文字符");
	
	//validate自定义方法:字母开头
	jQuery.validator.addMethod("checkBeginEnglish", function(value, element, param) {
		
		var re = /^[a-zA-Z]/;
		
	  return this.optional(element) || ( re.test(value));   
	}, "需以字母开头");
	
	//validate自定义方法:checkCodeType 使用字母、数字、下划线
	jQuery.validator.addMethod("checkCodeType", function(value, element, param) {
		
		var re = /^[a-zA-Z_0-9]+$/;
		
	  return this.optional(element) || ( re.test(value));   
	}, "使用字母、数字、下划线，需以字母开头");
	
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
            				//验证用户名和密码
	            			$.ajax({ 
	                        	url: "../manager/checkByManagerNameAndPassword",
	                            type: "post",
	                            dataType: "json",
	                            data : {"managerName":$("#managerName").val(),"password":$("#password").val()},
	                        	error : function(result)
	                        	{
	                        		
	                        	} , 
	                        	success : function(result)
	                        	{
	                        		if(result.responseCode == 1)
	                        			{
	                        				//用户名和密码正确
	                        				//提交表单
	                        				form.submit() ;
	                        			}
	                        		else
	                        			if(result.responseCode == 0)
	                        				{
	                        					alert("用户名或密码错误") ;
	                        				}
	                        			else
	                        				if(result.responseCode == 2)
	                        					{
	                        						alert("系统异常") ;
	                        					}
	                        	}
	                        }) ; 
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
        focusCleanup : true ,
        rules:{
        	managerName : {
        		checkChinese : [6,16] ,
        		checkCodeType : true ,
        		checkBeginEnglish : true ,
        		
        	} 
        } 
       
	//,
        //success: function(label) {
        	//label.addClass("checkSuccess").text("&nbsp;") ;
        	//label.html("").addClass("checkSuccess");
        //}
       
    });
});

</script>

</html>