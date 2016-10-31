<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>分管单位设置管理</title>
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
		if(rusult==0){
			alertx('请至少选择一项');
		}else{
			window.location.href = "${ctx}/ip/organizationDirector/form?id="
				+ rusult;
		}
		
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
		if(rusult==0){
			alertx('请至少选择一项');
		}else{
			var herf = "${ctx}/ip/organizationDirector/delete?id=" + rusult;
			confirmx('确认要删除该组织机构管理吗？', herf);
		}


	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ip/organizationDirector/">分管单位设置列表</a></li>
		<li class="pull-right">
			<input id="btnAdd" class="btn btn-primary" type="button"
						value="添加分管单位"
						onclick="window.location.href='${ctx}/ip/organizationDirector/form'" />
		</li>
	</ul>
	
	<form:form id="searchForm" modelAttribute="organizationDirector"
		action="${ctx}/ip/organizationDirector/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<label>工号：</label>
		<tags:treeselect id="zg_id" name="employee.id"
					value="${list.id}" labelName="list.name"
					labelValue="${list.name}" title="职工"
					url="/ip/employee/treeData" cssClass="required"/>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit"
			value="查询" />
	</form:form>
	<tags:message content="${message}" />
	<div style="overflow: auto; width: 100%;">
		<table id="contentTable"
			class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>选择</th>
					<th>职工编号</th>
					<th>姓名</th>
					<th>性别</th>
					<th>业务属性</th>
					<th>系统身份</th>
					<th>组织机构</th>

					<!--
				<th>备注</th>
				<shiro:hasPermission name="ip:organizationDirector:edit">
				
					<th>操作</th>
				</shiro:hasPermission>
				-->
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list}" var="organizationDirector">
					<tr>
						<td><input type="checkbox" value="${organizationDirector.id}"
							name="check" /></td>
						<td><a
							href="${ctx}/ip/organizationDirector/form?id=${organizationDirector.id}">${organizationDirector.employee.gh}</a></td>
							<td>${organizationDirector.employee.xm}</td>
							<td>${fns:getDictLabel(organizationDirector.employee.xb, 'd_xb', '无')}</td>
							<td></td>
							<td>${fns:getDictLabel(organizationDirector.employee.xtsfdm, 'd_sfdm', '无')}</td>
							<td>${organizationDirector.organization.jgmc}</td>

					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<input id="btnQuery" class="btn btn-primary" type="button" value="修改"
		onclick="edit_click()" />&nbsp;
	<input id="btnDelete" class="btn btn-primary" type="button" value="删除"
		onclick="del_click()" />
	<div class="pagination">${page}</div>
</body>
</html>
