<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false"%>
<div class="row" style="padding: 20px;">
	<input type="hidden" id="corporationID" name="corporationID"
		value="${requestScope.corporation.id }" /><span
		class="glyphicon glyphicon-list">类型:</span> <a
		href="../search/search?corporationTypeID=${corporation.typeID }">${corporation.corporationTypeName }
	</a>&nbsp;&nbsp;&nbsp;&nbsp; <span class="glyphicon glyphicon-time">注册时间:
		<fmt:formatDate value="${requestScope.corporation.registerTime }"
			pattern="yyyy-MM-dd" />
	</span>&nbsp;&nbsp;&nbsp;&nbsp; <span class="glyphicon glyphicon-signal">当前排名:${requestScope.corporation.ranking }</span>&nbsp;&nbsp;&nbsp;&nbsp;
	<a 
		href="../memberManager/list?corporationID=${requestScope.corporation.id }"><span
		class="glyphicon glyphicon-user">成员:${requestScope.corporation.memberAmount}</span></a>&nbsp;&nbsp;&nbsp;&nbsp;
	<a
		href="../apply/applyJoinCorporationUI?corporationID=${requestScope.corporation.id }"
		class="glyphicon glyphicon-plus">加入我们</a>&nbsp;&nbsp;&nbsp;&nbsp; 
	<c:if 
		test="${sessionScope.user != null && sessionScope.user.id == requestScope.corporation.presidentID}">
		<a
			href="../corporation/publishRecruitUI?corporationID=${requestScope.corporation.id }"
			class="glyphicon glyphicon-pencil">发布招募令</a>&nbsp;&nbsp;&nbsp;&nbsp;
							<a
			href="../corporation/updateCorporationInfoUI?corporationID=${requestScope.corporation.id }"
			class="glyphicon glyphicon-pencil">修改社团信息</a>&nbsp;&nbsp;&nbsp;&nbsp;
							<a id="logoutCorporation"
			href="../corporation/logoutCorporation?corporationID=${requestScope.corporation.id }"
			class="glyphicon glyphicon-pencil"
			onclick="return confirm('确定要注销该社团吗，注销后将无法恢复该社团相关信息')">注销</a>&nbsp;&nbsp;&nbsp;&nbsp;

					<a
			href="../memberManager/memberPositionList?corporationID=${requestScope.corporation.id }"
			class="glyphicon glyphicon-pencil">职位设置</a>&nbsp;&nbsp;&nbsp;&nbsp;
					<a
			href="../apply/corporationJoinApplyList?corporationID=${requestScope.corporation.id }"
			class="glyphicon glyphicon-pencil">处理申请</a>
	</c:if>




</div>