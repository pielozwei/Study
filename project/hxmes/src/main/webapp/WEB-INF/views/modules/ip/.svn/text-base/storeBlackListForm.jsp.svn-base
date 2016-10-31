<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>仓库黑名单管理</title>
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
		<li><a href="${ctx}/cangku/ckhmd/list/">仓库黑名单列表</a></li>
		<li class="active"><a href="${ctx}/cangku/ckhmd/form?id=${ckhmd.id}">仓库黑名单<shiro:hasPermission name="cangku:ckhmd:edit">${not empty ckhmd.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="cangku:ckhmd:edit">查看</shiro:lacksPermission></a></li>
		<button class="btn pull-right btn-primary" onclick="history.go(-1)">返回</button>
		&nbsp;
		<button class="btn pull-right btn-primary" onclick="$('#inputForm')[0].reset();">清空</button>
		&nbsp;
		<button class="btn pull-right btn-primary" onclick="$('#inputForm').submit();">保 存</button>
	</ul><br/>
	
	<form:form id="inputForm" modelAttribute="ckhmd" action="${ctx}/cangku/ckhmd/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<tags:message content="${message}"/>
		<%-- <div class="control-group">
			<label class="control-label" for="ipWlbmWlbm">物料编码:</label>
			<div class="controls">
				<form:input path="ipWlbmWlbm" htmlEscape="false" maxlength="200" class="required"/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label" for="wlbm">物料编码:</label>
			<div class="controls">
				<%-- <form:input path="ipWlbmWlbm" htmlEscape="false" maxlength="200"
					class="required" /> --%>
				<tags:treeselect id="company1" name="ipWlbmWlbm" value="${ckhmd.ipWlbmWlbm.id}" labelName="wlbm.name" labelValue="${ckhmd.ipWlbmWlbm.wlbm}"
					title="物料编码" url="/cangku/ckbmd/treeData1?type=1" cssClass="required"/>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label" for="ipCkCkbh">仓库编号:</label>
			<div class="controls">
				<form:textarea path="ipCkCkbh" htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge"/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label" for="company">仓库编码:</label>
			<div class="controls">
                <tags:treeselect id="company" name="ipCkCkbh" value="${ckhmd.ipCkCkbh.id}" labelName="company.name" labelValue="${ckhmd.ipCkCkbh.ckbh}"
					title="仓库编码" url="/cangku/ckbmd/treeData?type=1" cssClass="required"/>
			</div>
		</div>
		<%-- <div class="form-actions">
			<shiro:hasPermission name="cangku:ckhmd:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div> --%>
	</form:form>
</body>
</html>
