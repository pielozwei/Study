<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工艺参数管理</title>
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
		<li class="active">
			<a href="${ctx}/ip/techniqueParameter/form?id=${techniqueParameter.id}">质量检测项 </a>
		</li>
		<li class="pull-right">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存" onclick="$('#inputForm').submit();" />
				<input type='button' class="btn btn-primary" value='重置' onClick="$('#inputForm')[0].reset();">
				<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)" />		
		</li>
	</ul>
	<form:form id="inputForm" modelAttribute="techniqueParameter" action="${ctx}/ip/techniqueParameter/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<tags:message content="${message}"/>
		<div class="control-group">
			<label class="control-label" for="gycsbm">工艺参数编码:</label>
			<div class="controls">
				<form:input path="gycsbm" htmlEscape="false" maxlength="200" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="gycsmc">工艺参数名称:</label>
			<div class="controls">
				<form:input path="gycsmc" htmlEscape="false"  maxlength="200" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="gylx_id">工序:</label>
			<div class="controls">
				<tags:treeselect id="gylx_id" name="technique.id" value="${list.id}" labelName="technique.mc"
					labelValue="${list.name}" title="工序名称" url="/ip/technique/treeData"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="gycsdw">工艺参数单位:</label>
			<div class="controls">
				<form:select path="gycsdw" class="required">
					<form:options items="${fns:getDictList('d_gycsdw')}" itemValue="value" itemLabel="label" htmlEscape="false"/>
				</form:select>  
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="gycssxz">上限:</label>
			<div class="controls">
				<form:input path="gycssxz" htmlEscape="false" maxlength="200" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="gycsxxz">下限:</label>
			<div class="controls">
				<form:input path="gycsxxz" htmlEscape="false" maxlength="200" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="gycsbzz">标准值:</label>
			<div class="controls">
				<form:input path="gycsbzz" htmlEscape="false" maxlength="200" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="sfqy">是否启用:</label>
			<div class="controls">
				<form:select path="sfqy" class="required">
					<form:options items="${fns:getDictList('yes_no')}" itemValue="value" itemLabel="label" htmlEscape="false"/>
				</form:select>  
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="xssx">显示顺序:</label>
			<div class="controls">
				<form:input path="xssx" htmlEscape="false" maxlength="200" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="bz">备注:</label>
				<div class="controls">
					<form:textarea path="bz" htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge"/>
				</div>
		</div>
	</form:form>
</body>
</html>
