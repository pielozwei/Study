
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>工艺路线管理工艺文件</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		$("#wjbm").focus();
		$("#inputForm").validate();
	});
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active">
			<a href="${ctx}/ip/techniqueDocument/form?id=${techniqueDocument.id}">新增工艺文件 </a>
		</li>
		<li class="pull-right">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存" onclick="$('#inputForm').submit();" />
				<input type='button' class="btn btn-primary" value='重置' onClick="$('#inputForm')[0].reset();">
				<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)" />		
		</li>
	</ul>	
	<form:form id="inputForm" modelAttribute="techniqueDocument"
		action="${ctx}/ip/techniqueDocument/save" method="post"
		class="form-horizontal">
		<form:hidden path="id" />
		<tags:message content="${message}" />
		<div class="control-group">
			<label class="control-label" for="wjbm">工艺文件编码:</label>
			<div class="controls">
				<form:input path="wjbm" htmlEscape="false" maxlength="200"
					class="required" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="wjbtmc">工艺文件名称:</label>
			<div class="controls">
				<form:input path="wjbtmc" htmlEscape="false" maxlength="200"
					class="required" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="gylx_id">工序:</label>
			<div class="controls">
				<tags:treeselect id="gylx_id" name="technique.id" value="${list.id}" labelName="technique.mc"
					labelValue="${list.name}" title="工序名称" url="/ip/technique/treeData" cssClass="required"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="bbh">文件版本:</label>
			<div class="controls">
				<form:input path="bbh" htmlEscape="false" maxlength="200" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="wjlb">文件类型:</label>
			<div class="controls">
				<form:select path="wjlb" class="required">
					<form:options items="${fns:getDictList('d_wjlx')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="fjwjm">文件名:</label>
			<div class="controls">
				<form:hidden path="fjwjm" htmlEscape="false" maxlength="255"
					class="input-xlarge" />
				<tags:ckfinder input="fjwjm" type="files" uploadPath="/cms/site" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="fwxh">发文序号:</label>
			<div class="controls">
				<form:input path="fwxh" htmlEscape="false" maxlength="200" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="fwdw">发文单位:</label>
			<div class="controls">
				<form:input path="fwdw" htmlEscape="false" maxlength="200" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="fwrq">发文日期:</label>
			<div class="controls">
				<input id="fwrq" name="fwrq" type="text" maxlength="20" class="Wdate" 
				    value="<fmt:formatDate value="${techniqueDocument.fwrq}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="sfqy">是否启用:</label>
			<div class="controls">
				<form:select path="sfqy" name="sfqy" class="required">
					<form:options items="${fns:getDictList('yes_no')}"
						itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>

	</form:form>
</body>
</html>
