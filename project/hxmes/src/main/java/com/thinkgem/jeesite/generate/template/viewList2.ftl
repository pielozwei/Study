<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>${functionName}管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出${functionName}数据吗？","系统提示",function(v,h,f){
					if(v == "ok"){
						exportTable('contentTable',"","","${functionName}数据",'${r"${ctx}"}');
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
        function delList(){
			//获取勾选的条数
			var checked = new Array;
			var rows = document.getElementsByName("zj");
			var j=0;
			for ( var i = 0; i < rows.length; i++) {
				if (rows[i].checked){
					checked[j]=rows[i].value;
					j++;
				}
			}
			if(checked.length>=1){
				confirmx('确认要删除所选的${functionName}吗？', "${r"${ctx}"}/${urlPrefix}/deleteList?ids="+checked);
			}else{
				confirmx('请选择至少一行数据', window.location.href);
				return;
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${r"${ctx}"}/${urlPrefix}/?nodeId=${r"${nodeId}"}">${functionName}列表</a></li>
		<shiro:hasPermission name="${permissionPrefix}:edit"><li><a href="${r"${ctx}"}/${urlPrefix}/form?nodeId=${r"${nodeId}"}">${functionName}添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="${className}" 
	action="${r"${ctx}"}/${urlPrefix}/?nodeId=${r"${nodeId}"}" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${r"${page.pageNo}"}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${r"${page.pageSize}"}"/>
		<label>名称 ：</label><form:input path="name" htmlEscape="false" maxlength="50" class="input-small"/>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>
	<tags:message content="${r"${message}"}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr>
			<th><input type="checkbox" id="zj" onclick="$('.zj').prop('checked',$(this).prop('checked'));"/></th>
			<th>参数代码</th>
			<th>参数名称</th>
			<th>父节点编码</th>
			<th>父节点名称</th>
			<th>备注</th>
			<shiro:hasPermission name="${permissionPrefix}:edit"><th>操作</th></shiro:hasPermission>
		</tr></thead>
		<tbody>
		<c:forEach items="${r"${page.list}"}" var="${className}">
			<tr>
				<td><input type="checkbox" class="zj" name="zj" value="${"${"+className+".id}"}" /></td>
				<td><a href="${r"${ctx}"}/${urlPrefix}/form?id=${"${"+className+".id}"}">${"${"+className+".id}"}</a></td>
				<td><a href="${r"${ctx}"}/${urlPrefix}/form?id=${"${"+className+".id}"}">${"${"+className+".name}"}</a></td>
				<td>${"${"+className+".parent.id}"}</td>
				<td>${"${"+className+".parent.name}"}</td>
				<td>${"${"+className+".remarks}"}</td>
				<shiro:hasPermission name="${permissionPrefix}:edit"><td>
    				<a href="${r"${ctx}"}/${urlPrefix}/form?id=${"${"+className+".id}"}">修改</a>
					<a href="${r"${ctx}"}/${urlPrefix}/delete?id=${"${"+className+".id}"}" onclick="return confirmx('确认要删除该${functionName}吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<shiro:hasPermission name="${permissionPrefix}:edit"><div>
		<button class="btn" onclick="delList();">删除</button>
		<button class="btn" id="btnExport">导出</button>
	</div></shiro:hasPermission>
	<div class="pagination">${r"${page}"}</div>
</body>
</html>
