<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工作中心维护管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#name").focus();
			$("#inputForm").validate();
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li ><a href="#">工作中心时界配置</a></li>
	</ul><br/>
	
	<form:form id="inputForm" modelAttribute="gzzxwh" action="${ctx}/gzzx/gzzxwh/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<%-- <tags:message content="${message}"/> --%>
		<div class="control-group">
			<label class="control-label" for="weight">需求时界：</label>
			<div class="controls">
				<form:select path="xqsjsjdw" class="input-small">
                    <form:option value="" label="请选择"/>
                    <form:option value="0" label="是"/>
                    <form:option value="1" label="否"/>
                </form:select>
				&nbsp;&nbsp;&nbsp;&nbsp;需求时界值：
				<form:input path="xysjsjz" htmlEscape="false" maxlength="200" class="required input-mini"/>			
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="">说明:</label>
			<div class="controls">
				<form:textarea path="xqsjsm" htmlEscape="false" rows="4" maxlength="200" class="input-xlarge"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="weight">计划时界：</label>
			<div class="controls">
				<form:select path="jhsjsjdw" class="input-small">
                    <form:option value="" label="请选择"/>
                    <form:option value="0" label="是"/>
                    <form:option value="1" label="否"/>
                </form:select>
				&nbsp;&nbsp;&nbsp;&nbsp;计划时界值：
				<form:input path="jhsjsjz" htmlEscape="false" maxlength="200" class="required input-mini"/>			
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="">说明:</label>
			<div class="controls">
				<form:textarea path="jhsjsm" htmlEscape="false" rows="4" maxlength="200" class="input-xlarge"/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="gzzx:gzzxwh:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
