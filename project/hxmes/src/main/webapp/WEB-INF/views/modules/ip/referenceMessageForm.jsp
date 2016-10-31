<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>参考消息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#name").focus();
			var p='${referenceMessage.bt}';
			$("#inputForm").validate({
				rules : {
					bt : {
						remote : "${ctx}/ip/referenceMessage/checkBt?oldBt="+ p
					}
				},
				messages : {
					bt : {
						remote : "该参考消息名称已经存在!"
					}
				}
			});
		});
		function jhwcsjClick(){
			if (!$("#jhkssj").val()) {
				top.$.jBox.tip('请先选择实际开始时间再来选择实际完成时间','warning');
				return;
			} else {
				WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false,minDate:'#F{$dp.$D(\'jhkssj\')}'});
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<%-- <li><a href="${ctx}/ip/referenceMessage/">参考消息列表</a></li> --%>
		<li class="active"><a href="${ctx}/ip/referenceMessage/form?id=${referenceMessage.id}">参考消息
		<shiro:hasPermission name="ip:referenceMessage:edit">${not empty referenceMessage.id?'修改':'添加'}</shiro:hasPermission></a></li>
		<li class="pull-right">
			<c:if test="${empty referenceMessage.sffs || referenceMessage.sffs==1}">
				<input id="btnSubmit" class="btn btn-primary" type="button" value="保 存" onclick="$('#inputForm').submit();" />
			</c:if>
			<input id="btnCancel" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-1)" />
			<c:if test="${empty referenceMessage.sffs || referenceMessage.sffs==1}">
				<input TYPE='BUTTON' class="btn btn-primary" value='重置' onClick="$('#inputForm')[0].reset();">
			</c:if>
		</li>
	</ul><br/>
	
	<form:form id="inputForm" modelAttribute="referenceMessage" action="${ctx}/ip/referenceMessage/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="sffs" value="${empty referenceMessage.sffs ? 1 : referenceMessage.sffs}"/>
		<form:hidden path="sfyd" value="${empty referenceMessage.sfyd ? 1 : referenceMessage.sfyd}"/>
		<form:hidden path="sfyyg" value="${empty referenceMessage.sfyyg ? 1 : referenceMessage.sfyyg}"/>
		<tags:message content="${message}"/>
		
		<div class="control-group">
			<label class="control-label" for="bt">参考消息名称:</label>
			<div class="controls">
				<form:input path="bt" htmlEscape="false" maxlength="100" class="required"/>
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
			<label class="control-label" for="jhkssj">计划开始时间:</label>
			<div class="controls">
				<input id="jhkssj" name="jhkssj" type="text" readonly="readonly" maxlength="20" class="input-small Wdate required" value="<fmt:formatDate value="${referenceMessage.jhkssj}" pattern="yyyy-MM-dd"/>" 
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false,maxDate:'#F{$dp.$D(\'jhwcsj\')}'});" class="required" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="jhwcsj">计划完成时间:</label>
			<div class="controls">
				<input id="jhwcsj" name="jhwcsj" type="text" readonly="readonly" maxlength="20" class="input-small Wdate required" value="<fmt:formatDate value="${referenceMessage.jhwcsj}" pattern="yyyy-MM-dd"/>"
					onclick="jhwcsjClick();" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="nr">参考消息内容:</label>
			<div class="controls">
				<form:textarea path="nr" htmlEscape="false" rows="4" maxlength="500" class="input-xxlarge"/>
			</div>
		</div>
	</form:form>
</body>
</html>
