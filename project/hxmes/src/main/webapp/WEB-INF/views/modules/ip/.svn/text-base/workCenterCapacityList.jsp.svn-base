<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工作中心产能配置管理</title>
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
				confirmx('确认要删除所选的产能信息吗？', "${ctx}/gzzx/gzzxcn/deleteList?ids="+checked,checked);
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
					exportTable('contentTable',"","","工作中心产能信息",'${ctx}');
					//$("#searchForm").attr("action","${ctx}/shebei/sbcc/export?id=${sbcc.id}&pageNo=${page.pageNo}&pageSize=${page.pageSize}").submit();
				}
			},{buttonsFocus:1});
			top.$('.jbox-body .jbox-icon').css('top','55px');
		});
	});
		function addf() {
			window.location.href = "${ctx}/gzzx/gzzxcn/form";
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/gzzx/gzzxcn/">工作中心产能配置列表</a></li>
		<%-- <shiro:hasPermission name="gzzx:gzzxcn:edit"><li><a href="${ctx}/gzzx/gzzxcn/form">工作中心产能配置添加</a></li></shiro:hasPermission> --%>
		<button class="btn pull-right btn-primary" onclick="addf();">新增</button>
	</ul>
	<form:form id="searchForm" modelAttribute="gzzxcn" action="${ctx}/gzzx/gzzxcn/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>产品编码 ：</label><form:input path="name" htmlEscape="false" maxlength="50" class="input-small"/>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>
	<tags:message content="${message}"/>
	<div style="overflow: auto;">
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th width="5%">选择</th>
				<th>标准产品编码</th>
				<th>标准生产时间单位</th>
				<th>标准生产时间</th>
				<th>设计年生产量</th>
				<th>审定年生产量</th>
				<th>实际年生产量</th>
				<th>年生产量百分比</th>
				<th>厂级标准设计年生产量</th>
				<th>厂级标准实际年生产量</th>
				<th>厂级标准审定年生产量</th>
				<th>厂级标准年生产量百分比</th>
				<%-- <shiro:hasPermission name="gzzx:gzzxcn:edit"><th>操作</th></shiro:hasPermission> --%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="gzzxcn">
			<tr>
				<%-- <td><a href="${ctx}/gzzx/gzzxcn/form?id=${gzzxcn.id}">${gzzxcn.name}</a></td> --%>
				<td><input type="checkbox" name="zj" value="${gzzxcn.id}"/></td>
				<td><a href="${ctx}/gzzx/gzzxcn/form?id=${gzzxcn.id}">${gzzxcn.bzcpId}</a></td>
				<td>${fns:getDictLabel(gzzxcn.bzscsjdw, 'gzzx_cn_bzscsjdw', '无')}</td>
				<td>${gzzxcn.bzscsj}</td>
				<td>${gzzxcn.sjnscl}</td>
				<td>${gzzxcn.sdnscl}</td>
				<td>${gzzxcn.sjnscl2}</td>
				<td>${gzzxcn.nsclbfb}</td>
				<td>${gzzxcn.cbpsjnscl}</td>
				<td>${gzzxcn.cbpsjnscl2}</td>
				<td>${gzzxcn.cbpsdnscl}</td>
				<td>${gzzxcn.cbpclbfb}</td>
				<%-- <shiro:hasPermission name="gzzx:gzzxcn:edit"><th>
    				<a href="${ctx}/gzzx/gzzxcn/form?id=${gzzxcn.id}">修改</a>
					<a href="${ctx}/gzzx/gzzxcn/delete?id=${gzzxcn.id}" onclick="return confirmx('确认要删除该工作中心产能配置吗？', this.href)">删除</a>
				</th></shiro:hasPermission> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div>
		<button class="btn btn-primary" onclick="delList();">删除</button>
		<button class="btn btn-primary" id="btnExport">导出</button>
		<button class="btn btn-primary" onclick="editList('zj','${ctx}/gzzx/gzzxcn/form')">编辑</button></div>
	<div class="pagination">${page}</div>
	</div>
</body>
</html>
