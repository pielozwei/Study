<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>计量单位管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {

	});
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
	function qx(){
		var flag = document.getElementById("qx");
		var rows = document.getElementsByName("zj");
		var j = 0;
		//alert(flag.checked);
		if(flag.checked){
			for (var i = 0; i < rows.length; i++) {
				rows[i].checked=true;
			}
		}else{
			for (var i = 0; i < rows.length; i++) {
				rows[i].checked=false;
			}
		}
		
	}
	function delList() {
		//获取勾选的条数
		var checked = new Array;
		var rows = document.getElementsByName("zj");
		var j = 0;
		for (var i = 0; i < rows.length; i++) {
			if (rows[i].checked) {
				checked[j] = rows[i].value;
				j++;
			}
		}
		if (checked.length >= 1) {
			confirmx_tree('确认要删除所选的计量单位吗？', "${ctx}/ip/jldw/deleteList?ids="
					+ checked, checked);
		} else {
			confirmx('请选择至少一行数据', window.location.href);
			return;
		}
	}

	// 确认对话框
	function confirmx_tree(mess, href, checked) {
		top.$.jBox.confirm(mess, '系统提示', function(v, h, f) {
			if (v == 'ok') {
				location = href;
			}
		}, {
			buttonsFocus : 1
		});
		top.$('.jbox-body .jbox-icon').css('top', '55px');
		return false;
	}
	function addf() {
		window.location.href = "${ctx}/ip/jldw/form";
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ip/jldw/">计量单位列表</a></li>
		<%-- <shiro:hasPermission name="ip:jldw:edit">
			<li><a href="${ctx}/ip/jldw/form">计量单位添加</a></li>
		</shiro:hasPermission> --%>
		<button class="btn pull-right btn-primary" onclick="addf();">添加</button>
	</ul>
	<form:form id="searchForm" modelAttribute="jldw"
		action="${ctx}/ip/jldw/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
		<label>名称 ：</label>
		<form:input path="jldwmc" htmlEscape="false" maxlength="50"
			class="input-small" />
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit"
			value="查询" />
	</form:form>
	<tags:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th><input type="checkbox" id="qx" onclick="qx();" />全选</th>
				<th>计量单位编码</th>
				<th>计量单位名称</th>
				<th>显示顺序</th>
				<th>是否启用</th>
				<th>备注</th>
				<%-- <shiro:hasPermission name="ip:jldw:edit">
					<th>操作</th>
				</shiro:hasPermission> --%>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="jldw">
				<tr>
					<%-- <td><a href="${ctx}/ip/jldw/form?id=${jldw.id}">${jldw.name}</a></td> --%>
					<th><input type="checkbox" name="zj" value="${jldw.id}" /></th>
					<td>${jldw.jldwbm}</td>
					<td>${jldw.jldwmc}</td>
					<td>${jldw.xssx}</td>
					<%-- <td>${jldw.sfqy}</td> --%>
					<th>${fns:getDictLabel(jldw.sfqy, 'ckgl_ckgl_sfqy', '无')}</th>
					<td>${jldw.bz}</td>
					<%-- <shiro:hasPermission name="ip:jldw:edit">
						<td><a href="${ctx}/ip/jldw/form?id=${jldw.id}">修改</a> <a
							href="${ctx}/ip/jldw/delete?id=${jldw.id}"
							onclick="return confirmx('确认要删除该计量单位吗？', this.href)">删除</a></td>
					</shiro:hasPermission> --%>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div>
		<button class="btn btn-primary" onclick="editList('zj','${ctx}/ip/jldw/form')">编辑</button>
			<!-- <button class="btn btn-primary" onclick="delList();">删除</button> -->
			<%-- <button class="btn btn-primary" onclick="stopStateList('zj','${ctx}/ip/jldw/updateStateList?ids=','0');">启用</button>
			<button class="btn btn-primary" onclick="stopStateList('zj','${ctx}/ip/jldw/updateStateList?ids=','1');">禁用</button> --%>
	</div>
	<div class="pagination">${page}</div>
</body>
</html>
