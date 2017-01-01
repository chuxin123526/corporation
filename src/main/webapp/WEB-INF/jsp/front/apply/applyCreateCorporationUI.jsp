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
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/bootstrap-datetimepicker.min.css" />
<script type="text/javascript" src = "<%=request.getContextPath()%>/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src = "<%=request.getContextPath()%>/js/bootstrap-datetimepicker.zh-CN.js"></script>
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
				<h2 class="text-center" style = "color : green ; ">申请创建社团</h2>
				<form id = "userForm" class="form-horizontal" method = "post"  role="form" action = "applyCreateCorporation">
					<input type = "hidden" id = "corporationID" name = "corporationID" value = "${requestScope.corporation.id}" />
					
					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">社团名称</label>
						<div class="col-sm-10">
							<input type = "text" minlength = "3" maxlength = "20" required class = "form-control" name = "name" id = "name" />
						
							<label style = "color : #999999 ; font-size : 12px ;">3~20字符</label>	
						</div>
					</div>
					<div class="form-group">
						<label for="corporationTypeID" class="col-sm-2 control-label">社团类型</label>
						<div class="col-sm-10">
							<select class = "form-control" name = "corporationTypeID" id = "corporationTypeID">
								<c:forEach items = "${corporationTypeList }" var = "corporationType">
									<option value = "${corporationType.id }">${corporationType.name }</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label for="address" class="col-sm-2 control-label">社团地址</label>
						<div class="col-sm-10">
							<input type = "text"  maxlength = "50" required class = "form-control" name = "address" id = "address" />
						
							<label style = "color : #999999 ; font-size : 12px ;">少于50字符</label>
						</div>
					</div>
					<div class="form-group">
						<label for="phoneNumber" class="col-sm-2 control-label">联系电话</label>
						<div class="col-sm-10">
							<input type = "text"  class = "form-control" required name = "phoneNumber" id = "phoneNumber" />
						
						</div>
					</div>
					<div class="form-group">
						<label for="email" class="col-sm-2 control-label">联系邮箱</label>
						<div class="col-sm-10">
							<input type = "email" required  class = "form-control" name = "email" id = "email" />
						</div>
					</div>
					<div class="form-group">
						<label for="introduction" class="col-sm-2 control-label">简介</label>
						<div class="col-sm-10">
							<textarea rows = "10" required maxlength="200"  required class = "form-control" name = "introduction" id = "introduction" ></textarea>
							<label style = "color : #999999 ; font-size : 12px ;">少于200字符</label>
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
		
		$("#userForm").validate({
			submitHandler:function(form){
				form.submit();
	        },
	        focusCleanup : true ,
	        rules:{
	        	phoneNumber : {
	        		isMobile :true 
	        	}
	        }
		}) ;
		
	});
</script>
</html>