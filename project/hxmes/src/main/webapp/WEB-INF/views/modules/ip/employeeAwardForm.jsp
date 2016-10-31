<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>获奖情况记录管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#zg_id").focus();
			$("#inputForm").validate();
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">

		<li id="li_hjqk" class=""><a onclick="displaytable('hjqk')">职工获奖情况记录</a></li>
		<li class="pull-right">
			<input class="btn btn-primary" type="button" value="保 存" onclick="$('#inputForm').submit();" />
			<input id="btnCancel" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-1)" />
			<input TYPE='BUTTON' class="btn btn-primary" value='重置' onClick="$('#inputForm')[0].reset();">
		</li>
	</ul>
	<br/>	
	<form:form id="inputForm" modelAttribute="employeeAward" action="${ctx}/ip/employeeAward/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<tags:message content="${message}"/>
		
		<div class="control-group">
			<label class="control-label" for="zg_id">姓名:</label>
			<div class="controls">
				<tags:treeselect id="zg_id" name="employee.id" value="${employeeAward.employee.id}" labelName="list.name" labelValue="${employeeAward.employee.xm}"
					title="员工" url="/ip/employee/treeData"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="hjjb">获奖级别:</label>
			<div class="controls">
				<form:select path="hjjb">
					<form:options items="${fns:getDictList('d_hjjb')}" itemValue="value" itemLabel="label" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="hjmc">获奖名称:</label>
			<div class="controls">
				<form:input path="hjmc" htmlEscape="false" maxlength="200" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="hjrq">获奖时间：</label>
			<div class="controls">
				<input id="hjrq" name="hjrq" type="text" maxlength="20" class="Wdate"
					value="<fmt:formatDate value="${employeeAward.hjrq}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
	
		
		<div class="control-group">
			<label class="control-label" for="bjjgmc">颁奖机构名称:</label>
			<div class="controls">
				<form:textarea path="bjjgmc" htmlEscape="false"  maxlength="200" class="input-xxlarge"/>
			</div>
		</div>
		
	<!--	<div class="form-actions">
			<shiro:hasPermission name="ip:employeeAward:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>  -->
	</form:form>
</body>
</html>
