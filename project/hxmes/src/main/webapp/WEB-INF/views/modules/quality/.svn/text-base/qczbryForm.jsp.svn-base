<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>质量问题单管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					//loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
		function lcqd(){
			window.location.href="${ctx}/quality/zjWttzd/start1?id=${zjWttzd.id}";
		}
		function qc(){
			window.location.href="${ctx}/quality/zjWttzd/zbryComplete?id=${zjWttzd.id}";
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/quality/zjWttzd/jumpQczbyPage">历史任务</a></li>
		<li><a href="${ctx}/quality/zjWttzd/getCompleteTask">待处理任务</a></li>
		<li><a href="${ctx}/quality/zjWttzd/requestList">质量问题通知单列表</a></li>
		<%-- <li ><a href="${ctx}/quality/zjWttzd/qczbyForm?id=${zjWttzd.id}">质量问题通知单<shiro:hasPermission name="quality:zjWttzd:edit">${not empty zjWttzd.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="quality:zjWttzd:edit">查看</shiro:lacksPermission></a></li> --%>
		<shiro:hasPermission name="quality:zjWttzd:edit"><li><a href="${ctx}/quality/zjWttzd/qczbyForm">问题通知单起草</a></li></shiro:hasPermission>
	</ul><br/>
	
	<form:form id="inputForm" modelAttribute="zjWttzd" action="${ctx}/quality/zjWttzd/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">序号：</label>
			<div class="controls">
				<form:input path="xuhao" htmlEscape="false" maxlength="16" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="dwZr">责任单位:</label>
			<div class="controls">
                <tags:treeselect id="office2" name="dwZr" value="${zjWttzd.dwZr.id}" labelName="office2" labelValue="${zjWttzd.dwZr.name}"
					title="部门" url="/quality/zjWttzd/treeData?type=2" cssClass="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="dwJd">监督单位:</label>
			<div class="controls">
                <tags:treeselect id="office3" name="dwJd" value="${zjWttzd.dwJd.id}" labelName="office3" labelValue="${zjWttzd.dwJd.name}"
					title="部门" url="/quality/zjWttzd/treeData?type=2" cssClass="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="dwJc">检查单位:</label>
			<div class="controls">
                <tags:treeselect id="office4" name="dwJc" value="${zjWttzd.dwJc.id}" labelName="office4" labelValue="${zjWttzd.dwJc.name}"
					title="部门" url="/quality/zjWttzd/treeData?type=2" cssClass="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">检查日期：</label>
			<div class="controls">
				<input name="dwJcdate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${zjWttzd.dwJcdate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">问题描述：</label>
			<div class="controls">
				<form:textarea path="wtms" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="quality:zjWttzd:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<c:if test="${identity == 'lcqd' }">
			<input id="btnSubmit" class="btn btn-primary" type="button" onclick="lcqd();" value="启动流程"/>
			</c:if>
			<c:if test="${identity == 'cl' }">
			<input id="btnSubmit" class="btn btn-primary" type="button" onclick="qc();" value="完成起草"/>
			</c:if>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>
