<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>存储白名单管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		$("#wl_id").focus();
		$("#inputForm").validate();
	});
</script>
</head>
<body>
	<br />
	<form:form id="inputForm" modelAttribute="techniqueStore"
		action="${ctx}/ip/techniqueStore/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<tags:message content="${message}" />

		<table>
			<tr>
				<td><div class="control-group">
						<label class="control-label" for="">工艺路线:</label>
						<div class="controls">
							<tags:treeselect id="" name="" value="${list.id}"
								labelName="name" labelValue="${list.name}" title="工艺路线:"
								url="/ip/workCenter/treeData" />
						</div>
					</div></td>
				<td><div class="control-group">
						<label class="control-label" for="">工艺路线编码:</label>
						<div class="controls">
							<form:input path="" htmlEscape="false" maxlength="200"
								class="required" />
						</div>
					</div></td>
			</tr>

			<tr>
				<td><div class="control-group">
						<label class="control-label" for="">工作中心名称:</label>
						<div class="controls">
							<form:input path="" htmlEscape="false" maxlength="200"
								class="required" />
						</div>
					</div></td>

				<td><div class="control-group">
						<label class="control-label" for="gygzzx_id">工作中心编码:</label>
						<div class="controls">
							<form:input path="gygzzx_id" htmlEscape="false" maxlength="200"
								class="required" />
						</div>
					</div></td>
			</tr>
		</table>

		<div class="control-group">
			<label class="control-label" for="">是否默认工作中心:</label>
			<div class="controls">
				<form:select path="">
					<form:options items="${fns:getDictList('d_yesno_IP_GYGZZX')}"
						itemLabel="label" itemValue="value" htmlEscape="false"
						class="required" />
				</form:select>
			</div>
		</div>

		<table>
			<tr>
				<td><div class="control-group">
						<label class="control-label" for="">物料:</label>
						<div class="controls">
							<tags:treeselect id="" name="" value="${list.id}"
								labelName="name" labelValue="${list.name}" title="物料:"
								url="/ip/workCenter/treeData" />
						</div>
					</div></td>
				<td><div class="control-group">
						<label class="control-label" for="wl_id">物料编码:</label>
						<div class="controls">
							<form:input path="wl_id" htmlEscape="false" maxlength="200"
								class="required" />
						</div>
					</div></td>
			</tr>

			<tr>
				<td><div class="control-group">
						<label class="control-label" for="">仓库:</label>
						<div class="controls">
							<tags:treeselect id="" name="" value="${list.id}"
								labelName="name" labelValue="${list.name}" title="工作中心名称:"
								url="/ip/workCenter/treeData" />
						</div>
					</div></td>

				<td><div class="control-group">
						<label class="control-label" for="ccck_id">仓库编码:</label>
						<div class="controls">
							<form:input path="ccck_id" htmlEscape="false" maxlength="200"
								class="required" />
						</div>
					</div></td>
			</tr>


			<tr>
				<td><div class="control-group">
						<label class="control-label" for="sfqy">是否启用:</label>
						<div class="controls">
							<form:select path="sfqy">
								<form:options items="${fns:getDictList('')}" itemLabel="label"
									itemValue="value" htmlEscape="false" class="required" />
							</form:select>
						</div>
					</div></td>
				<td><div class="control-group">
						<label class="control-label" for="xssx">显示顺序:</label>
						<div class="controls">
							<form:input path="xssx" htmlEscape="false" maxlength="200"
								class="required" />
						</div>
					</div></td>
			</tr>
		</table>

		<div class="control-group">
			<label class="control-label" for="bz">备注:</label>
			<div class="controls">
				<form:textarea path="bz" htmlEscape="false" maxlength="200" />
			</div>
		</div>

		<div class="form-actions">
			<shiro:hasPermission name="ip:techniqueStore:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit"
					value="保 存" />&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回"
				onclick="history.go(-1)" />
		</div>
	</form:form>
</body>
</html>
