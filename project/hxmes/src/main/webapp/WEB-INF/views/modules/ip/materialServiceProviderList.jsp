<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>物料服务供应商管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		$(document).ready(function() {
			$("#btnExport").click(function() {
				top.$.jBox.confirm("确认要导出用户数据吗？", "系统提示", function(v, h, f) {
					if (v == "ok") {
						exportTable('contentTable', "", "", "物料服务供应商", '${ctx}');
					}
				}, {
					buttonsFocus : 1
				});
				top.$('.jbox-body .jbox-icon').css('top', '55px');
			});
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
		batchFnc('确认要删除所选的设备信息吗？', "${ctx}/ip/wlfwgys/delete", "zj", "")
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active">
			<a href="${ctx}/ip/wlfwgys/?wlbm.id=${wlfwgys.wlbm.id}">物料服务供应商列表</a>
		</li>
			<li class="pull-right">
				<input id="btnAdd" class="btn btn-primary " type="button" value="新增"
					onclick="window.location.href='${ctx}/ip/wlfwgys/form/?wlbm.id=${wlfwgys.wlbm.id}'" />
			</li>
	</ul>
	<form:form id="searchForm" modelAttribute="wlfwgys" action="${ctx}/ip/wlfwgys/?wlbm.id=${wlfwgys.wlbm.id}" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
		<label>名称 ：</label>
		<form:input path="provider.gysmc" htmlEscape="false" maxlength="50" class="input-small" />
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" />
	</form:form>
	<tags:message content="${message}" />
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>
					<input type="checkbox" id="zj" onclick="$('.zj').prop('checked',$(this).prop('checked'));" />
				</th>
				<th>服务商编码</th>
				<th>服务商名称</th>
				<th>服务类型</th>
				<th>联系人</th>
				<th>联系人电话</th>
				<th>备注</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="wlfwgys">
				<tr>
					<td>
						<input type="checkbox" name="zj" class="zj" value="${wlfwgys.id}" />
					</td>
					<td>${wlfwgys.provider.gysbm}</td>
					<td>
						<a href="${ctx}/ip/wlfwgys/form?id=${wlfwgys.id}">${wlfwgys.provider.gysmc}</a>
					</td>
					<td>${fns:getDictLabel(wlfwgys.fwlx, 'd_fwlx', '无')}</td>
					<td>${wlfwgys.provider.lxr}</td>
					<td>${wlfwgys.provider.lxdh}</td>
					<td>${wlfwgys.bz}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div>
		<button class="btn btn-primary" onclick="editList('zj','${ctx}/ip/wlfwgys/form');">修改</button>
		<button class="btn btn-primary" onclick="delList();">删除</button>
		<button class="btn btn-primary" id="btnExport">导出</button>
		<button class="btn btn-primary" onclick="fanhui();">返回基本信息列表</button>
	</div>
	<div class="pagination">${page}</div>
</body>
</html>
