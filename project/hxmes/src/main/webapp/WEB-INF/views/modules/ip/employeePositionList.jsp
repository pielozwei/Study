<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>行政党派职务经历管理</title>
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
	function selectAll() {
		var all = document.getElementById("selectAll");
		var checkbox = document.getElementsByName("select");
		for (var i = 0; i < checkbox.length; i++) {
			if (all.checked == true) {
				checkbox[i].checked = true;
			} else {
				checkbox[i].checked = false;
			}
		}
	}
	function edit_click() {
		var result = 0;
		var number = 0;
		var check_array = document.getElementsByName("select");
		for (var i = 0; i < check_array.length; i++) {
			if (check_array[i].checked == true) {
				result = check_array[i].id;
				number++;
			}
		}
		if (number > 1) {
			alert("一次只能修改一条信息");
			return;
		} else if (number == 0) {
			alert("请选中一条需要修改的信息");
			return;
		}
		window.location.href = "${ctx}/ip/employeePosition/form?id=" + result;
		//window.location.href="/hxmes/ip/organization/form?id="+result；
		//alert("edit"+result);
	}

	function del_click() {
		var number = 0;
		var result = 0;
		var check_array = document.getElementsByName("select");
		for (var i = 0; i < check_array.length; i++) {
			if (check_array[i].checked == true) {
				result = check_array[i].id;
				number++;
			}
		}
		if (number == 0) {
			alert("请选中一条需要删除的信息");
			return;
		} else if (number > 1) {
			var idAll = new Array();
			var count = 0;
			var herfs = new Array();
			for (var i = 0; i < check_array.length; i++) {
				if (check_array[i].checked == true) {
					idAll[count] = check_array[i].id;
					count++;
				}
			}
			for (var j = 0; j < count; j++) {
				herfs[j] = idAll[j];
			}
			var herfss = "${ctx}/ip/employeePosition/deletes?id="
					+ herfs.toString();
			confirmx('确认要删除这些政党派职务经历吗？', herfss);
		} else {
			var herf = "${ctx}/ip/employeePosition/delete?id=" + result;
			confirmx('确认要删除该行政党派职务经历吗？', herf);
		}
	}
	window.onload = function() {
		var xl = document.getElementsByClassName("xl");
		for (i = 0; i < xl.length; i++) {
			xl[i].innerHTML = i + 1;
		}
	}
</script>
</head>
<body>

	<ul class="nav nav-tabs">
			<li class="active">
				<a href="#">职工党派行政经历</a>
			</li>
			<li class="pull-right">
				<input id="btnAdd" class="btn btn-primary " type="button" value="新增" onclick="window.location.href='${ctx}/ip/employeePosition/form'" />
			</li>
		</ul>
	<form:form id="searchForm" modelAttribute="employeePosition"
		action="${ctx}/ip/employeePosition/list" method="post"
		class="breadcrumb form-search">

	    &nbsp;
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<label>职工姓名 ：</label>
		<tags:treeselect id="zg_id" name="employee.id"
					value="${list.id}" labelName="employee.xm" labelValue="${list.name}"
					title="姓名：" url="/ip/employee/treeData" />
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit"
			value="查询" />
	</form:form>

	<tags:message content="${message}" />
	<div style="overflow: auto; width: 100%;">
		<table id="contentTable"
			class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th><input type="checkbox" name="selectAll" id="selectAll"
						onClick="selectAll()" />全选</th>
					<th>序列</th>
					<th>姓名</th>
					<th>当前任职状态</th>
					<th>职务</th>
					<th>决定或批准任职的日期</th>
					<th>任职文号</th>
					<th>备案时间</th>
					<th>审核状态</th>

				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list}" var="employeePosition">
					<tr>
						<td><input type="checkbox" name="select" class="select"
							id="${employeePosition.id}"></td>
						<td class="xl">${employeePosition.xl}</td>
						<td><a
							href="${ctx}/ip/employeePosition/form?id=${employeePosition.id}">${employeePosition.employee.xm}</a></td>
						<td>${employeePosition.dqrzzt}</td>
						<td>${employeePosition.zw}</td>
						<td><fmt:formatDate value="${employeePosition.pzrq}"
								pattern='yyyy-MM-dd'></fmt:formatDate></td>
						<td>${employeePosition.rzwh}</td>
						<td><fmt:formatDate value="${employeePosition.basj}"
								pattern='yyyy-MM-dd'></fmt:formatDate></td>
						<td>审核状态</td>
						<!-- 
					<shiro:hasPermission name="ip:employeePosition:edit">
						<td><a
							href="${ctx}/ip/employeePosition/form?id=${employeePosition.id}"
							class="btn btn-primary">修改</a> <a
							href="${ctx}/ip/employeePosition/delete?id=${employeePosition.id}"
							class="btn btn-primary"
							onclick="return confirmx('确认要删除该行政党派职务经历吗？', this.href)">删除</a></td>
					</shiro:hasPermission>
					 -->
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<input id="btnQuery" class="btn btn-primary" type="button" value="修改"
		onclick="edit_click()" />&nbsp;
	<input id="btnDelete" class="btn btn-primary" type="button" value="删除"
		onclick="del_click()" /> &nbsp;
	<div class="pagination">${page}</div>
</body>
</html>
