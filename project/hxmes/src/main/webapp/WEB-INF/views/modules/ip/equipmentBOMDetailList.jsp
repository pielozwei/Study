<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>设备BOM明细管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		$("#btnExport").click(function() {
			top.$.jBox.confirm("确认要导出设备BOM明细数据吗？", "系统提示", function(v, h, f) {
				if (v == "ok") {
					exportTable('contentTable', "", "", "设备BOM明细数据", '${ctx}');
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
	function addNextFunc() {
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
		if (checked.length == 1) {
			window.location.href = '${ctx}/ip/equipmentBOMDetail/form?equipmentBOMSheet.id=${equipmentBOMSheetId}&sjbommxid='+checked
		} else {
			confirmx('请选择一行数据', window.location.href);
			return;
		}
	}
	function delList() {
		batchFnc('确认要删除所选的数据吗？', "${ctx}/ip/equipmentBOMDetail/delete", "zj", "");
	}
	function fanhui() {
		var ipMenuFrame = $("#sheibeiMenuFrame", parent.document);
		ipMenuFrame.contents().find("#tree a.curSelectedNode")[0].click();
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li>
			<input id="btnAdd" class="btn btn-primary " type="button" value="新增"
				onclick="window.location.href='${ctx}/ip/equipmentBOMDetail/form?equipmentBOMSheet.id=${equipmentBOMSheetId}'" />
		</li>
		<li>&nbsp;</li>
		<li>
			<input id="btnAdd" class="btn btn-primary " type="button" value="新增下级" onclick="addNextFunc();" />
		</li>
		<li>&nbsp;</li>
		<li>
			<button class="btn btn-primary" onclick="editList('zj', '${ctx}/ip/equipmentBOMDetail/form');">修改</button>
		</li>
		<li>&nbsp;</li>
		<li>
			<div>
				<button class="btn btn-primary" id="btnExport">导出</button>
			</div>
		</li>
		<li>&nbsp;</li>
		<li>
			<button class="btn btn-primary" onclick="delList();">删除</button>
		</li>
	</ul>

	<tags:message content="${message}" />
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>
					<input type="checkbox" id="zj" onclick="$('.zj').prop('checked',$(this).prop('checked'));" />
				</th>
				<th>部组零件编码</th>
				<th>部组零件名称</th>
				<th>规格</th>
				<th>型号</th>
				<th>上级编码</th>
				<th>数量</th>
				<th>类别</th>
				<th>是否关键</th>
				<th>备注</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="equipmentBOMDetail">
				<tr>
					<td>
						<input type="checkbox" class="zj" name="zj" value="${equipmentBOMDetail.id}" />
					</td>
					<td>
						<a href="${ctx}/ip/equipmentBOMDetail/form?id=${equipmentBOMDetail.id}">${equipmentBOMDetail.equipmentModel.sbggxhbm}</a>
					</td>
					<td>
						<a href="${ctx}/ip/equipmentBOMDetail/form?id=${equipmentBOMDetail.id}">${equipmentBOMDetail.equipmentModel.sbggxhmc}</a>
					</td>
					<td>${equipmentBOMDetail.equipmentModel.sbgg}</td>
					<td>${equipmentBOMDetail.equipmentModel.sbxh}</td>
					<td>${equipmentBOMDetail.sjbommxid}</td>
					<td>${equipmentBOMDetail.sl}</td>
					<td>${fns:getDictLabel(equipmentBOMDetail.sbbomzjlb, 'd_sbbomzjlb', '无')}</td>
					<td>${fns:getDictLabel(equipmentBOMDetail.sfgjbj, 'd_yesno', '无')}</td>
					<td>${equipmentBOMDetail.bz}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<div class="pagination">${page}</div>
</body>
</html>
