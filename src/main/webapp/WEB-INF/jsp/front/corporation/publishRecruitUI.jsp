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
				<h2 class="text-center" style = "color : green ; ">发布招募令</h2>
				
				
				<jsp:include page="commonInfo.jsp"></jsp:include>
				
				<hr/>
				
				
				<form id = "userForm" class="form-horizontal" method = "post" action = "../corporation/publishRecruit" role="form">
					<input type = "hidden" name = "corporationID" value = "${corporationID}" />
					<div class="form-group">
						<label for="title" class="col-sm-2 control-label">标题</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name = "title" id="title"  required minlength = "5"
								 maxlength = "20"
								placeholder="标题">
							<label style = "color : #999999 ; font-size : 12px ;">5~20个字符</label>
						</div> 
					</div>
		
					<div class="form-group">
						<label for="content" class="col-sm-2 control-label">内容</label>
						<div class="col-sm-10">
							<textarea rows = "10" class="form-control" placeholder="请输入内容" name = "content" 
							id="content" required maxlength = "1000" minlength = "50"></textarea>
							<label style = "color : #999999 ; font-size : 12px ;">50~1000个字符</label>
						</div>
					</div>
					<div class="form-group">
						<label for="beginTime" class="col-sm-2 control-label">开始时间</label>
						<div class="col-sm-10">
							<input class="form-control dataTimePicker" size="16" id = "beginTime" 
							name = "beginTime" type="text" value="" data-date-format="yyyy-mm-dd" required
							
							readonly>
							 
						</div>
					</div>
					
					<div class="form-group">
						<label for="endTime" class="col-sm-2 control-label">结束时间</label>
						<div class="col-sm-10">
							<input class="form-control dataTimePicker" size="16" id = "endTime" 
							name = "endTime" type="text" value="" data-date-format="yyyy-mm-dd" required
							
							readonly>
						</div>
					</div>
					
					<div class="form-group">
						<label for="amount" class="col-sm-2 control-label">招募人数</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" name = "amount" id="amount"  required
								placeholder="请输入招募人数">
						</div>
					</div>
					
					<div class="form-group">
						<label for="cost" class="col-sm-2 control-label">入会费用</label>
						<div class="col-sm-10">
							<select name = "cost" id = "cost" class = "form-control" >
							  <option value = "20">20</option>
							  <option value = "30">30</option>
							  <option value = "50">50</option>
							  <option value = "70">70</option>
							  <option value = "100">100</option>
							  <option value = "150">150</option>
							  <option value = "200">200</option>
							  <option value = "300">300</option>
							  <option value = "400">400</option>
							  <option value = "500">500</option>
							   <option value = "600">600</option>
							    <option value = "700">700</option>
							     <option value = "800">800</option>
							      <option value = "900">900</option>
							  <option value = "1000">1000</option>
							</select>
						</div>
					</div>
					
					<div class="form-group">
						<div class=" text-center">
							<button type="submit" class="btn btn-primary">提交</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="btn btn-primary" onclick = "history.go(-1)">返回</button>
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
		//时间选择器
		$('#beginTime').datetimepicker({
		        language:  'zh-CN',
		        format: 'yyyy-mm-dd' ,
		        startDate: new Date() , 
		        weekStart: 1,
		        todayBtn:  1,
				autoclose: 1,
				todayHighlight: 1,
				startView: 2,
				minView: 2,
				forceParse: 0
		    });
		$('#endTime').datetimepicker({
	        language:  'zh-CN',
	        format: 'yyyy-mm-dd' ,
	        startDate: new Date() , 
	        weekStart: 1,
	        todayBtn:  1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 2,
			minView: 2,
			forceParse: 0
	    });
		
		// 自定义方法， 校验人数，正能输入正整数
		jQuery.validator.addMethod("positiveInteger", function(value, element) {
		    var name = /^[1-9]\d*$/ ;   
		    return this.optional(element) ||  name.test(value);  
		}, "只能输入正整数");  

		//校验输入
		$("#userForm").validate({
			submitHandler:function(form){
				//提交表单
				form.submit() ;
	        },
	        focusCleanup : true ,
	        rules:{
	        	amount : {
	        		positiveInteger : true
	        	}
	        } 
	       
	    });
		
	});
</script>
</html>