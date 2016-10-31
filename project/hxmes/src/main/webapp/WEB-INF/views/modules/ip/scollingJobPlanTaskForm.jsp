<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>滚动工作计划任务管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#gzrwmc").focus();
			var p='${scollingJobPlanTask.task.gzrwmc}';
			$("#inputForm").validate({
				rules : {
					"task.wcqx" : {
						min : 1
					},
					"task.gzrwmc" : {
						remote : "${ctx}/ip/scollingJobPlanTask/checkName?oldName=" + p 
					}
				},
				messages : {
					"task.wcqx" : {
						min : "最小值为1"
					},
					"task.gzrwmc" : {
						remote : "该任务名称已经存在!"
					}
				},
				submitHandler: function(form){
                    if ($("#rwfzrId").val() == $("#rwxzrId").val()) {
                    	top.$.jBox.tip('负责人和协助人不能是同一个人','warning');
                	} else {
                        //loading('正在提交，请稍等...');
                        form.submit();
                    }
				}
			});
			setJhwcsj();
		});
		function setJhwcsj(){
			var wcqx=$("#wcqx").val();
			var jhkssj=$("#jhkssj").val();
			if(wcqx && jhkssj){
				var jhkssjDate=new Date(Date.parse(jhkssj.replace(/-/g,   "/")));
				var jhwcsjDate=new Date(jhkssjDate.getTime()+1000*3600*24*(wcqx-1));
				var jhwcsj = jhwcsjDate.getFullYear()+"-"+(jhwcsjDate.getMonth()+1)+"-"+jhwcsjDate.getDate(); //字符串日期格式           
				$("#jhwcsj").val(jhwcsj);
			}else{
				$("#jhwcsj").val("");
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<%-- <li><a href="${ctx}/ip/scollingJobPlanTask/?scollingJobPlanId=${scollingJobPlanId}">滚动工作计划任务列表</a></li> --%>
		<li class="active">
			<a href="${ctx}/ip/scollingJobPlanTask/form?id=${scollingJobPlanTask.id}&scollingJobPlanId=${scollingJobPlanId}">
				滚动工作计划任务
				<shiro:hasPermission name="ip:scollingJobPlanTask:edit">${not empty scollingJobPlanTask.id?'修改':'添加'}</shiro:hasPermission>
			</a>
		</li>
		<li class="pull-right">
			<input id="btnSubmit" class="btn btn-primary" type="button" value="保 存" onclick="$('#inputForm').submit();" />
			<input id="btnCancel" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-1)" />
			<input TYPE='BUTTON' class="btn btn-primary" value='重置' onClick="$('#inputForm')[0].reset();">
		</li>
	</ul><br/>
	
	<form:form id="inputForm" modelAttribute="scollingJobPlanTask" action="${ctx}/ip/scollingJobPlanTask/save?referenceMessageId=${referenceMessageId}" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="task.fbzt" value="0"/>
		<form:hidden path="task.zxzt" value="1"/>
		<form:hidden path="scollingJobPlan.id" value="${not empty scollingJobPlanId ? scollingJobPlanId : scollingJobPlanTask.scollingJobPlan.id}"/>
		<input type="hidden" name="scollingJobPlanId" value="${not empty scollingJobPlanId ? scollingJobPlanId : scollingJobPlanTask.scollingJobPlan.id}"/>
		<tags:message content="${message}"/>
		
		<div class="control-group">
			<label class="control-label" for="gzrwmc">工作任务名称:</label>
			<div class="controls">
				<form:input id="gzrwmc" path="task.gzrwmc" htmlEscape="false" maxlength="200" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="wcqx">完成期限:</label>
			<div class="controls">
				<form:input id="wcqx" oninput="setJhwcsj()" onpropertychange="setJhwcsj()" path="task.wcqx" htmlEscape="false" maxlength="20" class="required digits"/>天
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="rwfzr">负责人:</label>
			<div class="controls">
                <tags:treeselect id="rwfzr" name="task.rwfzr.id" value="${scollingJobPlanTask.task.rwfzr.id}" labelName="task.rwfzr.name" labelValue="${scollingJobPlanTask.task.rwfzr.name}"
					title="公司" url="/ip/scollingJobPlanTask/getAllMemberByWorkCenterId?scollingJobPlanId=${not empty scollingJobPlanId ? scollingJobPlanId : scollingJobPlanTask.scollingJobPlan.id}" cssClass="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="rwxzr">协助人:</label>
			<div class="controls">
                <tags:treeselect id="rwxzr" name="task.rwxzr.id" value="${scollingJobPlanTask.task.rwxzr.id}" labelName="task.rwxzr.name" labelValue="${scollingJobPlanTask.task.rwxzr.name}"
					title="公司" url="/ip/scollingJobPlanTask/getAllMemberByWorkCenterId?scollingJobPlanId=${not empty scollingJobPlanId ? scollingJobPlanId : scollingJobPlanTask.scollingJobPlan.id}" cssClass="required"/>
			</div>
		</div>
		<div class="control-group">
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
				<input id="jhkssj" name="task.jhkssj" type="text" readonly="readonly" maxlength="20" class="input-small Wdate required"
				 value="<fmt:formatDate value="${scollingJobPlanTask.task.jhkssj}" pattern="yyyy-MM-dd"/>" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false,onpicked:setJhwcsj});" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="jhwcsj">计划完成时间:</label>
			<div class="controls">
				<input id="jhwcsj" name="task.jhwcsj" type="text" readonly="readonly" maxlength="20" class="input-small" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="task.bz">备注:</label>
			<div class="controls">
				<form:textarea path="task.bz" htmlEscape="false" rows="4" maxlength="500" class="input-xxlarge"/>
			</div>
		</div>
	</form:form>
	
</body>
</html>
