<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工器具分类管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出工器具分类数据吗？","系统提示",function(v,h,f){
					if(v == "ok"){
						exportTable('contentTable',"","","工器具分类数据",'${ctx}');
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
				confirmx('确认要删除所选的工器具分类吗？', "${ctx}/ip/toolCategory/deleteList?ids="+checked);
			}else{
				confirmx('请选择至少一行数据', window.location.href);
				return;
			}
		}
        function addf() {
    		window.location.href = "${ctx}/ip/toolCategory/form?nodeId=${nodeId}";
    	}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ip/toolCategory/?nodeId=${nodeId}">工器具分类列表</a></li>
		<%-- <shiro:hasPermission name="ip:toolCategory:edit"><li><a href="${ctx}/ip/toolCategory/form?nodeId=${nodeId}">工器具分类添加</a></li></shiro:hasPermission> --%>
		<button class="btn pull-right btn-primary" onclick="addf();">新增</button>
	</ul>
	<form:form id="searchForm" modelAttribute="toolCategory" 
	action="${ctx}/ip/toolCategory/?nodeId=${nodeId}" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>名称 ：</label><form:input path="lbmc" htmlEscape="false" maxlength="50" class="input-small"/>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr>
			<th><input type="checkbox" id="zj" onclick="$('.zj').prop('checked',$(this).prop('checked'));"/></th>
			<th>分类编码</th>
			<th>分类名称</th>
			<th>分类简称</th>
			<th>是否启用</th>
			<th>父节点编码</th>
			<th>父节点名称</th>
			<th>备注</th>
			<%-- <shiro:hasPermission name="ip:toolCategory:edit"><th>操作</th></shiro:hasPermission> --%>
		</tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="toolCategory">
			<tr>
				<td><input type="checkbox" class="zj" name="zj" value="${toolCategory.id}" /></td>
				<td><a href="${ctx}/ip/toolCategory/form?id=${toolCategory.id}">${toolCategory.lbbh}</a></td>
				<td><a href="${ctx}/ip/toolCategory/form?id=${toolCategory.id}">${toolCategory.lbmc}</a></td>
				<td>${toolCategory.lbjc}</td>
				<td>${fns:getDictLabel(toolCategory.sfqy, 'ckgl_ckgl_sfqy', '无')}</td>
				<td>${toolCategory.parent.lbbh}</td>
				<td>${toolCategory.parent.lbmc}</td>
				<td>${toolCategory.remarks}</td>
				<%-- <shiro:hasPermission name="ip:toolCategory:edit"><td>
    				<a href="${ctx}/ip/toolCategory/form?id=${toolCategory.id}">修改</a>
					<a href="${ctx}/ip/toolCategory/delete?id=${toolCategory.id}" onclick="return confirmx('确认要删除该工器具分类吗？', this.href)">删除</a>
				</td></shiro:hasPermission> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<shiro:hasPermission name="ip:toolCategory:edit"><div>
		<button class="btn btn-primary" onclick="delList();">删除</button>
		<button class="btn btn-primary" id="btnExport">导出</button>
		<button class="btn btn-primary" onclick="stopStateList('zj','${ctx}/ip/toolCategory/updateStateList?ids=','0');">启用</button>
		<button class="btn btn-primary" onclick="stopStateList('zj','${ctx}/ip/toolCategory/updateStateList?ids=','1');">禁用</button>
	</div></shiro:hasPermission>
	<div class="pagination">${page}</div>
</body>
</html>
