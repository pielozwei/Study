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
						//$("#searchForm").attr("action","${ctx}/ip/equipment/export?nodeId=${nodeId}").submit();
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
				confirmx('确认要删除所选的基本信息吗？', "${ctx}/ip/equipment/deleteList?ids="+checked);
			}else{
				confirmx('请选择至少一行数据', window.location.href);
				return;
			}
		}
        function addf() {
    		window.location.href = "${ctx}/ip/equipment/form?nodeId=${nodeId}";
    	}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ip/equipment/list?nodeId=${nodeId}&_module=${_module}">基本信息列表</a></li>
		<%-- <shiro:hasPermission name="ip:equipment:edit">
			<c:if test="${_module=='equipment'}">
				<li><a href="${ctx}/ip/equipment/form?nodeId=${nodeId}">基本信息添加</a></li>
			</c:if>
		</shiro:hasPermission> --%>
		<button class="btn pull-right btn-primary" onclick="addf();">新增</button>
	</ul>
	<form:form id="searchForm" modelAttribute="equipment" 
	action="${ctx}/ip/equipment/list?nodeId=${nodeId}&_module=${_module}" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>名称 ：</label><form:input path="equipmentModel.sbggxhmc" htmlEscape="false" maxlength="50" class="input-small"/>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr>
			<th><input type="checkbox" id="zj" onclick="$('.zj').prop('checked',$(this).prop('checked'));"/></th>
			<th>设备编号</th>
			<th>设备名称</th>
			<th>出厂编号</th>
			<th>生产类型</th>
			<th>价值ABC分类</th>
			<th>设备等级</th>
			<th>安装区域位置</th>
			<th>来源</th>
			<th>备注</th>
			<%-- <shiro:hasPermission name="ip:equipment:edit"><c:if test="${_module=='equipment'}"><th>操作</th></c:if></shiro:hasPermission> --%>
		</tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="equipment">
			<tr>
				<td><input type="checkbox" class="zj" name="zj" value="${equipment.id}" /></td>
				<c:if test="${_module=='equipment'}">
					<td><a href="${ctx}/ip/equipment/form?id=${equipment.id}">${equipment.sbbm}</a></td>
					<td><a href="${ctx}/ip/equipment/form?id=${equipment.id}">${equipment.equipmentModel.sbggxhmc}</a></td>
				</c:if>
				<c:if test="${_module!='equipment'}">
					<td><a href="${ctx}/ip/${_module}/?equipmentId=${equipment.id}">${equipment.sbbm}</a></td>
					<td><a href="${ctx}/ip/${_module}/?equipmentId=${equipment.id}">${equipment.equipmentModel.sbggxhmc}</a></td>
				</c:if>
				<td>${equipment.ccbh}</td>
				<td>${fns:getDictLabel(equipment.sbsclx, 'd_sbsclx', '无')}</td>
				<td>${fns:getDictLabel(equipment.jzabcfl, 'd_abc', '无')}</td>
				<td>${fns:getDictLabel(equipment.sbdj, 'd_sbdj', '无')}</td>
				<td>${equipment.azqywz}</td>
				<td>${fns:getDictLabel(equipment.ly, 'd_sbly', '无')}</td>
				<td>${equipment.remarks}</td>
				<%-- <shiro:hasPermission name="ip:equipment:edit">
					<c:if test="${_module=='equipment'}">
						<td>
    						<a href="${ctx}/ip/equipment/form?id=${equipment.id}">修改</a>
							<a href="${ctx}/ip/equipment/delete?id=${equipment.id}" onclick="return confirmx('确认要删除该基本信息吗？', this.href)">删除</a>
						</td>
					</c:if>
				</shiro:hasPermission> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<shiro:hasPermission name="ip:equipment:edit">
		<c:if test="${_module=='equipment'}">
			<div>
				<button class="btn btn-primary" onclick="editList('zj','${ctx}/ip/equipment/form')">编辑</button>
				<button class="btn btn-primary" onclick="delList();">删除</button>
				<button class="btn btn-primary" id="btnExport">导出</button>
			</div>
		</c:if>
	</shiro:hasPermission>
	<div class="pagination">${page}</div>
</body>
</html>
