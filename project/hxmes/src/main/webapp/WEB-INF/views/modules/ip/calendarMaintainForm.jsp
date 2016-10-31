<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>工厂日历维护管理</title>
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

		<li id="li_rlsc" class=""><a onclick="displaytable('rlsc')">系统属性</a></li>
		<li class="pull-right">
			<input class="btn btn-primary" type="button" value="保 存" onclick="$('#inputForm').submit();" />
			<input id="btnCancel" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-1)" />
			<input TYPE='BUTTON' class="btn btn-primary" value='重置' onClick="$('#inputForm')[0].reset();">
		</li>
	</ul>
<%-- 	<ul class="nav nav-tabs">
		<li><a href="${ctx}/ip/calendarMaintain/">工厂日历维护列表</a></li>
		<li class="active"><a
			href="${ctx}/ip/calendarMaintain/form?id=${calendarMaintain.id}">工厂日历维护<shiro:hasPermission
					name="ip:calendarMaintain:edit">${not empty calendarMaintain.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="ip:calendarMaintain:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<br />
 --%>
	<form:form id="inputForm" modelAttribute="calendarMaintain"
		action="${ctx}/ip/calendarMaintain/save" method="post"
		class="form-horizontal">
		
		<form:hidden path="workcenter.id" />
		<tags:message content="${message}" />



		<div class="control-group">
			<label class="control-label" for="id">编号:</label>
			<div class="controls">
				<form:input path="id" htmlEscape="false" maxlength="200" readOnly="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="workcenter.gzzxbm">工作中心编码:</label>
			<div class="controls">
				<form:input path="workcenter.gzzxbm" htmlEscape="false" maxlength="200" readOnly="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="workcenter.gzzxmc">工作中心名称:</label>
			<div class="controls">
				<form:input path="workcenter.gzzxmc" htmlEscape="false" maxlength="200" readOnly="true" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="rq">日期:</label>
			<div class="controls">
				<form:input path="rq" htmlEscape="false" maxlength="200"
					readonly="readonly"
					onclick="WdatePicker({dateFmt:'yyyy-mm-dd',isShowClear:false});" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="gzrlx">工作日类型:</label>
			<div class="controls">
				<form:select path="gzrlx">
					
							<form:options items="${fns:getDictList('d_gzrlx')}" itemValue="value" itemLabel="label" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="sfsb">是否上班:</label>
			<div class="controls">
				<form:select path="sfsb">
					
							<form:options items="${fns:getDictList('yes_no')}" itemValue="value" itemLabel="label" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="bm">班次名:</label>
			<div class="controls">
				<form:input path="bm" htmlEscape="false" maxlength="200" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="bcxz">班次性质:</label>
			<div class="controls">
				<form:select path="bcxz">
							<form:options items="${fns:getDictList('d_bcxz')}" itemValue="value" itemLabel="label" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="bcsfsb">班次是否上班:</label>
			<div class="controls">
				<form:select path="bcsfsb">
					<form:options items="${fns:getDictList('yes_no')}" itemValue="value" itemLabel="label" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
					<label class="control-label" for="faly">日历方案:</label>
				<div class="controls">
				<form:input path="faly" htmlEscape="false" maxlength="200" class = "readonly" />
			</div>
				</div>
		<!-- <div class="form-actions">
			<shiro:hasPermission name="ip:calendarMaintain:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div> -->
	</form:form>
</body>
</html>
