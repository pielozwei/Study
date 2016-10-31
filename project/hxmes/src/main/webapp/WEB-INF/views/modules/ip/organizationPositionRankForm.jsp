<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>人员业务分类管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		$("#ZZJG_ID").focus();
		$("#inputForm").validate();
	});
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/ip/organizationPositionRank/">人员业务分类管理</a></li>
		<li class="pull-right">
			<input class="btn btn-primary" type="button" value="保 存" onclick="$('#inputForm').submit();" />
			<input id="btnCancel" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-1)" />
			<input TYPE='BUTTON' class="btn btn-primary" value='重置' onClick="$('#inputForm')[0].reset();">
		</li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="organizationPositionRank"
		action="${ctx}/ip/organizationPositionRank/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<input type="hidden" name="ZZJG_ID" value="0" />
		<tags:message content="${message}" />
		<div class="control-group">
			<label class="control-label" for="RYYWLBBM">业务分类编码:</label>
			<div class="controls">
				<form:input path="RYYWLBBM" htmlEscape="false" maxlength="200"
					class="input-large" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="RYYWLBMC">业务分类名称:</label>
			<div class="controls">
				<form:input path="RYYWLBMC" htmlEscape="false" maxlength="200"
					class="input-large" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="RYYWLBMS">业务分类描述:</label>
			<div class="controls">
				<form:textarea path="RYYWLBMS" htmlEscape="false" maxlength="200"
					class="input-large" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="sfqy">是否启用:</label>
			<div class="controls">
				<form:select path="sfqy">
					<form:options items="${fns:getDictList('yes_no')}"
						itemValue="value" itemLabel="label" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="xssx">显示顺序:</label>
			<div class="controls">
				<form:input path="xssx" htmlEscape="false" maxlength="200"
					class="input-large" />
			</div>
		</div>
	</form:form>
</body>
</html>
