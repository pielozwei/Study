<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>科研项目记录管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		$("#name").focus();
		$("#inputForm").validate();
	});
</script>
</head>
<body>
	<!--  <ul class="nav nav-tabs">
		<li><a href="${ctx}/ip/employeeProject/">科研项目记录列表</a></li>
		<li class="active"><a
			href="${ctx}/ip/employeeProject/form?id=${employeeProject.id}">科研项目记录<shiro:hasPermission
					name="ip:employeeProject:edit">${not empty employeeProject.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="ip:employeeProject:edit">查看</shiro:lacksPermission></a></li>
	</ul>-->
	<ul class="nav nav-tabs">

		<li id="li_kyxm" class=""><a onclick="displaytable('kyxm')">职工科研项目记录</a></li>
		<li class="pull-right">
			<input class="btn btn-primary" type="button" value="保 存" onclick="$('#inputForm').submit();" />
			<input id="btnCancel" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-1)" />
			<input TYPE='BUTTON' class="btn btn-primary" value='重置' onClick="$('#inputForm')[0].reset();">
		</li>
	</ul>
	<br />

	<form:form id="inputForm" modelAttribute="employeeProject"
		action="${ctx}/ip/employeeProject/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<tags:message content="${message}" />
		<div class="control-group">
			<label class="control-label" for="zg_id">姓名:</label>
			<div class="controls">
				<tags:treeselect id="zg_id" name="employee.id" value="${employeeProject.employee.id}" labelName="employee.xm" labelValue="${employeeProject.employee.xm}"
					title="员工" url="/ip/employee/treeData"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="kyxmmc">科研项目名称:</label>
			<div class="controls">
				<form:input path="kyxmmc" htmlEscape="false" maxlength="200"
					class="required" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="kyxmjb">科研项目级别:</label>
			<div class="controls">
				<form:select path="kyxmjb">
					<form:options items="${fns:getDictList('d_kyxmjb')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="kssj">开始时间:</label>
			<div class="controls">
				<input id="kssj" name="kssj" type="text" maxlength="20"
					class="Wdate" value="<fmt:formatDate value="${employeeProject.kssj}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="jssj">结束时间:</label>
			<div class="controls">
				<input id="jssj" name="jssj" type="text" maxlength="20"
					class="Wdate" value="<fmt:formatDate value="${employeeProject.jssj}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="jbdw">举办单位:</label>
			<div class="controls">
				<form:input path="jbdw" htmlEscape="false" maxlength="200" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="cgmc">成果名称:</label>
			<div class="controls">
				<form:input path="cgmc" htmlEscape="false" maxlength="200" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="wcxs">完成形式:</label>
			<div class="controls">
				<form:select path="wcxs">
					<form:options items="${fns:getDictList('d_wcxs')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<!-- <div class="form-actions">
			<shiro:hasPermission name="ip:employeeProject:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div> -->
	</form:form>
</body>
</html>
