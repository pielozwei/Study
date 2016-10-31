<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>物料编码信息管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		$("#btnExport").click(function() {
			top.$.jBox.confirm("确认要导出用户数据吗？", "系统提示", function(v, h, f) {
				if (v == "ok") {
					exportTable('contentTable', "", "", "物料编码", '${ctx}');
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
		batchFnc('确认要删除所选的设备信息吗？', "${ctx}/ip/wlbm/delete", "zj", "")
	}
</script>
</head>
<body>

	<ul class="nav nav-tabs">
		<li class="active">
			<a href="${ctx}/ip/wlbm/?wllb.id=${wlbm.wllb.id}&_module=${_module}">物料编码信息列表</a>
		</li>
		<c:if test="${_module=='wlbm'}">
			<li class="pull-right">
				<input id="btnAdd" class="btn btn-primary " type="button" value="新增" onclick="window.location.href='${ctx}/ip/wlbm/form/?wllb.id=${wlbm.wllb.id}'" />
			</li>
		</c:if>
	</ul>
	<form:form id="searchForm" modelAttribute="wlbm" action="${ctx}/ip/wlbm/?wllb.id=${wllb.id}&_module=${_module}" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
		<label>名称 ：</label>
		<form:input path="wlmc" htmlEscape="false" maxlength="50" class="input-small" />
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" />
	</form:form>
	<tags:message content="${message}" />
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<c:if test="${_module=='wlbm'}">
					<th>
						<input type="checkbox" id="zj" onclick="$('.zj').prop('checked',$(this).prop('checked'));" />
					</th>
				</c:if>
				<th>物料编码</th>
				<th>物料名称</th>
				<th>规格</th>
				<th>型号</th>
				<th>重要ABC</th>
				<th>计量单位</th>
				<th>综合物料类别</th>
				<th>IAEA编码</th>
				<th>备注</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="wlbm">
				<tr>
					<c:if test="${_module=='wlbm'}">
						<td>
							<input type="checkbox" name="zj" class="zj" value="${wlbm.id}" />
						</td>
					</c:if>
					<td>${wlbm.wlbm}</td>
					<td>
						<c:if test="${_module=='wlbm'}">
							<a href="${ctx}/ip/wlbm/form?id=${wlbm.id}">${wlbm.wlmc}</a>
						</c:if>
						<c:if test="${_module=='wljscs'}">
							<a href="${ctx}/ip/wljscs/list?wlbm=${wlbm.id}">${wlbm.wlmc}</a>
						</c:if>
						<c:if test="${_module=='wlfwgys'}">
							<a href="${ctx}/ip/wlfwgys/list?wlbm=${wlbm.id}">${wlbm.wlmc}</a>
						</c:if>
					</td>
					<td>${wlbm.gg}</td>
					<td>${wlbm.xh}</td>
					<td>${fns:getDictLabel(wlbm.zyabc, 'd_abc', '无')}</td>
					<td>${fns:getJldwDictLabel(wlbm.jldw.id,'无')}</td>
					<td>${fns:getDictLabel(wlbm.wlzhlb, 'd_wlzhlb', '无')}</td>
					<td>${wlbm.iaeabm}</td>
					<td>${wlbm.bz}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<c:if test="${_module=='wlbm'}">
		<div>
			<button class="btn btn-primary" onclick="editList('zj','${ctx}/ip/wlbm/form');">修改</button>
			<button class="btn btn-primary" onclick="delList();">删除</button>
			<button class="btn btn-primary" id="btnExport">导出</button>
		</div>
	</c:if>
	<div class="pagination">${page}</div>
</body>
</html>
