<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>计量单位管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		var flag=0;
		$(document).ready(function() {
			$("#name").focus();
			$("#inputForm").validate();
		});
		function validation(){
			var val1=$('#bm').val();
			$.ajax({
				type : "post",
				dataType : "json",
				url : '${ctx}/ip/jldw/validation?val1='+val1,
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
		<li><a href="${ctx}/ip/jldw/">计量单位列表</a></li>
		<li class="active"><a href="${ctx}/ip/jldw/form?id=${jldw.id}">计量单位<shiro:hasPermission name="ip:jldw:edit">${not empty jldw.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="ip:jldw:edit">查看</shiro:lacksPermission></a></li>
		<button class="btn pull-right btn-primary" onclick="history.go(-1)">返回</button>&nbsp;
		<button class="btn pull-right btn-primary" onclick="$('#inputForm')[0].reset();">清空</button>&nbsp;
		<button class="btn pull-right btn-primary" onclick="submitMethod();">保存</button>&nbsp;
	</ul><br/>
	
	<form:form id="inputForm" modelAttribute="jldw" action="${ctx}/ip/jldw/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<tags:message content="${message}"/>
		<div class="control-group">
			<label class="control-label" for="jldwbm">计量单位编码:</label>
			<div class="controls">
				<form:input path="jldwbm" id="bm" htmlEscape="false" maxlength="200" class="required" oninput="validation();"/><span id="span1"></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="jldwmc">计量单位名称:</label>
			<div class="controls">
				<form:input path="jldwmc" htmlEscape="false" maxlength="200" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="xssx">显示顺序:</label>
			<div class="controls">
				<form:input path="xssx" htmlEscape="false" maxlength="200" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="sfqy">是否启用:</label>
			<div class="controls">
				<form:select path="sfqy" >
                    <form:option value="" label="请选择"/>
                    <form:option value="0" label="是"/>
                    <form:option value="1" label="否"/>
                </form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="bz">备注:</label>
			<div class="controls">
				<form:textarea path="bz" htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge"/>
			</div>
		</div>
		<%-- <div class="form-actions">
			<shiro:hasPermission name="ip:jldw:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div> --%>
	</form:form>
</body>
</html>
