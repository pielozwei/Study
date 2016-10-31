<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>人员业务类别管理</title>
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
	<div id="input" style="display:">
		<form:form id="inputForm"
			modelAttribute="employeePositionRankDirector"
			action="${ctx}/ip/employeePositionRankDirector/save" method="post"
			style="display: " class="form-horizontal">
			<form:hidden path="id" />
			<tags:message content="${message}" />
			<p>
			<div class="control-group">
				<label class="control-label" for="">单位简称:</label>
				<div class="controls">
					<form:select path="">
						<form:options items="${fns:getDictList('')}" itemLabel="label"
							itemValue="value" htmlEscape="false" class="required" />
					</form:select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="">人员业务类别:</label>
				<div class="controls">
					<form:select path="">
						<form:options items="${fns:getDictList('')}" itemLabel="label"
							itemValue="value" htmlEscape="false" />
					</form:select>
				</div>
			</div>

			<div class="control-group">
				<div class="controls">
					<input id="btnSubmit" class="btn" value="选择人员"
						onclick="jumpToChoose()" />
				</div>
			</div>

			<div class="form-actions">
				<shiro:hasPermission name="ip:employeePositionRankDirector:edit">
					<input id="btnSubmit" class="btn btn-primary" type="submit"
						value="保 存" />&nbsp;
				</shiro:hasPermission>
				<input id="btnCancel" class="btn" type="button" value="返 回"
					onclick="history.go(-1)" />
			</div>
		</form:form>
	</div>

	<div id="choose" style="display: none">
		<table>
			<form:form id="searchForm"
				modelAttribute="employeePositionRankDirector"
				action="${ctx}/ip/employeePositionRankDirector/" method="post"
				class="breadcrumb form-search">
				<tr>
					<td><label>系统身份 ：</label> <form:select path="">
							<option>B</option>
							<option>A</option>
						</form:select>&nbsp;&nbsp;</td>
					<td><label>性别 ：</label> <form:select path="">
							<option>男</option>
							<option>女</option>
						</form:select>&nbsp;&nbsp;</td>
					<td><label>学历 ：</label> <form:select path="">
							<option>B</option>
							<option>A</option>
						</form:select>&nbsp;&nbsp;</td>
					<td>&nbsp;<input id="btnSubmit" class="btn btn-primary"
						type="submit" value="查询" /></td>
				</tr>
				<tr>
					<td><shiro:hasPermission
							name="ip:employeePositionRankDirector:edit">
							<input id="btnAdd" class="btn btn-primary" type="button"
								value="确定" onclick="jumpToInput()" />
						</shiro:hasPermission></td>
				</tr>
			</form:form>
		</table>
		<p>

			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
			<input id="pageSize" name="pageSize" type="hidden"
				value="${page.pageSize}" />


			<%-- 	<label>名称 ：</label>
		<form:input path="id" htmlEscape="false" maxlength="50"
			class="input-small" /> --%>

			<tags:message content="${message}" />
		<table id="contentTable"
			class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th><input name="check" type="checkbox" id="SelectAll"
						onclick="CheckSelect()" />全选</th>
					<th>序列</th>
					<th>姓名</th>
					<th>性别</th>
					<th>业务属性</th>
					<th>系统身份</th>
					<th>学历</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list}" var="employeePositionRankDirector">
					<tr>
						<td><input type="checkbox"
							value="${employeePositionRankDirector.id}" name="Select"
							class="Select" /></td>

						<!--  -->






					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>
