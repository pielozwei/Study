<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>质量问题通知单管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		function search(){
			var search = $('#searchForm').serialize();
			//处理serialize()方法乱码问题
			var params = decodeURIComponent(search,true);
			search = encodeURI(encodeURI(params));
			var sign = '${sign}';
			if(sign == 'jumpQczbyPage'){
				window.location.href = '${ctx}/quality/zjWttzd/jumpShcjdwfzrPage?'+search;
			}else if(sign == 'getCompleteTask'){
				window.location.href = '${ctx}/quality/zjWttzd/getCompleteTaskShcjdwfzr?'+search;
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/quality/zjWttzd/jumpShcjdwfzrPage">历史任务</a></li>
		<li><a href="${ctx}/quality/zjWttzd/getCompleteTaskShcjdwfzr">待处理任务</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="zjWttzd" action="${ctx}/quality/zjWttzd/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
			<label>序号：</label>
				<form:input path="xuhao" htmlEscape="false" maxlength="16" class="input-medium"/>
			<label>责任单位：</label>
				<form:input path="dwZr.name" htmlEscape="false" maxlength="255" class="input-medium"/>
			<label>流程状态：</label>
				<form:input path="processStatus" htmlEscape="false" maxlength="50" class="input-medium"/>
			<input id="btnSubmit" class="btn btn-primary" type="button" onclick="search();" value="查询"/>
	</form:form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>流程实例编号</th>
				<th>责任单位</th>
				<th>监督单位</th>
				<th>检查单位</th>
				<th>问题描述</th>
				<!-- <th>被监督单位意见</th>
				<th>整改措施及完成情况</th>
				<th>整改完成填报人</th>
				<th>整改完成验证情况</th>
				<th>整改完成验证人</th> -->
				<th>流程状态</th> 
				<c:if test="${sign == 'getCompleteTask'}">
				<shiro:hasPermission name="quality:zjWttzd:edit"><th>操作</th></shiro:hasPermission>
				</c:if>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="zjWttzd">
			<tr>
				<td><a href="${ctx}/quality/zjWttzd/form?id=${zjWttzd.id}">${zjWttzd.xuhao}</a></td>
				<td>${zjWttzd.processInstanceId}</td>
				<td>${zjWttzd.dwZr.name}</td>
				<td>${zjWttzd.dwJd.name}</td>
				<td>${zjWttzd.dwJc.name}</td>
				<td>${zjWttzd.wtms}</td>
				<%-- <td>${zjWttzd.jcdwYj}</td>
				<td>${zjWttzd.zgTb}</td>
				<td>${zjWttzd.zgTbr}</td>
				<td>${zjWttzd.zgYz}</td> 
				<td>${zjWttzd.zgYzr}</td> --%>
				<td>${zjWttzd.processStatus}</td>
				<c:if test="${sign == 'getCompleteTask'}">
				<shiro:hasPermission name="quality:zjWttzd:edit"><td>
					<a href="${ctx}/quality/zjWttzd/shcjdwfzrForm?id=${zjWttzd.id}&identity=sh">审核</a>
    				<%-- <a href="${ctx}/quality/zjWttzd/qczbyForm?id=${zjWttzd.id}">修改</a>
					<a href="${ctx}/quality/zjWttzd/delete?id=${zjWttzd.id}" onclick="return confirmx('确认要删除该质量问题单吗？', this.href)">删除</a> --%>
				</td></shiro:hasPermission>
				</c:if>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
