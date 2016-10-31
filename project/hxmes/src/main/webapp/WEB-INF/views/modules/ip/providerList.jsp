<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>供应商基本信息管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		$("#btnExport").click(function() {
			top.$.jBox.confirm("确认要导出用户数据吗？", "系统提示", function(v, h, f) {
				if (v == "ok") {
					exportTable('contentTable', "", "", "供应商基本信息", '${ctx}');
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
		batchFnc('确认要删除所选的设备分类吗？', "${ctx}/ip/provider/delete", "zj", "");
	}
	function enableFnc() {
		batchFnc('确认要启用所选的设备分类吗？', "${ctx}/ip/provider/updateState", "zj", "&state=1");
	}
	function disableFnc() {
		batchFnc('确认要禁用所选的设备分类吗？', "${ctx}/ip/provider/updateState", "zj", "&state=0");
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active">
			<a href="#">供应商基本信息列表</a>
		</li>
		<li class="pull-right">
			<input id="btnAdd" class="btn btn-primary " type="button" value="新增" onclick="window.location.href='${ctx}/ip/provider/form'" />
		</li>
	</ul>
	<form:form id="searchForm" modelAttribute="provider" action="${ctx}/ip/provider/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
		<label>名称 ：</label>
		<form:input path="gysmc" htmlEscape="false" maxlength="50" class="input-small" />
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" />
	</form:form>
	<tags:message content="${message}" />
	<div style="overflow: auto;">
		<table id="contentTable" class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<th>
						<input type="checkbox" id="zj" onclick="$('.zj').prop('checked',$(this).prop('checked'));" />
					</th>
					<th>供应商编码</th>
					<th>供应商名称</th>
					<th>地址</th>
					<th>邮政编码</th>
					<th>法人代表</th>
					<th>代理人</th>
					<th>联系电话</th>
					<th>传真</th>
					<th>网站地址</th>
					<th>信用等级</th>
					<th>注册资金</th>
					<th>状态</th>
					<th>加入时间</th>
					<th>备注</th>

				</tr>
			</thead>
			</div>
			<tbody>
				<c:forEach items="${page.list}" var="provider">
					<tr>
						<td>
							<input type="checkbox" name="zj" value="${provider.id}" />
						</td>
						<td>${provider.gysbm}</td>
						<td>
							<c:if test="${module=='provider'}">
								<a href="${ctx}/ip/provider/form?id=${provider.id}">${provider.gysmc}</a>
							</c:if>
						</td>
						<td>${provider.dz}</td>
						<td>${provider.yzbm}</td>
						<td>${provider.frdb}</td>
						<td>${provider.dlr}</td>
						<td>${provider.lxdh}</td>
						<td>${provider.cz}</td>
						<td>${provider.wzdz}</td>
						<td>${fns:getDictLabel(provider.xydj, 'd_xydj', '无')}</td>
						<td>${provider.zczj}</td>
						<td>${fns:getDictLabel(provider.gyszt, 'd_shiyong', '无')}</td>
						<td>${provider.jrsj}</td>
						<td>${provider.bz}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div>
			<c:if test="${module=='provider'}">
				<button class="btn btn-primary" onclick="editList('zj', '${ctx}/ip/provider/form');">修改</button>
				<button class="btn btn-primary" onclick="delList();">删除</button>
				<button class="btn btn-primary" id="btnExport">导出</button>
				<button class="btn btn-primary" id="btnEnable" onclick="enableFnc();">启用</button>
				<button class="btn btn-primary" id="btnDisable" onclick="disableFnc()">禁用</button>
			</c:if>
		</div>
		<div class="pagination">${page}</div>
</body>
</html>
