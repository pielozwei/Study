<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>故障树管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		$("#name").focus();
		var code = '${equipmentFault.gzbm}';
		$("#inputForm").validate({
		rules : {
			gzbm : {
				remote : "${ctx}/ip/equipmentFault/checkGzbm?oldGzbm=" + code
			}
		},
		messages : {
			gzbm : {
				remote : "编码已经存在!"
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
			<a href="${ctx}/ip/equipmentFault/form?id=${equipmentFault.id}&equipmentModel.id=${equipmentFault.equipmentModel.id}">
				故障树
				<shiro:hasPermission name="ip:equipmentFault:edit">${not empty equipmentFault.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="ip:equipmentFault:edit">查看</shiro:lacksPermission>
			</a>
		</li>
		<li class="pull-right">
			<input class="btn btn-primary" type="button" value="保 存" onclick="doSubmit();" />
			<input id="btnCancel" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-1)" />
			<input TYPE='BUTTON' class="btn btn-primary" value='重置' onClick="$('#inputForm')[0].reset();">
		</li>
	</ul>
	<br />

	<form:form id="inputForm" modelAttribute="equipmentFault" action="${ctx}/ip/equipmentFault/save" method="post" class="form-horizontal">
		<form:hidden path="id" />
		<form:hidden path="equipmentModel.id" value="${equipmentFault.equipmentModel.id}" />
		<tags:message content="${message}" />
		<table>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label" for="gzbm">故障编码:</label>
						<div class="controls">
							<form:input path="gzbm" htmlEscape="false" maxlength="30" class="required" />
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label" for="gxlb">故障类型:</label>
						<div class="controls">
							<form:select path="gzlb">
								<form:options items="${fns:getDictList('d_qxlb')}" itemLabel="label" itemValue="value" htmlEscape="false" />
							</form:select>
						</div>
					</div>
				</td>
			</tr>

			<tr>
				<td>
					<div class="control-group">
						<label class="control-label" for="qxjbabc">故障级别ABC:</label>
						<div class="controls">
							<form:select path="gzjbabc">
								<form:options items="${fns:getDictList('d_abc')}" itemLabel="label" itemValue="value" htmlEscape="false" />
							</form:select>
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label" for="xxmsgjc">故障关键词:</label>
						<div class="controls">
							<form:input path="xxmsgjc" htmlEscape="false" maxlength="30" class="required"/>
						</div>
					</div>
				</td>
			</tr>

			<tr>
				<td>
					<div class="control-group">
						<label class="control-label" for="gzxx">故障现象:</label>
						<div class="controls" style="width: 10px">
							<form:textarea path="gzxx" htmlEscape="false" rows="4" maxlength="4000" class="input-xxlarge required" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label" for="gzyspwjm">故障音视频文件名:</label>
						<div class="controls">
							<input type="hidden" id="gzyspwjm" name="gzyspwjm" value="${equipmentFault.gzyspwjm}" />
							<tags:ckfinder input="gzyspwjm" type="files" uploadPath="/ip/equipmentFault" selectMultiple="false" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label" for="gzpcznwjm">故障排除指南文件名:</label>
						<div class="controls">
							<input type="hidden" id="gzpcznwjm" name="gzpcznwjm" value="${equipmentFault.gzpcznwjm}" />
							<tags:ckfinder input="gzpcznwjm" type="files" uploadPath="/ip/equipmentFault" selectMultiple="false" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label" for="gzpcyspwjm">故障排除音视频文件名:</label>
						<div class="controls">
							<input type="hidden" id="gzpcyspwjm" name="gzpcyspwjm" value="${equipmentFault.gzpcyspwjm}" />
							<tags:ckfinder input="gzpcyspwjm" type="files" uploadPath="/ip/equipmentFault" selectMultiple="false" />
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
