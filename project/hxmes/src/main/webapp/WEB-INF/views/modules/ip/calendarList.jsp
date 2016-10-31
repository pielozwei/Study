<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>工厂日历方案管理</title>
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
		window.location.href = "${ctx}/ip/calendar/form?id=" + result;
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
		} else if (number > 1) {
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
			var herfss = "${ctx}/ip/calendar/deletes?id="
					+ herfs.toString();
			confirmx('确认要删除这些工厂日历方案吗？', herfss);
		} else {
			var herf = "${ctx}/ip/calendar/delete?id=" + result;
			confirmx('确认要删除该行工厂日历方案吗？', herf);
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
				<a href="#">工厂日历方案</a>
			</li>
			<li class="pull-right">
				<input id="btnAdd" class="btn btn-primary " type="button" value="新增" onclick="window.location.href='${ctx}/ip/calendar/form'" />
			</li>
		</ul>
	<form:form id="searchForm" modelAttribute="calendar"
		action="${ctx}/ip/calendar/" method="post"
		class="breadcrumb form-search">

	    &nbsp;
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<label>方案名称：</label>
		<form:input path="famc" htmlEscape="false" maxlength="50"
			class="input-small" />
			&nbsp;
		<!-- <label>年份：</label>
		<form:input path="nf" htmlEscape="false" maxlength="50"
			class="input-small" /> -->
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit"
			value="查询" />
	</form:form>
	<tags:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th><input type="checkbox" id="all" onclick="setAll()" />全选</th>
				<th>方案名称</th>
				<th>年份</th>
				<!--<shiro:hasPermission name="ip:calendar:edit">
					<th>操作</th>
				</shiro:hasPermission>-->
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="calendar">
				<tr>
					<td class="xl"></td>
					<td><input type="checkbox" value="${calendar.id}"
						class="subBoxs" /></td>
					<td><a
						href="${ctx}/ip/calendarDetail/list?gcrlfa_id=${calendar.id}">${calendar.famc}</a></td>
					<td>${calendar.nf}</td>
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

