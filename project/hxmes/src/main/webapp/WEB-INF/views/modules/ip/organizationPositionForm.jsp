<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>岗位信息管理管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		$("#gwbm").focus();
		$("#inputForm").validate();
	});
</script>
</head>
<body>
	 
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/ip/organizationPosition/">维护岗位信息</a></li>
		<li class="pull-right">
			<input class="btn btn-primary" type="button" value="保 存" onclick="$('#inputForm').submit();" />
			<input id="btnCancel" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-1)" />
			<input TYPE='BUTTON' class="btn btn-primary" value='重置' onClick="$('#inputForm')[0].reset();">
		</li>
	</ul>
	<br />

	<form:form id="inputForm" modelAttribute="organizationPosition"
		action="${ctx}/ip/organizationPosition/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<tags:message content="${message}" />
		<div class="control-group">
			<label class="control-label" for="gwbm">岗位编码:</label>
			<div class="controls">
				<form:input path="gwbm" htmlEscape="false" rows="4" maxlength="200"
					class="input-large required" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="gwmc">岗位名称:</label>
			<div class="controls">
				<form:input path="gwmc" htmlEscape="false" rows="4" maxlength="200"
					class="input-large required" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="gwms">岗位描述:</label>
			<div class="controls">
				<form:textarea path="gwms" htmlEscape="false" maxlength="200"
					class="input-large" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="organizationposintionrank.id">人员业务分类:</label>
			<div class="controls">
				<form:select path="organizationposintionrank.id" class="input-xlarge">
					<option value="">--请选择--</option>
					<c:forEach var="PRlist" items="${PRlist}" varStatus="s">
                    	<option value="${PRlist.id}" <c:if test="${PRlist.id eq organizationPosition.organizationposintionrank.id}">selected="selected"</c:if> >${PRlist.RYYWLBMC}</option>
                    </c:forEach>
				</form:select>
			</div>
		</div>
		<table>
			<tr>

				<td><div class="control-group">
						<label class="control-label" for="zzjgbm_id">部门:</label>
						<div class="controls">
							<tags:treeselect id="zzjgbm_id" name="organization.id"
								value="${list.id}" labelName="organization.jgmc"
								labelValue="${list.name}" title="部门"
								url="/ip/organization/treeData" />
						</div>
					</div></td>
			</tr>
		</table>
		<div class="control-group">
			<label class="control-label" for="sfqy">是否启用:</label>
			<div class="controls">
				<form:select path="sfqy">
					<form:options items="${fns:getDictList('yes_no')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
				&nbsp;
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="xssx">显示顺序:</label>
			<div class="controls">
				<form:input path="xssx" htmlEscape="false" rows="4" maxlength="200"
					class="input-large required" />
			</div>
		</div>
	</form:form>
</body>
</html>
