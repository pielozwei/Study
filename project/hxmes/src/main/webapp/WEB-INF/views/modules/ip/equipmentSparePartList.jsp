<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>备品备件管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		$("#btnExport").click(function() {
			top.$.jBox.confirm("确认要导出备品备件数据吗？", "系统提示", function(v, h, f) {
				if (v == "ok") {
					exportTable('contentTable', "", "", "设备备品备件数据", '${ctx}');
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
	function delList() {
		batchFnc('确认要删除所选的设备分类吗？', "${ctx}/ip/equipmentSparePart/delete", "zj", "");
	}
	function fanhui() {
		var ipMenuFrame = $("#shebeiMenuFrame", parent.document);
		ipMenuFrame.contents().find("#tree a.curSelectedNode")[0].click();
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active">
			<a href="${ctx}/ip/equipmentSparePart/?equipmentModelId=${equipmentModelId}">备品备件列表</a>
		</li>
		<li class="pull-right">
			<input id="btnAdd" class="btn btn-primary " type="button" value="新增"
				onclick="window.location.href='${ctx}/ip/equipmentSparePart/form?equipmentModel.id=${equipmentModelId}'" />
		</li>
	</ul>
	<form:form id="searchForm" modelAttribute="equipmentSparePart" action="${ctx}/ip/equipmentSparePart/?equipmentModelId=${equipmentModelId}"
		method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
		<label>名称 ：</label>
		<form:input path="bjwlbm.wlmc" htmlEscape="false" maxlength="50" class="input-small" />
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" />
	</form:form>
	<tags:message content="${message}" />
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>
					<input type="checkbox" id="zj" onclick="$('.zj').prop('checked',$(this).prop('checked'));" />
				</th>
				<th>备品备件编码</th>
				<th>备品备件名称</th>
				<th>规格</th>
				<th>型号</th>
				<th>单位</th>
				<th>数量</th>
				<th>备注</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="equipmentSparePart">
				<tr>
					<td>
						<input type="checkbox" class="zj" name="zj" value="${equipmentSparePart.id}" />
					</td>
					<td>
						<a href="${ctx}/ip/equipmentSparePart/form?id=${equipmentSparePart.id}">${equipmentSparePart.bjwlbm.wlbm}</a>
					</td>
					<td>
						<a href="${ctx}/ip/equipmentSparePart/form?id=${equipmentSparePart.id}">${equipmentSparePart.bjwlbm.wlmc}</a>
					</td>
					<td>${equipmentSparePart.bjwlbm.gg}</td>
					<td>${equipmentSparePart.bjwlbm.xh}</td>
					<td>${fns:getJldwDictLabel(equipmentSparePart.bjwlbm.jldw.id,'无')}</td>
					<td>${equipmentSparePart.sl}</td>
					<td>${equipmentSparePart.bz}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
		<div>
			<button class="btn btn-primary" onclick="editList('zj', '${ctx}/ip/equipmentSparePart/form');">修改</button>
			<button class="btn btn-primary" onclick="delList();">删除</button>
			<button class="btn btn-primary" id="btnExport">导出</button>
			<button class="btn btn-primary" onclick="fanhui();">返回基本信息列表</button>
		</div>
	<div class="pagination">${page}</div>
</body>
</html>
