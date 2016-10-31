<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>物料技术参数管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		$("#csdm").focus();
		var code = '${wljscs.csdm}';
		$("#inputForm").validate({
		rules : {
			csdm : {
				remote : "${ctx}/ip/wljscs/checkCsdm?oldCsdm=" + code
			}
		},
		messages : {
			csdm : {
				remote : "编码已经存在!"
			}
		}
		});
	});
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active">
			<a href="${ctx}/ip/wljscs/form?wlbm.id=${wljscs.wlbm.id}">
				物料技术参数
				<shiro:hasPermission name="wuliao:wljscs:edit">${not empty wljscs.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="wuliao:wljscs:edit">查看</shiro:lacksPermission>
			</a>
		</li>
		<li class="pull-right">
			<input class="btn btn-primary" type="button" value="保 存" onclick="$('#inputForm').submit();" />
			<input id="btnCancel" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-1)" />
			<input TYPE='button' class="btn btn-primary" value='重置' onClick="$('#inputForm')[0].reset();">
		</li>
	</ul>
	<br />

	<form:form id="inputForm" modelAttribute="wljscs" action="${ctx}/ip/wljscs/save" method="post" class="form-horizontal">
		<form:hidden path="id" />
		<tags:message content="${message}" />
		<input name="wlbm.id" type="hidden" value="${wljscs.wlbm.id}">
		<div class="control-group">
			<label class="control-label" for="csdm">参数代码:</label>
			<div class="controls">
				<form:input path="csdm" htmlEscape="false" maxlength="200" class="required" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="csmc">参数名称:</label>
			<div class="controls">
				<form:input path="csmc" htmlEscape="false" maxlength="200" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="csjc">参数简称:</label>
			<div class="controls">
				<form:input path="csjc" htmlEscape="false" maxlength="200" class="required" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="cszlx">参数值类型:</label>
			<div class="controls">
				<form:select path="cszlx">
					<form:options items="${fns:getDictList('d_wlcszlx')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="csjldw">参数计量单位:</label>
			<div class="controls">
				<form:select path="csjldw.id">
					<form:options items="${fns:getJldwDictList()}" itemLabel="jldwmc" itemValue="id" htmlEscape="false" class="required" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="bzz">标准值:</label>
			<div class="controls">
				<form:input path="bzz" htmlEscape="false" maxlength="200" class="number" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="sxz">上限值:</label>
			<div class="controls">
				<form:input path="sxz" htmlEscape="false" maxlength="200" class="number" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="xxz">下限值:</label>
			<div class="controls">
				<form:input path="xxz" htmlEscape="false" maxlength="200" class="number" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="xssx">显示顺序:</label>
			<div class="controls">
				<form:input path="xssx" value="0" htmlEscape="false" class="digits" />
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
			<label class="control-label" for="cssm">参数说明:</label>
			<div class="controls">
				<form:textarea path="cssm" htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge" />
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
