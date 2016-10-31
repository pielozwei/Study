<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工作班制管理</title>
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
				confirmx_tree('确认要删除所选的工作班制吗？', "${ctx}/ip/workShift/deleteList?ids="+checked,checked);
			}else{
				confirmx('请选择至少一行数据', window.location.href);
				return;
			}
		}
		
		// 确认对话框
		function confirmx_tree(mess, href,checked){
			top.$.jBox.confirm(mess,'系统提示',function(v,h,f){
				if(v=='ok'){
					//hasChildOrSb(checked,href);
					location = href;
				}
			},{buttonsFocus:1});
			top.$('.jbox-body .jbox-icon').css('top','55px');
			return false;
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
					exportTable('contentTable',"","","工作班制",'${ctx}');
					//$("#searchForm").attr("action","${ctx}/shebei/sbcc/export?id=${sbcc.id}&pageNo=${page.pageNo}&pageSize=${page.pageSize}").submit();
				}
			},{buttonsFocus:1});
			top.$('.jbox-body .jbox-icon').css('top','55px');
		});
	});
		function addf() {
			window.location.href = "${ctx}/ip/workShift/form";
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ip/workShift/">工作班制列表</a></li>
		<%-- <li><a href="${ctx}/ip/workShift/form">工作班制添加</a></li> --%>
		<button class="btn pull-right btn-primary" onclick="addf();">新增</button>
	</ul>
	<form:form id="searchForm" modelAttribute="workShift" action="${ctx}/ip/workShift/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>名称 ：</label><form:input path="bzmc" htmlEscape="false" maxlength="50" class="input-small"/>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>选择</th>
				<th>序号</th>
				<th>班制名</th>
				<th>班次管理</th>
				<%-- <shiro:hasPermission name="ip:workShift:edit"><th>操作</th></shiro:hasPermission> --%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="workShift" varStatus="vars">
			<tr>
				<td><input type="checkbox" name="zj" value="${workShift.id}"/></td>
				<td>${vars.count}</td>
				<td><a href="${ctx}/ip/workShift/form?id=${workShift.id}">${workShift.bzmc}</a></td>
				<td><a href="${ctx}/gzzx/gzzxbc?gzbzId=${workShift.id}">设置</a></td>
				
				<%-- <shiro:hasPermission name="ip:workShift:edit"><td>
    				<a href="${ctx}/ip/workShift/form?id=${workShift.id}">修改</a>
					<a href="${ctx}/ip/workShift/delete?id=${workShift.id}" onclick="return confirmx('确认要删除该工作班制吗？', this.href)">删除</a>
				</td></shiro:hasPermission> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div>
		<button class="btn btn-primary" onclick="editList('zj','${ctx}/ip/workShift/form')">编辑</button>
		<button class="btn btn-primary" onclick="delList();">删除</button>
		<button class="btn btn-primary" id="btnExport">导出</button>
		<%-- <button class="btn btn-primary" id="btnExport">导出</button>&nbsp;
		<button class="btn btn-primary" onclick="stopStateList('zj','${ctx}/gzzx/gzzxwh/updateStateList?ids=','0');">启用</button>&nbsp;
		<button class="btn btn-primary" onclick="stopStateList('zj','${ctx}/gzzx/gzzxwh/updateStateList?ids=','1');">禁用</button>&nbsp; --%>
	</div>
	<div class="pagination">${page}</div>
</body>
</html>
