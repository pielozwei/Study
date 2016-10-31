<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>基本信息管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		$("#btnExport").click(function() {
			top.$.jBox.confirm("确认要导出基本信息数据吗？", "系统提示", function(v, h, f) {
				if (v == "ok") {
					exportTable('contentTable', "", "", "基本信息数据", '${ctx}');
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
		//获取勾选的条数
		batchFnc('确认要删除所选的设备信息吗？', "${ctx}/ip/equipmentModel/delete", "zj", "")
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active">
			<a href="${ctx}/ip/equipmentModel/list?nodeId=${nodeId}&_module=${_module}">基本信息列表</a>
		</li>
		<c:if test="${_module=='equipmentModel'}">
			<li class="pull-right">
				<input id="btnAdd" class="btn btn-primary " type="button" value="新增"
					onclick="window.location.href='${ctx}/ip/equipmentModel/form?nodeId=${nodeId}'" />
			</li>
		</c:if>
	</ul>
	<form:form id="searchForm" modelAttribute="equipmentModel" action="${ctx}/ip/equipmentModel/list?nodeId=${nodeId}&_module=${_module}" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
		<label>名称 ：</label>
		<form:input path="sbggxhmc" htmlEscape="false" maxlength="50" class="input-small" />
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" />
	</form:form>
	<tags:message content="${message}" />
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>
					<input type="checkbox" id="zj" onclick="$('.zj').prop('checked',$(this).prop('checked'));" />
				</th>
				<th>设备规格型号编号</th>
				<th>设备规格型号名称</th>
				<th>简称</th>
				<th>规格</th>
				<th>型号</th>
				<th>功率</th>
				<th>技术性能描述</th>
				<th>所属分类</th>
				<th>备注</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="equipmentModel">
				<tr>
					<td>
						<input type="checkbox" class="zj" name="zj" value="${equipmentModel.id}" />
					</td>
					<c:if test="${_module=='equipmentModel'}">
						<td>
							<a href="${ctx}/ip/equipmentModel/form?id=${equipmentModel.id}">${equipmentModel.sbggxhbm}</a>
						</td>
						<td>
							<a href="${ctx}/ip/equipmentModel/form?id=${equipmentModel.id}">${equipmentModel.sbggxhmc}</a>
						</td>
					</c:if>
					<c:if test="${_module!='equipmentModel'}">
						<td>
							<a href="${ctx}/ip/${_module}/?equipmentModelId=${equipmentModel.id}">${equipmentModel.sbggxhbm}</a>
						</td>
						<td>
							<a href="${ctx}/ip/${_module}/?equipmentModelId=${equipmentModel.id}">${equipmentModel.sbggxhmc}</a>
						</td>
					</c:if>
					<td>${equipmentModel.sbggxhjc}</td>
					<td>${equipmentModel.sbgg}</td>
					<td>${equipmentModel.sbxh}</td>
					<td>${equipmentModel.gl}</td>
					<td>${equipmentModel.jsxnms}</td>
					<td>${equipmentModel.equipmentCategory.sblbmc}</td>
					<td>${equipmentModel.bz}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<c:if test="${_module=='equipmentModel'}">
		<div>
			<button class="btn btn-primary" onclick="editList('zj','${ctx}/ip/equipmentModel/form');">修改</button>
			<button class="btn btn-primary" onclick="delList();">删除</button>
			<button class="btn btn-primary" id="btnExport">导出</button>
		</div>
	</c:if>
	<div class="pagination">${page}</div>
</body>
</html>
