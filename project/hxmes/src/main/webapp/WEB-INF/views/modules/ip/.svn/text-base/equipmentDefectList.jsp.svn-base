<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>缺陷树管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		$("#btnExport").click(function() {
			top.$.jBox.confirm("确认要导出缺陷树数据吗？", "系统提示", function(v, h, f) {
				if (v == "ok") {
					exportTable('contentTable', "", "", "缺陷树数据", '${ctx}');
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
		batchFnc('确认要删除所选的数据吗？', "${ctx}/ip/equipmentDefect/delete", "zj", "");
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
			<a href="${ctx}/ip/equipmentDefect/?equipmentModelId=${equipmentModelId}">缺陷树列表</a>
		</li>
		<li class="pull-right">
			<input id="btnAdd" class="btn btn-primary " type="button" value="新增"
				onclick="window.location.href='${ctx}/ip/equipmentDefect/form?equipmentModel.id=${equipmentModelId}'" />
		</li>
	</ul>
		<form:form id="searchForm" modelAttribute="equipmentDefect" 
	action="${ctx}/ip/equipmentDefect/?equipmentModelId=${equipmentModelId}" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>名称 ：</label><form:input path="qxbm" htmlEscape="false" maxlength="50" class="input-small"/>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>
	<tags:message content="${message}" />
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>
					<input type="checkbox" id="zj" onclick="$('.zj').prop('checked',$(this).prop('checked'));" />
				</th>
				<th>缺陷编码</th>
				<th>缺陷类别</th>
				<th>缺陷级别ABC</th>
				<th>缺陷现象描述关键词</th>
				<th>缺陷音视频文件名</th>
				<th>缺陷排除指南文件名</th>
				<th>缺陷排除音视频文件名</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="equipmentDefect">
				<tr>
					<td>
						<input type="checkbox" class="zj" name="zj" value="${equipmentDefect.id}" />
					</td>
					<td>
						<a href="${ctx}/ip/equipmentDefect/form?id=${equipmentDefect.id}">${equipmentDefect.qxbm}</a>
					</td>
					<td>
						<a href="${ctx}/ip/equipmentDefect/form?id=${equipmentDefect.id}">${fns:getDictLabel(equipmentDefect.qxlb, 'd_qxlb', '无')}</a>
					</td>
					<td>${fns:getDictLabel(equipmentDefect.qxjbabc, 'd_abc', '无')}</td>
					<td>${equipmentDefect.xxmsgjc}</td>
					<td>
						<c:if test="${not empty equipmentDefect.qxyspwjm}">
							<a href="javascript:$('#url').val('${equipmentDefect.qxyspwjm}');$('#filename').val('${equipmentDefect.qxbm}-缺陷音视频');downLoad.submit();"
								target='_blank'>缺陷音视频</a>
						</c:if>
					</td>
					<td>
						<c:if test="${not empty equipmentDefect.qxpcznwjm}">
							<a href="javascript:$('#url').val('${equipmentDefect.qxpcznwjm}');$('#filename').val('${equipmentDefect.qxbm}-缺陷排除指南');downLoad.submit();"
								target='_blank'>${equipmentDefect.qxbm}-缺陷排除指南</a>
						</c:if>
					</td>
					<td>
						<c:if test="${not empty equipmentDefect.qxpcyspwjm}">
							<a
								href="javascript:$('#url').val('${equipmentDefect.qxpcyspwjm}');$('#filename').val('${equipmentDefect.qxbm}-缺陷排除音视频');downLoad.submit();"
								target='_blank'>缺陷排除音视频</a>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<button class="btn btn-primary" onclick="editList('zj', '${ctx}/ip/equipmentDefect/form');">修改</button>
	<button class="btn btn-primary" onclick="delList();">删除</button>
	<button class="btn btn-primary" id="btnExport">导出</button>
	<button class="btn btn-primary" onclick="fanhui();">返回基本信息列表</button>
	<div class="pagination">${page}</div>
	<!-- 下载附件. -->
	<form:form modelAttribute="equipmentDefect" id="downLoad" class="breadcrumb form-search" style="text-align:center;display: none;"
		action="${ctx}/ip/equipmentDefect/downLoad?equipmentModelId=${equipmentModelId}" method="post">
		<input type="hidden" name="pageSize" value="${page.pageSize}" />
		<input type="hidden" name="pageNo" value="${page.pageNo}" />
		<input type="hidden" id="url" name="url" />
		<input type="hidden" id="filename" name="filename" />
	</form:form>
</body>
</html>
