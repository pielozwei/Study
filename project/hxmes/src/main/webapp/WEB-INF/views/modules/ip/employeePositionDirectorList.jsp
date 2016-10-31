<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>人员岗位设置管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
		}
        	function CheckSelect() {
        		var all = document.getElementById("SelectAll");
        		var checkbox = document.getElementsByName("check");
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
        		var check_array = document.getElementsByName("check");
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
        		window.location.href = "${ctx}/ip/employeePositionDirector/form?id="
        				+ rusult;
        		
        	}
        	
        	function del_click() {
        		var rusult = 0;
        		var number = 0;
        		var check_array = document.getElementsByName("check");
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
        			var herfss = "${ctx}/ip/employeePositionDirector/deletes?id="
        					+ herfs.toString();
        			confirmx('确认要删除这些人员岗位管理信息吗？', herfss);
        		} else {
        			var herf = "${ctx}/ip/employeePositionDirector/delete?id=" + rusult;
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
	
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ip/organizationPosition/">人员岗位设置</a></li>
		<li class="pull-right">
			<input id="btnAdd" class="btn btn-primary" type="button"
						value="设置人员职务身份"
						onclick="window.location.href='${ctx}/ip/employeePositionDirector/form'" />
		</li>
	</ul>
	
	<table>
		<form:form id="searchForm" modelAttribute="employeePositionDirector"
			action="${ctx}/ip/employeePositionDirector/" method="post"
			class="breadcrumb form-search">
			<tr>
				<td>
					<label>性别 ：</label> 
						<form:select path="employee.xb" class="input-small">
							<form:options items="${fns:getDictList('d_xb')}"
								itemLabel="label" itemValue="value" htmlEscape="false" />
						</form:select>
				</td>
				<td>
					<label>岗位 ：</label> 
					<form:select path="organizationposition.id" class="input-xlarge">
						<c:forEach var="organizationpositionlist" items="${organizationpositionlist}" varStatus="s">
                        	<option value="${organizationpositionlist.id}">${organizationpositionlist.gwmc}</option>
                        </c:forEach>
					</form:select>
				<td>
					<label>工号 ：</label> 
						<form:input path="employee.gh" name="employee.gh" type="text" maxlength="20"></form:input>
				</td>
				<td>&nbsp;<input id="btnSubmit" class="btn btn-primary"
					type="submit" value="查询" /></td>
			</tr>
		</form:form>
	</table>
<p>

	<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
	<input id="pageSize" name="pageSize" type="hidden"
		value="${page.pageSize}" />

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
			<c:forEach items="${page.list}" var="employeePositionDirector">
				<tr>
					<td><input type="checkbox"
						value="${employeePositionDirector.id}" name="check"
						class="Select" />
					</td>
					<td class="Sequence"> </td>
					<td>${employeePositionDirector.employee.xm}</td>
					<td>${fns:getDictLabel(employeePositionDirector.employee.xb, 'd_xb', '无')} </td>
					<td>${employeePositionDirector.employee.nc}</td>
					<td></td>
					<td>${fns:getDictLabel(employeePositionDirector.employee.xldm, 'd_xl', '无')} </td>
					<td>${fns:getDictLabel(employeePositionDirector.employee.zyxkdm, 'd_zyxk', '无')}</td>
					<td>${employeePositionDirector.organizationposition.organizationDepartment.organization.jgmc}</td>
					<td>${employeePositionDirector.organizationposition.gwmc}</td>
					<td>${employeePositionDirector.organizationposition.organizationposintionrank.RYYWLBMC}</td>
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
