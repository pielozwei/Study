<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>仓库白名单管理</title>
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
				confirmx('确认要删除所选的仓库白名单吗？', "${ctx}/cangku/ckbmd/deleteList?ids="+checked,checked);
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
					exportTable('contentTable',"","","仓库白名单数据",'${ctx}');
					//$("#searchForm").attr("action","${ctx}/shebei/sbcc/export?id=${sbcc.id}&pageNo=${page.pageNo}&pageSize=${page.pageSize}").submit();
				}
			},{buttonsFocus:1});
			top.$('.jbox-body .jbox-icon').css('top','55px');
		});
	});
	
	function addf() {
		window.location.href = "${ctx}/cangku/ckbmd/form?ipCkCkbh=${ipCkCkbh}";
	}
	
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/cangku/ckbmd/list">仓库白名单列表</a></li>
		<%-- <shiro:hasPermission name="cangku:ckbmd:edit"><li><a href="${ctx}/cangku/ckbmd/form?ipCkCkbh=${ipCkCkbh}">仓库白名单添加</a></li></shiro:hasPermission> --%>
		<button class="btn pull-right btn-primary" onclick="addf();">新增</button>
	</ul>
	<form:form id="searchForm" modelAttribute="ckbmd" action="${ctx}/cangku/ckbmd/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>编号 ：</label><form:input path="name" htmlEscape="false" maxlength="50" class="input-small"/>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form> 
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th width="5%">选择</th>
				<th>仓库编码</th>
				<th>物料编码</th>
				<th>最大存储天数</th>
				<th>安全库存</th>
				<th>最高库存限额</th>
				<th>循环盘点间隔</th>
				<%-- <shiro:hasPermission name="cangku:ckbmd:edit"><th>操作</th></shiro:hasPermission> --%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="ckbmd">
			<tr>
				<%-- <td><a href="${ctx}/cangku/ckbmd/form?id=${ckbmd.id}">${ckbmd.id}</a></td> --%>
				<td><input type="checkbox" name="zj" value="${ckbmd.id}"/></td>
				<td><a href="${ctx}/cangku/ckbmd/form?id=${ckbmd.id}">${ckbmd.ipCkCkbh.ckbh}</a></td>
				<td>${ckbmd.ipWlbmWlbm.wlbm}</td>
				<td>${ckbmd.zdccts}</td>
				<td>${ckbmd.aqkc}</td>
				<td>${ckbmd.zdkcxe}</td>
				<td>${ckbmd.xhpdjg}</td>
				<%-- <shiro:hasPermission name="cangku:ckbmd:edit"><td>
    				<a href="${ctx}/cangku/ckbmd/form?id=${ckbmd.id}">修改</a>
					<a href="${ctx}/cangku/ckbmd/delete?id=${ckbmd.id}&ipCkCkbh=${ipCkCkbh}" onclick="return confirmx('确认要删除该仓库白名单吗？', this.href)">删除</a>
				</td></shiro:hasPermission> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div>
		<button class="btn btn-primary" onclick="editList('zj','${ctx}/cangku/ckbmd/form')">编辑</button>
		<button class="btn btn-primary" onclick="delList();">删除</button>
		<button class="btn btn-primary" id="btnExport">导出</button>
	</div>
	<div class="pagination">${page}</div>
</body>
</html>
