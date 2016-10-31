<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>技术参数管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		$("#btnExport").click(function() {
			top.$.jBox.confirm("确认要导出技术参数数据吗？", "系统提示", function(v, h, f) {
				if (v == "ok") {
					exportTable('contentTable', "", "", "设备技术参数数据", '${ctx}');
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
		var ipMenuFrame = $("#shebeiMenuFrame", parent.document);
		ipMenuFrame.contents().find("#tree a.curSelectedNode")[0].click();
	}
	function delList() {
		//获取勾选的条数
		batchFnc('确认要删除所选的设备信息吗？', "${ctx}/ip/equipmentTechParam/delete", "zj", "")
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active">
			<a href="${ctx}/ip/equipmentTechParam/?equipmentModelId=${equipmentModelId}">技术参数列表</a>
		</li>
		<li class="pull-right">
			<input id="btnAdd" class="btn btn-primary " type="button" value="新增"
				onclick="window.location.href='${ctx}/ip/equipmentTechParam/form?equipmentModel.id=${equipmentModelId}'" />
		</li>
	</ul>
	<form:form id="searchForm" modelAttribute="equipmentTechParam" action="${ctx}/ip/equipmentTechParam/?equipmentModelId=${equipmentModelId}"
		method="post" class="breadcrumb form-search">
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
				<th>参数说明</th>
				<th>参数值类型</th>
				<th>参数计量单位</th>
				<th>标准值</th>
				<th>上限值</th>
				<th>下限值</th>
				<th>备注</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="equipmentTechParam">
				<tr>
					<td>
						<input type="checkbox" class="zj" name="zj" value="${equipmentTechParam.id}" />
					</td>
					<td>
						<a href="${ctx}/ip/equipmentTechParam/form?id=${equipmentTechParam.id}">${equipmentTechParam.csbm}</a>
					</td>
					<td>
						<a href="${ctx}/ip/equipmentTechParam/form?id=${equipmentTechParam.id}">${equipmentTechParam.csmc}</a>
					</td>
					<td>${equipmentTechParam.csjc}</td>
					<td>${equipmentTechParam.cssm}</td>
					<td>${fns:getDictLabel(equipmentTechParam.cszlx, 'd_jscszlx', '无')}</td>
					<td>${fns:getJldwDictLabel(equipmentTechParam.csjldw.jldwbm,'无')}</td>
					<td>${equipmentTechParam.bzz}</td>
					<td>${equipmentTechParam.sxz}</td>
					<td>${equipmentTechParam.xxz}</td>
					<td>${equipmentTechParam.bz}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<shiro:hasPermission name="ip:equipmentTechParam:edit">
		<div>
			<button class="btn btn-primary" onclick="editList('zj','${ctx}/ip/equipmentTechParam/form');">修改</button>
			<button class="btn btn-primary" onclick="delList();">删除</button>
			<button class="btn btn-primary" id="btnExport">导出</button>
			<button class="btn btn-primary" onclick="fanhui();">返回基本信息列表</button>
		</div>
	</shiro:hasPermission>
	<div class="pagination">${page}</div>
</body>
</html>
