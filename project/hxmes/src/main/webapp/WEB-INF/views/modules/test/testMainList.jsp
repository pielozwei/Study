<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>主子表管理</title>
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
		<li class="active"><a href="${ctx}/test/testMain/">主子表列表</a></li>
		<shiro:hasPermission name="test:testMain:edit"><li><a href="${ctx}/test/testMain/form">主子表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="testMain" action="${ctx}/test/testMain/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
			<label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			<label>性别：</label>
				<form:radiobuttons path="sex" items="${fns:getDictList('d_xb')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>编号</th><th>名称</th><th>性别</th><th>更新时间</th><th>备注信息</th>
				<shiro:hasPermission name="test:testMain:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="testMain">
			<tr>
				<td><a href="${ctx}/test/testMain/form?id=${testMain.id}">${testMain.id}</a></td>
				<td>${testMain.name}</td>
				<td>${fns:getDictLabel(testMain.sex, 'd_xb', '')}</td>
				<td><fmt:formatDate value="${testMain.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${testMain.remarks}</td>
				<shiro:hasPermission name="test:testMain:edit"><td>
    				<a href="${ctx}/test/testMain/form?id=${testMain.id}">修改</a>
					<a href="${ctx}/test/testMain/delete?id=${testMain.id}" onclick="return confirmx('确认要删除该主子表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
