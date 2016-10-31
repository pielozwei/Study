<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>组织考核经历管理</title>
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

		<li id="li_zzkh" class=""><a onclick="displaytable('zzkh')">职工组织考核记录</a></li>
		<li class="pull-right">
			<input class="btn btn-primary" type="button" value="保 存" onclick="$('#inputForm').submit();" />
			
			<input TYPE='BUTTON' class="btn btn-primary" value='重置' onClick="$('#inputForm')[0].reset();">
			<input id="btnCancel" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-1)" />
		</li>
	</ul>
	<br/>
	<form:form id="inputForm" modelAttribute="employeeAppraisal" action="${ctx}/ip/employeeAppraisal/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<tags:message content="${message}"/>
		<div class="control-group">
			<label class="control-label" for="zg_id">姓名:</label>		
			<div class="controls">
				<tags:treeselect id="zg_id" name="employee.id"
					value="${employeeAppraisal.employee.id}" labelName="name"
					labelValue="${employeeAppraisal.employee.xm}" title="员工"
					url="/ip/employee/treeData" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="khnd">考核年度:</label>
			<div class="controls">
				<form:input path="khnd" name="khnd" type="text" maxlength="20" class="Wdate"
					onclick="WdatePicker({dateFmt:'yyyy',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="khjllb">考核结论类别:</label>
			<div class="controls">
				<form:select path="khjllb" class="input-xlarge required">
				  <form:options items="${fns:getDictList('d_khjllb')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="cjkhbddcyy">参加考核不定等次原因:</label>
			<div class="controls">
				<form:select path="cjkhbddcyy" class="input-xlarge">
					<form:options items="${fns:getDictList('d_cjkhbddcyy')}" itemLabel="label" itemValue="value" htmlEscape="false" /> 
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="wcjkhyy">未参加考核原因:</label>
			<div class="controls">
				<form:select path="wcjkhyy" class="input-xlarge">
					<form:options items="${fns:getDictList('d_wcjkhyy')}" itemLabel="label" itemValue="value" htmlEscape="false" /> 
				</form:select>
			</div>
		</div>
		
	</form:form>
</body>
</html>
