<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
				<h2 class="text-center" style = "color : green ; ">修改社团信息</h2>
				
				
				<jsp:include page="commonInfo.jsp"></jsp:include>
				
				<hr/>
				
				<form enctype="multipart/form-data" id = "userForm" class="form-horizontal" method = "post" action = "../corporation/updateCorporationInfo" role="form">
					<input type = "hidden" name = "corporationID" value = "${requestScope.corporation.id}" />
					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">社团名称</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name = "name" id="name"  required minlength = "3" value = "${requestScope.corporation.name }"
								 maxlength = "20"
								placeholder="请输入社团名称">
							<label style = "color : #999999 ; font-size : 12px ;">3~20个字符</label>
						</div> 
					</div>
		
					
					<div class="form-group">
						<label for="address" class="col-sm-2 control-label">地址</label> 
						<div class="col-sm-10">
							<input type = "text" class="form-control" placeholder="请输入地址" name = "address" maxlength = "200"  value = "${requestScope.corporation.address }"
							id="address" required  />
							<label style = "color : #999999 ; font-size : 12px ;">少于200个字符</label>
						</div>
					</div>
					
					<div class="form-group">
						<label for="phoneNumber" class="col-sm-2 control-label">联系号码</label>
						<div class="col-sm-10">
							<input type = "text" class="form-control" placeholder="请输入手机号码" name = "phoneNumber"  value = "${requestScope.corporation.phoneNumber }"
							id="phoneNumber" required  />
						</div>
					</div>
					
					<div class="form-group">
						<label for="image" class="col-sm-2 control-label">上传图片</label>
						<div class="col-sm-10">
							<input type = "file" class="form-control" placeholder="选择文件" name = "image" 
							id="image" />
						</div>
					</div>
				
					<div class="form-group">
						<label for="inroduction" class="col-sm-2 control-label">简介</label>
						<div class="col-sm-10">
							<textarea rows = "10" class="form-control" placeholder="请输入内容" name = "inroduction" 
							id="inroduction" required maxlength = "1000" minlength = "50">${requestScope.corporation.introduction }</textarea>
							<label style = "color : #999999 ; font-size : 12px ;">50~1000个字符</label>
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
		
		// 自定义方法，手机号码验证  
		jQuery.validator.addMethod("isMobile", function(value, element) {  
		    var length = value.length;  
		    var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(15[0-9]{9})$/;  
		    return this.optional(element) || (length == 11 && mobile.test(value));  
		}, "请正确填写您的手机号码");  
		
		//校验输入
		$("#userForm").validate({
			submitHandler:function(form){
				//提交表单
				form.submit() ;
	        },
	        focusCleanup : true ,
	        rules:{
	        	phoneNumber : {
	        		isMobile : true
	        	}
	        } 
	       
	    });
		
	});
</script>
</html>