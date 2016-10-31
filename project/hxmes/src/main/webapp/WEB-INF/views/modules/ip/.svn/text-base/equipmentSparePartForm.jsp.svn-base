<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>备品备件管理</title>
<meta name="decorator" content="default" />
<script>
	$(function() {
		$("[data-toggle='popover']").popover();
	});
</script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#bjmc").focus();
		$("#inputForm").validate();
	});
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active">
			<a href="${ctx}/ip/equipmentSparePart/form?id=${equipmentSparePart.id}&equipmentModel.id=${equipmentSparePart.equipmentModel.id}">
				备品备件
				<shiro:hasPermission name="ip:equipmentSparePart:edit">${not empty equipmentSparePart.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="ip:equipmentSparePart:edit">查看</shiro:lacksPermission>
			</a>
		</li>
		<li class="pull-right">
			<input class="btn btn-primary" type="button" value="保 存" onclick="$('#inputForm').submit();" />
			<input id="btnCancel" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-1)" />
			<input TYPE='BUTTON' class="btn btn-primary" value='重置' onClick="$('#inputForm')[0].reset();">
		</li>
	</ul>
	<br />

	<form:form id="inputForm" modelAttribute="equipmentSparePart" action="${ctx}/ip/equipmentSparePart/save" method="post" class="form-horizontal">
		<form:hidden path="id" />
		<form:hidden path="equipmentModel.id" value="${equipmentSparePart.equipmentModel.id}" />
		<form:hidden path="equipmentModel.sbggxhbm" value="${equipmentSparePart.equipmentModel.sbggxhbm}" />
		<tags:message content="${message}" />
		<div class="control-group">
			<label class="control-label" for="bjwlbm">备品备件编码:</label>
			<div class="controls">
				<input type="text" id="wlbm" value="${equipmentSparePart.bjwlbm.wlbm}" readonly="readonly">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="bjmc">备品备件名称:</label>
			<div class="controls">
				<tags:taglistselect url="/Integrate/getMaterial" name="bjwlbm.id" cssClass="required" id="wlbm" itemNames="物料编码,物料名称,规格,型号,单位"
					labelValue="${equipmentSparePart.bjwlbm.wlmc}" labelName="wlmc" itemCodes="wlbm,wlmc,gg,xh,jldwmc" title="物料基本信息"
					value="${equipmentSparePart.bjwlbm.id}"></tags:taglistselect>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="gg">规格:</label>
			<div class="controls">
				<input type="text" id="gg" value="${equipmentSparePart.bjwlbm.gg}" readonly="readonly">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="xh">型号:</label>
			<div class="controls">
				<input type="text" id="xh" value="${equipmentSparePart.bjwlbm.xh}" readonly="readonly">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="dw">单位:</label>
			<div class="controls">
				<input type="text" id="dw" value="${equipmentSparePart.bjwlbm.jldw.jldwmc}" readonly="readonly">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="sl">数量:</label>
			<div class="controls">
				<form:input path="sl" htmlEscape="false" maxlength="200" class="digits required" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="bpbjfj">附件上传:</label>
			<div class="controls">
				<input type="hidden" id="bpbjfj" name="bpbjfj" value="${equipmentSparePart.bpbjfj}" />
				<tags:ckfinder input="bpbjfj" type="files" uploadPath="/ip/equipmentSparePart" selectMultiple="false" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="bz">备注:</label>
			<div class="controls">
				<form:textarea path="bz" htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge" />
			</div>
		</div>

	</form:form>
</body>
</html>
