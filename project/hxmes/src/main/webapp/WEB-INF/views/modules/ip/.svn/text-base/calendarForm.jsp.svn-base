<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>工厂日历方案管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		$("#name").focus();
		$("#inputForm").validate();
	});
</script>
</head>
<body>
<ul class="nav nav-tabs">

		<li id="li_rlfa" class=""><a onclick="displaytable('rlfa')">工厂日历方案</a></li>
		<li class="pull-right">
			<input class="btn btn-primary" type="button" value="保 存" onclick="$('#inputForm').submit();" />
			<input id="btnCancel" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-1)" />
			<input TYPE='BUTTON' class="btn btn-primary" value='重置' onClick="$('#inputForm')[0].reset();">
		</li>
	</ul>
	<br />
	
	<form:form id="inputForm" modelAttribute="calendar"
		action="${ctx}/ip/calendar/save?" method="post" class="form-horizontal">
		<form:hidden path="id" />
		<tags:message content="${message}" />
		<div class="control-group">
			<label class="control-label" for="famc">方案名称:</label>
			<div class="controls">
				<form:input path="famc" htmlEscape="false" maxlength="200"
					class="required" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="nf">年份:</label>
			<div class="controls">
				<form:input path="nf" htmlEscape="false"
					maxlength="200" class="required" />    <!--  input-xxlarge  -->
			</div>
		</div>
		<!-- <div class="form-actions">
			<shiro:hasPermission name="ip:calendar:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div> -->
	</form:form>
</body>
</html>
