<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>技术资料管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		$("#btnExport").click(function() {
			top.$.jBox.confirm("确认要导出技术资料数据吗？", "系统提示", function(v, h, f) {
				if (v == "ok") {
					exportTable('contentTable', "", "", "设备技术资料数据", '${ctx}');
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
		batchFnc('确认要删除所选的数据吗？', "${ctx}/ip/equipmentDocument/delete", "zj", "");
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
			<a href="${ctx}/ip/equipmentDocument/?equipmentModelId=${equipmentModelId}">技术资料列表</a>
		</li>
		<li class="pull-right">
			<input id="btnAdd" class="btn btn-primary " type="button" value="新增"
				onclick="window.location.href='${ctx}/ip/equipmentDocument/form?equipmentModel.id=${equipmentModelId}'" />
		</li>
	</ul>
	<form:form id="searchForm" modelAttribute="equipmentDocument" action="${ctx}/ip/equipmentDocument/?equipmentModelId=${equipmentModelId}"
		method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
		<label>名称 ：</label>
		<form:input path="wdbt" htmlEscape="false" maxlength="50" class="input-small" />
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" />
	</form:form>
	<tags:message content="${message}" />
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>
					<input type="checkbox" id="zj" onclick="$('.zj').prop('checked',$(this).prop('checked'));" />
				</th>
				<th>文档编码</th>
				<th>文档标题</th>
				<th>关 键 词</th>
				<th>文档摘要</th>
				<th>文档类别</th>
				<th>备注</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="equipmentDocument">
				<tr>
					<td>
						<input type="checkbox" class="zj" name="zj" value="${equipmentDocument.id}" />
					</td>
					<td>
						<a href="${ctx}/ip/equipmentDocument/form?id=${equipmentDocument.id}">${equipmentDocument.wdbm}</a>
					</td>
					<td>
						<a href="${ctx}/ip/equipmentDocument/form?id=${equipmentDocument.id}">${equipmentDocument.wdbt}</a>
					</td>
					<td>${equipmentDocument.gjc}</td>
					<td>${equipmentDocument.zy}</td>
					<td>${fns:getDictLabel(equipmentDocument.wdlb, 'd_wdfl', '无')}</td>
					<td>${equipmentDocument.bz}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
		<div>
			<button class="btn btn-primary" onclick="editList('zj', '${ctx}/ip/equipmentDocument/form');">修改</button>
				<button class="btn btn-primary" onclick="delList();">删除</button>
				<button class="btn btn-primary" id="btnExport">导出</button>
				<button class="btn btn-primary" onclick="fanhui();">返回设备基本信息列表</button>
		</div>
	<div class="pagination">${page}</div>
	<!-- 下载附件. -->
	<form:form modelAttribute="equipmentDocument" id="downLoad" class="breadcrumb form-search" style="text-align:center;display: none;"
		action="${ctx}/ip/equipmentDocument/downLoad?equipmentModelId=${equipmentModelId}" method="post">
		<input type="hidden" name="pageSize" value="${page.pageSize}" />
		<input type="hidden" name="pageNo" value="${page.pageNo}" />
		<input type="hidden" id="url" name="url" />
		<input type="hidden" id="filename" name="filename" />
	</form:form>
</body>
</html>
