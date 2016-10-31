<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>设备BOM管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		$("#btnExport").click(function() {
			top.$.jBox.confirm("确认要导出设备BOM数据吗？", "系统提示", function(v, h, f) {
				if (v == "ok") {
					exportTable('contentTable', "", "", "设备BOM数据", '${ctx}');
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
		batchFnc('确认要删除所选的数据吗？', "${ctx}/ip/equipmentBOMSheet/delete", "zj", "");
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
			<a href="${ctx}/ip/equipmentBOMSheet/?equipmentModelId=${equipmentModelId}">设备BOM列表</a>
		</li>
		<li class="pull-right">
			<input id="btnAdd" class="btn btn-primary " type="button" value="新增"
				onclick="window.location.href='${ctx}/ip/equipmentBOMSheet/form?equipmentModel.id=${equipmentModelId}'" />
		</li>
	</ul>
	<form:form id="searchForm" modelAttribute="equipmentBOMSheet" action="${ctx}/ip/equipmentBOMSheet/?equipmentModelId=${equipmentModelId}"
		method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
		<label>BOM单编号 ：</label>
		<form:input path="sbbomdbh" htmlEscape="false" maxlength="50" class="input-small" />
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" />
	</form:form>
	<tags:message content="${message}" />
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>
					<input type="checkbox" id="zj" onclick="$('.zj').prop('checked',$(this).prop('checked'));" />
				</th>
				<th>设备BOM单编号</th>
				<th>设备规格编号</th>
				<th>设备规格名称</th>
				<th>版本号</th>
				<th>创建人</th>
				<th>创建日期</th>
				<th>审核人</th>
				<th>审核日期</th>
				<th>设备bom状态</th>
				<th>备注</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="equipmentBOMSheet">
				<tr>
					<td>
						<input type="checkbox" class="zj" name="zj" value="${equipmentBOMSheet.id}" />
					</td>
					<td>
						<a href="${ctx}/ip/equipmentBOMSheet/form?id=${equipmentBOMSheet.id}">${equipmentBOMSheet.sbbomdbh}</a>
					</td>
					<td>${equipmentBOMSheet.equipmentModel.sbggxhmc}</td>
					<td>${equipmentBOMSheet.equipmentModel.sbggxhbm}</td>
					<td>${equipmentBOMSheet.bbh}</td>
					<td>${equipmentBOMSheet.cjr}</td>
					<td>${equipmentBOMSheet.cjrq}</td>
					<td>${equipmentBOMSheet.shr}</td>
					<td>${equipmentBOMSheet.shrq}</td>
					<td>${fns:getDictLabel(equipmentBOMSheet.sbbomdzt, 'd_shiyong', '无')}</td>
					<td>${equipmentBOMSheet.bz}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<button class="btn btn-primary" onclick="editList('zj', '${ctx}/ip/equipmentBOMSheet/form');">修改</button>
	<button class="btn btn-primary" onclick="delList();">删除</button>
	<button class="btn btn-primary" id="btnExport">导出</button>
	<button class="btn btn-primary" onclick="fanhui();">返回基本信息列表</button>
	<div class="pagination">${page}</div>
</body>
</html>
