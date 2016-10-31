<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>基本信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出基本信息数据吗？","系统提示",function(v,h,f){
					if(v == "ok"){
						exportTable('contentTable',"","","基本信息数据",'${ctx}');
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
				confirmx('确认要删除所选的基本信息吗？', "${ctx}/ip/tool/deleteList?ids="+checked);
			}else{
				confirmx('请选择至少一行数据', window.location.href);
				return;
			}
		}
        function addf() {
    		window.location.href = "${ctx}/ip/tool/form?nodeId=${nodeId}";
    	}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ip/tool/list?nodeId=${nodeId}&_module=${_module}">基本信息列表</a></li>
		<%-- <shiro:hasPermission name="ip:tool:edit">
			<c:if test="${_module=='tool'}">
				<li><a href="${ctx}/ip/tool/form?nodeId=${nodeId}">基本信息添加</a></li>
			</c:if>
		</shiro:hasPermission> --%>
		<button class="btn pull-right btn-primary" onclick="addf();">新增</button>
	</ul>
	<form:form id="searchForm" modelAttribute="tool" 
	action="${ctx}/ip/tool/list?nodeId=${nodeId}&_module=${_module}" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>名称 ：</label><form:input path="gzqjmc" htmlEscape="false" maxlength="50" class="input-small"/>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr>
			<th><input type="checkbox" id="zj" onclick="$('.zj').prop('checked',$(this).prop('checked'));"/></th>
			<th>工器具编码</th>
			<th>工器具名称</th>
			<th>规格</th>
			<th>型号</th>
			<th>出厂编码</th>
			<th>归属部门</th>
			<th>使用人</th>
			<th>备注</th>
			<%-- <shiro:hasPermission name="ip:tool:edit"><c:if test="${_module=='tool'}"><th>操作</th></c:if></shiro:hasPermission> --%>
		</tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="tool">
			<tr>
				<td><input type="checkbox" class="zj" name="zj" value="${tool.id}" /></td>
				<c:if test="${_module=='tool'}">
					<td><a href="${ctx}/ip/tool/form?id=${tool.id}">${tool.gzqjbm}</a></td>
					<td><a href="${ctx}/ip/tool/form?id=${tool.id}">${tool.gzqjmc}</a></td>
				</c:if>
				<c:if test="${_module!='tool'}">
					<td><a href="${ctx}/ip/${_module}/?toolId=${tool.id}">${tool.gzqjbm}</a></td>
					<td><a href="${ctx}/ip/${_module}/?toolId=${tool.id}">${tool.gzqjmc}</a></td>
				</c:if>
				<td>${tool.gg}</td>
				<td>${tool.ccbh}</td>
				<td>${tool.gsbm}</td>
				<td>${tool.syr}</td>
				<td>${tool.xh}</td>
				<td>${tool.bz}</td>
				<%-- <shiro:hasPermission name="ip:tool:edit">
					<c:if test="${_module=='tool'}">
						<td>
    						<a href="${ctx}/ip/tool/form?id=${tool.id}">修改</a>
							<a href="${ctx}/ip/tool/delete?id=${tool.id}" onclick="return confirmx('确认要删除该基本信息吗？', this.href)">删除</a>
						</td>
					</c:if>
				</shiro:hasPermission> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<shiro:hasPermission name="ip:tool:edit">
		<c:if test="${_module=='tool'}">
			<div>
				<button class="btn btn-primary" onclick="delList();">删除</button>
				<button class="btn btn-primary" id="btnExport">导出</button>
			</div>
		</c:if>
	</shiro:hasPermission>
	<div class="pagination">${page}</div>
</body>
</html>
