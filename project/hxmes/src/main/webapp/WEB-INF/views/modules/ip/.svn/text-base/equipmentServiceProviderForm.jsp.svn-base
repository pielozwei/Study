<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>服务商管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("input[name='provider.gysmc']").focus();
			$("#inputForm").validate();
			$("input[name='provider.gysbm']").attr("readonly",true);
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/ip/equipmentServiceProvider/?equipmentId=${equipmentServiceProvider.equipment.id}">服务商列表</a></li>
		<li class="active"><a href="${ctx}/ip/equipmentServiceProvider/form?id=${equipmentServiceProvider.id}&equipment.id=${equipmentServiceProvider.equipment.id}">服务商
		<shiro:hasPermission name="ip:equipmentServiceProvider:edit">${not empty equipmentServiceProvider.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="ip:equipmentServiceProvider:edit">查看</shiro:lacksPermission></a></li>
		<button class="btn pull-right btn-primary" onclick="history.go(-1)">返回</button>&nbsp;
		<button class="btn pull-right btn-primary" onclick="$('#inputForm')[0].reset();">清空</button>&nbsp;
		<button class="btn pull-right btn-primary" onclick="$('#inputForm').submit();">保存</button>&nbsp;
	</ul><br/>
	
	<form:form id="inputForm" modelAttribute="equipmentServiceProvider" action="${ctx}/ip/equipmentServiceProvider/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="equipment.id" value="${equipmentServiceProvider.equipment.id}"/>
		<%-- <form:hidden path="equipment.equipmentbm" value="${equipmentServiceProvider.equipment.equipmentbm}"/> --%>
		<tags:message content="${message}"/>
		
		<div class="control-group">
			<label class="control-label" for="ip_gys_id">服务商:</label>
			<div class="controls">
				<tags:treepreserve id="ip_gys_id" name="provider.id" value="${equipmentServiceProvider.provider.id}" labelName="ip_gys_mc" labelValue="${equipmentServiceProvider.provider.gysmc}"
					title="服务商名称" url="/ip/provider/treeData" module="provider" selectScopeModule="true" notAllowSelectRoot="false" notAllowSelectParent="true" cssClass="required"/>&nbsp;
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="fwlx">服务商类别:</label>
			<div class="controls">
				<form:select path="fwlx">
					<form:options items="${fns:getDictList('d_fwlx')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="lxr">联系人:</label>
			<div class="controls">
				<form:input path="lxr" htmlEscape="false" maxlength="30" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="lxdh">联系电话:</label>
			<div class="controls">
				<form:input path="lxdh" htmlEscape="false" maxlength="30" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="remarks">备注:</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge"/>
			</div>
		</div>
		<%-- <div class="form-actions">
			<shiro:hasPermission name="ip:equipmentServiceProvider:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div> --%>
	</form:form>
</body>
</html>
