<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>滚动工作计划任务管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#inputForm div.control-group").each(function(i,e){
				if(i>=0 && i<=7){
					$(e).find("input").prop("readonly",true);
					$(e).find("textarea").prop("readonly",true);
				}
			});
			$("#rwfjwjDiv a.btn").hide();
			$("#rwfjwjPreview a:contains('×')").remove();
			$("#zxzt option[value='3']").remove();//去掉执行状态的已取消
			setCancelItems("禁用");
			/* if(!$("#sjwcsj").val()){
				$("#jzcd option[value='10']").remove();
				//是否按期完成
				$("#sfaqwc").val("1");
				$("#sfaqwcDiv a span").text("否");
				$("#sfaqwc").attr("readonly",true);
			}else{
				sjwcsjChange();
			}
			$("#zxzt").change(function(){
				var v=this.value;
				if(!$("#sjwcsj").val()){//完成时间为空
					if(v==2){
						top.$.jBox.tip('请先选择实际开始和完成时间','warning');
						$("#zxzt").val("1");
						$("#zxztDiv a span").text("进行中");
					}
				}
			}); */
			
			$("#sjkssj").focus();
			
			$("#inputForm").validate({
				submitHandler: function(form){
                    if ($("#rwfzrId").val() == $("#rwxzrId").val()) {
                    	top.$.jBox.tip('负责人和协助人不能是同一个人','warning');
                	} else {
                        //loading('正在提交，请稍等...');
                        form.submit();
                    }
				}
			});
		});
		function sjwcsjClick(){//选择实际完成时间
			/* if (!$("#sjkssj").val()) {
				top.$.jBox.tip('请先选择实际开始时间再来选择实际完成时间','warning');
				return;
			} else { */
				WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true/* ,minDate:'#F{$dp.$D(\'sjkssj\')}',maxDate:'#F{\'new Date()\'}',onpicked:sjwcsjChange,oncleared:sjwcsjChange */});
			/* } */
		}
		function sjwcsjChange(){//清空/选择 实际完成时间
			/* if(!$("#sjwcsj").val()){//未完成
				//执行状态
				$("#zxzt").val("1");
				$("#zxztDiv a span").text("进行中");
				$("#zxzt").attr("readonly",false);
				//进展程度
				$("#jzcd").val("0");
				$("#jzcdDiv a span").text("0%");
				//移除100%选项
				$("#jzcd option[value='10']").remove();
				$("#jzcd").attr("readonly",false);
				//是否按期完成
				$("#sfaqwc").val("1");
				$("#sfaqwcDiv a span").text("否");
				$("#sfaqwc").attr("readonly",true);
			}else {//已完成
				//执行状态
				$("#zxzt").val("2");
				$("#zxztDiv a span").text("已完成");
				$("#zxzt").attr("readonly",true);
				//进展程度
				$("#jzcd").val("10");
				$("#jzcdDiv a span").text("100%");
				$("#jzcd").attr("readonly",true);
				//是否按期完成
				var sjwcsj = $("#sjwcsj").val();
				var jhwcsj = $("#jhwcsj").val();
				var sjwcsjDate= new Date(Date.parse(sjwcsj.replace(/-/g,   "/"))); //转换成Data();
				var jhwcsjDate= new Date(Date.parse(jhwcsj.replace(/-/g,   "/"))); //转换成Data();
				var sfaqwc = sjwcsjDate<=jhwcsjDate? "0" : "1";
				$("#sfaqwc").val(sfaqwc);
				$("#sfaqwcDiv a span").text(sfaqwc == "1" ? "否" : "是");
				//移除100%选项
				if($("#jzcd option[value='10']").length==0){
					$("#jzcd").append('<option value="10">100%</option>');
				}
				$("#sfaqwc").attr("readonly",true);
			} */
		}
		//禁用启用 取消项
		function setCancelItems(flag){
			if(flag=="禁用"){
				$("#qxlx").attr("readonly",true);
				$("#qxyy").attr("readonly",true);
				$("#qxsj").attr("readonly",true);
				$("#qxr").attr("readonly",true);
			}
			if(flag=="启用"){
				$("#qxlx").attr("readonly",false);
				$("#qxyy").attr("readonly",false);
				$("#qxsj").attr("readonly",false);
				$("#qxr").attr("readonly",false);
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ip/scollingJobPlanTask/formReleased?id=${scollingJobPlanTask.id}&scollingJobPlanId=${scollingJobPlanId}">滚动工作计划任务 修改</a></li>
		<li class="pull-right">
			<input id="btnSubmit" class="btn btn-primary" type="button" value="保 存" onclick="$('#inputForm').submit();" />
			<input id="btnCancel" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-1)" />
			<input TYPE='BUTTON' class="btn btn-primary" value='重置' onClick="$('#inputForm')[0].reset();">
		</li>
	</ul><br/>
	
	<form:form id="inputForm" modelAttribute="scollingJobPlanTask" action="${ctx}/ip/scollingJobPlanTask/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="task.fbzt" value="${scollingJobPlanTask.task.fbzt}"/>
		<form:hidden path="scollingJobPlan.id" value="${scollingJobPlanTask.scollingJobPlan.id}"/>
		<input type="hidden" name="scollingJobPlanId" value="${scollingJobPlanTask.scollingJobPlan.id}"/>
		<tags:message content="${message}"/>
	<!-- 任务项	 start -->
		<div class="control-group">
			<label class="control-label" for="gzrwmc">工作任务名称:</label>
			<div class="controls">
				<form:input path="task.gzrwmc" htmlEscape="false" maxlength="200"/>
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
                <%-- <tags:treeselect  id="rwfzr" disabled="true" name="task.rwfzr.id" value="${scollingJobPlanTask.task.rwfzr.id}" labelName="task.rwfzr.name" labelValue="${scollingJobPlanTask.task.rwfzr.name}"
					title="公司" url="/ip/scollingJobPlanTask/getAllMemberByWorkCenterId?scollingJobPlanId=${not empty scollingJobPlanId ? scollingJobPlanId : scollingJobPlanTask.scollingJobPlan.id}"/> --%>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="rwxzr">协助人:</label>
			<div class="controls">
				<form:input path="task.rwxzr.name" htmlEscape="false" maxlength="200"/>
                <%-- <tags:treeselect disabled="true" id="rwxzr" name="task.rwxzr.id" value="${scollingJobPlanTask.task.rwxzr.id}" labelName="task.rwxzr.name" labelValue="${scollingJobPlanTask.task.rwxzr.name}"
					title="公司" url="/ip/scollingJobPlanTask/getAllMemberByWorkCenterId?scollingJobPlanId=${not empty scollingJobPlanId ? scollingJobPlanId : scollingJobPlanTask.scollingJobPlan.id}"/> --%>
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
				<input id="jhwcsj" name="task.jhwcsj" type="text" readonly="readonly" maxlength="20" class="input-small" value="<fmt:formatDate value="${scollingJobPlanTask.task.jhwcsj}" pattern="yyyy-MM-dd"/>"/>
			</div>
		</div>
	<!-- 任务项	 end -->
		
	<!-- 汇报项	 start -->
		<div class="control-group">
			<label class="control-label" for="sjkssj">实际开始时间:</label>
			<div class="controls">
				<input id="sjkssj" name="task.sjkssj" type="text" readonly="readonly" maxlength="20" class="input-small Wdate" value="<fmt:formatDate value="${scollingJobPlanTask.task.sjkssj}" pattern="yyyy-MM-dd"/>" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});" />
					<!-- onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true,maxDate:'#F{$dp.$D(\'sjwcsj\')}'});" /> -->
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="sjwcsj">实际完成时间:</label>
			<div class="controls">
				<input id="sjwcsj" name="task.sjwcsj" type="text" readonly="readonly" maxlength="20" class="input-small Wdate" value="<fmt:formatDate value="${scollingJobPlanTask.task.sjwcsj}" pattern="yyyy-MM-dd"/>"
					onclick="sjwcsjClick();" />
			</div>
		</div>
		<div class="control-group" id="zxztDiv">
			<label class="control-label" for="zxzt">任务执行状态:</label>
			<div class="controls">
				<form:select id="zxzt" path="task.zxzt">
					<form:options items="${fns:getDictList('d_rwzxzt')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group" id="sfaqwcDiv">
			<label class="control-label" for="sfaqwc">是否按期完成:</label>
			<div class="controls">
				<form:select id="sfaqwc" path="task.sfaqwc">
					<form:options items="${fns:getDictList('d_yesno')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="rwnd">任务难度:</label>
			<div class="controls">
				<form:select id="rwnd" path="task.rwnd">
					<form:options items="${fns:getDictList('d_rwnd')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="wcqksm">完成情况说明:</label>
			<div class="controls">
				<form:textarea path="task.wcqksm" htmlEscape="false" rows="4" maxlength="100" class="input-xxlarge"/>
			</div>
		</div>
		<div class="control-group" id="jzcdDiv">
			<label class="control-label" for="jzcd">进展程度:</label>
			<div class="controls">
				<form:select id="jzcd" path="task.jzcd">
					<form:options items="${fns:getDictList('d_rwjzcd')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="jzqksm">进展情况说明:</label>
			<div class="controls">
				<form:textarea path="task.jzqksm" htmlEscape="false" rows="4" maxlength="30" class="input-xxlarge"/>
			</div>
		</div>
	<!-- 汇报项	 end -->
		
		<div class="control-group">
			<label class="control-label" for="task.bz">备注:</label>
			<div class="controls">
				<form:textarea path="task.bz" htmlEscape="false" rows="4" maxlength="500" class="input-xxlarge"/>
			</div>
		</div>
		
	<!-- 取消项	 start -->
		<div class="control-group">
			<label class="control-label" for="qxlx">取消类型:</label>
			<div class="controls">
				<form:select id="qxlx" path="task.qxlx" >
					<option value=""></option>
					<form:options items="${fns:getDictList('d_rwqxnx')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<!-- 取消时间、取消人 系统生成 -->
		<div class="control-group">
			<label class="control-label" for="qxsj">取消时间:</label>
			<div class="controls">
				<input id="qxsj" name="task.qxsj" type="text" value="<fmt:formatDate value="${scollingJobPlanTask.task.qxsj}" pattern="yyyy-MM-dd"/>" maxlength="200" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="qxr">取消人:</label>
			<div class="controls">
				<c:if test="${scollingJobPlanTask.task.zxzt == 3}">
					<form:input id="qxr" path="task.updateBy.name" htmlEscape="false" maxlength="200" />
				</c:if>
				<c:if test="${scollingJobPlanTask.task.zxzt != 3}">
					<input id="qxr" type="text" />
				</c:if>
			</div>
		</div>
		<!-- 取消时间、取消人 系统生成 -->
		<div class="control-group">
			<label class="control-label" for="qxyy">取消原因:</label>
			<div class="controls">
				<form:textarea id="qxyy" path="task.qxyy" htmlEscape="false" rows="4" maxlength="100" class="input-xxlarge"/>
			</div>
		</div>
	<!-- 取消项	 end -->	
	</form:form>
	
</body>
</html>
