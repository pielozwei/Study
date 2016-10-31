<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>缺陷树管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		$("#qxmc").focus();
		var code = '${equipmentDefect.qxbm}';
		$("#inputForm").validate({
		rules : {
			qxbm : {
				remote : "${ctx}/ip/equipmentDefect/checkQxbm?oldQxbm=" + code
			}
		},
		messages : {
			qxbm : {
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
			<a href="${ctx}/ip/equipmentDefect/form?id=${equipmentDefect.id}&equipmentModel.id=${equipmentDefect.equipmentModel.id}">
				缺陷树
				<shiro:hasPermission name="ip:equipmentDefect:edit">${not empty equipmentDefect.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="ip:equipmentDefect:edit">查看</shiro:lacksPermission>
			</a>
		</li>
		<li class="pull-right">
			<input class="btn btn-primary" type="button" value="保 存" onclick="$('#inputForm').submit();" />
			<input id="btnCancel" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-1)" />
			<input TYPE='BUTTON' class="btn btn-primary" value='重置' onClick="$('#inputForm')[0].reset();">
		</li>
	</ul>
	<br />

	<form:form id="inputForm" modelAttribute="equipmentDefect" action="${ctx}/ip/equipmentDefect/save" method="post" class="form-horizontal">
		<form:hidden path="id" />
		<form:hidden path="equipmentModel.id" value="${equipmentDefect.equipmentModel.id}" />
		<tags:message content="${message}" />
		<table>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label" for="qxbm">缺陷编码:</label>
						<div class="controls">
							<form:input path="qxbm" htmlEscape="false" maxlength="30" class="required" />
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label" for="qxlb">缺陷类型:</label>
						<div class="controls">
							<form:select path="qxlb">
								<form:options items="${fns:getDictList('d_qxlb')}" itemLabel="label" itemValue="value" htmlEscape="false" />
							</form:select>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label" for="qxjbabc">缺陷ABC:</label>
						<div class="controls">
							<form:select path="qxjbabc">
								<form:options items="${fns:getDictList('d_abc')}" itemLabel="label" itemValue="value" htmlEscape="false" />
							</form:select>
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label" for="xxmsgjc">缺陷关键词:</label>
						<div class="controls">
							<form:input path="xxmsgjc" htmlEscape="false" maxlength="30" class="required" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label" for="qxxx">缺陷现象:</label>
						<div class="controls" style="width: 10px">
							<form:textarea path="qxxx" htmlEscape="false" rows="4" class="input-xxlarge required" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label" for="qxyspwjm">缺陷音视频文件名:</label>
						<div class="controls">
							<input type="hidden" id="qxyspwjm" name="qxyspwjm" value="${equipmentDefect.qxyspwjm}" />
							<tags:ckfinder input="qxyspwjm" type="files" uploadPath="/ip/equipmentDefect" selectMultiple="false" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label" for="qxpcznwjm">缺陷排除指南文件名:</label>
						<div class="controls">
							<input type="hidden" id="qxpcznwjm" name="qxpcznwjm" value="${equipmentDefect.qxpcznwjm}" />
							<tags:ckfinder input="qxpcznwjm" type="files" uploadPath="/ip/equipmentDefect" selectMultiple="false" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label" for="qxpcyspwjm">缺陷排除音视频文件名:</label>
						<div class="controls">
							<input type="hidden" id="qxpcyspwjm" name="qxpcyspwjm" value="${equipmentDefect.qxpcyspwjm}" />
							<tags:ckfinder input="qxpcyspwjm" type="files" uploadPath="/ip/equipmentDefect" selectMultiple="false" />
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
