<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>设备BOM管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		$("#sbbomdbh").focus();
		var code = '${equipmentBOMSheet.sbbomdbh}';
		$("#inputForm").validate({
		rules : {
			sbbomdbh : {
				remote : "${ctx}/ip/equipmentBOMSheet/checkSbbomdbh?oldSbbomdbh=" + code
			}
		},
		messages : {
			sbbomdbh : {
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
			<a href="${ctx}/ip/equipmentBOMSheet/form?id=${equipmentBOMSheet.id}&equipmentModel.id=${equipmentBOMSheet.equipmentModel.id}">
				设备BOM
				<shiro:hasPermission name="ip:equipmentBOMSheet:edit">${not empty equipmentBOMSheet.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="ip:equipmentBOMSheet:edit">查看</shiro:lacksPermission>
			</a>
		<li class="pull-right">
			<input class="btn btn-primary" type="button" value="保 存" onclick="doSubmit();" />
			<input id="btnCancel" class="btn btn-primary" type="button" value="返 回" onclick="window.location.href='${ctx}/ip/equipmentBOMSheet/?equipmentModelId=${equipmentModelId}'" />
			<input TYPE='BUTTON' class="btn btn-primary" value='重置' onClick="$('#inputForm')[0].reset();">
		</li>


	</ul>
	<br />

	<form:form id="inputForm" modelAttribute="equipmentBOMSheet" action="${ctx}/ip/equipmentBOMSheet/save" method="post" class="form-horizontal">
		<form:hidden path="id" />
		<form:hidden path="equipmentModel.id" value="${equipmentBOMSheet.equipmentModel.id}" />
		<tags:message content="${message}" />
		<table>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label" for="sbbomdbh">BOM单编码:</label>
						<div class="controls">
							<form:input path="sbbomdbh" htmlEscape="false" maxlength="200" class="required" />
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label" for="bbh">版本号:</label>
						<div class="controls">
							<form:input path="bbh" htmlEscape="false" maxlength="200" class="required" />
						</div>
					</div>
				</td>
			</tr>

			<tr>
				<td>
					<div class="control-group">
						<label class="control-label" for="cjr">创建人:</label>
						<div class="controls">
							<form:input path="cjr" htmlEscape="false" maxlength="200" />
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label" for="cjrq">创建日期:</label>
						<div class="controls">
							<input id="cjrq" name="cjrq" type="text" readonly="readonly" maxlength="20" class="input-small Wdate"
								value="<fmt:formatDate value="${equipmentBOMSheet.cjrq}" pattern="yyyy-MM-dd"/>"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label" for="shr">审核人:</label>
						<div class="controls">
							<form:input path="shr" htmlEscape="false" maxlength="200" />
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label" for="shrq">审核日期:</label>
						<div class="controls">
							<input id="shrq" name="shrq" type="text" readonly="readonly" maxlength="20" class="input-small Wdate "
								value="<fmt:formatDate value="${equipmentBOMSheet.shrq}" pattern="yyyy-MM-dd"/>"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label" for="sbbomdzt">状态:</label>
						<div class="controls">
							<form:select path="sbbomdzt">
								<form:options items="${fns:getDictList('d_shiyong')}" itemLabel="label" itemValue="value" htmlEscape="false" />
							</form:select>
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
		<c:if test="${not empty equipmentBOMSheet.id}">
			<div >
				<iframe id="detailList" src="${ctx}/ip/equipmentBOMDetail/?equipmentBOMSheetId=${equipmentBOMSheet.id}" style="overflow: hidden;" scrolling="no"
					frameborder="no" width="100%" height="510px"></iframe>
			</div>
		</c:if>
	</form:form>
</body>
</html>
