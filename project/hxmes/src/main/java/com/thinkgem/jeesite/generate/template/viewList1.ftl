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
		<li class="active"><a href="${r"${ctx}"}/${urlPrefix}/list?nodeId=${r"${nodeId}&_module=${r"${_module}"}"}">${functionName}列表</a></li>
		<shiro:hasPermission name="${permissionPrefix}:edit">
			<c:if test="${"${"+"_module=='"+className3+"'}"}">
				<li><a href="${r"${ctx}"}/${urlPrefix}/form?nodeId=${r"${nodeId}"}&_module=${r"${_module}"}"}">${functionName}添加</a></li>
			</c:if>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="${className3}" 
	action="${r"${ctx}"}/${urlPrefix}/list?nodeId=${r"${nodeId}"}" method="post" class="breadcrumb form-search">
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
			<th>备注</th>
			<shiro:hasPermission name="${permissionPrefix}:edit"><c:if test="${"${"+"_module=='"+className3+"'}"}"><th>操作</th></c:if></shiro:hasPermission>
		</tr></thead>
		<tbody>
		<c:forEach items="${r"${page.list}"}" var="${className3}">
			<tr>
				<td><input type="checkbox" class="zj" name="zj" value="${"${"+className3+".id}"}" /></td>
				<c:if test="${"${"+"_module=='"+className3+"'}"}">
					<td><a href="${r"${ctx}"}/${urlPrefix}/form?id=${"${"+className3+".id}"}">${"${"+className3+".id}"}</a></td>
					<td><a href="${r"${ctx}"}/${urlPrefix}/form?id=${"${"+className3+".id}"}">${"${"+className3+".name}"}</a></td>
				</c:if>
				<c:if test="${"${"+"_module!='"+className3+"'}"}">
					<td><a href="${r"${ctx}"}/${moduleName}/${r"${_module}"}/?${className3}Id=${"${"+className3+".id}"}">${"${"+className3+".id}"}</a></td>
					<td><a href="${r"${ctx}"}/${moduleName}/${r"${_module}"}/?${className3}Id=${"${"+className3+".id}"}">${"${"+className3+".name}"}</a></td>
				</c:if>
				<td>${"${"+className3+".remarks}"}</td>
				<shiro:hasPermission name="${permissionPrefix}:edit">
					<c:if test="${"${"+"_module=='"+className3+"'}"}">
						<td>
    						<a href="${r"${ctx}"}/${urlPrefix}/form?id=${"${"+className3+".id}"}">修改</a>
							<a href="${r"${ctx}"}/${urlPrefix}/delete?id=${"${"+className3+".id}"}" onclick="return confirmx('确认要删除该${functionName}吗？', this.href)">删除</a>
						</td>
					</c:if>
				</shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<shiro:hasPermission name="${permissionPrefix}:edit">
		<c:if test="${"${"+"_module=='"+className3+"'}"}">
			<div><button class="btn" onclick="delList();">删除</button><button class="btn" id="btnExport">导出</button></div>
		</c:if>
	</shiro:hasPermission>
	<div class="pagination">${r"${page}"}</div>
</body>
</html>
