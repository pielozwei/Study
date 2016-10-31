<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>工艺参数管理</title>
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
	function CheckAll() {
		var subbox = document.getElementsByClassName("subbox");
		for (var i = 0; i < subbox.length; ++i) {
			subbox[i].checked = true;
		}
	}
	function UnCheckAll() {
		var subbox = document.getElementsByClassName("subbox");
		for (var i = 0; i < subbox.length; ++i) {
			subbox[i].checked = false;
		}
	}
	function setAll() {
		var all = document.getElementById("all");
		if (all.checked == true) {
			CheckAll();
		} else {
			UnCheckAll();
		}
	}
	function edit_click() {
		var result = 0;
		var number = 0;
		var check_array = document.getElementsByClassName("subbox");
		for (var i = 0; i < check_array.length; i++) {
			if (check_array[i].checked == true) {
				result = check_array[i].value;
				number++;
			}
		}
		if (number == 1) {
			window.location.href = "${ctx}/ip/techniqueParameter/form?id="
					+ result;
		} else if (number > 1) {
			alert("一次只能修改一条信息");
		} else if (number == 0) {
			alert("请选中一条需要修改的信息");
		}

	}
	function del_click() {
		var result = 0;
		var number = 0;
		var check_array = document.getElementsByClassName("subbox");
		for (var i = 0; i < check_array.length; i++) {
			if (check_array[i].checked == true) {
				result = check_array[i].value;
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
					idAll[count] = check_array[i].value;
					count++;
				}
			}
			for (var j = 0; j < count; j++) {
				herfs[j] = idAll[j];
			}
			var herfss = "${ctx}/ip/techniqueParameter/deletes?id="
					+ herfs.toString();
			confirmx('确认要删除这些工艺参数信息吗？', herfss);
		} else {
			var herf = "${ctx}/ip/techniqueParameter/delete?id=" + result;
			confirmx('确认要删除该行工艺参数信息吗？', herf);
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
		<li class="active"><a href="${ctx}/ip/techniqueParameter/">工艺参数列表</a></li>


		<li class="pull-right">
		<shiro:hasPermission name="ip:techniqueParameter:edit">
			<input id="btnAdd" class="btn btn-primary" type="button" value="新增"
				onclick="window.location.href='${ctx}/ip/techniqueParameter/form'" />
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="techniqueParameter"
		action="${ctx}/ip/techniqueParameter/" method="post"
		class="breadcrumb form-search">
		
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<label>工序 ：</label>
			<tags:treeselect id="gylx_id" name="technique.id" value="${list.id}" labelName="thchnique.mc"
			labelValue="${list.name}" title="工序名称" url="/ip/technique/treeData"/>
		<label>是否启用 ：</label>
		<form:select class="input-small" path="sfqy">
			<form:options items="${fns:getDictList('yes_no')}" itemValue="value" itemLabel="label" htmlEscape="false"/>
		</form:select>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit"
			value="查询" />
	</form:form>
	<tags:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">

		<thead>
			<tr>
				<th><input type="checkbox" value="全选" id="all"
					onclick="setAll()">全选</th>
				<th>序号</th>
				<th>工序</th>
				<th>参数编码</th>
				<th>参数名称</th>
				<th>单位</th>
				<th>上限</th>
				<th>下限</th>
				<th>标准值</th>
				<th>是否启用</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="techniqueParameter">
				<tr>
					<td><input type="checkbox" value="${techniqueParameter.id}"
						class="subbox"></td>
					<td>${techniqueParameter.xssx}</td>
					<td>${techniqueParameter.technique.mc}</td>
					<td>${techniqueParameter.gycsbm}</td>
					<td><a
						href="${ctx}/ip/techniqueParameter/form?id=${techniqueParameter.id}">${techniqueParameter.gycsmc}</a></td>
					<td>${techniqueParameter.gycsdw}</td>
					<td>${techniqueParameter.gycssxz}</td>
					<td>${techniqueParameter.gycsxxz}</td>
					<td>${techniqueParameter.gycsbzz}</td>
					<td>${techniqueParameter.sfqy}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
		<input id="btndelete" class="btn btn-primary" type="button" value="编辑" onclick="edit_click()" />&nbsp;
		<input id="btndelete" class="btn btn-primary" type="button" value="删除" onclick="del_click()" />&nbsp;
		<input id="btnputout" class="btn btn-primary" type="button" value="导出" />&nbsp;
	<div class="pagination">${page}</div>
</body>
</html>
