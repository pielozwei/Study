<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>滚动工作计划管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
//	var minJhkssj = null;
	function jhkssjClick(){
		WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false/* ,minDate:'#F{\''+new Date(minJhkssj)+'\'}',maxDate:'#F{$dp.$D(\'jhjzsj\')}' */});
	}
	function jhjzsjClick(){
		/* if (!$("#jhkssj").val()) {
			top.$.jBox.tip('请先选择计划开始时间再来选择计划截止时间','warning');
			return;
		} else {
			var jhkssjVal = new Date($("#jhkssj").val());
			var d = new Date();
			var strTime = d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate(); //字符串日期格式           
			var date= new Date(Date.parse(strTime.replace(/-/g,   "/"))); //转换成Data();
			var minVal = jhkssjVal > date ? jhkssjVal : date; */
			WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false/* ,minDate:'#F{\''+minVal+'\'}' */});
		/* } */
	}
		$(document).ready(function() {
			<c:if test="${scollingJobPlan.fbzt == 1}">
				$("#inputForm *").each(function(i,e){
					$(this).prop("disabled",true);
				});
			</c:if>
			<c:if test="${empty scollingJobPlan.id || scollingJobPlan.fbzt == 0}">
				$("#jhbh").focus();
				var p='${scollingJobPlan.jhbh}';
				$("#inputForm").validate({
					rules : {
						jhbh : {
							remote : "${ctx}/ip/scollingJobPlan/checkJhbh?oldJhbh="+ p
						}
					},
					messages : {
						jhbh : {
							remote : "该滚动工作计划编号已经存在!"
						}
					}
				});
				$("#workCenterDiv select").change();
			</c:if>
		});
		function gzzx_idChange(obj) {
			$.ajax({
				type: "post",
				dataType:"json",
				url: "${ctx}/ip/scollingJobPlan/selectUserByWorkCenterId?workCenterId="+obj.value,
				beforeSend: function(XMLHttpRequest){
				},
				success: function(data, textStatus){
					if(data.length==2){
						$("#jhfzrId").val(data[0].id);
						$("#fzr").val(data[0].name);
						$("#jhxzrId").val(data[1].id);
						$("#xzr").val(data[1].name);
					}else {
						top.$.jBox.tip("真遗憾，改工作中心没有负责人或者协助人，不能选");
						return;
					}
				}
			});
			/* $.ajax({
				type: "post",
				dataType:"json",
				url: "${ctx}/ip/scollingJobPlan/getMaxJhjzsjByWorkCenterId?workCenterId="+obj.value+"&currentId=${scollingJobPlan.id}",
				beforeSend: function(XMLHttpRequest){
				},
				success: function(data, textStatus){
					if(data!==null){
						minJhkssj = data;
					}
				}
			}); */
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ip/scollingJobPlan/form?id=${scollingJobPlan.id}">滚动工作计划
		<shiro:hasPermission name="ip:scollingJobPlan:edit">${not empty scollingJobPlan.id?'修改':'添加'}</shiro:hasPermission></a></li>
		<li class="pull-right">
			<%-- <c:if test="${empty scollingJobPlan.id || scollingJobPlan.fbzt == 0}"> --%>
				<input id="btnSubmit" class="btn btn-primary" type="button" value="保 存" onclick="$('#inputForm').submit();" />
			<%-- </c:if> --%>
			<input id="btnCancel" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-1)" />
			<input TYPE='BUTTON' class="btn btn-primary" value='重置' onClick="$('#inputForm')[0].reset();">
		</li>
	</ul><br/>
	
	<form:form id="inputForm" modelAttribute="scollingJobPlan" action="${ctx}/ip/scollingJobPlan/save" method="post" class="form-horizontal">
		<form:hidden path="id" />
		<form:hidden path="fbzt" value="${not empty scollingJobPlan.fbzt ? scollingJobPlan.fbzt : 0}"/>
		<tags:message content="${message}"/>
		<!-- 序号、发布状态、执行状态、进展程度、工作任务、负责人、协助人
		滚动工作计划编号、发布状态、滚动工作计划名称、负责工作中心、创建人、计划开始时间、计划截止时间 -->
		<div class="control-group">
			<label class="control-label" for="jhbh">滚动工作计划编号:</label>
			<div class="controls">
				<form:input path="jhbh" htmlEscape="false" maxlength="200" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="jhmc">滚动工作计划名称:</label>
			<div class="controls">
				<form:input path="jhmc" htmlEscape="false" maxlength="200" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="gzzx_id">负责工作中心:</label>
			<div class="controls" id="workCenterDiv">
				<form:select path="workCenter.id" onchange="gzzx_idChange(this);">
					<form:options items="${listWorkCenter}" itemLabel="gzzxmc" itemValue="id" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="fzr">负责人:</label>
			<div class="controls">
				<input id="jhfzrId" name="jhfzrId" type="hidden" maxlength="200" value="${scollingJobPlan.jhfzrId}"/>
				<input id="fzr" name="fzr" type="text" maxlength="200" readonly="readonly"  value="${scollingJobPlan.fzr}"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="xzr">协助人:</label>
			<div class="controls">
				<input id="jhxzrId" name="jhxzrId" type="hidden" maxlength="200" value="${scollingJobPlan.jhxzrId}"/>
				<input id="xzr" name="xzr" type="text" maxlength="200" readonly="readonly" value="${scollingJobPlan.xzr}"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="jhkssj">计划开始时间:</label>
			<div class="controls">
				<input id="jhkssj" name="jhkssj" type="text" readonly="readonly" maxlength="20" class="input-small Wdate required" value="<fmt:formatDate value="${scollingJobPlan.jhkssj}" pattern="yyyy-MM-dd"/>" 
					onclick="jhkssjClick();" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="jhjzsj">计划截止时间:</label>
			<div class="controls">
				<input id="jhjzsj" name="jhjzsj" type="text" readonly="readonly" maxlength="20" class="input-small Wdate required" value="<fmt:formatDate value="${scollingJobPlan.jhjzsj}" pattern="yyyy-MM-dd"/>"
					onclick="jhjzsjClick();" />
			</div>
		</div>
	</form:form>
</body>
</html>
