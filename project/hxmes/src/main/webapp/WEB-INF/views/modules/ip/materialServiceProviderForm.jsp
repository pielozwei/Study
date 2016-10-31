<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>物料服务供应商管理</title>
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
		<li class="active">
			<a href="${ctx}/ip/wlfwgys/form?id=${wlfwgys.id}&wlbm.id=${wlfwgys.wlbm.id}">
				物料服务供应商
				<shiro:hasPermission name="wuliao:wlfwgys:edit">${not empty wlfwgys.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="wuliao:wlfwgys:edit">查看</shiro:lacksPermission>
			</a>
		</li>
		<li class="pull-right">
			<input class="btn btn-primary" type="button" value="保 存" onclick="$('#inputForm').submit();" />
			<input id="btnCancel" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-1)" />
			<input TYPE='button' class="btn btn-primary" value='重置' onClick="$('#inputForm')[0].reset();">
		</li>
	</ul>
	<br />

	<form:form id="inputForm" modelAttribute="wlfwgys" action="${ctx}/ip/wlfwgys/save" method="post" class="form-horizontal">
		<form:hidden path="id" />
		<form:hidden path="wlbm.id" value="${wlfwgys.wlbm.id}"/>
		<tags:message content="${message}" />
		<div class="control-group">
			<label class="control-label" for="gysbm">供应商编码:</label>
			<div class="controls">
				<tags:taglistselect name="provider.id" url="/Integrate/getProvider" id="provider" itemNames="供应商编码,供应商名称,联系人,联系电话" labelValue="${wlfwgys.provider.gysbm}" labelName="gysbm"
					itemCodes="gysbm,gysmc,lxr,lxdh" title="供应商基本信息" value="${wlfwgys.provider.id}"  cssClass="required"></tags:taglistselect>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="gysmc">供应商名称:</label>
			<div class="controls">
				<input type="text" id="gysmc" readonly="readonly" value="${wlfwgys.provider.gysmc}" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="lxr">联系人:</label>
			<div class="controls">
				<input type="text" id="lxr" readonly="readonly" value="${wlfwgys.provider.lxr}" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="lxdh">联系电话:</label>
			<div class="controls">
				<input type="text" id="lxdh" readonly="readonly" value="${wlfwgys.provider.lxdh}" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="fwlb">服务类型:</label>
			<div class="controls">
				<form:select path="fwlx">
					<form:options items="${fns:getDictList('d_fwlx')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
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
