<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>设备分类管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		$("#btnExport").click(function() {
			top.$.jBox.confirm("确认要导出设备分类数据吗？", "系统提示", function(v, h, f) {
				if (v == "ok") {
					exportTable('contentTable', "", "", "设备分类数据", '${ctx}');
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
		batchFnc('确认要删除所选的设备分类吗？', "${ctx}/ip/equipmentCategory/delete", "zj", "");
	}
	function enableFnc() {
		batchFnc('确认要启用所选的设备分类吗？', "${ctx}/ip/equipmentCategory/updateState", "zj", "&state=1");
	}
	function disableFnc() {
		batchFnc('确认要禁用所选的设备分类吗？', "${ctx}/ip/equipmentCategory/updateState", "zj", "&state=0");
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active">
			<a href="${ctx}/ip/equipmentCategory/?nodeId=${nodeId}">设备分类列表</a>
		</li>
		<li class="pull-right">
			<input id="btnAdd" class="btn btn-primary " type="button" value="新增"
				onclick="window.location.href='${ctx}/ip/equipmentCategory/form?nodeId=${nodeId}'" />
		</li>

	</ul>

	<form:form id="searchForm" modelAttribute="equipmentCategory" action="${ctx}/ip/equipmentCategory/list?nodeId=${nodeId}" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
		<label>名称 ：</label>
		<form:input path="sblbmc" htmlEscape="false" maxlength="50" class="input-small" />
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" />
	</form:form>
	<tags:message content="${message}" />
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>
					<input type="checkbox" id="zj" onclick="$('.zj').prop('checked',$(this).prop('checked'));" />
				</th>
				<th>显示顺序号</th>
				<th>设备类别编码</th>
				<th>设备类别名称</th>
				<th>设备类别简称</th>
				<th>是否启用</th>
				<th>上级类别编码</th>
				<th>备注</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="equipmentCategory">
				<tr>
					<td>
						<input type="checkbox" class="zj" name="zj" value="${equipmentCategory.id}" />
					</td>
					<td>${equipmentCategory.xssx}</td>
					<td>${equipmentCategory.sblbbm}</td>
					<td>
						<a href="${ctx}/ip/equipmentCategory/form?id=${equipmentCategory.id}">${equipmentCategory.sblbmc}</a>
					</td>
					<td>
						<a href="${ctx}/ip/equipmentCategory/form?id=${equipmentCategory.id}">${equipmentCategory.sblbjc}</a>
					</td>
					<td>${fns:getDictLabel(equipmentCategory.sfqy, 'yes_no', '无')}</td>
					<td>${equipmentCategory.parent.sblbbm}</td>
					<td>${equipmentCategory.remarks}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div>
		<button class="btn btn-primary" onclick="editList('zj', '${ctx}/ip/equipmentCategory/form');">修改</button>
		<button class="btn btn-primary" onclick="delList();">删除</button>
		<button class="btn btn-primary" id="btnExport">导出</button>
		<button class="btn btn-primary" id="btnEnable" onclick="enableFnc();">启用</button>
		<button class="btn btn-primary" id="btnDisable" onclick="disableFnc()">禁用</button>
	</div>
	<div class="pagination">${page}</div>
</body>
</html>
