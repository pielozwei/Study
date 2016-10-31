<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>仓库白名单管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		$("#name").focus();
		$("#inputForm").validate();
		//alert('${parentId}');
	});
	
</script>
</head>
<body>
	
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/cangku/ckbmd/list">仓库白名单列表</a></li>
		<li class="active"><a
			href="${ctx}/cangku/ckbmd/form?id=${ckbmd.id}">仓库白名单<shiro:hasPermission
					name="cangku:ckbmd:edit">${not empty ckbmd.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="cangku:ckbmd:edit">查看</shiro:lacksPermission></a></li>
		<button class="btn pull-right btn-primary" onclick="history.go(-1)">返回</button>
		&nbsp;
		<button class="btn pull-right btn-primary" onclick="$('#inputForm')[0].reset();">清空</button>
		&nbsp;
		<button class="btn pull-right btn-primary" onclick="$('#inputForm').submit();">保 存</button>
	</ul>
	<br />
<div>
	<form:form id="inputForm" modelAttribute="ckbmd"
		action="${ctx}/cangku/ckbmd/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<form type="hidden" name="ipCkCkbh" value="${ipCkCkbh.id}" />
		<tags:message content="${message}" />
		<div class="control-group">
			<label class="control-label" for="company">仓库编码:</label>
			<div class="controls">
				<tags:treeselect id="company" name="ipCkCkbh"
					value="${ckbmd.ipCkCkbh.id}" labelName="company.name"
					labelValue="${ckbmd.ipCkCkbh.ckbh}" title="仓库编码"
					url="/cangku/ckbmd/treeData?type=1" cssClass="required" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="wlbm">物料编码:</label>
			<div class="controls">
				<tags:treeselect id="wlbm" name="ipWlbmWlbm" value="${ckbmd.ipWlbmWlbm.id}" labelName="wlbm.name" labelValue="${ckbmd.ipWlbmWlbm.wlbm}"
					title="物料编码" url="/cangku/ckbmd/treeData1?type=1" cssClass="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="remarks">最大存储天数:</label>
			<div class="controls">
				<form:input path="zdccts" htmlEscape="false" maxlength="200" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="remarks">安全库存:</label>
			<div class="controls">
				<form:input path="aqkc" htmlEscape="false" maxlength="200"  />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="remarks">最大库存限额:</label>
			<div class="controls">
				<form:input path="zdkcxe" htmlEscape="false" maxlength="200"
					 />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="weight">循环盘点间隔：</label>
			<div class="controls">
				<form:input path="xhpdjg" htmlEscape="false" maxlength="200"
					class="digits" />
				<form:select path="xhpdjgsjdw" class="input-small">
					<form:option value="0" label="小时" />
					<form:option value="1" label="天" />
					<form:option value="2" label="月" />
					<form:option value="3" label="年" />
				</form:select>
			</div>
		</div>
		<%-- <div class="form-actions">
			<shiro:hasPermission name="cangku:ckbmd:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div> --%>
	</form:form>
	</div>
	

</body>
</html>
