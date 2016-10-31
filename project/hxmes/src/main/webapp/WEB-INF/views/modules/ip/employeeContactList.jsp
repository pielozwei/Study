<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>个人信息管理</title>
	<meta name="decorator" content="default"/>
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
			if(rusult == 0){
				alert("请至少选择一项");
			}else
			{window.location.href = "${ctx}/ip/employeeContact/form?id=" + rusult;}
			//window.location.href="/hxmes/ip/organization/form?id="+rusult；
			//alert("edit"+rusult);
		}

		function del_click() {
			var rusult = 0;
			var check_array = document.getElementsByName("check");
			for (var i = 0; i < check_array.length; i++) {
				if (check_array[i].checked == true) {
					rusult = check_array[i].value;
				}
			}
			if(rusult == 0){
				alert("请至少选择一项");
			}else{
			var herf = "${ctx}/ip/employeeContact/delete?id=" + rusult;
			confirmx('确认要删除该工作经历吗？', herf);}
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
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ip/employeeContact/">个人信息列表</a></li>
		<shiro:hasPermission name="ip:employeeContact:edit">
		<li><a href="${ctx}/ip/employeeContact/form">个人信息添加</a></li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="employeeContact" action="${ctx}/ip/employeeContact/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>名称 ：</label>
		<tags:treeselect id="id" name="employee.id" value="${list.id}" labelName="employee.xm" labelValue="${list.name}" title="员工" url="/ip/employee/treeData"/>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr>
				<th><input name="SelectAll" type="checkbox" id="SelectAll"
					onclick="CheckSelect()" />全选</th>
				<th>序号</th>
				<th>姓名</th>
				<th>昵称</th>
				<th>性别</th>
				<th>业务属性</th>
				<th>系统身份</th>
				<th>学历</th>
				
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="employee">
				<tr>
					<td><input type="checkbox" value="${employeecontact.id}"
						name="check" /></td>
					<td>${employeecontact.employee.xssx}</td>
					<td><a href="${ctx}/ip/employeeContact/form?id=${employeecontact.id}">${employeecontact.employee.xm}</a></td>
					<td>${employeecontact.employee.nc}</td>
					<td>${employeecontact.employee.xb}</td>
					<td>${employeecontact.employee.sfdm}</td>
					<td>${employeecontact.employee.xtsfdm}</td>
					<td>${employeecontact.employee.xldm}</td>
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
