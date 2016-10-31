<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>滚动工作计划任务管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(function() {
			$("[data-toggle='popover']").popover();
		});
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出滚动工作计划任务数据吗？","系统提示",function(v,h,f){
					if(v == "ok"){
						exportTable('contentTable',"","","滚动工作计划任务数据",'${ctx}');
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
		function showCancel(obj,id,zxzt){//data-toggle="modal" href="#yh"
        	//var zxzt = $(obj).parent().parent().find("input.zxzt").val();
        	if(zxzt==3){
        		confirmx('操作失败，改任务已取消', window.location.href);
        		return;
        	}
        	if(zxzt==2){
        		confirmx('操作失败，改任务已完成', window.location.href);
        		return;
        	}
        	$('#yh #id').val(id);
        	$(obj).attr("data-toggle","modal");
        	$(obj).attr("href","#yh");
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ip/scollingJobPlanTask/listHost?scollingJobPlanId=${scollingJobPlanId}">滚动工作计划任务列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="scollingJobPlanTask" 
	action="${ctx}/ip/scollingJobPlanTask/listHost?scollingJobPlanId=${scollingJobPlanId}" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>名称 ：</label><form:input path="task.gzrwmc" htmlEscape="false" maxlength="50" class="input-small"/>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr>
			<th><input type="checkbox" id="zj" onclick="$('.zj').prop('checked',$(this).prop('checked'));"/></th>
			<th>序号</th>
			<th>发布状态</th>
			<th>执行状态</th>
			<th>进展程度</th>
			<th>工作任务</th>
			<th>负责人</th>
			<th>协助人</th>
			<th>操作</th>
		</tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="scollingJobPlanTask" varStatus="status">
			<tr>
				<td><input type="checkbox" class="zj" name="zj" value="${scollingJobPlanTask.id}" /></td>
				<td>${status.index+1}</td>
				<td>${fns:getDictLabel(scollingJobPlanTask.task.fbzt, 'd_rwfbzt', '无')}</td>
				<td>${fns:getDictLabel(scollingJobPlanTask.task.zxzt, 'd_rwzxzt', '已取消')}</td>
				<td>${fns:getDictLabel(scollingJobPlanTask.task.jzcd, 'd_rwjzcd', '无')}</td>
				<td>
					<c:if test="${scollingJobPlanTask.task.fbzt == 0}">
						<a href="${ctx}/ip/scollingJobPlanTask/form?id=${scollingJobPlanTask.id}">${scollingJobPlanTask.task.gzrwmc}</a>
					</c:if>
					<c:if test="${scollingJobPlanTask.task.fbzt == 1 and scollingJobPlanTask.task.zxzt != 3}">
						<a href="${ctx}/ip/scollingJobPlanTask/formReleased?id=${scollingJobPlanTask.id}">${scollingJobPlanTask.task.gzrwmc}</a>
					</c:if>
					<c:if test="${scollingJobPlanTask.task.fbzt == 1 and scollingJobPlanTask.task.zxzt == 3}">
						<a href="${ctx}/ip/scollingJobPlanTask/formCanceled?id=${scollingJobPlanTask.id}">${scollingJobPlanTask.task.gzrwmc}</a>
					</c:if>
				</td>
				<td>${scollingJobPlanTask.task.rwfzr.name}</td>
				<td>${scollingJobPlanTask.task.rwxzr.name}</td>
				<td>
    				<c:if test="${scollingJobPlanTask.scollingJobPlan.fbzt!=0}">
						<a href="javascript:void(0);" onclick="showCancel(this,'${scollingJobPlanTask.id}','${scollingJobPlanTask.task.zxzt}');">取消任务</a> <!-- class="btn btn-primary" -->
					</c:if>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<table id="contentTable2" class="table" style="display:none;">
		<thead><tr>
			<th>序号</th>
			<th>发布状态</th>
			<th>执行状态</th>
			<th>进展程度</th>
			<th>工作任务</th>
			<th>负责人</th>
			<th>协助人</th>
		</tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="scollingJobPlanTask" varStatus="status">
			<tr>
				<td>${status.index+1}</td>
				<td>${fns:getDictLabel(scollingJobPlanTask.task.fbzt, 'd_rwfbzt', '无')}</td>
				<td>${fns:getDictLabel(scollingJobPlanTask.task.zxzt, 'd_rwzxzt', '已取消')}</td>
				<td>${fns:getDictLabel(scollingJobPlanTask.task.jzcd, 'd_rwjzcd', '无')}</td>
				<td>${scollingJobPlanTask.task.gzrwmc}</td>
				<td>${scollingJobPlanTask.task.rwfzr.name}</td>
				<td>${scollingJobPlanTask.task.rwxzr.name}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div id="yh" class="modal hide fade in" style="display: none; overflow:auto;height: auto;width:auto;" title="取消原因">
		<form:form id="cancelForm" modelAttribute="scollingJobPlanTask" action="${ctx}/ip/scollingJobPlanTask/cancelTask" method="post" class="breadcrumb form-search">
			<form:hidden path="id" />
			
			<div class="control-group">
				<label class="control-label" for="task.qxlx">取消类型:</label>
				<div class="controls">
					<form:select path="task.qxlx" cssClass="required">
						<form:options items="${fns:getDictList('d_rwqxnx')}" itemLabel="label" itemValue="value" htmlEscape="false" />
					</form:select>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="task.qxyy">取消原因:</label>
				<div class="controls">
					<form:textarea path="task.qxyy" htmlEscape="false" rows="4" maxlength="500" class="input-xlarge required"/>
				</div>
			</div>
			
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="确定" />&nbsp;
				<!-- onclick="return confirmx('确认要取消该任务吗？', this.href)" -->
				<a href="#" class="btn" data-dismiss="modal">返回</a>
		</form:form>
	</div>
	<div>
		<button class="btn btn-primary" id="btnExport">导出</button>
		<button class="btn btn-primary" onclick="printTable('contentTable2')">打印</button>
		<button class="btn btn-primary" id="backExport" onclick="location.href='${ctx}/ip/scollingJobPlan/listHosted'">返回</button>
	</div>
	<div class="pagination">${page}</div>
</body>
</html>
