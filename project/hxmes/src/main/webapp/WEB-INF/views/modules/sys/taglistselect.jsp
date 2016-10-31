<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html style="overflow-x: hidden; overflow-y: auto;">
<head>
<title>查询选择弹窗</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	function page(n, s) {
		$("#pageNo").val(n);
		$("#pageSize").val(s);
		$("#searchForm").submit();
		return false;
	}
</script>
</head>
<body>
	<form:form id="searchForm" action="${ctx}${url}?url=${url}&itemNames=${itemNames}&itemCodes=${itemCodes}" method="post">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}" />
	</form:form>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead id="theadText">
			<tr>
				<th style="width: 7%" id="theadId">选择</th>
				<c:forEach items="${itemNames}" var="itemName">
					<th>${itemName}</th>
				</c:forEach>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="obj">
				<tr>
					<td style="width: 7%" id="tbodyId">
						<input type="radio" class="zj" name="zj" value="${obj.id}" />
					</td>
					<c:forEach items="${itemCodes}" var="attr">
						<td>${obj[attr]}</td>
					</c:forEach>
				</tr>
			</c:forEach>

		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>