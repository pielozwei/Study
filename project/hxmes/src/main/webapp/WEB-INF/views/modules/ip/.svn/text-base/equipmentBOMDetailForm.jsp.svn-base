<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>设备BOM明细管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		$("#name").focus();
		$("#inputForm").validate();
	});
	function validateForm() {
		//validate方法参数可选
		return $("#inputForm").validate().form();
	}

	function doSubmit() {
		//do other things
		//验证通过后提交
		if (validateForm()) {
			$('#sjzbljId').val($('#sbggxhbmId').val());
			inputForm.submit();
		}
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active">
			<a href="${ctx}/ip/equipmentBOMDetail/form?id=${equipmentBOMDetail.id}&equipmentBOMSheet.id=${equipmentBOMDetail.equipmentBOMSheet.id}&sjbommxid=${sjbommxid}">
				设备BOM明细 ${not empty equipmentBOMDetail.id?'修改':'添加'}</a>
		</li>
		<li class="pull-right">
			<input class="btn btn-primary" type="button" value="保 存" onclick="doSubmit();" />
			<input id="btnCancel" class="btn btn-primary" type="button" value="返 回" onclick="window.location.href='${ctx}/ip/equipmentBOMDetail/?equipmentBOMSheetId=${equipmentBOMDetail.equipmentBOMSheet.id}'" />
			<input TYPE='BUTTON' class="btn btn-primary" value='重置' onClick="$('#inputForm')[0].reset();">
		</li>
	</ul>
	<br />

	<form:form id="inputForm" modelAttribute="equipmentBOMDetail" action="${ctx}/ip/equipmentBOMDetail/save" method="post" class="form-horizontal">
		<form:hidden path="id" />
		<form:hidden path="equipmentBOMSheet.id" value="${equipmentBOMDetail.equipmentBOMSheet.id}" />
		<tags:message content="${message}" />
		<table id="ta01">
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label" for="sbggxhbm">部组零件编码:</label>
						<div class="controls">
							<tags:taglistselect url="/Integrate/getEquipmentModel" name="equipmentModel.id" id="sbggxhbm"
								value="${equipmentBOMDetail.equipmentModel.id}" labelName="sbggxhbm" labelValue="${equipmentBOMDetail.equipmentModel.sbggxhbm}"
								itemCodes="sbggxhbm,sbggxhmc,sbgg,sbxh" itemNames="设备规格型号编号,设备规格型号名称,规格,型号" title="设备规格型号"></tags:taglistselect>
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label" for="sbggxhmc">部组零件名称:</label>
						<div class="controls">
							<input id="sbggxhmc" readonly="readonly" type="text" value="${equipmentBOMDetail.equipmentModel.sbggxhmc}" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<input type="hidden" id="sjzbljId" name="sjzbljId" value="${equipmentBOMDetail.equipmentModel.id}" />
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label" for="gg">规格:</label>
						<div class="controls">
							<input id="sbgg" readonly="readonly" type="text" value="${equipmentBOMDetail.equipmentModel.sbgg}" />
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label" for="xh">型号:</label>
						<div class="controls">
							<input id="sbxh" readonly="readonly" type="text" value="${equipmentBOMDetail.equipmentModel.sbxh}" />
						</div>
					</div>
				</td>
			</tr>


			<tr>
				<td>
					<div class="control-group">
						<label class="control-label" for="sl">数量:</label>
						<div class="controls">
							<form:input path="sl" htmlEscape="false" maxlength="200" class="required digits" />
						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label" for="th">图号:</label>
						<div class="controls">
							<form:input path="th" htmlEscape="false" maxlength="200" />
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label" for="sjbommxid">上级编码:</label>
						<div class="controls">
							<form:input path="sjbommxid" htmlEscape="false" maxlength="200" value="${sjbm}" readonly="true" />

						</div>
					</div>
				</td>
				<td>
					<div class="control-group">
						<label class="control-label" for="sbbomzjlb">类别:</label>
						<div class="controls">
							<form:select path="sbbomzjlb">
								<form:options items="${fns:getDictList('d_sbbomzjlb')}" itemLabel="label" itemValue="value" htmlEscape="false" />
							</form:select>
						</div>
					</div>
				</td>
			</tr>

			<tr>
				<td>
					<div class="control-group">
						<label class="control-label" for="sfgjbj">是否关键:</label>
						<div class="controls">
							<form:select path="sfgjbj">
								<form:options items="${fns:getDictList('d_yesno')}" itemLabel="label" itemValue="value" htmlEscape="false" />
							</form:select>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div class="control-group">
						<label class="control-label" for="bz">备注:</label>
						<div class="controls">
							<form:textarea path="bz" htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge" />
						</div>
					</div>
				</td>
			</tr>
		</table>
	</form:form>
</body>
</html>
