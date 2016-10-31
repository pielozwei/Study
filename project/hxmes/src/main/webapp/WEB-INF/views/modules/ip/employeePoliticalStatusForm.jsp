<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>政治面貌经历管理</title>
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

		<li id="li_xtsx" class=""><a onclick="displaytable('xtsx')">系统属性</a></li>
		<li class="pull-right">
			<input class="btn btn-primary" type="button" value="保 存" onclick="$('#inputForm').submit();" />
			<input id="btnCancel" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-1)" />
			<input TYPE='BUTTON' class="btn btn-primary" value='重置' onClick="$('#inputForm')[0].reset();">
		</li>
	</ul>
	<br/>
	<form:form id="inputForm" modelAttribute="employeePoliticalStatus" action="${ctx}/ip/employeePoliticalStatus/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<tags:message content="${message}"/>
		<div class="control-group">
			<label class="control-label" for="employee.id">职工姓名:</label>
			<div class="controls">
				<tags:treeselect id="zg" name="employee.id"
				 value="${employeePoliticalStatus.employee.id}" labelName="employee.xm" labelValue="${employeePoliticalStatus.employee.xm}"
				title="员工" url="/ip/employee/treeData"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="xl">序列:</label>
			<div class="controls">
				<form:input path="xl" htmlEscape="false" maxlength="200" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="zzmm">政治面貌:</label>
			<div class="controls">
			<form:select path="zzmm" >
					<form:options items="${fns:getDictList('d_zzmm')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
				 </div>
			</div>
		<div class="control-group">
			<label class="control-label" for="jrsj">加入时间:</label>
			<div class="controls">
				<input id="jrsj" name="jrsj" type="text" maxlength="20" htmlEscape="false" 
					value="<fmt:formatDate value="${employeePoliticalStatus.jrsj}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		<!-- <div class="form-actions">
			<shiro:hasPermission name="ip:employeePoliticalStatus:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div> -->
	</form:form>
</body>
</html>
