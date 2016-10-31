<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>操作规程管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		var name = '${equipmentOpsSpec.gcmc}';
		$("#inputForm").validate({
		rules : {
			gcmc : {
				remote : "${ctx}/ip/equipmentOpsSpec/checkGcmc?oldGcmc=" + name
			}
		},
		messages : {
			gcmc : {
				remote : "名称已经存在!"
			}
		}
		});
	});
	function validateForm() {
		//validate方法参数可选
		return $("#inputForm").validate().form();
	}

	function doSubmit() {
		//do other things
		//验证通过后提交
		if (validateForm()) {
			inputForm.submit();
		}
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active">
			<a href="${ctx}/ip/equipmentOpsSpec/form?id=${equipmentOpsSpec.id}&equipmentModel.id=${equipmentOpsSpec.equipmentModel.id}">
				操作规程
				<shiro:hasPermission name="ip:equipmentOpsSpec:edit">${not empty equipmentOpsSpec.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="ip:equipmentOpsSpec:edit">查看</shiro:lacksPermission>
			</a>
		</li>
		<li class="pull-right">
			<input class="btn btn-primary" type="button" value="保 存" onclick="doSubmit();" />
			<input id="btnCancel" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-1)" />
			<input TYPE='BUTTON' class="btn btn-primary" value='重置' onClick="$('#inputForm')[0].reset();">
		</li>
	</ul>
	<br />

	<form:form id="inputForm" modelAttribute="equipmentOpsSpec" action="${ctx}/ip/equipmentOpsSpec/save" method="post" class="form-horizontal">
		<form:hidden path="id" />
		<form:hidden path="equipmentModel.id" value="${equipmentOpsSpec.equipmentModel.id}" />
		<tags:message content="${message}" />
		<table>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label" for="gclb">规程类别:</label>
						<div class="controls">
							<form:select path="gclb">
								<form:options items="${fns:getDictList('d_gflb')}" itemLabel="label" itemValue="value" htmlEscape="false" />
							</form:select>
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label" for="gcmc">规程名称:</label>
						<div class="controls">
							<form:input path="gcmc" htmlEscape="false" maxlength="30" class="required" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label" for="gcms">规程描述:</label>
						<div class="controls" style="width: 10px">
							<form:textarea path="gcms" htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge required" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label" for=xssx>显示顺序:</label>
						<div class="controls">
							<form:input path="xssx" htmlEscape="false" maxlength="30" class="required" value='0' />
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label" for="sfqy">是否启用:</label>
						<div class="controls">
							<form:select path="sfqy">
								<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" />
							</form:select>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label" for="bz">备注:</label>
						<div class="controls" style="width: 10px">
							<form:textarea path="bz" htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge" />
						</div>
					</div>
				</td>
			</tr>

		</table>
	</form:form>
</body>
</html>
