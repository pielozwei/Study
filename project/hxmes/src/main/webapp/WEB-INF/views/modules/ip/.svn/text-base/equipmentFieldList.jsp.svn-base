<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>设备层次管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出设备层次数据吗？","系统提示",function(v,h,f){
					if(v == "ok"){
						exportTable('contentTable',"","","设备层次数据",'${ctx}');
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
				confirmx('确认要删除所选的设备层次吗？', "${ctx}/ip/equipmentField/deleteList?ids="+checked);
			}else{
				confirmx('请选择至少一行数据', window.location.href);
				return;
			}
		}
        function addf() {
    		window.location.href = "${ctx}/ip/equipmentField/form?nodeId=${nodeId}";
    	}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ip/equipmentField/?nodeId=${nodeId}">设备层次列表</a></li>
		<%-- <shiro:hasPermission name="ip:equipmentField:edit"><li><a href="${ctx}/ip/equipmentField/form?nodeId=${nodeId}">设备层次添加</a></li></shiro:hasPermission> --%>
		<button class="btn pull-right btn-primary" onclick="addf();">新增</button>
	</ul>
	<form:form id="searchForm" modelAttribute="equipmentField" 
	action="${ctx}/ip/equipmentField/?nodeId=${nodeId}" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>名称 ：</label><form:input path="ccmc" htmlEscape="false" maxlength="50" class="input-small"/>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr>
			<th><input type="checkbox" id="zj" onclick="$('.zj').prop('checked',$(this).prop('checked'));"/></th>
			<th>层次编码</th>
			<th>层次名称</th>
			<th>是否启用</th>
			<th>父节点编码</th>
			<th>父节点名称</th>
			<th>层次类型</th>
			<th>备注</th>
			<%-- <shiro:hasPermission name="ip:equipmentField:edit"><th>操作</th></shiro:hasPermission> --%>
		</tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="equipmentField">
			<tr>
				<td><input type="checkbox" class="zj" name="zj" value="${equipmentField.id}" /></td>
				<td><a href="${ctx}/ip/equipmentField/form?id=${equipmentField.id}">${equipmentField.ccbm}</a></td>
				<td><a href="${ctx}/ip/equipmentField/form?id=${equipmentField.id}">${equipmentField.ccmc}</a></td>
				<th>${fns:getDictLabel(equipmentField.sfqy, 'ckgl_ckgl_sfqy', '无')}</th>
				<td>${equipmentField.parent.ccbm}</td>
				<td>${equipmentField.parent.ccmc}</td>
				<td>${fns:getDictLabel(equipmentField.cclx, 'd_sbcclx', '无')}</td>
				<td>${equipmentField.remarks}</td>
				<%-- <shiro:hasPermission name="ip:equipmentField:edit"><td>
    				<a href="${ctx}/ip/equipmentField/form?id=${equipmentField.id}">修改</a>
					<a href="${ctx}/ip/equipmentField/delete?id=${equipmentField.id}" onclick="return confirmx('确认要删除该设备层次吗？', this.href)">删除</a>
				</td></shiro:hasPermission> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<shiro:hasPermission name="ip:equipmentField:edit"><div>
		<button class="btn btn-primary" onclick="editList('zj','${ctx}/ip/equipmentField/form')">编辑</button>
		<button class="btn btn-primary" onclick="delList();">删除</button>
		<button class="btn btn-primary" id="btnExport">导出</button>
		<button class="btn btn-primary" onclick="stopStateList('zj','${ctx}/ip/equipmentField/updateStateList?ids=','0');">启用</button>
		<button class="btn btn-primary" onclick="stopStateList('zj','${ctx}/ip/equipmentField/updateStateList?ids=','1');">禁用</button>
	</div></shiro:hasPermission>
	<div class="pagination">${page}</div>
</body>
</html>
