<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>左树右表_同一表管理</title>
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
		<li class="active"><a href="${ctx}/test/testLtree/">左树右表_同一表列表</a></li>
		<shiro:hasPermission name="test:testLtree:edit"><li><a href="${ctx}/test/testLtree/form">左树右表_同一表添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="testLtree" action="${ctx}/test/testLtree/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
			<label>节点名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="64" class="input-medium"/>
			<label>删除标记：</label>
				<form:radiobuttons path="delFlag" items="${fns:getDictList('del_flag')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>节点名称</th><th>定位</th><th>更新时间</th><th>备注</th>
				<shiro:hasPermission name="test:testLtree:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="testLtree">
			<tr>
				<td><a href="${ctx}/test/testLtree/form?id=${testLtree.id}">${testLtree.name}</a></td>
				<td>${testLtree.location}</td>
				<td><fmt:formatDate value="${testLtree.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${testLtree.remarks}</td>
				<shiro:hasPermission name="test:testLtree:edit"><td>
    				<a href="${ctx}/test/testLtree/form?id=${testLtree.id}">修改</a>
					<a href="${ctx}/test/testLtree/delete?id=${testLtree.id}" onclick="return confirmx('确认要删除该左树右表吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
