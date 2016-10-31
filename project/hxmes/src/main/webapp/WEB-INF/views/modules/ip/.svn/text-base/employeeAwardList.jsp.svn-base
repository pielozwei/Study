<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>获奖情况记录管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		function CheckAll(){
			var subbox = document.getElementsByClassName("subbox");
			for(var i=0;i<subbox.length;++i){
				subbox[i].checked=true;
			}
		}
		function UnCheckAll(){
			var subbox = document.getElementsByClassName("subbox");
			for(var i=0;i<subbox.length;++i){
				subbox[i].checked=false;
			}
		}
		function setAll(){
			var all = document.getElementById("all");
			if(all.checked==true){
				CheckAll();
			}
			else{
				UnCheckAll();
			}
		}
		function edit_click() {
			var result = 0;
			var number =0;
			var check_array = document.getElementsByClassName("subbox");
			for (var i = 0; i < check_array.length; i++) {
				if (check_array[i].checked == true) {
					result = check_array[i].value;
					number++;
				}
			}
			if(number==1){
				window.location.href = "${ctx}/ip/employeeAward/form?id=" + result;
			}
			else if(number>1){
				alert("一次只能修改一条信息");
				return;			}
			else if(number==0){
				alert("请选中一条需要修改的信息");
				return;			}

		}
		function del_click() {
			var result = 0;
			var number =0;
			var check_array = document.getElementsByClassName("subbox");
			for (var i = 0; i < check_array.length; i++) {
				if (check_array[i].checked == true) {
					result = check_array[i].value;
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
				var herfss = "${ctx}/ip/employeeAward/deletes?id=" + herfs.toString();
				confirmx('确认要删除这些获奖信息吗？', herfss);
			} else {
				var herf = "${ctx}/ip/employeeAward/delete?id=" + result;
				confirmx('确认要删除该获奖信息吗？', herf);
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
		<!--<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ip/employeeAward/">获奖情况记录列表</a></li>
		<shiro:hasPermission name="ip:employeeAward:edit"><li><a href="${ctx}/ip/employeeAward/form">获奖情况记录添加</a></li></shiro:hasPermission>
	</ul>-->
	<ul class="nav nav-tabs">
			<li class="active">
				<a href="#">职工获奖情况记录</a>
			</li>
			<li class="pull-right">
				<input id="btnAdd" class="btn btn-primary " type="button" value="新增" onclick="window.location.href='${ctx}/ip/employeeAward/form'" />
			</li>
		</ul>
	<form:form id="searchForm" modelAttribute="employeeAward" action="${ctx}/ip/employeeAward/" method="post" class="breadcrumb form-search">
			
	    &nbsp;
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>姓名 ：</label>
		<tags:treeselect id="zg_id" name="employee.id" value="${list.id}" labelName="name" labelValue="${list.name}" title="员工" url="/ip/employee/treeData" />
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit"
			value="查询" />
	</form:form>
	
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
	
		<thead><tr><th><input type="checkbox" value="全选" id="all" onclick="setAll()">全选</th>
		<th>序列</th>
		<th>姓名</th>
		<th>获奖级别</th>	
		<th>奖项名称</th>
		<th>获奖日期</th>
		<th>颁奖机构名称</th>
		<th>审核状态</th>
		</tr>
		<tbody>
		<c:forEach items="${page.list}" var="employeeAward">
			<tr>
			
				<td><input type="checkbox" value="${employeeAward.id}"  class="subbox"></td>
				
				<td class="xl">${employeeAward.xl}</td>
				<td>${employeeAward.employee.xm}</td>
				<td>${employeeAward.hjjb}</td>				
				<td>${employeeAward.hjmc}</td>
				<td><fmt:formatDate value="${employeeAward.hjrq}" pattern="yyyy-MM-dd"/></td>
				<td>${employeeAward.bjjgmc}</td>
				<td>审核状态</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<input  id="btnchange" class="btn btn-primary" type="button" value="修改" onclick="edit_click()" />&nbsp;
	<input  id="btndelete" class="btn btn-primary" type="button" value="删除" onclick=" del_click()"/>&nbsp;
	<div class="pagination">${page}</div>
</body>
</html>
