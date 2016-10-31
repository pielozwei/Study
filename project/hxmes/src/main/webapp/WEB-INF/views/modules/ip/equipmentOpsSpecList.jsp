<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>操作规程管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		$("#btnExport").click(function() {
			top.$.jBox.confirm("确认要导出操作规程数据吗？", "系统提示", function(v, h, f) {
				if (v == "ok") {
					exportTable('contentTable', "", "", "操作规程数据", '${ctx}');
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
		batchFnc('确认要删除所选的数据吗？', "${ctx}/ip/equipmentOpsSpec/delete", "zj", "");
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
			<a href="${ctx}/ip/equipmentOpsSpec/?equipmentModelId=${equipmentModelId}">操作规程列表</a>
		</li>
		<li class="pull-right">
			<input id="btnAdd" class="btn btn-primary " type="button" value="新增"
				onclick="window.location.href='${ctx}/ip/equipmentOpsSpec/form?equipmentModel.id=${equipmentModelId}'" />
		</li>
	</ul>
	<form:form id="searchForm" modelAttribute="equipmentOpsSpec" action="${ctx}/ip/equipmentOpsSpec/?equipmentModelId=${equipmentModelId}" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
		<label>规章类别 ：</label>
		<form:select path="gclb">
			<form:option value="">请选择</form:option>
			<form:options items="${fns:getDictList('d_gflb')}" itemLabel="label" itemValue="value" htmlEscape="false" />
		</form:select>
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
				<th>规程类别</th>
				<th>规程名称</th>
				<th>规程描述</th>
				<th>备注</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="equipmentOpsSpec">
				<tr>
					<td>
						<input type="checkbox" class="zj" name="zj" value="${equipmentOpsSpec.id}" />
					</td>
					<td>${equipmentOpsSpec.xssx}</td>
					<td>
						<a href="${ctx}/ip/equipmentOpsSpec/form?id=${equipmentOpsSpec.id}">${fns:getDictLabel(equipmentOpsSpec.gclb, 'd_gflb', '无')}</a>
					</td>
					<td>
						<a href="${ctx}/ip/equipmentOpsSpec/form?id=${equipmentOpsSpec.id}">${equipmentOpsSpec.gcmc}</a>
					</td>
					<td>${equipmentOpsSpec.gcms}</td>
					<td>${equipmentOpsSpec.bz}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<shiro:hasPermission name="ip:equipmentOpsSpec:edit">
		<div>
			<button class="btn btn-primary" onclick="editList('zj', '${ctx}/ip/equipmentOpsSpec/form');">修改</button>
			<button class="btn btn-primary" onclick="delList();">删除</button>
			<button class="btn btn-primary" id="btnExport">导出</button>
			<button class="btn btn-primary" onclick="fanhui();">返回基本信息列表</button>
		</div>
	</shiro:hasPermission>
	<div class="pagination">${page}</div>
</body>
</html>
