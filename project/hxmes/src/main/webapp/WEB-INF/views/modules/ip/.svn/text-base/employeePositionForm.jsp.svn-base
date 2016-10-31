<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>行政党派职务经历管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		$("#name").focus();
		$("#inputForm").validate();
	});
	window.onload = function() {
		var xl_name = document.getElementById("xl_id");
		var xl_err = document.getElementById("xl_err");
		xl_name.onblur = function() {
			if (/^[0-9]*$/.test(xl_name.value) == false)
				xl_err.innerHTML = "只能输入数字！";
			else
				xl_err.innerHTML = "";
		}
	}
</script>
</head>
<body>
	
	<ul class="nav nav-tabs">

		<li id="li_xzdp" class=""><a onclick="displaytable('xzdp')">行政党派职务经历</a></li>
		<li class="pull-right">
			<input class="btn btn-primary" type="button" value="保 存" onclick="$('#inputForm').submit();" />
			<input id="btnCancel" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-1)" />
			<input TYPE='BUTTON' class="btn btn-primary" value='重置' onClick="$('#inputForm')[0].reset();">
		</li>
	</ul>
	<br />

	<form:form id="inputForm" modelAttribute="employeePosition"
		action="${ctx}/ip/employeePosition/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<tags:message content="${message}" />


		<div class="control-group">
			<label class="control-label" for="zg_id">姓名:</label>
			<div class="controls">
				<tags:treeselect id="zg_id" name="employee.id"
					value="${employeePosition.employee.id}" labelName="employee.xm" labelValue="${employeePosition.employee.xm}"
					title="姓名：" url="/ip/employee/treeData" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label" for="dqrzzt">当前任职状态:</label>
			<div class="controls">
				<form:select path="dqrzzt">
					<form:options items="${fns:getDictList('d_rzzt')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label" for="zw">职务:</label>
			<div class="controls">
				<form:input path="zw" htmlEscape="false" maxlength="200"
					class="required" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="pzrq">决定或批准任职的日期:</label>
			<div class="controls">
				<input id="pzrq" htmlEscape="false" maxlength="200"
					readonly="readonly" value="<fmt:formatDate value="${employeePosition.pzrq}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="rzwh">任职文号:</label>
			<div class="controls">
				<form:input path="rzwh" htmlEscape="false" maxlength="200" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="basj">备案时间:</label>
			<div class="controls">
				<input id="basj" htmlEscape="false" maxlength="200"
					readonly="readonly" value="<fmt:formatDate value="${employeePosition.basj}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
			</div>
		</div>

		<!-- <div class="form-actions">
			<shiro:hasPermission name="ip:employeePosition:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div> -->
	</form:form>
</body>
</html>
