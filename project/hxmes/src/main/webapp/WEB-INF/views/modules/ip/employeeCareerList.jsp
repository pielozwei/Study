<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>工作经历管理</title>
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
		}if(rusult == 0){
			alert("请至少选择一项");
		}else{
		window.location.href = "${ctx}/ip/employeeCareer/form?id=" + rusult;}
		//window.location.href="/hxmes/ip/organization/form?id="+rusult；
		//alert("edit"+rusult);
	}

	
	function CheckSelect() { //遍历form
		var all = document.getElementById("SelectAll");
		var checkbox = document.getElementsByName("check"); //检查是否是指定的控件
		for (var i = 0; i < checkbox.length; i++) { //提取控件
			if (all.checked == true) { //正选
				checkbox[i].checked = true;
			} else { //反选
				checkbox[i].checked = false;
			}
		}
	}
	function del_click() {
		var rusult = 0;
		var number = 0;
		var check_array = document.getElementsByName("Select");
		for (var i = 0; i < check_array.length; i++) {
			if (check_array[i].checked == true) {
				rusult = check_array[i].value;
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
			var herfss = "${ctx}/ip/employeeCareer/deletes?id="
					+ herfs.toString();
			confirmx('确认要删除这些工作经历信息吗？', herfss);
		} else {
			var herf = "${ctx}/ip/employeeCareer/delete?id=" + rusult;
			confirmx('确认要删除该工作经历信息吗？', herf);
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
				<a href="#">职工工作经历</a>
			</li>
			<li class="pull-right">
				<input id="btnAdd" class="btn btn-primary " type="button" value="新增" onclick="window.location.href='${ctx}/ip/employeeCareer/form'"  />
			</li>
		</ul>
	<form:form id="searchForm" modelAttribute="employeeCareer"
		action="${ctx}/ip/employeeCareer/" method="post"
		class="breadcrumb form-search">
		<!--<shiro:hasPermission name="ip:employeeCareer:edit">
			<input id="btnAdd" class="btn btn-primary" type="button" value="新增工作经历"
				onclick="window.location.href='${ctx}/ip/employeeCareer/form'" />
		</shiro:hasPermission>-->
	    &nbsp;
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<label>姓名 ：</label>
		<tags:treeselect id="zg_id" name="employee.id" value="${list.id}" labelName="employee.xm" labelValue="${list.name}"
							title="员工" url="/ip/employee/treeData"/>
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
				<th>起始日期</th>
				<th>终止日期</th>
				<th>所在单位</th>
				<th>从事工作或担任职务</th>
				<th>审核状态</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="employeeCareer">
				<tr>


					<td><input type="checkbox" value="${employeeCareer.id}"
						name="check" /></td>
					<td class="xl">${employeeCareer.xl}</td>
					<td><a href="${ctx}/ip/employeeCareer/form?id=${employeeCareer.id}">${employeeCareer.employee.xm}</a></td>
					<td><fmt:formatDate value="${employeeCareer.qsrq}" pattern='yyyy-MM-dd'/></td>
					<td><fmt:formatDate value="${employeeCareer.zzrq}" pattern='yyyy-MM-dd'/></td>
					<td>${employeeCareer.szdw}</td>
					<td>${employeeCareer.zw}</td>
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
