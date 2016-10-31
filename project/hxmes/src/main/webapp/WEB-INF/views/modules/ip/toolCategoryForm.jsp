<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工器具分类管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		var flag=0;
		$(document).ready(function() {
			$("#lbmc").focus();
			$("#inputForm").validate();
			/* $("#lbbh").attr("readonly",true); */
		});
		//编码唯一校验
		function validation(){
			var val1=$('#bm').val();
			$.ajax({
				type : "post",
				dataType : "json",
				url : '${ctx}/ip/toolCategory/validation?val1='+val1,
				beforeSend : function(XMLHttpRequest) {
				},
				success : function(data){
					var span=document.getElementById("span1");
					span.innerHTML="<font color='red'>"+data.msg+"</font>";
					flag=data.flag;
				}
			})
		}
		function submitMethod(){
		//	alert(flag);
			if(flag==0){
				$('#inputForm').submit();
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/ip/toolCategory/?nodeId=${nodeId}">工器具分类列表</a></li>
		<li class="active"><a href="${ctx}/ip/toolCategory/form?id=${toolCategory.id}&nodeId=${nodeId}">工器具分类
		<shiro:hasPermission name="ip:toolCategory:edit">${not empty toolCategory.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="ip:toolCategory:edit">查看</shiro:lacksPermission></a></li>
		<button class="btn pull-right btn-primary" onclick="history.go(-1)">返回</button>&nbsp;
		<button class="btn pull-right btn-primary" onclick="$('#inputForm')[0].reset();">清空</button>&nbsp;
		<button class="btn pull-right btn-primary" onclick="submitMethod();">保存</button>&nbsp;
	</ul><br/>
	
	<form:form id="inputForm" modelAttribute="toolCategory" onsubmit="test();" action="${ctx}/ip/toolCategory/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<tags:message content="${message}"/>
		
		<div class="control-group">
			<label class="control-label" for="lbbh">工装器具类别编码:</label>
			<div class="controls">
				<form:input id="bm" path="lbbh" htmlEscape="false" maxlength="30" cssClass="required" oninput="validation();" /><span id="span1"></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="lbmc">工装器具类别名称:</label>
			<div class="controls">
				<form:input path="lbmc" htmlEscape="false" maxlength="30" cssClass="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="lbjc">工装器具类别简称:</label>
			<div class="controls">
				<form:input path="lbjc" htmlEscape="false" maxlength="30" cssClass="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="SJLBID">上级类别编码:</label>
			<div class="controls">
				<tags:treepreserve id="SJLBID" name="parent.id" cssClass="required" value="${toolCategory.parent.id}" labelName="parent.lbmc" labelValue="${toolCategory.parent.lbmc}"
					title="父节点" url="/ip/toolCategory/treeData" module="toolCategory" selectScopeModule="true" notAllowSelectRoot="false" notAllowSelectParent="true" />&nbsp;
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="xssx">显示顺序:</label>
			<div class="controls">
				<form:input path="xssx" htmlEscape="false" maxlength="30" cssClass="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="sfqy">是否启用:</label>
			<div class="controls">
				<form:select path="sfqy" cssClass="required">
                    <form:option value="0" label="启用"/>
                    <form:option value="1" label="停用"/>
                </form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="remarks">备注:</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge"/>
			</div>
		</div>
		<%-- <div class="form-actions">
			<shiro:hasPermission name="ip:toolCategory:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div> --%>
	</form:form>
</body>
</html>
