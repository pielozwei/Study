<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工厂日历明细管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#name").focus();
			$("#inputForm").validate();
		});
	</script>
</head>
<body>
	
	<form:form id="inputForm" modelAttribute="calendarDetail" action="${ctx}/ip/calendarDetail/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<tags:message content="${message}"/>
		<div class="control-group">
			<label class="control-label" for="gcrlfa_id">日历方案名称:</label>
			<div class="controls">
				<form:select path="gcrlfa_id" class="input-large required"  disabled="true">
					<c:forEach var="faList" items="${faList}" varStatus="s">
                    	<option value="${faList.id}" <c:if test="${faList.id eq calendarDetail.gcrlfa_id}"> selected="selected"</c:if>>${faList.famc}</option>
                    </c:forEach>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="rq">日期:</label>
			<div class="controls">
				<input id="rq" name="hjrq" type="text" maxlength="20" class="Wdate" disabled="disabled"
					value="<fmt:formatDate value="${calendarDetail.rq}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="gzrlx">工作日类型:</label>
			<div class="controls">
				<form:select path="gzrlx" >
					<form:options items="${fns:getDictList('d_gzrlx')}" itemValue="value" itemLabel="label" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="sfsb">是否上班:</label>
			<div class="controls">
				<form:select path="sfsb">
					<form:options items="${fns:getDictList('yes_no')}" itemValue="value" itemLabel="label" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="ip:calendarDetail:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
