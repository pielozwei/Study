<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>基本信息管理</title>
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
		window.location.href = "${ctx}/ip/techniqueWorkcenter/form?id=" + result;
		//window.location.href="/hxmes/ip/organization/form?id="+result；
		//alert("edit"+result);
	}
/* 	function edit_click() {
		var result = 0;
		var number = 0;
		var edit_err = document.getElementById("edit_err");
		var check_array = document.getElementsByName("select");
		for (var i = 0; i < check_array.length; i++) {
			if (check_array[i].checked == true) {
				result = check_array[i].id;
				number++;
			}
		}
		if (number != 1) {
			edit_err.innerHTML = "一次只能修改一条信息！";
			return;
		} else
			edit_err.innerHTML = "";

		window.location.href = "${ctx}/ip/techniqueWorkcenter/form?id="
				+ result;
		//window.location.href="/hxmes/ip/organization/form?id="+result；
		//alert("edit"+result);
	} */

	function exp_click() {
		top.$.jBox.confirm("确认要导出用户数据吗？", "系统提示", function(v, h, f) {
			if (v == "ok") {
				$("#searchForm").attr("action", "${ctx}/sys/user/export")
						.submit();
			}
		}, {
			buttonsFocus : 1
		});
		top.$('.jbox-body .jbox-icon').css('top', '55px');
	}

	function del_click() {
		var result = 0;
		var number = 0;
		var check_array = document.getElementsByClassName("select");
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
			var herfss = "${ctx}/ip/techniqueWorkcenter/deletes?id="
					+ herfs.toString();
			confirmx('确认要删除这些基本信息吗？', herfss);
		} else {
			var herf = "${ctx}/ip/techniqueWorkcenter/delete?id=" + result;
			confirmx('确认要删除该行基本信息吗？', herf);
		}
	}
	window.onload = function() {
		var subPageName = "${direct}";
		if (subPageName == "") {
			var js_href = document.getElementsByClassName("js_href");
			for (var i = 0; i < js_href.length; i++) {
				js_href[i].href = "${ctx}/ip/techniqueWorkcenter/form?id=${techniqueWorkcenter.id}";
			}
			document.getElementById("btnAdd").style.display = "inline-block";
		}
		var xl = document.getElementsByClassName("xl");
		for (i = 0; i < xl.length; i++) {
			xl[i].innerHTML = i + 1;
		}
	}
</script>
</head>
<body>
	<%-- <ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ip/techniqueWorkcenter/">基本信息列表</a></li>
		<shiro:hasPermission name="ip:techniqueWorkcenter:edit">
			<li><a href="${ctx}/ip/techniqueWorkcenter/form">基本信息添加</a></li>
		</shiro:hasPermission>
	</ul> --%>
	<ul class="nav nav-tabs">
		<li class="active">
			<a href="${ctx}/ip/technique/">基本信息</a>
		</li>
		<li class="pull-right">
			<input id="btnAdd" class="btn btn-primary " type="button" value="新增" onclick="window.location.href='${ctx}/ip/techniqueWorkcenter/form'" />
		</li>
	</ul>
	<table>
	<form:form id="searchForm" modelAttribute="techniqueWorkcenter"
		action="${ctx}/ip/techniqueWorkcenter/" method="post"
		class="breadcrumb form-search">
		<tr>
		<td>
			<shiro:hasPermission name="ip:techniqueWorkcenter:edit">
				<input style="display: none" id="btnAdd" class="btn btn-primary"
					type="button" value="新增工艺工作中心"
					onclick="window.location.href='${ctx}/ip/techniqueWorkcenter/form'" />
			</shiro:hasPermission>
			<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
			<input id="pageSize" name="pageSize" type="hidden"
				value="${page.pageSize}" />&nbsp;
		</td>
		<td>
		<label>工艺路线 ：</label>
		<tags:treeselect id="gylx_id" name="technique.id" value="${techniqueWorkcenter.technique.id}"
			labelName="technique.mc" labelValue="${techniqueWorkcenter.technique.mc}" title="工艺路线"
			url="/ip/technique/treeData" />&nbsp;
		</td>
			
		<td>
		<label>工作中心 ：</label>
		<tags:treeselect id="gzzx_id" name="workcenter.id" value="${techniqueWorkcenter.workcenter.id}"
			labelName="workcenter.gzzxmc" labelValue="${techniqueWorkcenter.workcenter.gzzxmc}" title="工作中心"
			url="/gzzx/gzzxwh/treeData?type=1" />&nbsp;
		</td>
		
		<td>
		<label>主产品 ：</label>	
			<tags:treeselect id="id" name="material.id" value="${techniqueWorkcenter.material.id}" labelName="material.wlmc"
					labelValue="${techniqueWorkcenter.material.wlmc}" title="产品编码" url="/ip/technique/wlbmtreeData"/>
		</td>
		</tr>
		<tr>
		<td></td>
		<td>
		<label>节点类型 ：</label>
		<form:select path="" class="input-large">
			<option>全部</option>
			<option>工艺路线</option>
			<option>工序组</option>
			<option>工序</option>
		</form:select>
		</td>
		<td>
		<label>默认工作中心 ：</label>
		<form:select class="input-large" path="sfmrgzzx">
			<form:options items="${fns:getDictList('yes_no')}" itemValue="value" itemLabel="label" htmlEscape="false"/>
		</form:select>
		</td>
		<td>
		<label>是否启用 ：</label>
		<form:select class="input-large" path="sfqy">
			<form:options items="${fns:getDictList('yes_no')}" itemValue="value" itemLabel="label" htmlEscape="false"/>
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
				<th><input type="checkbox" name="selectAll" id="selectAll"
					onClick="selectAll()" />全选</th>
				<th>序号</th>
				<th>工艺路线编码</th>
				<th>工艺路线</th>
				<th>节点类型</th>
				<th>工作中心编码</th>
				<th>工作中心名称</th>
				<th>默认工作中心</th>
				<th>主产品</th>
				<th>是否启用</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="techniqueWorkcenter">
				<tr>
					<td><input type="checkbox" value="${techniqueWorkcenter.id}"
						name="select" class="select" /></td>
					<td class="xl"></td>
					<td>${techniqueWorkcenter.technique.bm}</td>
					<td>${techniqueWorkcenter.technique.mc}</td>
					<td></td>
					<td>${techniqueWorkcenter.workcenter.gzzxbm}</td>
					<td><a href="${ctx}/ip/techniqueWorkcenter/form?id=${techniqueWorkcenter.id}">${techniqueWorkcenter.workcenter.gzzxmc}</a></td>
					<td>${fns:getDictLabel(techniqueWorkcenter.sfmrgzzx, 'yes_no', '无')}</td>
					<td>${techniqueWorkcenter.material.wlmc}</td>
					<td>${fns:getDictLabel(techniqueWorkcenter.sfqy, 'yes_no', '无')}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<shiro:hasPermission name="ip:techniqueWorkcenter:edit">
		<td><input id="btnQuery" class="btn btn-primary" type="button"
			value="编辑" onclick="edit_click()" />&nbsp; <input id="btnDelete"
			class="btn btn-primary" type="button" value="删除"
			onclick="del_click()" />&nbsp; <input id="btnExport"
			class="btn btn-primary" type="button" value="导出"
			onclick="exp_click()" />&nbsp; <i id="errorinformation"></i></td>
	</shiro:hasPermission>
	<i id="edit_err"></i>
	<div class="pagination">${page}</div>
</body>
</html>
