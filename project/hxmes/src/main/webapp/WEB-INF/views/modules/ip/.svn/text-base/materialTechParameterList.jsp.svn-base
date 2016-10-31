<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>物料技术参数管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		$("#btnExport").click(function() {
			top.$.jBox.confirm("确认要导出用户数据吗？", "系统提示", function(v, h, f) {
				if (v == "ok") {
					exportTable('contentTable', "", "", "物料参数", '${ctx}');
				}
			}, {
				buttonsFocus : 1
			});
			top.$('.jbox-body .jbox-icon').css('top', '55px');
		});
	});
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
	function fanhui() {
		var wuliaoMenuFrame = $("#wuliaoMenuFrame", parent.document);
		console.log(wuliaoMenuFrame.contents().find("#tree a.curSelectedNode")[0]);
		wuliaoMenuFrame.contents().find("#tree a.curSelectedNode")[0].click();
	}
	function delList() {
		//获取勾选的条数
		batchFnc('确认要删除所选的设备信息吗？', "${ctx}/ip/wljscs/delete", "zj", "")
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active">
			<a href="${ctx}/ip/wljscs/?wlbm.id=${wljscs.wlbm.id}">物料技术参数列表</a>
		</li>
		<shiro:hasPermission name="wuliao:wljscs:edit">
			<li class="pull-right">
				<input id="btnAdd" class="btn btn-primary " type="button" value="新增"
					onclick="window.location.href='${ctx}/ip/wljscs/form/?wlbm.id=${wljscs.wlbm.id}'" />
			</li>
		</shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="wljscs" action="${ctx}/ip/wljscs/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
		<label>名称 ：</label>
		<form:input path="csmc" htmlEscape="false" maxlength="50" class="input-small" />
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" />
	</form:form>
	<tags:message content="${message}" />
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>
					<input type="checkbox" id="zj" onclick="$('.zj').prop('checked',$(this).prop('checked'));" />
				</th>
				<th>参数代码</th>
				<th>参数名称</th>
				<th>参数简称</th>
				<th>参数值类型</th>
				<th>参数计量单位</th>
				<th>标准值</th>
				<th>上限值</th>
				<th>下限值</th>
				<th>参数说明</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="wljscs">
				<tr>
					<td>
						<input type="checkbox" name="zj" class="zj" value="${wljscs.id}" />
					</td>
					<td>${wljscs.csdm}</td>
					<td>
						<a href="${ctx}/ip/wljscs/form?id=${wljscs.id}">${wljscs.csmc}</a>
					</td>
					<td>${wljscs.csjc}</td>
					<td>${fns:getDictLabel(wljscs.cszlx, 'd_wlcszlx', '无')}</td>
					<td>${fns:getJldwDictLabel(wljscs.csjldw.id,'无')}</td>
					<td>${wljscs.bzz}</td>
					<td>${wljscs.sxz}</td>
					<td>${wljscs.xxz}</td>
					<td>${wljscs.cssm}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div>
		<div>
			<button class="btn btn-primary" onclick="editList('zj','${ctx}/ip/wljscs/form');">修改</button>
			<button class="btn btn-primary" onclick="delList();">删除</button>
			<button class="btn btn-primary" id="btnExport">导出</button>
			<button class="btn btn-primary" onclick="fanhui();">返回基本信息列表</button>
		</div>
	</div>
	<div class="pagination">${page}</div>
</body>
</html>
