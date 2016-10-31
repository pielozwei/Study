<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>物料编码信息管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		var pId = "${wllb.id}";
		if (pId == "")
			pId = "1";
		$("#cur_wllb", parent.document).val(pId);
		$("#btnExport").click(function() {
			top.$.jBox.confirm("确认要导出用户数据吗？", "系统提示", function(v, h, f) {
				if (v == "ok") {
					exportTable('contentTable', "", "", "物料类别", '${ctx}');
				}
			}, {
				buttonsFocus : 1
			});
			top.$('.jbox-body .jbox-icon').css('top', '55px');
		});
	});
	$(function() {

		var wuliaoMenuFrame = $("#wuliaoMenuFrame", parent.document);
		var src = wuliaoMenuFrame.attr("src");
		wuliaoMenuFrame.attr("src", src);
	});
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
	function delList() {
		batchFnc('确认要删除所选的设备分类吗？', "${ctx}/ip/wllb/delete", "zj", "");
	}
	function enableFnc() {
		batchFnc('确认要启用所选的设备分类吗？', "${ctx}/ip/wllb/updateState", "zj", "&state=1");
	}
	function disableFnc() {
		batchFnc('确认要禁用所选的设备分类吗？', "${ctx}/ip/wllb/updateState", "zj", "&state=0");
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active">
			<a href="#">物料类别信息列表</a>
		</li>
		<li class="pull-right">
			<input id="btnAdd" class="btn btn-primary " type="button" value="新增" onclick="window.location.href='${ctx}/ip/wllb/form?id=${wllb.id}&type=add'" />
		</li>
	</ul>
	<form:form id="searchForm" modelAttribute="wllb" action="${ctx}/ip/wllb/?nodeId=${nodeId}" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
		<label>名称 ：</label>
		<form:input path="wllbmc" htmlEscape="false" maxlength="50" class="input-small" />
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" />
	</form:form>
	<tags:message content="${message}" />
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
					<th>
						<input type="checkbox" id="zj" onclick="$('.zj').prop('checked',$(this).prop('checked'));" />
					</th>
				<th>分类编码</th>
				<th>分类名称</th>
				<th>备注</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="_wllb">
				<tr>
					<td>
						<input type="checkbox" name="zj"  class="zj" value="${_wllb.id}" />
					</td>
					<td>${_wllb.wllbbm}</td>
					<td>
						<a href="${ctx}/ip/wllb/form?id=${_wllb.id}">${_wllb.wllbmc}</a>
					</td>
					<td>${_wllb.bz}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div>
		<button class="btn btn-primary" onclick="editList('zj', '${ctx}/ip/wllb/form');">修改</button>
		<button class="btn btn-primary" onclick="delList();">删除</button>
		<button class="btn btn-primary" id="btnExport">导出</button>
		<button class="btn btn-primary" id="btnEnable" onclick="enableFnc();">启用</button>
		<button class="btn btn-primary" id="btnDisable" onclick="disableFnc()">禁用</button>
	</div>
	<div class="pagination">${page}</div>
</body>
</html>
