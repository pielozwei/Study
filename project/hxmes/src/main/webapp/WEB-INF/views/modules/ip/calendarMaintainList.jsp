<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>工厂日历维护管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		$("#inputForm").validate();
	});
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
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
		window.location.href = "${ctx}/ip/calendarMaintain/form?id=" + result;
		//window.location.href="/hxmes/ip/organization/form?id="+result；
		//alert("edit"+result);
	}

	function del_click() {
		var number = 0;
		var result = 0;
		var check_array = document.getElementsByName("select");
		for (var i = 0; i < check_array.length; i++) {
			if (check_array[i].checked == true) {
				result = check_array[i].id;
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
					idAll[count] = check_array[i].id;
					count++;
				}
			}
			for (var j = 0; j < count; j++) {
				herfs[j] = idAll[j];
			}
			var herfss = "${ctx}/ip/calendarMaintain/deletes?id="
					+ herfs.toString();
			confirmx('确认要删除这些工厂日历维护信息吗？', herfss);
		} else {
			var herf = "${ctx}/ip/calendarMaintain/delete?id=" + result;
			confirmx('确认要删除该行工厂日历维护信息吗？', herf);
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
				<a href="#">工厂日历生成</a>
			</li>

		</ul>
	<%-- <ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ip/calendarMaintain/">工厂日历维护列表</a></li>
		<shiro:hasPermission name="ip:calendarMaintain:edit">
			<li><a href="${ctx}/ip/calendarMaintain/form">工厂日历维护添加</a></li>
		</shiro:hasPermission>
	</ul> --%>
	
	<form:form id="inputForm" modelAttribute="calendarMaintain"
		action="${ctx}/ip/calendarMaintain/saveall" method="post"
		class="breadcrumb form-search">

			
			<div class="controls">
				<label class="control-label" for="workcenter.id">工作中心:</label>
				<tags:treeselect id="gzzx_id" name="workcenter.id"
					value="${list.id}" labelName="name"
					labelValue="${list.name}" title="工作中心" cssClass="required" 
					url="/gzzx/gzzxwh/treeData" />
				
				<label class="control-label" for="faly">日历方案:</label>
				<form:select path="faly" class="input-xlarge required">
					<option value="">--请选择--</option>
					<c:forEach var="FAList" items="${FAList}" varStatus="s">
                    	<option value="${FAList.id}">${FAList.famc}</option>
                    </c:forEach>
				</form:select>
				
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="生成" />
			</div>

		
	</form:form>
	
	<form:form id="searchForm" modelAttribute="calendarMaintain"
		action="${ctx}/ip/calendarMaintain/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<label>工作中心 :</label>
		<tags:treeselect id="workcenter" name="workcenter.id"
					value="${list.id}" labelName="name"
					labelValue="${list.name}" title="工作中心" 
					url="/gzzx/gzzxwh/treeData" />
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit"
			value="查询" />
	</form:form>
	<tags:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>工作中心</th>
				<th>工作日</th>
				<th>工作日类型</th>
				<th>上班日</th>
				<th>班次</th>
				<th>班次是否上班</th>
				<th>班次类型</th>
				<th>日历方案</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="calendarMaintain">
				<tr>
					<td class="xl"></td>
					<td>${calendarMaintain.workcenter.gzzxmc}</td>
					<td><a
						href="${ctx}/ip/calendarMaintain/form?id=${calendarMaintain.id}">${calendarMaintain.rq}</a></td>
					<td>${fns:getDictLabel(calendarMaintain.gzrlx, 'd_gzrlx', '无')}</td>
					<td>${calendarMaintain.sfsb}</td>
					<td>${calendarMaintain.bc}</td>
					<td>${fns:getDictLabel(calendarMaintain.bcsfsb, 'yes_no', '无')}</td>
					<td>${fns:getDictLabel(calendarMaintain.bcxz, 'd_bcxz', '无')}</td>
					<td>${calendarMaintain.faly}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
