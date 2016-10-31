<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>BOM清单管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		$("#btnExport").click(function() {
			top.$.jBox.confirm("确认要导出用户数据吗？", "系统提示", function(v, h, f) {
				if (v == "ok") {
					exportTable('contentTable', "", "", "BOM单", '${ctx}');
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
		batchFnc('确认要删除所选的产品BOM单吗？', "${ctx}/ip/productBOMSheet/delete", "zj", "");
	}
	window.onload = function() {
		//提示信息增加
		var msg = '${message}';
		if (msg) {
			top.$.jBox.tip(msg, 'success');
		}
		//增加序号
		var oTable = document.getElementById("tbody");
		for (var i = 0; i < oTable.rows.length; i++) {
			oTable.rows[i].cells[1].innerHTML = (i + 1);
		}
	};
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active">
			<a href="${ctx}/ip/productBOMSheet/">BOM清单列表</a>
		</li>
		<li class="pull-right">
			<input id="btnAdd" class="btn btn-primary " type="button" value="新增" onclick="window.location.href='${ctx}/ip/productBOMSheet/form/?type=add'" />
		</li>
	</ul>
	<form:form id="searchForm" modelAttribute="productBOMSheet" action="${ctx}/ip/productBOMSheet/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
		<label>BOM单号 ：</label>
		<form:input path="cpbomdbh" htmlEscape="false" maxlength="50" class="input-small" />
		<label>物料名称 ：</label>
		<form:input path="wlbm.wlmc" htmlEscape="false" maxlength="50" class="input-small" />
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" />
	</form:form>
	<tags:message content="${message}" />
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>
					<input type="checkbox" id="zj" onclick="$('.zj').prop('checked',$(this).prop('checked'));" />
				</th>
				<th>序号</th>
				<th>BOM单号</th>
				<th>版本</th>
				<th>物料编码</th>
				<th>物料名称</th>
				<th>BOM组别</th>
				<th>BOM树</th>
				<th>是否在用</th>
				<th>是否启用</th>
			</tr>
		</thead>
		<tbody id="tbody">
			<c:forEach items="${page.list}" var="productBOMSheet">
				<tr>
					<td>
						<input type="checkbox" class="zj" name="zj" value="${productBOMSheet.id}" />
					</td>
					<td></td>
					<td>
						<a href="${ctx}/ip/productBOMSheet/form?id=${productBOMSheet.id}">${productBOMSheet.cpbomdbh}</a>
					</td>
					<td>${productBOMSheet.bb}</td>
					<td>${productBOMSheet.wlbm.wlbm}</td>
					<td>${productBOMSheet.wlbm.wlmc}</td>
					<td>${fns:getDictLabel(productBOMSheet.bomzb, 'd_bomzb', '无')}</td>
					<td>
						<a href="${ctx}/ip/productBOMSheet/bomIndex?id=${productBOMSheet.id}">${productBOMSheet.bomzb=='0'?'查看':''}</a>
					</td>
					<td>${fns:getDictLabel(productBOMSheet.sfzy, 'yes_no', '无')}</td>
					<td>${fns:getDictLabel(productBOMSheet.sfqy, 'yes_no', '无')}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div>
		<button class="btn btn-primary" onclick="editList('zj','${ctx}/ip/productBOMSheet/form');">编辑</button>
		<button class="btn btn-primary" onclick="delList();">删除</button>
		<button class="btn btn-primary" id="btnExport">导出</button>
	</div>
	<div class="pagination">${page}</div>
</body>
</html>
