<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>人员岗位设置管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		$("#name").focus();
		$("#inputForm").validate();
	});
	function jumpToChoose() {
		var input = document.getElementById("input");
		var choose = document.getElementById("choose");
		input.style.display = "none";
		choose.style.display = "";
	}
	function jumpToInput() {
		var input = document.getElementById("input");
		var choose = document.getElementById("choose");
		input.style.display = "";
		choose.style.display = "none";
	}
</script>
</head>
<body>
	
	<ul class="nav nav-tabs">

		<li>人员职务身份设置</li>
		<li class="pull-right">
			<input class="btn btn-primary" type="button" value="保 存" onclick="$('#inputForm').submit();" />
			<input id="btnCancel" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-1)" />
			<input TYPE='BUTTON' class="btn btn-primary" value='重置' onClick="$('#inputForm')[0].reset();">
		</li>
	</ul>
	
	<div id="input" style="display:">
		<form:form id="inputForm" modelAttribute="employeePositionDirector"
			action="${ctx}/ip/employeePositionDirector/save" method="post"
			style="display: " class="form-horizontal">
			<form:hidden path="id" />
			<tags:message content="${message}" />
			<p>
			<div class="control-group">
				<label class="control-label" for="">单位简称:</label>
				<div class="controls">
					<tags:treeselect id="" name="employeePositionDirector.organizationposition.organization.id"
								value="${list.id}" labelName="employeePositionDirector.organizationposition.organization.jgmc"
								labelValue="${list.name}" title="单位"
								url="/ip/organization/treeData" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="">人员业务分类:</label>
				<div class="controls">
					<form:select path="" class="input-xlarge">
						<c:forEach var="ryywlist" items="${ryywlist}" varStatus="s">
                    		<option value="${ryywlist.id}" <c:if test="${ryywlist.id eq employeePositionDirector.organizationposition.organizationposintionrank.id}">selected="selected"</c:if> >${ryywlist.RYYWLBMC}</option>
                    	</c:forEach>
					</form:select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="organizationposition.id">岗位:</label>
				<div class="controls">
					<form:select path="organizationposition.id" class="input-xlarge">
						<c:forEach var="organizationpositionlist" items="${organizationpositionlist}" varStatus="s">
                        	<option value="${organizationpositionlist.id}" <c:if test="${organizationpositionlist.id eq employeePositionDirector.organizationposition.id}">selected="selected"</c:if>>${organizationpositionlist.gwmc}</option>
                        </c:forEach>
					</form:select>
				</div>
			</div>

			<div class="control-group">
				<label class="control-label" for="employee.id">选择人员:</label>
				<div class="controls">
					<tags:treeselect id="zg_id" name="employee.id" checked="true"
						value="${list.id}" labelName="list.name"
						labelValue="${list.name}" title="职工"
						url="/ip/employee/treeData" cssClass="required"/>
				</div>
			</div>

			<div class="form-actions">
				<shiro:hasPermission name="ip:employeePositionDirector:edit">
					<input id="btnSubmit" class="btn btn-primary" type="submit"
						value="保 存" />&nbsp;
			</shiro:hasPermission>
				<input id="btnCancel" class="btn" type="button" value="返 回"
					onclick="history.go(-1)" />
			</div>
		</form:form>
	</div>
</body>
</html>
