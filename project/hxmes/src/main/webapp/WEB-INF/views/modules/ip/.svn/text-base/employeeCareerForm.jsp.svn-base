<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工作经历管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#name").focus();
			$("#inputForm").validate();
		});
	</script>
</head>
<body>
	<!-- <ul class="nav nav-tabs">
		<li><a href="${ctx}/ip/employeeCareer/">工作经历列表</a></li>
		<li class="active"><a href="${ctx}/ip/employeeCareer/form?id=${employeeCareer.id}">工作经历<shiro:hasPermission name="ip:employeeCareer:edit">${not empty employeeCareer.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="ip:employeeCareer:edit">查看</shiro:lacksPermission></a></li>
	</ul> -->
	<ul class="nav nav-tabs">
		<li id="li_gzjl" class="active">
			<a onclick="displaytable('gzjl')">工作经历</a>
		</li>
		<li class="pull-right">
			<input class="btn btn-primary" type="button" value="保 存" onclick="$('#inputForm').submit();" />
			<input id="btnCancel" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-1)" />
			<input TYPE='BUTTON' class="btn btn-primary" value='重置' onClick="$('#inputForm')[0].reset();">
		</li>
	</ul>
	<br/>
	<form:form id="inputForm" modelAttribute="employeeCareer" action="${ctx}/ip/employeeCareer/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<tags:message content="${message}"/>
		
		

		<div class="control-group">
			<label class="control-label" for="zg_id">职工姓名:</label>
				<div class="controls">
			    	<tags:treeselect id="zg_id" name="employee.id" value="${list.id}" labelName="employee.xm" labelValue="${list.name}"
							title="员工" url="/ip/employee/treeData"/>
				</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="xl">序列:</label>
			<div class="controls">
				<form:input path="xl" htmlEscape="false" maxlength="200" class="required"/>
			</div>
		</div>
		
			<div class="control-group">
			<label class="control-label" for="qsrq">起始日期：</label>
			<div class="controls">
				<input id="qsrq" name="qsrq" type="text" maxlength="20" class=" Wdate required"
					value="<fmt:formatDate value="${employeeCareer.qsrq}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>	
		<div class="control-group">
			<label class="control-label" for="zzrq">终止日期：</label>
			<div class="controls">
				<input id="zzrq" name="zzrq" type="text" maxlength="20" class="Wdate required"
					value="<fmt:formatDate value="${employeeCareer.zzrq}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		
		
		
		
		<div class="control-group">
			<label class="control-label" for="szdw">所在单位:</label>
			<div class="controls">
				<form:input path="szdw" htmlEscape="false" maxlength="200" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="zw">职务:</label>
			<div class="controls">
				<form:input path="zw" htmlEscape="false" maxlength="200" class="required"/>
			</div>
		</div>
		
		<div class="control-group">
			<label class="control-label" for="remarks">备注:</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge"/>
			</div>
		</div>
		
		
		
		
		<!-- <div class="form-actions">
			<shiro:hasPermission name="ip:employeeCareer:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div> -->
	</form:form>
</body>
</html>
