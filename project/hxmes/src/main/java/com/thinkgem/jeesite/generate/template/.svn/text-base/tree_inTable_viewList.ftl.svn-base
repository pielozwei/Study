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
		<li class="active"><a href="${r"${ctx}"}/${urlPrefix}/">${functionName}列表</a></li>
		<shiro:hasPermission name="${permissionPrefix}:edit"><li><a href="${r"${ctx}"}/${urlPrefix}/form">${functionName}添加</a></li></shiro:hasPermission>
	</ul>
	<tags:message content="${r"${message}"}"/>
	<form id="listForm" method="post">
		<table id="treeTable" class="table table-striped table-bordered table-condensed">
			<tr>
				<#list table.columnList as c><#if c.isList?? && c.isList == "1"><th>${c.comments}</th></#if></#list>
				<shiro:hasPermission name="${permissionPrefix}:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		<c:forEach items="${r"${list}"}" var="${className}">
			<tr id="${"${"+className+".id}"}" pId="${"${"+className+".parent.id ne '1'?"+className+".parent.id:'0'}"}" >
				<#assign firstListField = true>
				<#list table.columnList as c>
					<#if c.isList?? && c.isList == "1">
				<td><#if firstListField><a href="${r"${ctx}"}/${urlPrefix}/form?id=${"${"+className+".id}"}"></#if><#if c.simpleJavaType == "Date"><fmt:formatDate value="${"$"}{${className}.${c.javaFieldId}}" pattern="yyyy-MM-dd HH:mm:ss"/><#elseif c.showType == "select" || c.showType == "checkbox" || c.showType == "radiobox">${"$"}{fns:getDictLabel(${className}.${c.javaFieldId}, '${c.dictType}', '')}<#elseif c.showType == "userselect" || c.showType == "officeselect" || c.showType == "areaselect">${"$"}{${className}.${c.javaFieldName}}<#else>${"$"}{${className}.${c.javaFieldId}}</#if><#if firstListField></a></#if></td>
						<#assign firstListField = false>
					</#if>
				</#list>
				<shiro:hasPermission name="${permissionPrefix}:edit"><td>
    				<a href="${r"${ctx}"}/${urlPrefix}/form?id=${"${"+className+".id}"}">修改</a>
					<a href="${r"${ctx}"}/${urlPrefix}/delete?id=${"${"+className+".id}"}" onclick="return confirmx('确认要删除该${functionNameSimple}及其子节点吗？', this.href)">删除</a>
    				<a href="${r"${ctx}"}/${urlPrefix}/form?parent.id=${"${"+className+".id}"}">添加下级节点</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
	</table>
	</form>
</body>
</html>
