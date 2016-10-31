<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>组织考核经历管理</title>
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
	function checkAll() {
		var subBoxs = document.getElementsByClassName("subBoxs");
		for (var i = 0; i < subBoxs.length; ++i) {
			subBoxs[i].checked = true;
		}
	}
	function unCheckAll() {
		var subBoxs = document.getElementsByClassName("subBoxs");
		for (var i = 0; i < subBoxs.length; ++i) {
			subBoxs[i].checked = false;
		}
	}
	function setAll() {
		var all = document.getElementById("all");
		if (all.checked == true) {
			checkAll();
		} else {
			unCheckAll();
		}
	}
	function edit_click() {
		var result = 0;
		var number = 0;
		var check_array = document.getElementsByClassName("subBoxs");
		for (var i = 0; i < check_array.length; i++) {
			if (check_array[i].checked == true) {
				result = check_array[i].value;
				number++
			}
		}
		if (number == 0) {
			alert("请选中一条需要修改的信息");
			return false;
		} else if (number > 1) {
			alert("一次只能修改一条信息");
			return false;
		}
		window.location.href = "${ctx}/ip/employeeAppraisal/form?id=" + result;
	}

	function del_click() {
		var result = 0;
		var number = 0;
		var check_array = document.getElementsByClassName("subBoxs");
		for (var i = 0; i < check_array.length; i++) {
			if (check_array[i].checked == true) {
				result = check_array[i].value;
				number++;
			}
		}
		if (number == 0) {
			alert("请选中一条需要删除的信息");
			return false;
		}else if (number > 1) {
			var idAll = new Array();
			var count = 0;
			var herfs = new Array();
			for (var i = 0; i < check_array.length; i++) {
				if (check_array[i].checked == true) {
					idAll[count] = check_array[i].value;
					count++;
				}
			}
			for (var j = 0; j < count; j++) {
				herfs[j] = idAll[j];
			}
			var herfss = "${ctx}/ip/employeeAppraisal/deletes?id="
					+ herfs.toString();
			confirmx('确认要删除这些组织机构信息吗？', herfss);
		} else {
			var herf = "${ctx}/ip/employeeAppraisal/delete?id=" + result;
			confirmx('确认要删除该行组织机构信息吗？', herf);
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
				<a href="#">职工组织考核记录</a>
			</li>
			<li class="pull-right">
				<input id="btnAdd" class="btn btn-primary " type="button" value="新增" onclick="window.location.href='${ctx}/ip/employeeAppraisal/form'" />
			</li>
		</ul>
	<form:form id="searchForm" modelAttribute="employeeAppraisal"
		action="${ctx}/ip/employeeAppraisal/" method="post"
		class="breadcrumb form-search">

		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<label>姓名 ：</label>
		<tags:treeselect id="zg_id" name="employee.id" value="${list.id}" labelName="name" labelValue="${list.name}" title="员工" url="/ip/employee/treeData" />
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit"
			value="查询" />
	</form:form>
	<tags:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th><input type="checkbox" id="all" onclick="setAll()" />全选</th>
				<th>序列</th>
				<th>姓名</th>
				<th>考核年度</th>
				<th>考核结论类别</th>
				<th>参加考核不定等次原因</th>
				<th>未参加考核原因</th>
				<th>审核状态</th>
				<shiro:hasPermission name="ip:employeeAppraisal:edit"></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="employeeAppraisal">
				<tr>
					<td><input type="checkbox" value="${employeeAppraisal.id}"
						class="subBoxs" /></td>
					<td class="xl">${employeeAppraisal.xl}</td>
					<td>${employeeAppraisal.employee.xm}</td>
					<td>${employeeAppraisal.khnd}</td>
					<td>${employeeAppraisal.khjllb}</td>
					<td>${employeeAppraisal.cjkhbddcyy}</td>
					<td>${employeeAppraisal.wcjkhyy}</td>
					<td>审核状态</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<input id="btnQuery" class="btn btn-primary" type="button" value="修改"
		onclick="edit_click()" />&nbsp;
	<input id="btnDelete" class="btn btn-primary" type="button" value="删除"
		onclick="del_click()" />
	<div class="pagination">${page}</div>
</body>
</html>
