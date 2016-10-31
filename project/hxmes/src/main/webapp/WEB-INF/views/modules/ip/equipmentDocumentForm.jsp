<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>技术资料管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		$("#wdmc").focus();
		var code = '${equipmentDocument.wdbm}';
		$("#inputForm").validate({
		rules : {
			wdbm : {
				remote : "${ctx}/ip/equipmentDocument/checkWdbm?oldWdbm=" + code
			}
		},
		messages : {
			wdbm : {
				remote : "编码已经存在!"
			}
		}
		});
	});
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active">
			<a href="${ctx}/ip/equipmentDocument/form?id=${equipmentDocument.id}&equipmentModel.id=${equipmentDocument.equipmentModel.id}">
				技术资料<shiro:hasPermission name="ip:equipmentDocument:edit">${not empty equipmentDocument.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="ip:equipmentDocument:edit">查看</shiro:lacksPermission>
			</a>
		</li>
		<li class="pull-right">
			<input class="btn btn-primary" type="button" value="保 存" onclick="$('#inputForm').submit();" />
			<input id="btnCancel" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-1)" />
			<input TYPE='BUTTON' class="btn btn-primary" value='重置' onClick="$('#inputForm')[0].reset();">
		</li>
	</ul>
	<br />

	<form:form id="inputForm" modelAttribute="equipmentDocument" action="${ctx}/ip/equipmentDocument/save" method="post" class="form-horizontal">
		<form:hidden path="id" />
		<form:hidden path="equipmentModel.id" value="${equipmentDocument.equipmentModel.id}" />
		<form:hidden path="equipmentModel.sbggxhbm" value="${equipmentDocument.equipmentModel.sbggxhbm}" />
		<tags:message content="${message}" />

		<div class="control-group">
			<label class="control-label" for="wdbm">文档编码:</label>
			<div class="controls">
				<form:input path="wdbm" htmlEscape="false" maxlength="30" class="required" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="wdbt">文档标题:</label>
			<div class="controls">
				<form:input path="wdbt" htmlEscape="false" maxlength="100" class="required" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="gjc">关 键 词:</label>
			<div class="controls">
				<form:input path="gjc" htmlEscape="false" maxlength="30" class="required" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="wdlb">文档类别:</label>
			<div class="controls">
				<form:select path="wdlb">
					<form:options items="${fns:getDictList('d_wdfl')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="zy">摘要:</label>
			<div class="controls">
				<form:textarea path="zy" htmlEscape="false" rows="4" maxlength="2000" class="input-xxlarge" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="wjm">附件文件:</label>
			<div class="controls">
				<input type="hidden" id="wjm" name="wjm" value="${equipmentDocument.wjm}" />
				<tags:ckfinder input="wjm" type="files" uploadPath="/ip/equipmentDocument" selectMultiple="false" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label" for="createBy">上传人:</label>
			<div class="controls">
				<form:input path="createBy" htmlEscape="false" maxlength="128" value="${equipmentDocument.createBy.name}" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="createDate">上传日期:</label>
			<div class="controls">
				<input id="createDate" type="text" value="<fmt:formatDate value='${equipmentDocument.createDate}' pattern='yyyy-MM-dd'/>" readonly="readonly" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="bz">备注:</label>
			<div class="controls">
				<form:textarea path="bz" htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge" />
			</div>
		</div>
	</form:form>
</body>
</html>
