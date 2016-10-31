<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>个人信息管理</title>
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
		if(rusult == 0){
			alert("请至少选择一项");
		}else
		{window.location.href = "${ctx}/ip/employee/form?id=" + rusult;}
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
		var herf = "${ctx}/ip/employee/delete?id=" + rusult;
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
			<li class="active">
				<a href="#">职工个人信息</a>
			</li>
			<li class="pull-right">
				<input id="btnAdd" class="btn btn-primary " type="button" value="新增" onclick="window.location.href='${ctx}/ip/employee/form'" />
			</li>
		</ul>
	<form:form id="searchForm" modelAttribute="employee"
		action="${ctx}/ip/employee/" method="post"
		class="breadcrumb form-search">

		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
		<label>姓名 ：</label>
			<tags:treeselect id="id" name="id" value="${list.id}" labelName="name" labelValue="${list.name}" title="员工" url="/ip/employee/treeData"/>
		<label>性别 ：</label>
		<form:select path="xb" class="input-small">
			<form:option value="" label="请选择" />
			<form:options items="${fns:getDictList('d_xb')}"
				itemLabel="label" itemValue="value" htmlEscape="false" />
		</form:select>
		<label>入职年份 ：</label>
			<form:input path="rzsj" name="rzsj" type="text" maxlength="20" class="Wdate" readonly="readonly"
				onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
		<label>系统身份 ：</label>
		<form:select path="xtsfdm" class="input-small">
			<form:option value="" label="请选择" />
			<form:options items="${fns:getDictList('d_xtsf')}"
				itemLabel="label" itemValue="value" htmlEscape="false" />
		</form:select>
		
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" />
	</form:form>
	<tags:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
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
					<td><input type="checkbox" value="${employee.id}"
						name="check" /></td>
					<td>${employee.xssx}</td>
					<td><a href="${ctx}/ip/employee/form?id=${employee.id}">${employee.xm}</a></td>
					<td>${employee.nc}</td>
					<td>${employee.xb}</td>
					<td>${employee.sfdm}</td>
					<td>${employee.xtsfdm}</td>
					<td>${employee.xldm}</td>
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
