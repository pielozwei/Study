<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>关键技术参数管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	    var flag=0;
		$(document).ready(function() {
			$("#csmc").focus();
			$("#inputForm").validate();
			/* $("#csdm").attr("readonly",true); */
		});
		//编码唯一校验
		function validation(){
			var val1=$('#bm').val();
			$.ajax({
				type : "post",
				dataType : "json",
				url : '${ctx}/ip/toolTechParam/validation?val1='+val1,
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
		<li><a href="${ctx}/ip/toolTechParam/?toolId=${toolTechParam.tool.id}">关键技术参数列表</a></li>
		<li class="active"><a href="${ctx}/ip/toolTechParam/form?id=${toolTechParam.id}&tool.id=${toolTechParam.tool.id}">关键技术参数
		<shiro:hasPermission name="ip:toolTechParam:edit">${not empty toolTechParam.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="ip:toolTechParam:edit">查看</shiro:lacksPermission></a></li>
		<button class="btn pull-right btn-primary" onclick="history.go(-1)">返回</button>
		&nbsp;
		<button class="btn pull-right btn-primary" onclick="$('#inputForm')[0].reset();">清空</button>
		&nbsp;
		<button class="btn pull-right btn-primary" onclick="submitMethod()">保 存</button>
	</ul><br/>
	
	<form:form id="inputForm" modelAttribute="toolTechParam" action="${ctx}/ip/toolTechParam/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="tool.id" value="${toolTechParam.tool.id}"/>
		<tags:message content="${message}"/>
		
		<div class="control-group">
			<label class="control-label" for="csdm">参数编码:</label>
			<div class="controls">
				<form:input path="csdm" id="bm" htmlEscape="false" maxlength="30" class="required" oninput="validation()"/><span id="span1"></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="csmc">参数名称:</label>
			<div class="controls">
				<form:input path="csmc" htmlEscape="false" maxlength="30" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="csjc">参数简称:</label>
			<div class="controls">
				<form:input path="csjc" htmlEscape="false" maxlength="30" class="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="cszlx">参数值类型:</label>
			<div class="controls">
				<form:select path="cszlx">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('d_qzqjcszlx')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="csjldw">计量单位:</label>
			<div class="controls">
				<form:select path="jldwId.id">
					<form:options items="${fns:getJldwDictList()}" itemLabel="jldwmc" itemValue="id" htmlEscape="false" class="required" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="bzz">标准值:</label>
			<div class="controls">
				<form:input path="bzz" htmlEscape="false" maxlength="126" class="number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="sxz">上限值:</label>
			<div class="controls">
				<form:input path="sxz" htmlEscape="false" maxlength="126" class="number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="xxz">下限值:</label>
			<div class="controls">
				<form:input path="xxz" htmlEscape="false" maxlength="126" class="number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="xssx">显示顺序:</label>
			<div class="controls">
				<form:input path="xssx" htmlEscape="false" maxlength="30" class="required"/>
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
			<label class="control-label" for="cssm">参数说明:</label>
			<div class="controls">
				<form:textarea path="cssm" htmlEscape="false" rows="4" maxlength="30" class="input-xxlarge"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="bz">备注:</label>
			<div class="controls">
				<form:textarea path="bz" htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge"/>
			</div>
		</div>
		<%-- <div class="form-actions">
			<shiro:hasPermission name="ip:toolTechParam:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div> --%>
	</form:form>
</body>
</html>
