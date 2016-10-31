<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>工艺路线基本信息管理</title>
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
			window.location.href = "${ctx}/ip/technique/index?id=" + result;
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
			var herfss = "${ctx}/ip/technique/deletes?id=" + herfs.toString();
			confirmx('确认要删除这些工艺路线基本信息吗？', herfss);
		} else {
			var herf = "${ctx}/ip/technique/delete?id=" + result;
			confirmx('确认要删除该行工艺路线基本信息吗？', herf);
		}
	}
	function ifItSelf() {
		var btnAdd = document.getElementById("btnAdd");
		var target = "${direct}";
		var link = document.getElementsByClassName("link");
		if (target == "") {
			btnAdd.style.display = "inline-block";
			for (var i = 0; i < link.length; i++) {
				link[i].href = "${ctx}/ip/technique/form?id=${technique.id}";
			}
		}
	}
	window.onload = function() {
		ifItSelf();
	}
</script>
</head>
<body>

	<ul class="nav nav-tabs">
		<li class="active">
			<a href="${ctx}/ip/technique/">基本信息</a>
		</li>
		<li class="pull-right">
			<input id="btnAdd" class="btn btn-primary " type="button" value="新增" onclick="window.location.href='${ctx}/ip/technique/form'" />
		</li>
	</ul>
	
	<form:form id="searchForm" modelAttribute="technique"
		action="${ctx}/ip/technique/" method="post"
		class="breadcrumb form-search">

		<label>主产品 ：</label>
			<tags:treeselect id="id" name="material.id" value="${technique.material.id}" labelName="material.wlmc"
					labelValue="${technique.material.wlmc}" title="产品编码" url="/ip/technique/wlbmtreeData"/>
			
		<label>是否主工艺 ：</label>
		<form:select class="input-small" path="sfzgy">
			<form:options items="${fns:getDictList('yes_no')}" itemValue="value" itemLabel="label" htmlEscape="false"/>
		</form:select>
		<label>是否启用 ：</label>
		<form:select class="input-small" path="sfqy">
			<form:options items="${fns:getDictList('yes_no')}" itemValue="value" itemLabel="label" htmlEscape="false"/>
		</form:select>	
		
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" />
	</form:form>
	<tags:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th><input type="checkbox" value="全选" id="all"
					onclick="setAll()">全选</th>
				<th>序号</th>
				<th>路线编号</th>
				<th>名称</th>
				<th>简称</th>
				<th>主产品</th>
				<th>是否主工艺 </th>
				<th>是否启用</th>
		<tbody>
			<c:forEach items="${page.list}" var="technique">
				<tr>
					<td><input type="checkbox" value="${technique.id}"
						class="subbox" /></td>
					<td>${technique.xssx}</td>
					<td>${technique.bm}</td>
					<td><a href="${ctx}/ip/technique/form?id=${technique.id}">${technique.mc}</a></td>
					<td>${technique.jc}</td>
					<td>${technique.material.wlmc}</td>
					<td>${fns:getDictLabel(technique.sfzgy, 'yes_no', '无')}</td>
					<td>${fns:getDictLabel(technique.sfqy, 'yes_no', '无')}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
		<input id="btnedit" class="btn btn-primary" type="button" value="编辑" onclick="edit_click()" />&nbsp;
		<input id="btndelete" class="btn btn-primary" type="button" value="删除" onclick="del_click()" />&nbsp;
		<input id="btnoutput" class="btn btn-primary" type="button" value="导出" />&nbsp;
	<div class="pagination">${page}</div>
</body>
</html>
