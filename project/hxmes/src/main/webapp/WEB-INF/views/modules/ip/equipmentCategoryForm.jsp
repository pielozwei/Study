<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>设备分类管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		$("#sblbmc").focus();
		var code='${equipmentCategory.sblbbm}';
		$("#inputForm").validate({
		rules : {
			sblbbm : {
				remote : "${ctx}/ip/equipmentCategory/checkSblbbm?oldSblbbm="+ code
			}
		},
		messages : {
			sblbbm : {
				remote : "编码已经存在!"
			}
		}
		});
	});
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<%-- 		<li><a href="${ctx}/ip/equipmentCategory/?nodeId=${nodeId}">设备分类列表</a></li>
 --%>
		<li class="active">
			<a href="${ctx}/ip/equipmentCategory/form?id=${equipmentCategory.id}&nodeId=${nodeId}">
				设备分类
				<shiro:hasPermission name="ip:equipmentCategory:edit">${not empty equipmentCategory.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="ip:equipmentCategory:edit">查看</shiro:lacksPermission>
			</a>
		</li>
		<li class="pull-right">
			<input id="btnSubmit" class="btn btn-primary" type="button" value="保 存" onclick="$('#inputForm').submit();" />
			<input id="btnCancel" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-1)" />
			<input TYPE='BUTTON' class="btn btn-primary" value='重置' onClick="$('#inputForm')[0].reset();">
		</li>
	</ul>
	<br />

	<form:form id="inputForm" modelAttribute="equipmentCategory" action="${ctx}/ip/equipmentCategory/save" method="post" class="form-horizontal">
		<form:hidden path="id" />
		<tags:message content="${message}" />

		<div class="control-group">
			<label class="control-label" for="sblbbm">设备类别编码:</label>
			<div class="controls">
				<form:input path="sblbbm" htmlEscape="false" maxlength="30" class="required"  />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="sblbmc">设备类别名称:</label>
			<div class="controls">
				<form:input path="sblbmc" htmlEscape="false" maxlength="30" class="required" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="sblbjc">设备类别简称:</label>
			<div class="controls">
				<form:input path="sblbjc" htmlEscape="false" maxlength="30" class="required" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="SJLBID">上级类别编码:</label>
			<div class="controls">
				<tags:treepreserve id="SJLBID" name="parent.id" cssClass="required" value="${equipmentCategory.parent.id}" labelName="parent.sblbmc"
					labelValue="${equipmentCategory.parent.sblbmc}" title="上级类别编码" url="/ip/equipmentCategory/treeData" module="equipmentCategory"
					selectScopeModule="true" notAllowSelectRoot="false" notAllowSelectParent="true" />
				&nbsp;
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="xssx">显示顺序:</label>
			<div class="controls">
				<form:input path="xssx" htmlEscape="false" maxlength="30" value="0" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="sfqy">是否启用:</label>
			<div class="controls">
				<form:select path="sfqy">
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="remarks">备注:</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge" />
			</div>
		</div>
	</form:form>
</body>
</html>
