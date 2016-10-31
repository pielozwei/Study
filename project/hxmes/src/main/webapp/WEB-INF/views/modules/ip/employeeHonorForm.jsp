<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>荣誉称号记录管理</title>
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

		<li id="li_rych" class=""><a onclick="displaytable('rych')">职工荣誉称号经历</a></li>
		<li class="pull-right">
			<input class="btn btn-primary" type="button" value="保 存" onclick="$('#inputForm').submit();" />
			<input id="btnCancel" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-1)" />
			<input TYPE='BUTTON' class="btn btn-primary" value='重置' onClick="$('#inputForm')[0].reset();">
		</li>
	</ul>
	<br />

	<form:form id="inputForm" modelAttribute="employeeHonor"
		action="${ctx}/ip/employeeHonor/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<tags:message content="${message}" />

		<div class="control-group">
			<label class="control-label" for="zg_id">职工姓名:</label>
			<div class="controls">
				<tags:treeselect id="zg_id" name="employee.id"
					value="${employeeHonor.employee.id}" labelName="name"
					labelValue="${employeeHonor.employee.xm}" title="员工"
					url="/ip/employee/treeData" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label" for="jb">荣誉称号级别:</label>
			<div class="controls">
				<form:select path="jb">
					<form:options items="${fns:getDictList('d_hjjb')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
				&nbsp;
			</div>
		</div>

		<div class="control-group">
			<label class="control-label" for="mc">荣誉称号名称:</label>
			<div class="controls">
				<form:input path="mc" htmlEscape="false" maxlength="200"
					class="required" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label" for="pzrq">荣誉称号批准日期:</label>
			<div class="controls">
				<input id="pzrq" name="pzrq" type="text" maxlength="20" class="Wdate required" 
					value="<fmt:formatDate value="${employeeHonor.pzrq}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label" for="pzjgmc">荣誉称号批准机构名称:</label>
			<div class="controls">
				<form:input path="pzjgmc" htmlEscape="false" maxlength="200"
					class="required" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label" for="ycxjlje">一次性奖励金额:</label>
			<div class="controls">
				<form:input path="ycxjlje" htmlEscape="false" maxlength="200" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label" for="yjlje">月奖励金额:</label>
			<div class="controls">
				<form:input path="yjlje" htmlEscape="false" maxlength="200" />
			</div>
		</div>

		<!--  <div class="form-actions">
			<shiro:hasPermission name="ip:employeeHonor:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;
				 input id="btnSubmit" class="btn btn-primary" type="submit" value="保存并新增荣誉称号记录"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div> -->
	</form:form>
</body>
</html>
