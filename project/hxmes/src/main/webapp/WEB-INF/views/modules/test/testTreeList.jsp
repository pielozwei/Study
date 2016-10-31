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
		function updateSort() {
			loading('正在提交，请稍等...');
	    	$("#listForm").attr("action", "${ctx}/test/testTree/updateSort");
	    	$("#listForm").submit();
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/test/testTree/">树结构生成列表</a></li>
		<shiro:hasPermission name="test:testTree:edit"><li><a href="${ctx}/test/testTree/form">树结构生成添加</a></li></shiro:hasPermission>
	</ul>
	<tags:message content="${message}"/>
	<form id="listForm" method="post">
		<table id="treeTable" class="table table-striped table-bordered table-condensed">
			<tr><th>名称</th><th>排序</th><th>更新时间</th><th>备注信息</th><shiro:hasPermission name="test:testTree:edit"><th>操作</th></shiro:hasPermission></tr>
			<c:forEach items="${list}" var="testTree">
				<tr id="${testTree.id}" pId="${testTree.parent.id ne '1' ? testTree.parent.id : '0'}">
					<td><a href="${ctx}/test/testTree/form?id=${testTree.id}">${testTree.name}</a></td>
					<td style="text-align:center;">
							${testTree.sort}
					</td>
					<td>${testTree.updateDate}</td>
					<td>${testTree.remarks}</td>
					<shiro:hasPermission name="test:testTree:edit"><td>
						<a href="${ctx}/test/testTree/form?id=${testTree.id}">修改</a>
						<a href="${ctx}/test/testTree/delete?id=${testTree.id}" onclick="return confirmx('要删除该菜单及所有子菜单项吗？', this.href)">删除</a>
						<a href="${ctx}/test/testTree/form?parent.id=${testTree.id}">添加下级菜单</a> 
					</td></shiro:hasPermission>
				</tr>
			</c:forEach>
		</table>
	</form>
</body>
</html>
