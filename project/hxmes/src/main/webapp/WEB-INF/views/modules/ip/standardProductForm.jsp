<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>标准产品管理</title>
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
		<%-- <li><a href="${ctx}/ip/standardProduct/">标准产品列表</a></li> --%>
		<li class="active"><a href="${ctx}/ip/standardProduct/form?id=${standardProduct.id}">标准产品<shiro:hasPermission name="ip:standardProduct:edit">${not empty standardProduct.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="ip:standardProduct:edit">查看</shiro:lacksPermission></a></li>
		<button class="btn pull-right btn-primary" onclick="history.go(-1)">返回</button>
		&nbsp;
		<button class="btn pull-right btn-primary" onclick="$('#inputForm')[0].reset();">清空</button>
		&nbsp;
		<button class="btn pull-right btn-primary" onclick="$('#inputForm').submit();">保 存</button>
	</ul><br/>
	
	<form:form id="inputForm" modelAttribute="standardProduct" action="${ctx}/ip/standardProduct/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<tags:message content="${message}"/>
		<div class="control-group">
			<label class="control-label" for="cpbh">产品编号:</label>
			<div class="controls">
				<form:input path="cpbh" htmlEscape="false" maxlength="200" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="cpmc">产品名称:</label>
			<div class="controls">
				<form:input path="cpmc" htmlEscape="false" maxlength="200" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="bzsj">标准时间:</label>
			<div class="controls">
				<form:input path="bzsj" htmlEscape="false" maxlength="200" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="bzpldw">标准批量单位:</label>
			<div class="controls">
				<form:input path="bzpldw" htmlEscape="false" maxlength="200" class="required"/>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label" for="remarks">备注:</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge"/>
			</div>
		</div> --%>
		<%-- <div class="form-actions">
			<shiro:hasPermission name="ip:standardProduct:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div> --%>
	</form:form>
</body>
</html>
