<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>人员业务类别管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        	function CheckSelect() {
        		var all = document.getElementById("SelectAll");
        		var checkbox = document.getElementsByName("Select");
        		for (var i = 0; i < checkbox.length; i++) {
        			if (all.checked == true) {
        				checkbox[i].checked = true;
        			} else {
        				checkbox[i].checked = false;
        			}
        		}
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
        		if (number == 0) {
        			alert("请选中一条需要修改的信息");
        			return;
        		} else if (number > 1) {
        			alert("一次只能修改一条信息");
        			return;
        		}
        		window.location.href = "${ctx}/ip/employeePositionRankDirector/form?id="
        				+ rusult;
        		//window.location.href="/hxmes/ip/organization/form?id="+rusult；
        		//alert("edit"+rusult);
        	}
        	/* function edit_click() {
        		var rusult = 0;
        		var number = 0;
        		var errorinformation = document.getElementById("errorinformation");
        		var check_array = document.getElementsByName("Select");
        		for (var i = 0; i < check_array.length; i++) {
        			if (check_array[i].checked == true) {
        				rusult = check_array[i].value;
        				number++;
        			}
        		}
        		if (number == 0) {
        			errorinformation.innerHTML = "您尚未选择！";
        			return;
        		} else if (number > 1) {
        			errorinformation.innerHTML = "您只能修改一条信息！";
        			return;
        		} else {
        			errorinformation.innerHTML = "";
        		}
        		window.location.href = "${ctx}/ip/employeePositionRankDirector/form?id=" + rusult;
        		//window.location.href="/hxmes/ip/organization/form?id="+rusult；
        		//alert("edit"+rusult);
        	} */
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
        			var herfss = "${ctx}/ip/employeePositionRankDirector/deletes?id="
        					+ herfs.toString();
        			confirmx('确认要删除这些人员岗位管理信息吗？', herfss);
        		} else {
        			var herf = "${ctx}/ip/employeePositionRankDirector/delete?id=" + rusult;
        			confirmx('确认要删除该行人员岗位管理信息吗？', herf);
        		}
        	}
        	window.onload = function() {
        		var sequencer = document.getElementsByClassName("Sequence");
        		for (var i = 0; i < sequencer.length; i++) {
        			sequencer[i].innerHTML = i + 1;
        		}
        	}
        </script>
</head>
<body>
	<table>
		<form:form id="searchForm" modelAttribute="employeePositionRankDirector"
			action="${ctx}/ip/employeePositionRankDirector/" method="post"
			class="breadcrumb form-search">
			<tr>

				<td><label>性别 ：</label> <form:select path="">
						<option>男</option>
						<option>女</option>
					</form:select>&nbsp;&nbsp;</td>
				<td><label>岗位 ：</label> <form:select path="">
						<option>B</option>
						<option>A</option>
					</form:select>&nbsp;&nbsp;</td>
				<td><label>工号 ：</label> <form:input path=""></form:input></td>
				<td>&nbsp;<input id="btnSubmit" class="btn btn-primary"
					type="submit" value="查询" /></td>
			</tr>
			<tr>
				<td><shiro:hasPermission name="ip:employeePositionRankDirector:edit">
					<input id="btnAdd" class="btn btn-primary" type="button"
						value="设置人员职务身份"
						onclick="window.location.href='${ctx}/ip/employeePositionRankDirector/form'" />
				</shiro:hasPermission></td>
			</tr>
		</form:form>
	</table>
<p>

	<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
	<input id="pageSize" name="pageSize" type="hidden"
		value="${page.pageSize}" />


	<%-- 	<label>名称 ：</label>
	<form:input path="id" htmlEscape="false" maxlength="50"
		class="input-small" /> --%>

	<tags:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th><input name="check" type="checkbox" id="SelectAll"
					onclick="CheckSelect()" />全选</th>
				<th>序列</th>
				<th>姓名</th>
				<th>性别</th>
				<th>昵称</th>
				<th>业务属性</th>
				<th>学历</th>
				<th>学科</th>
				<th>单位简称</th>
				<th>岗位</th>
				<th>人员业务分类</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="employeePositionRankDirector">
				<tr>
					<td><input type="checkbox"
						value="${employeePositionRankDirector.id}" name="Select"
						class="Select" /></td>

					<!--  -->






				</tr>
			</c:forEach>
		</tbody>
	</table>

	<input id="btnQuery" class="btn btn-primary" type="button" value="修改"
		onclick="edit_click()" />&nbsp;
	<div class="pagination">${page}</div>
</body>
</html>
