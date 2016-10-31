<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>学习经历管理</title>
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
		}
		if(rusult==0){
			alertx('请至少选择一项');
		}else{
			window.location.href = "${ctx}/ip/employeeEducation/form?id=" + rusult;
		}
		
		//window.location.href="/hxmes/ip/organization/form?id="+rusult；
		//alert("edit"+rusult);
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
			var herfss = "${ctx}/ip/employeeEducation/deletes?id="
					+ herfs.toString();
			confirmx('确认要删除这些学习经历信息吗？', herfss);
		} else {
			var herf = "${ctx}/ip/employeeEducation/delete?id=" + rusult;
			confirmx('确认要删除该学习经历信息吗？', herf);
		}
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
				<a href="#">职工学习经历</a>
			</li>
			<li class="pull-right">
				<input id="btnAdd" class="btn btn-primary " type="button" value="新增" onclick="window.location.href='${ctx}/ip/employeeEducation/form'" />
			</li>
		</ul>
	<form:form id="searchForm" modelAttribute="employeeEducation"
		action="${ctx}/ip/employeeEducation/" method="post"
		class="breadcrumb form-search">
	    &nbsp;
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<label>姓名 ：</label>
		<tags:treeselect id="zg_id" name="employee.id" value="${list.id}" labelName="employee.xm" labelValue="${list.name}" title="员工" url="/ip/employee/treeData"/>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit"
			value="查询" />
	</form:form>
	<tags:message content="${message}" />
	<div style="overflow: auto; width: 100%;">
		<table id="contentTable"
			class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th><input name="SelectAll" type="checkbox" id="SelectAll"
					onclick="CheckSelect()" />全选</th>
					<th>序列</th>
					<th>姓名</th>
					<th>学历性质</th>
					<th>学历</th>
					<th>所学专业类别</th>
					<th>入学时间</th>
					<th>学制</th>
					<th>毕(肄)业日期</th>
					<th>学校(单位)名称</th>
					<th>专业</th>
					<th>学位</th>
					<th>学位授予日期</th>
					<th>审核状态</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${page.list}" var="employeeEducation">
					<tr>
						<td><input type="checkbox" value="${employeeEducation.id}"
							name="check" /></td>
						<td class="xl"></td>
						<td><a href="${ctx}/ip/employeeEducation/form?id=${employeeEducation.id}">${employeeEducation.employee.xm}</a></td>
						<td>${employeeEducation.xlxzdm}</td>
						<td>${employeeEducation.xldm}</td>
						<td>${employeeEducation.sxzylb}</td>
						<td><fmt:formatDate value="${employeeEducation.rxsj}" pattern='yyyy-MM-dd'/></td>
						<td>${employeeEducation.xz}</td>
						<td><fmt:formatDate value="${employeeEducation.byyyrq}" pattern='yyyy-MM-dd'/></td>
						<td>${employeeEducation.xxdwmc}</td>
						<td>${employeeEducation.zymc}</td>
						<td>${employeeEducation.xw}</td>
						<td><fmt:formatDate value="${employeeEducation.xwsyrq}" pattern='yyyy-MM-dd'/></td>
						<td>${employeeEducation.shzt}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<input id="btnQuery" class="btn btn-primary" type="button" value="修改"
		onclick="edit_click()" />&nbsp;
	<input id="btnDelete" class="btn btn-primary" type="button" value="删除"
		onclick="del_click()" />
	<div class="pagination">${page}</div>
</body>
</html>
