<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>能源定额管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#name").focus();
			$("#inputForm").validate();
		});
	</script>
</head>
<body>
	<br/>
	<form:form id="inputForm" modelAttribute="techniqueEnergy" action="${ctx}/ip/techniqueEnergy/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<tags:message content="${message}"/>
				<table>
		<tr>
			<td>
				<div class="control-group">
					<label class="control-label" for="">工艺路线:</label>
					<div class="controls">
						<tags:treeselect id="" name="" value="${list.id}" labelName="name"
							labelValue="${list.name}" title="工艺路线："
							url="" />
					</div>
				</div>
			</td>
			<td>
				<div class="control-group">
					<label class="control-label" for="">工艺路线编码:</label>
					<div class="controls">
						<tags:treeselect id="" name="" value="${list.id}" labelName="name"
							labelValue="${list.name}" title="工艺路线编码："
							url="" />
					</div>
				</div>
			</td>
		</tr>
		
		<tr>
			<td>
				<div class="control-group">
					<label class="control-label" for="">工艺中心名称:</label>
					<div class="controls">
						<tags:treeselect id="" name="" value="${list.id}" labelName="name"
							labelValue="${list.name}" title="工艺中心名称："
							url="" />
					</div>
				</div>
			</td>
			<td>
				<div class="control-group">
					<label class="control-label" for="">工艺中心编码:</label>
					<div class="controls">
						<tags:treeselect id="" name="" value="${list.id}" labelName="name"
							labelValue="${list.name}" title="工艺中心编码："
							url="" />
					</div>
				</div>
			</td>
		</tr>
		
		<tr>
			<td>
				<div class="control-group">
					<label class="control-label" for="">是否默认工作中心:</label>
					<div class="controls">
						<tags:treeselect id="" name="" value="${list.id}" labelName="name"
							labelValue="${list.name}" title="是否默认工作中心："
							url="" />
					</div>
				</div>
			</td>
		</tr>
		
		<tr>
			<td>
				<div class="control-group">
					<label class="control-label" for="nylx">能源类型:</label>
					<div class="controls">
						<form:select path="nylx">
							<form:options items="${fns:getDictList('d_nylx')}" itemValue="value" itemLabel="label" htmlEscape="false"/>
						</form:select>
					</div>
				</div>
			</td>
		</tr>
		
		<tr>
			<td>
				<div class="control-group">
					<label class="control-label" for="jldw_id">计量单位:</label>
					<div class="controls">
						<form:select path="jldw_id">
							<form:options items="${fns:getDictList('d_jldw')}" itemValue="value" itemLabel="label" htmlEscape="false"/>
						</form:select>
					</div>
				</div>
			</td>
			<td>
				<div class="control-group">
					<label class="control-label" for="sl">消耗定额:</label>
					<div class="controls">
						<form:input path="sl" htmlEscape="false" class="input-xlarge"/>
					</div>
				</div>
			</td>
		</tr>
		
		<tr>
			<td>
				<div class="control-group">
					<label class="control-label" for="sfqy">是否启用:</label>
					<div class="controls">
						<form:select path="sfqy" class="required">
							<form:option value="" label="请选择" />
							<form:options items="${fns:getDictList('yes_no')}"
								itemLabel="label" itemValue="value" htmlEscape="false" />
						</form:select>
					</div>
				</div>
			</td>
			<td>
				<div class="control-group">
					<label class="control-label" for="xssx">显示顺序:</label>
					<div class="controls">
						<form:input path="xssx" htmlEscape="false" maxlength="200"
							class="required" />
					</div>
				</div>
			</td>
		</tr>
		
		<tr>
			<td>
				<div class="control-group">
					<label class="control-label" for="bz">备注:</label>
					<div class="controls">
						<form:textarea path="bz" htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge"/>
					</div>
				</div>
			</td>
		</tr>
		</table>
		<div class="form-actions">
			<shiro:hasPermission name="ip:techniqueEnergy:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
