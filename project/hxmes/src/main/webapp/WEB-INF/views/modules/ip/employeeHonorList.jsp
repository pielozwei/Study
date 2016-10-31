<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>荣誉称号记录管理</title>
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
		var number = 0;
		var check_array = document.getElementsByName("Select");
		for (var i = 0; i < check_array.length; i++) {
			if (check_array[i].checked == true) {
				rusult = check_array[i].value;
				number++;
			}
		}
		if (number > 1) {
			alert("一次只能修改一条信息");
			return;
		}else if(number == 0){
			alert("请选中一条需要修改的信息");
			return;
		}
		window.location.href = "${ctx}/ip/employeeHonor/form?id=" + rusult;
		//window.location.href="/hxmes/ip/organization/form?id="+rusult；
		//alert("edit"+rusult);
	}

	function del_click() {
		var rusult = 0;
		var number = 0;
		var check_array = document.getElementsByClassName("Select");
		for (var i = 0; i < check_array.length; i++) {
			if (check_array[i].checked == true) {
				rusult = check_array[i].value;
				number++;
			}
		}
		if(number == 0){
			alert("请选中一条需要删除的信息");
			return;
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
			var herfss = "${ctx}/ip/employeeHonor/deletes?id=" + herfs.toString();
			confirmx('确认要删除这些荣誉称号信息吗？', herfss);
		} else {
		var herf = "${ctx}/ip/employeeHonor/delete?id=" + rusult;
		confirmx('确认要删除该荣誉称号信息吗？', herf);
		}
	}

	function CheckSelect() { //遍历form
		var all = document.getElementById("SelectAll");
		var checkbox = document.getElementsByName("Select"); //检查是否是指定的控件
		for (var i = 0; i < checkbox.length; i++) { //提取控件
			if (all.checked == true) { //正选
				checkbox[i].checked = true;
			} else { //反选
				checkbox[i].checked = false;
			}
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
				<a href="#">职工荣誉称号经历</a>
			</li>
			<li class="pull-right">
				<input id="btnAdd" class="btn btn-primary " type="button" value="新增" onclick="window.location.href='${ctx}/ip/employeeHonor/form'" />
			</li>
		</ul>
	<form:form id="searchForm" modelAttribute="employeeHonor"
		action="${ctx}/ip/employeeHonor/" method="post"
		class="breadcrumb form-search">
		<shiro:hasPermission name="ip:organization:edit">

		</shiro:hasPermission>
	    &nbsp;
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
				<th><input name="SelectAll" type="checkbox" id="SelectAll"
					onclick="CheckSelect()" />全选</th>
				<th>序列</th>
				<th>姓名</th>
				<th>荣誉称号级别</th>
				<th>荣誉称号名称</th>
				<th>荣誉称号批准日期</th>
				<th>荣誉称号批准机构名称</th>
				<th>一次性奖励金额</th>
				<th>月奖励金额</th>
				<th>审核状态</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="employeeHonor">
				<tr>
					<td align="center"><input name="Select" type="checkbox"
						class="Select" value="${employeeHonor.id}" /></td>
					<td class="xl"></td>
					<td>${employeeHonor.employee.xm}</td>
					<td>${employeeHonor.jb}</td>
					<td>${employeeHonor.mc}</td>
					<td><fmt:formatDate value="${employeeHonor.pzrq}" pattern='yyyy-MM-dd'/></td>
					<td>${employeeHonor.pzjgmc}</td>
					<td>${employeeHonor.ycxjlje}</td>
					<td>${employeeHonor.yjlje}</td>
					<td>审核状态</td>
					<!--<shiro:hasPermission name="ip:employeeHonor:edit">
						<td><a
							href="${ctx}/ip/employeeHonor/form?id=${employeeHonor.id}">修改</a>
							<a href="${ctx}/ip/employeeHonor/delete?id=${employeeHonor.id}"
							onclick="return confirmx('确认要删除该荣誉称号记录吗？', this.href)">删除</a></td>
					</shiro:hasPermission>-->
				</tr>
			</c:forEach>
		</tbody>
	</table>
	&nbsp;
	<input id="btnQuery" class="btn btn-primary" type="button" value="修改"
		onclick="edit_click()" />&nbsp;
	<input id="btnDelete" class="btn btn-primary" type="button" value="删除"
		onclick="del_click()" />
	<div class="pagination">${page}</div>
</body>
</html>
