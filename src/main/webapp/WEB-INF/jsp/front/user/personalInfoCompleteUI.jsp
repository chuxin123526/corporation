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
<script type="text/javascript" src = "<%=request.getContextPath()%>/js/jquery.city.select.min.js"></script>
<script type="text/javascript" src = "<%=request.getContextPath()%>/js/data.js"></script>
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
				<h2 class="text-center" style = "color : green ; ">个人信息完善</h2>
				<form id = "userForm" class="form-horizontal" action = "personalInfoComplete" role="form">
					
					<div class="form-group">
						<label for="userName" class="col-sm-2 control-label">用户名</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name = "userName" id="userName" value = "${requestScope.user.userName }" readonly>
						</div> 
					</div>
				
					<div class="form-group">
						<label for="realName" class="col-sm-2 control-label">真实姓名</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name = "realName" id="realName" value = "${requestScope.user.realName }" required 
								minlength = "2" maxlength = "20"
								placeholder="请输入真实姓名">
							<label style = "color : #999999 ; font-size : 12px ;">2~20个字符中文或字母</label>
						</div> 
					</div>
					<div class="form-group">
						<label for="sex" class="col-sm-2 control-label">性别</label>
						<div class="col-sm-10">
						<c:if test="${requestScope.user.sex == '男' || requestScope.user.sex == null}">
						<div class="radio">
								<label> <input type="radio" name="sex"
									id="optionsRadios1" value="男" checked>男
								</label>
							</div>
							<div class="radio">
								<label> <input type="radio" name="sex"
									id="optionsRadios2" value="女"> 女
								</label>
							</div>
						</c:if>
						<c:if test="${requestScope.user.sex == '女' }">
						<div class="radio">
								<label> <input type="radio" name="sex"
									id="optionsRadios1" value="男">男
								</label>
							</div>
							<div class="radio">
								<label> <input type="radio" name="sex"
									id="optionsRadios2" value="女" checked> 女
								</label>
							</div>
						</c:if>
							
						</div>
					</div>

					<div class="form-group">
						<label for="email" class="col-sm-2 control-label">邮箱</label>
						<div class="col-sm-10">
							<input type="email" class="form-control" name = "email" id="email" value = "${requestScope.user.email }" disabled
								placeholder="请输入邮箱">
						</div>
					</div>
					<div class="form-group">
						<label for="phoneNumber" class="col-sm-2 control-label">手机号码</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name = "phoneNumber" id="phoneNumber" value = "${requestScope.user.phoneNumber }"
								required digits="true"
								placeholder="请输入手机号码">
						</div>
					</div>
					<div class="form-group">
						<input type = "hidden" value = "${requestScope.user.college }" id = "hiddenInputCollege"  />
						<label for="college" class="col-sm-2 control-label">学院</label>
						<div class="col-sm-10 ">
							<select id = "college" name = "college" class = "form-control" >
							
							</select>
						</div>
					</div>
					
					<div class="form-group">
						<input type = "hidden" value = "${requestScope.user.major }" id = "hiddenInputMajor"  />
						<label for="major" class="col-sm-2 control-label">专业</label>
						<div class="col-sm-10 ">
							<select name = "major" id = "major" class = "form-control" >
							 
							</select>
						</div>
					</div>
					
					<div class="form-group">
						<label for="period" class="col-sm-2 control-label">年级</label>
						<div class="col-sm-10 ">
							<select name = "period" id = "period" class = "form-control" >
							  <option value = "2008">2008 级</option>
							  <option value = "2009">2009 级</option>
							  <option value = "2010">2010 级</option>
							  <option value = "2011">2011 级</option>
							  <option value = "2012">2012 级</option>
							  <option selected value = "2013">2013 级</option>
							  <option value = "2014">2014 级</option>
							  <option value = "2015">2015 级</option>
							  <option value = "2016">2016 级</option>
							</select>
						</div>
					</div>

					<div class="form-group">
						<label for="introduction" class="col-sm-2 control-label">简介</label>
						<div class="col-sm-10">
							<textarea rows="5"  class="form-control"  maxlength = "200" name = "introduction" id="introduction" required>${requestScope.user.introduction}</textarea>
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
		
		
		
		//获取默认学院，专业
		var collegeID = 0 ; 
		var majorID = 0 ;
		var college = $("#hiddenInputCollege").val() ; 
		var major = $("#hiddenInputMajor").val() ;
		for(var i = 0 ; i < data.length ; i++)
			{
				if(data[i].name == college)
					{
						collegeID = data[i].id ;
						for(var j = 0 ; j < data[i].cities.length ; j++)
							{
								if(data[i].cities[j].name == major)
									{
										majorID = data[i].cities[j].id ; 
									}
							}
					}
			}
		
		//两级联动 学院->专业
		$('#college, #major').citylist({
	        data    : data,
	        id      : 'id',
	        children: 'cities',
	        name    : 'name',
	        metaTag : 'name',
	        selected : [collegeID,majorID] //设置默认学院专业
	    });
		
		// 自定义方法，手机号码验证  
		jQuery.validator.addMethod("isMobile", function(value, element) {  
		    var length = value.length;  
		    var mobile = /^(13[0-9]{9})|(18[0-9]{9})|(14[0-9]{9})|(15[0-9]{9})$/;  
		    return this.optional(element) || (length == 11 && mobile.test(value));  
		}, "请正确填写您的手机号码");  
		

		// 自定义方法，只能包含中文或字母
		jQuery.validator.addMethod("realName", function(value, element) {
		    var name = /^[\u4e00-\u9fa5a-zA-Z]+$/ ;   
		    return this.optional(element) ||  name.test(value);  
		}, "只能包含中文或字母");  

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
	        	} , 
	       		realName : {
	       			realName : true
	       		}
	        } 
	       
	    });
		
	});
</script>
</html>