<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>副产品信息管理</title>
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
		if(number==0){
			alert("未选中任何一个！");
			return false;
		}
		else if(number>1){
			alert("仅能编辑一个！");
			return false;
		}
		window.location.href = "${ctx}/ip/techniqueOutput/form?id=" + result;
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
			var herfss = "${ctx}/ip/techniqueOutput/deletes?id="
					+ herfs.toString();
			confirmx('确认要删除这些副产品信息吗？', herfss);
		} else {
			var herf = "${ctx}/ip/techniqueOutput/delete?id=" + result;
			confirmx('确认要删除该行副产品信息吗？', herf);
		}
	}
	function order_number() {
		var xh = document.getElementsByClassName("xh");
		for (var i = 0; i < xh.length; i++) {
			xh[i].innerHTML = i + 1;
		}
	}
	window.onload = function() {
		order_number();
	}
</script>
</head>
<body>
<ul class="nav nav-tabs">
		<li class="active">
			<a href="${ctx}/ip/technique/">副产品信息</a>
		</li>
		<li class="pull-right">
			<input id="btnAdd" class="btn btn-primary " type="button" value="新增" onclick="window.location.href='${ctx}/ip/technique/form'" />
		</li>
	</ul>
	<table>
	<form:form id="searchForm" modelAttribute="techniqueOutput"
		action="${ctx}/ip/techniqueOutput/" method="post"
		class="breadcrumb form-search">
		<tr>
		<td>
		<shiro:hasPermission name="ip:techniqueOutput:edit">
			<a href="${ctx}/ip/techniqueOutput/form" class="btn btn-primary">新增</a>
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
				<th><input type="checkbox" id="all" onclick="setAll()" />全选</th>
				<th>序号</th>
				<th>工艺路线编码</th>
				<th>工艺路线</th>
				<th>节点类型</th>
				<th>工作中心编码</th>
				<th>工作中心名称</th>
				<th>默认工作中心</th>
				<th>物料</th>
				<th>计量单位</th>
				<th>数量</th>
				<th>副产品类别</th>
				<th>是否启用</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="techniqueOutput">
				<tr>
					<td><input type="checkbox" value="${techniqueOutput.id}" class="subBoxs" /></td>
					<td class="xh"></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td><a href="${ctx}/ip/techniqueOutput/form?id=${techniqueOutput.id}">${techniqueOutput.ip_gygzzx_id}</a></td>
					<td></td>
					<td>${techniqueOutput.ip_wl_id}</td>
					<td>${techniqueOutput.jldw}</td>
					<td>${techniqueOutput.sl}</td>
					<td>${techniqueOutput.ip_fcplb}</td>
					<td>${techniqueOutput.sfqy}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<shiro:hasPermission name="ip:techniqueInput:edit">
		<input id="btnQuery" class="btn btn-primary" type="button" value="编辑" onclick="edit_click()" />&nbsp;
		<input id="btnDelete" class="btn btn-primary" type="button" value="删除" onclick="del_click()" />&nbsp;
		<input id="btnOutput" class="btn btn-primary" type="button" value="导出" onclick="" />&nbsp;
	</shiro:hasPermission>
	<div class="pagination">${page}</div>
</body>
</html>
