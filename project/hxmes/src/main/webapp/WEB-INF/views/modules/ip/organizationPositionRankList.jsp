<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>人员业务分类管理</title>
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
					result = check_array[i].value;
					number++
				}
			}
			if(number==0){
				alert("请选中一条需要修改的信息");
				return false;
			}
			else if(number>1){
				alert("一次只能修改一条信息");
				return false;
			}
			window.location.href = "${ctx}/ip/organizationPositionRank/form?id=" + result;
		}
		function del_click() {
			var result = 0;
			var number = 0;
			var check_array = document.getElementsByName("select");
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
				var herfss = "${ctx}/ip/organizationPositionRank/deletes?id="
						+ herfs.toString();
				confirmx('确认要删除这些人员业务分类管理信息吗？', herfss);
			} else {
				var herf = "${ctx}/ip/organizationPositionRank/delete?id=" + result;
				confirmx('确认要删除该行人员业务分类管理信息吗？', herf);
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ip/organizationPositionRank/">人员业务类别管理</a></li>
		<li class="pull-right">
			<input id="btnAdd" class="btn btn-primary " type="button" value="新增人员业务类别" onclick="window.location.href='${ctx}/ip/organizationPositionRank/form'" />
		</li>
	</ul>
	<!-- 
	<form:form id="searchForm" modelAttribute="organizationPositionRank" action="${ctx}/ip/organizationPositionRank/" method="post" class="breadcrumb form-search">
		
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>单位 ：</label>
		<tags:treeselect id="ZZJG_ID" name="ZZJG_ID"
								value="${list.id}" labelName="name"
								labelValue="${list.name}" title="部门"
								url="/ip/organization/treeData" />
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>
	 -->
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr>
		
		
		<th><input type="checkbox" name="selectAll" id="selectAll" onclick="selectAll()" />全选</th>
		<th>序列</th>
		<th>人员业务类别</th>
		
		
		<!--<shiro:hasPermission name="ip:organizationPositionRank:edit"><th>操作</th></shiro:hasPermission></tr></thead>-->
		<tbody>
		<c:forEach items="${page.list}" var="organizationPositionRank">
			<tr>
				<td><input type="checkbox" value="${organizationPositionRank.id}" name="select" /></td>
				<td>${organizationPositionRank.xssx}</td>
				<td>${organizationPositionRank.RYYWLBMC}</td>
				<!-- 
				<shiro:hasPermission name="ip:organizationPositionRank:edit"><td>
    				<a href="${ctx}/ip/organizationPositionRank/form?id=${organizationPositionRank.id}">修改</a>
					<a href="${ctx}/ip/organizationPositionRank/delete?id=${organizationPositionRank.id}" onclick="return confirmx('确认要删除该人员业务分类管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
				 -->
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<input id="btnQuery" class="btn btn-primary" type="button" value="修改" onclick="edit_click()" />&nbsp;
	<input id="btnDelete" class="btn btn-primary" type="button" value="删除" onclick="del_click()" />
	<div class="pagination">${page}</div>
</body>
</html>
