<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>服务商管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出服务商数据吗？","系统提示",function(v,h,f){
					if(v == "ok"){
						exportTable('contentTable',"","","设备服务商数据",'${ctx}');
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
				confirmx('确认要删除所选的服务商吗？', "${ctx}/ip/equipmentServiceProvider/deleteList?ids="+checked);
			}else{
				confirmx('请选择至少一行数据', window.location.href);
				return;
			}
		}
        function fanhui(){
        	var ipMenuFrame=$("#shebeiMenuFrame",parent.document);
        	ipMenuFrame.contents().find("#tree a.curSelectedNode")[0].click();
        }
        function addf() {
    		window.location.href = "${ctx}/ip/equipmentServiceProvider/form?equipment.id=${equipmentId}";
    	}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ip/equipmentServiceProvider/?equipmentId=${equipmentId}">服务商列表</a></li>
		<%-- <shiro:hasPermission name="ip:equipmentServiceProvider:edit"><li><a href="${ctx}/ip/equipmentServiceProvider/form?equipment.id=${equipmentId}">服务商添加</a></li></shiro:hasPermission> --%>
		<button class="btn pull-right btn-primary" onclick="addf();">新增</button>
	</ul>
	<form:form id="searchForm" modelAttribute="equipmentServiceProvider" 
	action="${ctx}/ip/equipmentServiceProvider/?equipmentId=${equipmentId}" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>名称 ：</label><form:input path="provider.gysmc" htmlEscape="false" maxlength="50" class="input-small"/>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr>
			<th><input type="checkbox" id="zj" onclick="$('.zj').prop('checked',$(this).prop('checked'));"/></th>
			<th>服务商编码</th>
			<th>服务商名称</th>
			<th>服务商类型</th>
			<th>联系人</th>
			<th>联系电话</th>
			<th>备注</th>
			<%-- <shiro:hasPermission name="ip:equipmentServiceProvider:edit"><th>操作</th></shiro:hasPermission> --%>
		</tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="equipmentServiceProvider">
			<tr>
				<td><input type="checkbox" class="zj" name="zj" value="${equipmentServiceProvider.id}" /></td>
				<td><a href="${ctx}/ip/equipmentServiceProvider/form?id=${equipmentServiceProvider.id}">${equipmentServiceProvider.provider.gysbm}</a></td>
				<td><a href="${ctx}/ip/equipmentServiceProvider/form?id=${equipmentServiceProvider.id}">${equipmentServiceProvider.provider.gysmc}</a></td>
				<td>${fns:getDictLabel(equipmentServiceProvider.fwlx, 'd_fwlx', '无')}</td>
				<td>${equipmentServiceProvider.lxr}</td>
				<td>${equipmentServiceProvider.lxdh}</td>
				<td>${equipmentServiceProvider.remarks}</td>
				<%-- <shiro:hasPermission name="ip:equipmentServiceProvider:edit"><td>
    				<a href="${ctx}/ip/equipmentServiceProvider/form?id=${equipmentServiceProvider.id}">修改</a>
					<a href="${ctx}/ip/equipmentServiceProvider/delete?id=${equipmentServiceProvider.id}" onclick="return confirmx('确认要删除该服务商吗？', this.href)">删除</a>
				</td></shiro:hasPermission> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<shiro:hasPermission name="ip:equipmentServiceProvider:edit"><div>
		<button class="btn btn-primary" onclick="delList();">删除</button>
		<button class="btn btn-primary" id="btnExport">导出</button>
		<button class="btn btn-primary" onclick="fanhui();">返回设备基本信息列表</button>
	</div></shiro:hasPermission>
	<div class="pagination">${page}</div>
</body>
</html>
