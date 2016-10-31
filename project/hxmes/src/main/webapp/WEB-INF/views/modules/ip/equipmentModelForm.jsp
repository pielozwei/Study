<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>基本信息管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		$("#sbggxhmc").focus();
		var code = '${equipmentModel.sbggxhbm}';
		$("#inputForm").validate({
		rules : {
			sbggxhbm : {
				remote : "${ctx}/ip/equipmentModel/checkSbggxhbm?oldSbggxhbm=" + code
			}
		},
		messages : {
			sbggxhbm : {
				remote : "编码已经存在!"
			}
		}
		});
	});
	function validateForm() {
		//validate方法参数可选
		return $("#inputForm").validate().form();
	}

	function doSubmit() {
		//do other things
		//验证通过后提交
		if (validateForm()) {
			inputForm.submit();
		}
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active">
			<a href="${ctx}/ip/equipmentModel/form?id=${equipmentModel.id}&nodeId=${nodeId}">
				基本信息
				<shiro:hasPermission name="ip:equipmentModel:edit">${not empty equipmentModel.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="ip:equipmentModel:edit">查看</shiro:lacksPermission>
			</a>
		</li>

		<li class="pull-right">
			<input id="btnSubmit" class="btn btn-primary" type="button" value="保 存" onclick="doSubmit();" />
			&nbsp;
			<input id="btnCancel" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-1)" />
			<input TYPE='button' class="btn btn-primary" value='重置' onClick="$('#inputForm')[0].reset();">
		</li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="equipmentModel" action="${ctx}/ip/equipmentModel/save" method="post" class="form-horizontal">
		<form:hidden path="id" />
		<form:hidden path="equipmentCategory.id" value="${nodeId}" />
		<tags:message content="${message}" />

		<div class="control-group">
			<label class="control-label" for="sbggxhbm">设备规格编码:</label>
			<div class="controls">
				<form:input path="sbggxhbm" htmlEscape="false" maxlength="100" class="required" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="sbggxhmc">设备规格名称:</label>
			<div class="controls">
				<form:input path="sbggxhmc" htmlEscape="false" maxlength="30" class="required" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="sbggxhjc">简称:</label>
			<div class="controls">
				<form:input path="sbggxhjc" htmlEscape="false" maxlength="30" class="required" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="sbgg">规格:</label>
			<div class="controls">
				<form:input path="sbgg" htmlEscape="false" maxlength="30" class="required" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="sbxh">型号:</label>
			<div class="controls">
				<form:input path="sbxh" htmlEscape="false" maxlength="30" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label" for="sftzsb">是否特种设备:</label>
			<div class="controls">
				<form:select path="sftzsb" onchange="if(this.value==0)$('#tzhongshebei').show();else $('#tzhongshebei').hide();">
					<form:option value="" label="请选择" />
					<form:options items="${fns:getDictList('d_yesno')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group" id="tzhongshebei" style="display: none;">
			<label class="control-label" for="tzsblb">特种设备类别:</label>
			<div class="controls">
				<form:select path="tzsblb">
					<form:option value="" label="请选择" />
					<form:options items="${fns:getDictList('d_tzsblb')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="wxzcc">外形尺寸:</label>
			<div class="controls">
				<form:input path="wxzcc" htmlEscape="false" maxlength="30" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="zl">重量:</label>
			<div class="controls">
				<form:input path="zl" htmlEscape="false" maxlength="30" class="number" />
				(吨)
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="gl">功率:</label>
			<div class="controls">
				<form:input path="gl" htmlEscape="false" maxlength="200" class="digits" />
				(千瓦/台)
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="jssp">技术水平:</label>
			<div class="controls">
				<form:select path="jssp">
					<form:option value="1" label="高" />
					<form:option value="2" label="中" />
					<form:option value="3" label="低" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="jsxnms">技术性能描述:</label>
			<div class="controls">
				<form:textarea path="jsxnms" htmlEscape="false" rows="4" maxlength="4000" class="input-xxlarge" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label" for=xssx>显示顺序:</label>
			<div class="controls">
				<form:input path="xssx" htmlEscape="false" maxlength="30" class="required" value='0' />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="sfqy">是否启用:</label>
			<div class="controls">
				<form:select path="sfqy">
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
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
