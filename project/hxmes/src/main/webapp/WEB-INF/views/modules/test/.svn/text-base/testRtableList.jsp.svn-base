<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>库房管理管理</title>
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
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/test/testRtable/">库房管理列表</a></li>
		<shiro:hasPermission name="test:testRtable:edit"><li><a href="${ctx}/test/testRtable/form">库房管理添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="testRtable" action="${ctx}/test/testRtable/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
			<label>工器具编号：</label>
				<form:input path="toolNo" htmlEscape="false" maxlength="8" class="input-medium"/>
			<label>工器具名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-medium"/>
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>工器具编号</th><th>工器具名称</th><th>工器具数量</th><th>工器具状态</th><th>更新时间</th><th>备注</th>
				<shiro:hasPermission name="test:testRtable:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="testRtable">
			<tr>
				<td><a href="${ctx}/test/testRtable/form?id=${testRtable.id}">${testRtable.toolNo}</a></td>
				<td>${testRtable.name}</td>
				<td>${testRtable.toolNumber}</td>
				<td>${fns:getDictLabel(testRtable.toolState, 'd_syzt', '')}</td>
				<td><fmt:formatDate value="${testRtable.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${testRtable.remarks}</td>
				<shiro:hasPermission name="test:testRtable:edit"><td>
    				<a href="${ctx}/test/testRtable/form?id=${testRtable.id}">修改</a>
					<a href="${ctx}/test/testRtable/delete?id=${testRtable.id}" onclick="return confirmx('确认要删除该库房管理吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
