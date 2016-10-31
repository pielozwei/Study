<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>组织机构管理管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {

	});
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}

	function edit_click() {
		var rusult = 0;
		var check_array = document.getElementsByName("check");
		for (var i = 0; i < check_array.length; i++) {
			if (check_array[i].checked == true) {
				rusult = check_array[i].value;
			}
		}
		window.location.href = "${ctx}/ip/organization/form?id=" + rusult;
		//window.location.href="/hxmes/ip/organization/form?id="+rusult；
		//alert("edit"+rusult);
	}

	function del_click() {
		var rusult = 0;
		var check_array = document.getElementsByName("check");
		for (var i = 0; i < check_array.length; i++) {
			if (check_array[i].checked == true) {
				rusult = check_array[i].value;
			}
		}
		var herf = "${ctx}/ip/organization/delete?id=" + rusult;
		confirmx('确认要删除该组织机构管理吗？', herf);
	}
</script>
</head>
<body>

	<form:form id="searchForm" modelAttribute="organization"
		action="${ctx}/ip/organization/list" method="post"
		class="breadcrumb form-search">
		<shiro:hasPermission name="ip:organizationDepartment:edit">
			<input id="btnAdd" class="btn btn-primary" type="button" value="新增部门" onclick="window.location.href='${ctx}/ip/organizationDepartment/form'" />
		</shiro:hasPermission>
	    &nbsp;
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
		<label>机构名称 ：</label>
		<form:input path="jgmc" htmlEscape="false" maxlength="50" class="input-small" />
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" />
	</form:form>
	<tags:message content="${message}" />
	<div style="overflow: auto; width: 100%;">
		<table id="contentTable" class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>选择</th>
					<th>机构名称</th>
					<th>机构简称</th>
					<th>机构编码</th>
					<th>联系电话</th>
					<th>行政主管领导名称</th>
					<th>行政副管领导名称</th>
					<th>党务主管领导名称</th>
					<th>党务副管领导名称</th>
					<th>机构简介</th>
					<th>是否有分址</th>
					<th>备注</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list}" var="organization">
					<tr>
						
						<td><input type="checkbox" value="${organization.id}" name="check" /></td>
						<td><a class="jgmc" href="${ctx}/ip/organization/form?id=${organization.id}">${organization.jgmc}</a></td>
						<td>${organization.jgjc}</td>
						<td>${organization.jgbm}</td>
						<td>${organization.lxdh}</td>
						<td>${organization.xzzgldmc}</td>
						<td>${organization.xzfgldmc}</td>
						<td>${organization.dwzgldmc}</td>
						<td>${organization.dwfgldmc}</td>
						<td>${organization.jgjj}</td>
						<td>${organization.sfyfz}</td>
						<td>${organization.remarks}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	&nbsp;
	<input id="btnQuery" class="btn btn-primary" type="submit" value="修改" onclick="edit_click()" />&nbsp;
	<input id="btnDelete" class="btn btn-primary" type="button" value="删除" onclick="del_click()" />
	<div class="pagination">${page}</div>
</body>
</html>
