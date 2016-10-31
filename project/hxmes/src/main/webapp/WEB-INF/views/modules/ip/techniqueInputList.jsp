<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>投入物料管理</title>
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
				result = check_array[i].value;
				number++
			}
		}
		if(number==0){
			alert("请选中一条需要修改的信息");
			return false;
		}
		else if(number>1){
			alert("一次只能修改一条信息");
			return false;
		}
		window.location.href = "${ctx}/ip/techiqueEnergry/form?id=" + result;
	}

	function del_click() {
		var result = 0;
		var number = 0;
		var check_array = document.getElementsByName("select");
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
			var herfss = "${ctx}/ip/techniqueInput/deletes?id="
					+ herfs.toString();
			confirmx('确认要删除这些投入物料信息吗？', herfss);
		} else {
			var herf = "${ctx}/ip/techniqueInput/delete?id=" + result;
			confirmx('确认要删除该行投入物料信息吗？', herf);
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
			<a href="${ctx}/ip/technique/">投入物料</a>
		</li>
		<li class="pull-right">
			<input id="btnAdd" class="btn btn-primary " type="button" value="新增" onclick="window.location.href='${ctx}/ip/technique/form'" />
		</li>
	</ul>
	<%-- <ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ip/techniqueInput/">投入物料列表</a></li>
		<shiro:hasPermission name="ip:techniqueInput:edit"><li><a href="${ctx}/ip/techniqueInput/form">投入物料添加</a></li></shiro:hasPermission>
	</ul> --%>
	<table>
	<form:form id="searchForm" modelAttribute="techniqueInput"
		action="${ctx}/ip/techniqueInput/" method="post"
		class="breadcrumb form-search">
		<tr>
		<td>
		<shiro:hasPermission name="ip:techniqueInput:edit">
			<input id="btnAdd" class="btn btn-primary" type="button" value="新增"
				onclick="window.location.href='${ctx}/ip/techniqueInput/form'" />
		</shiro:hasPermission>
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />&nbsp;
		</td>
		<td>
		<label>工艺路线 ：</label>
		<tags:treeselect id="gylx_id" name="gylx_id" value="${list.id}"
			labelName="name" labelValue="${list.name}" title="工艺路线名称:"
			url="/ip/workCenter/treeData" />&nbsp;
		</td>
		<td>
		<label>工作中心 ：</label>
		<tags:treeselect id="gzzx_id" name="gzzx_id" value="${list.id}"
			labelName="name" labelValue="${list.name}" title="工作中心名称:"
			url="/ip/workCenter/treeData" />&nbsp;
		</td>
		<td>
		<label>物料 ：</label>
			<tags:treeselect id="" name="" value="${list.id}"
				labelName="name" labelValue="${list.name}" title="物料:"
				url="/ip/workCenter/treeData" />
		</td>
		</tr>
		<tr>
		<td></td>
		<td>
		<label>节点类型 ：</label>
		<form:select path="">
			<option>全部</option>
			<option>工艺路线</option>
			<option>工序组</option>
			<option>工序</option>
		</form:select>
		</td>
		<td>
		<label>默认工作中心 ：</label>
		<form:select class="input-small" path="">
			<form:options items="${fns:getDictList('yes_no')}" itemValue="value" itemLabel="label" htmlEscape="false"/>
		</form:select>
		</td>
		<td>
		<label>是否启用 ：</label>
		<form:select class="input-small" path="sfqy">
			<form:options items="${fns:getDictList('shi_yong')}" itemValue="value" itemLabel="label" htmlEscape="false"/>
		</form:select>
		</td>
		<td>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit"
			value="查询" />
		</td>
		</tr>
	</form:form>
	</table>
	<br>
	<tags:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th><input type="checkbox" name="selectAll" id="selectAll" onclick="selectAll()" />全选</th>
				<th>序号</th>
				<th>工艺路线编码</th>
				<th>工艺路线</th>
				<th>节点类型</th>
				<th>工作中心编码</th>
				<th>工作中心名称</th>
				<th>默认工作中心</th>
				<th>物料</th>
				<th>计量单位</th>
				<th>消耗定额</th>
				<th>来源仓库</th>
				<th>是否启用</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="techniqueInput">
				<tr>
					<td><input type="checkbox" value="${techniqueInput.id}" name="select" class="select" /></td>
					<td class="xl"></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td><a href="${ctx}/ip/techniqueInput/form?id=${techniqueInput.id}">${techniqueInput.gygzzx_id}</a></td>
					<td></td>
					<td>${techniqueInput.wl_id}</td>
					<td>${techniqueInput.jldw}</td>
					<td>${techniqueInput.xhde}</td>
					<td>${techniqueInput.lyk_id}</td>
					<td>${techniqueInput.sfqy}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<shiro:hasPermission name="ip:techniqueInput:edit">
			<input id="btnEdit" class="btn btn-primary" type="button" value="编辑" onclick="edit_click()" />&nbsp;
			<input id="btnDelete" class="btn btn-primary" type="button" value="删除" onclick="del_click()" />&nbsp;
			<input id="btnOutput" class="btn btn-primary" type="button" value="导出" onclick="" />
	</shiro:hasPermission>
	<div class="pagination">${page}</div>
</body>
</html>
