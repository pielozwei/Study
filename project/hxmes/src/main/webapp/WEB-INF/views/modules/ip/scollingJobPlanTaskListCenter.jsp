<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>滚动工作计划任务管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出中心任务数据吗？","系统提示",function(v,h,f){
					if(v == "ok"){
						exportTable('contentTable',"","","中心任务数据",'${ctx}');
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ip/task/listCenter">中心任务列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="task" 
	action="${ctx}/ip/task/listCenter" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>名称 ：</label><form:input path="gzrwmc" htmlEscape="false" maxlength="50" class="input-small"/>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr>
			<th><input type="checkbox" id="zj" onclick="$('.zj').prop('checked',$(this).prop('checked'));"/></th>
			<th>序号</th>
			<th>工作任务</th>
			<th>进展程度</th>
			<th>负责工作中心</th>
			<th>负责人</th>
			<th>协助人</th>
			
			<th>任务难度</th>
			<th>计划开始时间</th>
			<th>计划完成时间</th>
			<th>实际开始时间</th>
			<th>实际完成时间</th>
			<th>是否取消</th>
			<th>取消人</th>
			<th>取消时间</th>
			<th>取消原因</th>
		</tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="task" varStatus="status">
			<tr>
				<td><input type="checkbox" class="zj" name="zj" value="${task.id}" /></td>
				<td>${status.index+1}</td>
				<td><a href="${ctx}/ip/task/formCenter?id=${task.id}">${task.gzrwmc}</a></td>
				<td>${fns:getDictLabel(task.jzcd, 'd_rwjzcd', '无')}</td>
				<td></td>
				<td>${task.rwfzr.name}</td>
				<td>${task.rwxzr.name}</td>
				
				<td>${fns:getDictLabel(task.rwnd, 'd_rwnd', '无')}</td>
				<td><fmt:formatDate value="${task.jhkssj}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${task.jhwcsj}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${task.sjkssj}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${task.sjwcsj}" pattern="yyyy-MM-dd"/></td>
				<td>${task.zxzt == 3 ? '是' : '否'}</td>
				<td>${task.zxzt == 3 ? task.updateBy.name : ''}</td>
				<td><fmt:formatDate value="${task.qxsj}" pattern="yyyy-MM-dd"/></td>
				<td>${task.qxyy}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<table id="contentTable2" class="table" style="display:none;">
		<thead><tr>
			<th>序号</th>
			<th>工作任务</th>
			<th>进展程度</th>
			<th>负责工作中心</th>
			<th>负责人</th>
			<th>协助人</th>
			
			<th>任务难度</th>
			<th>计划开始时间</th>
			<th>计划完成时间</th>
			<th>实际开始时间</th>
			<th>实际完成时间</th>
			<th>是否取消</th>
			<th>取消人</th>
			<th>取消时间</th>
			<th>取消原因</th>
		</tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="task" varStatus="status">
			<tr>
				<td>${status.index+1}</td>
				<td>${task.gzrwmc}</td>
				<td>${fns:getDictLabel(task.jzcd, 'd_rwjzcd', '无')}</td>
				<td></td>
				<td>${task.rwfzr.name}</td>
				<td>${task.rwxzr.name}</td>
				
				<td>${fns:getDictLabel(task.rwnd, 'd_rwnd', '无')}</td>
				<td><fmt:formatDate value="${task.jhkssj}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${task.jhwcsj}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${task.sjkssj}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${task.sjwcsj}" pattern="yyyy-MM-dd"/></td>
				<td>${task.zxzt == 3 ? '是' : '否'}</td>
				<td>${task.zxzt == 3 ? task.updateBy.name : ''}</td>
				<td><fmt:formatDate value="${task.qxsj}" pattern="yyyy-MM-dd"/></td>
				<td>${task.qxyy}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
	<div>
		<button class="btn btn-primary" id="btnExport">导出</button>
		<button class="btn btn-primary" onclick="printTable('contentTable2')">打印</button>
	</div>
	<div class="pagination">${page}</div>
</body>
</html>
