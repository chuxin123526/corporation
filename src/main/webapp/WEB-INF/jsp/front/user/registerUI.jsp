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
			<h2 class="text-center" style  = "font-family:monaco;color : green ;">社团网申系统</h2>
			<form method = "post" action = "../user/register" id = "userForm" class="form-horizontal" role="form">
				<div class="form-group">
					<label for="userName" class="col-md-3 control-label">用户名</label>
					<div class="col-md-8">
						<input type="text" class="form-control" name = "userName" id="userName" required minlength = "6" maxlength = "16"
							placeholder="请输入用户名">
							<label style = "color : #999999 ; font-size : 12px ;">6~16个字符，可使用字母、数字、下划线，需以字母开头</label>
					</div>
				</div>
				<div class="form-group">
					<label for="password" class="col-md-3 control-label">密码</label>
					<div class="col-md-8">
						<input type="password" name = "password" class="form-control" id="password"  required minlength = "6" maxlength = "16"
							placeholder="请输入密码">
							<label style = "color : #999999 ; font-size : 12px ;">6~16个字符，区分大小写</label>
					</div>
				</div>
				<div class="form-group">
					<label for="twicePassword" class="col-md-3 control-label">确认密码</label>
					<div class="col-md-8">
						<input type="password" name = "twicePassword" class="form-control" id="twicePassword" equalTo="#password"  required minlength = "6" maxlength = "16"
							placeholder="请输入密码">
					</div>
				</div>
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
						<button type="submit" class="btn btn-primary  ">注册</button>
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
	//validate自定义方法:校验用户名 
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
	
	//validate自定义方法:checkCodeType
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
        focusCleanup : true ,
        rules:{
        	userName : {
        		checkChinese : [6,16] ,
        		checkCodeType : true ,
        		checkBeginEnglish : true ,
        		remote: {
        		    url: "../user/checkUserNameAvailable",     //后台处理程序
        		    type: "post",               //数据发送方式
        		    dataType: "json",           //接受数据格式   
        		    data: {                     //要传递的数据
        		        userName: function() {
        		            return $("#userName").val();
        		        }
        		    }
        		}
        	} , 
        	email : {
        		remote : {
        			url: "../user/checkEmailAvailable",     //后台处理程序
         		    type: "post",               //数据发送方式
         		    dataType: "json",           //接受数据格式   
         		    data: {                     //要传递的数据
         		        userName: function() {
         		            return $("#email").val();
         		        }
         		    }
        		}
        	}
        } , 
        messages : {
        	userName : {
        		remote : "该用户名已被注册"
        	} , 
        	email : {
        		remote : "该邮箱已被注册"
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