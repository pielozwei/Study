<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>滚动工作计划任务管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#gzrwmc").focus();
			$("#inputForm").validate();
			$("#inputForm div.control-group").each(function(i,e){
				$(e).find("input").prop("readonly",true);
				$(e).find("textarea").prop("readonly",true);
			});
			$("#rwfjwjDiv a.btn").hide();
			$("#rwfjwjPreview a:contains('×')").remove();
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<%-- <li><a href="${ctx}/ip/scollingJobPlanTask/?scollingJobPlanId=${scollingJobPlanId}">滚动工作计划任务列表</a></li> --%>
		<li class="active"><a href="${ctx}/ip/scollingJobPlanTask/formCanceled?id=${scollingJobPlanTask.id}&scollingJobPlanId=${scollingJobPlanId}">滚动工作计划任务 查看</a></li>
		<li class="pull-right">
			<input id="btnCancel" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-1)" />
		</li>
	</ul><br/>
	
	<form:form id="inputForm" modelAttribute="scollingJobPlanTask" action="${ctx}/ip/scollingJobPlanTask/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="task.fbzt" value="${scollingJobPlanTask.task.fbzt}"/>
		<form:hidden path="scollingJobPlan.id" value="${scollingJobPlanTask.scollingJobPlan.id}"/>
		<tags:message content="${message}"/>
		
		<div class="control-group">
			<label class="control-label" for="gzrwmc">工作任务名称:</label>
			<div class="controls">
				<form:input path="task.gzrwmc" htmlEscape="false" maxlength="200" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="wcqx">完成期限:</label>
			<div class="controls">
				<form:input path="task.wcqx" htmlEscape="false" maxlength="200" />天
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="rwfzr">负责人:</label>
			<div class="controls">
				<form:input path="task.rwfzr.name" htmlEscape="false" maxlength="200"/>
                <%-- <tags:treeselect id="rwfzr" name="task.rwfzr.id" value="${scollingJobPlanTask.task.rwfzr.id}" labelName="task.rwfzr.name" labelValue="${scollingJobPlanTask.task.rwfzr.name}"
					title="公司" url="/ip/scollingJobPlanTask/getAllMemberByWorkCenterId?scollingJobPlanId=${not empty scollingJobPlanId ? scollingJobPlanId : scollingJobPlanTask.scollingJobPlan.id}" /> --%>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="rwxzr">协助人:</label>
			<div class="controls">
				<form:input path="task.rwxzr.name" htmlEscape="false" maxlength="200"/>
                <%-- <tags:treeselect id="rwxzr" name="task.rwxzr.id" value="${scollingJobPlanTask.task.rwxzr.id}" labelName="task.rwxzr.name" labelValue="${scollingJobPlanTask.task.rwxzr.name}"
					title="公司" url="/ip/scollingJobPlanTask/getAllMemberByWorkCenterId?scollingJobPlanId=${not empty scollingJobPlanId ? scollingJobPlanId : scollingJobPlanTask.scollingJobPlan.id}" /> --%>
			</div>
		</div>
		<div class="control-group" id="rwfjwjDiv">
			<label class="control-label" for="rwfjwj">任务附件:</label>
			<div class="controls">
				<input type="hidden" id="rwfjwj" name="task.rwfjwj" value="${scollingJobPlanTask.task.rwfjwj}" />
				<tags:ckfinder input="rwfjwj" type="files" uploadPath="/ip/scollingJobPlanTask" selectMultiple="false"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="mbjyq">目标及要求:</label>
			<div class="controls">
				<form:textarea path="task.mbjyq" htmlEscape="false" rows="4" maxlength="500" class="input-xxlarge"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="jhkssj">计划开始时间:</label>
			<div class="controls">
				<input id="jhkssj" name="task.jhkssj" type="text" readonly="readonly" maxlength="20" class="input-small" value="<fmt:formatDate value="${scollingJobPlanTask.task.jhkssj}" pattern="yyyy-MM-dd"/>" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="jhwcsj">计划完成时间:</label>
			<div class="controls">
				<input id="jhjzsj" name="task.jhwcsj" type="text" readonly="readonly" maxlength="20" class="input-small" value="<fmt:formatDate value="${scollingJobPlanTask.task.jhwcsj}" pattern="yyyy-MM-dd"/>" />
			</div>
		</div>
		
		
		<div class="control-group">
			<label class="control-label" for="sjkssj">实际开始时间:</label>
			<div class="controls">
				<input id="sjkssj" name="task.sjkssj" type="text" readonly="readonly" maxlength="20" class="input-small" value="<fmt:formatDate value="${scollingJobPlanTask.task.sjkssj}" pattern="yyyy-MM-dd"/>" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="sjwcsj">实际完成时间:</label>
			<div class="controls">
				<input id="sjwcsj" name="task.sjwcsj" type="text" readonly="readonly" maxlength="20" class="input-small" value="<fmt:formatDate value="${scollingJobPlanTask.task.sjwcsj}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="zxzt">任务执行状态:</label>
			<div class="controls">
				<form:input path="task.zxzt" value="${fns:getDictLabel(scollingJobPlanTask.task.zxzt, 'd_rwzxzt', '无')}"/>
				<%-- <form:select path="zxzt" >
					<form:options items="${fns:getDictList('d_rwzxzt')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select> --%>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="sfaqwc">是否按期完成:</label>
			<div class="controls">
				<form:input path="task.sfaqwc" value="${fns:getDictLabel(scollingJobPlanTask.task.sfaqwc, 'd_yesno', '无')}"/>
				<%-- <form:select path="sfaqwc" >
					<form:options items="${fns:getDictList('d_yesno')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select> --%>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="rwnd">任务难度:</label>
			<div class="controls">
				<form:input path="task.rwnd" value="${fns:getDictLabel(scollingJobPlanTask.task.rwnd, 'd_rwnd', '无')}"/>
				<%-- <form:select path="rwnd" >
					<form:options items="${fns:getDictList('d_rwnd')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select> --%>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label" for="zxzt">任务执行状态:</label>
			<div class="controls">
				<form:select path="task.zxzt" >
					<form:options items="${fns:getDictList('d_rwzxzt')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="sfaqwc">是否按期完成:</label>
			<div class="controls">
				<form:select path="task.sfaqwc" >
					<form:options items="${fns:getDictList('d_yesno')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="rwnd">任务难度:</label>
			<div class="controls">
				<form:select path="task.rwnd" >
					<form:options items="${fns:getDictList('d_rwnd')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label" for="wcqksm">完成情况说明:</label>
			<div class="controls">
				<form:textarea path="task.wcqksm" htmlEscape="false" rows="4" maxlength="100" class="input-xxlarge"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="jzcd">进展程度:</label>
			<div class="controls">
				<form:input path="task.jzcd" value="${fns:getDictLabel(scollingJobPlanTask.task.jzcd, 'd_rwjzcd', '无')}"/>
				<%-- <form:select path="task.jzcd" >
					<form:options items="${fns:getDictList('d_rwjzcd')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select> --%>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="jzqksm">进展情况说明:</label>
			<div class="controls">
				<form:textarea path="task.jzqksm" htmlEscape="false" rows="4" maxlength="30" class="input-xxlarge"/>
			</div>
		</div>
		
		
		
		<div class="control-group">
			<label class="control-label" for="task.bz">备注:</label>
			<div class="controls">
				<form:textarea path="task.bz" htmlEscape="false" rows="4" maxlength="500" class="input-xxlarge"/>
			</div>
		</div>
		
		
		<div class="control-group">
			<label class="control-label" for="qxlx">取消类型:</label>
			<div class="controls">
				<form:input path="task.qxlx" value="${fns:getDictLabel(scollingJobPlanTask.task.qxlx, 'd_rwqxnx', '')}"/>
				<%-- <form:select path="task.qxlx" >
					<option value=""></option>
					<form:options items="${fns:getDictList('d_rwqxnx')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select> --%>
			</div>
		</div>
		<!-- 取消时间、取消人 系统生成 -->
		<div class="control-group">
			<label class="control-label" for="qxsj">取消时间:</label>
			<div class="controls">
				<input name="task.qxsj" type="text" value="<fmt:formatDate value="${scollingJobPlanTask.task.qxsj}" pattern="yyyy-MM-dd"/>" maxlength="200" class="digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="qxr">取消人:</label>
			<div class="controls">
				<c:if test="${scollingJobPlanTask.task.zxzt == 3}">
					<form:input path="task.updateBy.name" htmlEscape="false" maxlength="200"/>
				</c:if>
				<c:if test="${scollingJobPlanTask.task.zxzt != 3}">
					<input type="text" />
				</c:if>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="qxyy">取消原因:</label>
			<div class="controls">
				<form:textarea path="task.qxyy" htmlEscape="false" rows="4" maxlength="100" class="input-xxlarge"/>
			</div>
		</div>
	</form:form>
	
</body>
</html>
