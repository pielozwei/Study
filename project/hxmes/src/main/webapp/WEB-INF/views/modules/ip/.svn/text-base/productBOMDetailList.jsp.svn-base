<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>BOM明细管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		$("#btnExport").click(function() {
			top.$.jBox.confirm("确认要导出用户数据吗？", "系统提示", function(v, h, f) {
				if (v == "ok") {
					exportTable('contentTable', "", "", "物料明细", '${ctx}');
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
	/* function tChuang() {
		window.open('${ctx}/ip/productBOMDetail/form/?wlbmId=${wlbmId}&bomqdId=${bomqdId}', 'newwindow',
				'height=560, width=860,alwaysRaised=no,z-look=yes,top=100%, left=100%, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=no')
	} */
	function delList() {
		batchFnc('确认要删除所选的数据吗？', "${ctx}/ip/productBOMDetail/delete", "zj", "");
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<%-- 		<li class="active">
			<a href="${ctx}/ip/productBOMDetail/list?productBOMSheet.id=${productBOMDetail.productBOMSheet.id}">BOM明细列表</a>
		</li> --%>
		<c:if test="${type!='view'}">
			<li>
				<input id="btnAdd" class="btn btn-primary " type="button" value="新增"
					onclick="window.location.href='${ctx}/ip/productBOMDetail/form/?productBOMSheet.id=${productBOMDetail.productBOMSheet.id}'" />
			</li>
			<li>&nbsp;</li>
			<li>
				<button class="btn btn-primary" onclick="editList('zj', '${ctx}/ip/productBOMDetail/form');">编辑</button>
			</li>
			<li>&nbsp;</li>
			<li>
				<button class="btn btn-primary" onclick="delList();">删除</button>
			</li>
			<li>&nbsp;</li>

		</c:if>
		<c:if test="${type=='view'}">
			<li class="active">
				<a href="#">查看BOM树</a>
			</li>
			<li class="pull-right">
				<div>
					<input id="btnCancel" class="btn btn-primary" type="button" value="返 回" onclick="window.parent.location.href='${ctx}/ip/productBOMSheet/'" />
				</div>
			</li>
		</c:if>
	</ul>

	<tags:message content="${message}" />
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<c:if test="${type!='view'}">
				<th>
					<input type="checkbox" id="zj" onclick="$('.zj').prop('checked',$(this).prop('checked'));" />
				</th>
				</c:if>
				<th>子物料编码</th>
				<th>子物料名称</th>
				<th>数量</th>
				<th>单位</th>
				<th>是否末级</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="productBOMDetail">
				<tr>
					<c:if test="${type!='view'}">
					<td>
						<input type="checkbox" class="zj" name="zj" value="${productBOMDetail.id}" />
					</td>
					</c:if>
					<td>${productBOMDetail.zwlbm.wlbm}</td>
					<td>
						<c:if test="${type!='view'}">
							<a href="${ctx}/ip/productBOMDetail/form?id=${productBOMDetail.id}">${productBOMDetail.zwlbm.wlmc}</a>
						</c:if>
						<c:if test="${type=='view'}">
							${productBOMDetail.zwlbm.wlmc}
						</c:if>
					</td>
					<td>${productBOMDetail.sl}</td>
					<td>${fns:getJldwDictLabel(productBOMDetail.jldw.id,'无')}</td>
					<td>${fns:getDictLabel(productBOMDetail.sfmj,'yes_no','无')}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
