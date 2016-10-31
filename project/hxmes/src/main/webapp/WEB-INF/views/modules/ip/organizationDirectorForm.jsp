<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>分管单位设置管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		$("#zg_id").focus();
		$("#inputForm").validate();
	});
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/ip/organizationDirector/">分管单位设置</a></li>
		<li class="pull-right">
			<input class="btn btn-primary" type="button" value="保 存" onclick="$('#inputForm').submit();" />
			<input id="btnCancel" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-1)" />
			<input TYPE='BUTTON' class="btn btn-primary" value='重置' onClick="$('#inputForm')[0].reset();">
		</li>
	</ul>

	<form:form id="inputForm" modelAttribute="organizationDirector"
		action="${ctx}/ip/organizationDirector/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<tags:message content="${message}" />
		<div class="control-group">
			<label class="control-label" for="employee.id">选择人员:</label>
			<div class="controls">
				<tags:treeselect id="zg_id" name="employee.id"
					value="${list.id}" labelName="list.name"
					labelValue="${list.name}" title="职工"
					url="/ip/employee/treeData" cssClass="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="organization.id">选择分管单位:</label>
			<div class="controls">
				<tags:treeselect id="fgbm_id" name="organization.id" checked="true"
					value="${fgdw.zzjg_id}" labelName="fgdw.name"
					labelValue="${fgdw.name}" title="组织机构"
					url="/ip/organization/treeData" />
			</div>
		</div>

	</form:form>
</body>
</html>
