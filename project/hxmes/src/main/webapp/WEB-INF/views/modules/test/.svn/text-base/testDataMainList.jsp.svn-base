<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>主子表参考用例管理</title>
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
		<li class="active"><a href="${ctx}/test/testDataMain/">主子表参考用例列表</a></li>
		<shiro:hasPermission name="test:testDataMain:edit"><li><a href="${ctx}/test/testDataMain/form">主子表参考用例添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="testDataMain" action="${ctx}/test/testDataMain/" method="post" class="breadcrumb form-search">
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
				<th>更新时间</th><th>备注信息</th><th>名称</th><th>性别</th>
				<shiro:hasPermission name="test:testDataMain:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="testDataMain">
			<tr>
				<td><a href="${ctx}/test/testDataMain/form?id=${testDataMain.id}"><fmt:formatDate value="${testDataMain.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/></a></td>
				<td>${testDataMain.remarks}</td>
				<td>${testDataMain.name}</td>
				<td>${fns:getDictLabel(testDataMain.sex, 'd_xb', '')}</td>
				<shiro:hasPermission name="test:testDataMain:edit"><td>
    				<a href="${ctx}/test/testDataMain/form?id=${testDataMain.id}">修改</a>
					<a href="${ctx}/test/testDataMain/delete?id=${testDataMain.id}" onclick="return confirmx('确认要删除该主子表用例吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
