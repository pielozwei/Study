<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工作中心班次配置管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
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
				confirmx('确认要删除所选的班次信息吗？', "${ctx}/gzzx/gzzxbc/deleteList?ids="+checked,checked);
			}else{
				confirmx('请选择至少一行数据', window.location.href);
				return;
			}
		}
		
		//将信息导出到excel表格中
		$(document).ready(function() {
		$("#btnExport").click(function(){
			/*submit: function (v, h, f) { return true; }, 
			点击信息按钮后的回调函数，返回true时表示关闭窗口，参数有三个，
			v表示所点的按钮的返回值，h表示窗口内容的jQuery对象，f表示窗
			口内容里的form表单键值 */
			top.$.jBox.confirm("确认要导出用户数据吗？","系统提示",function(v,h,f){
				if(v == "ok"){
					exportTable('contentTable',"","","工作中心班次",'${ctx}');
					//$("#searchForm").attr("action","${ctx}/shebei/sbcc/export?id=${sbcc.id}&pageNo=${page.pageNo}&pageSize=${page.pageSize}").submit();
				}
			},{buttonsFocus:1});
			top.$('.jbox-body .jbox-icon').css('top','55px');
		});
	});
		function addf() {
			window.location.href = "${ctx}/gzzx/gzzxbc/form?gzbzId=${gzzxbc.gzbzId.id}";
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/gzzx/gzzxbc/">工作中心班次配置列表</a></li>
		<%-- <shiro:hasPermission name="gzzx:gzzxbc:edit"><li><a href="${ctx}/gzzx/gzzxbc/form?gzbzId=${gzzxbc.gzbzId.id}">工作中心班次配置添加</a></li></shiro:hasPermission> --%>
		<button class="btn pull-right btn-primary" onclick="history.go(-1)">返回</button>
		<button class="btn pull-right btn-primary" onclick="addf();">新增</button>
	</ul>
	<%-- <form:form id="searchForm" modelAttribute="gzzxbc" action="${ctx}/gzzx/gzzxbc/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>名称 ：</label><form:input path="name" htmlEscape="false" maxlength="50" class="input-small"/>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form> --%>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th width="5%">选择</th>
				<th>班次</th>
				<th>班名</th>
				<th>性质</th>
				<th>班时</th>
				<th>跨天标识</th>
				<th>开始时间</th>
				<th>结束时间</th>
				<%-- <shiro:hasPermission name="gzzx:gzzxbc:edit"><th>操作</th></shiro:hasPermission> --%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="gzzxbc">
			<tr>
				<td><input type="checkbox" name="zj" value="${gzzxbc.id}"/></td>
				<td>${gzzxbc.bc}</td>
				<td>${gzzxbc.bm}</td>
				<td>${fns:getDictLabel(gzzxbc.xz, 'gzzx_bc_xz', '无')}</td>
				<td>${gzzxbc.bs}</td>
				<td>${gzzxbc.ktbs}</td>
				<td>${gzzxbc.kssj}</td>
				<td>${gzzxbc.jssj}</td>
				
				<%-- <shiro:hasPermission name="gzzx:gzzxbc:edit"><td>
    				<a href="${ctx}/gzzx/gzzxbc/form?id=${gzzxbc.id}">修改</a>
					<a href="${ctx}/gzzx/gzzxbc/delete?id=${gzzxbc.id}" onclick="return confirmx('确认要删除该工作中心班次配置吗？', this.href)">删除</a>
				</td></shiro:hasPermission> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div>
		<button class="btn btn-primary" onclick="delList();">删除</button>
		<button class="btn btn-primary" id="btnExport">导出</button>
		<button class="btn btn-primary" onclick="editList('zj','${ctx}/gzzx/gzzxbc/form')">编辑</button>
	</div>
	<div class="pagination">${page}</div>
</body>
</html>
