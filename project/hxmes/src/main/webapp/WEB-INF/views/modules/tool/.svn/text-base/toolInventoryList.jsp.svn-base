<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>树结构管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	<style type="text/css">.table td i{margin:0 2px;}</style>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#treeTable").treeTable({expandLevel : 3});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/tool/toolInventory/">树形库房管理列表</a></li>
		<shiro:hasPermission name="tool:toolInventory:edit"><li><a href="${ctx}/tool/toolInventory/form">树形库房管理添加</a></li></shiro:hasPermission>
	</ul>
	<tags:message content="${message}"/>
	<form id="listForm" method="post">
		<table id="treeTable" class="table table-striped table-bordered table-condensed">
			<tr>
				<th>货架</th><th>货位</th><th>工器具编号</th><th>工器具名称</th><th>工器具数量</th><th>工器具状态</th><th>更新时间</th><th>备注</th>
				<shiro:hasPermission name="tool:toolInventory:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		<c:forEach items="${list}" var="toolInventory">
			<tr id="${toolInventory.id}" pId="${toolInventory.parent.id ne '1'?toolInventory.parent.id:'0'}" >
				<td><a href="${ctx}/tool/toolInventory/form?id=${toolInventory.id}">${toolInventory.shelves}</a></td>
				<td>${toolInventory.location}</td>
				<td>${toolInventory.toolNo}</td>
				<td>${toolInventory.name}</td>
				<td>${toolInventory.toolNumber}</td>
				<td>${fns:getDictLabel(toolInventory.toolState, 'd_syzt', '')}</td>
				<td><fmt:formatDate value="${toolInventory.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${toolInventory.remarks}</td>
				<shiro:hasPermission name="tool:toolInventory:edit"><td>
    				<a href="${ctx}/tool/toolInventory/form?id=${toolInventory.id}">修改</a>
					<a href="${ctx}/tool/toolInventory/delete?id=${toolInventory.id}" onclick="return confirmx('确认要删除该树形库房管理吗？', this.href)">删除</a>
					<a href="${ctx}/tool/toolInventory/form?parent.id=${toolInventory.id}">添加下级节点</a> 
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
	</table>
	</form>
</body>
</html>
