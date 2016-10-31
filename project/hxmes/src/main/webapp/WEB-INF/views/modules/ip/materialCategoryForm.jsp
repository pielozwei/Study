<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>物料编码信息管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		$("#wllbbm").focus();
		var code = '${wllb.wllbbm}';
		$("#inputForm").validate({
		rules : {
			wllbbm : {
				remote : "${ctx}/ip/wllb/checkWllbbm?oldWllbbm=" + code
			}
		},
		messages : {
			wllbbm : {
				remote : "编码已经存在!"
			}
		}
		});
	});
</script>
<c:if test="${not empty type && type == 'add'}">
	<script type="text/javascript">
		$(document).ready(function() {
			$('#inputForm').find("input").each(function(i, e) {
				var id = $(this).attr("id");
				if ($(this).is(":hidden") || id == "btnCancel" || id == "btnSubmit") {
				} else
					$(this).val("");
			});
		});
	</script>
</c:if>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active">
			<a href="${ctx}/ip/wllb/form?id=${wllb.id}&type=${type}">
				物料类别信息
				<shiro:hasPermission name="wuliao:wllb:edit">${empty type?'修改':(type=='add'?'添加':'修改')}</shiro:hasPermission>
				<shiro:lacksPermission name="wuliao:wllb:edit">查看</shiro:lacksPermission>
			</a>
		</li>
		<li class="pull-right">
			<input id="btnSubmit" class="btn btn-primary" type="button" value="保 存" onclick="$('#inputForm').submit();" />
			<input id="btnCancel" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-1)" />
			<input TYPE='BUTTON' class="btn btn-primary" value='重置' onClick="$('#inputForm')[0].reset();">
		</li>

	</ul>
	<br />

	<form:form id="inputForm" modelAttribute="wllb" action="${ctx}/ip/wllb/save" method="post" class="form-horizontal">
		<c:if test="${empty type&&type!='add'}">
			<form:hidden path="id" />
		</c:if>
		<input type="hidden" name="pId" value="${pId}" />
		<input type="hidden" name="type" value="${type}" />
		<tags:message content="${message}" />
		<div class="control-group">
			<label class="control-label" for="wllbbm">物料类别编号:</label>
			<div class="controls">
				<form:input path="wllbbm" htmlEscape="false" maxlength="200" class="required" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="wllbmc">物料类别名称:</label>
			<div class="controls">
				<form:input path="wllbmc" htmlEscape="false" maxlength="200" class="required" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="parent">上级节点:</label>
			<div class="controls">
				<tags:treepreserve id="wllb" name="parent.id" value="${wllb.parent.id}" labelName="parent.wllbmc" labelValue="${wllb.parent.wllbmc}" title="父节点"
					extId="${wllb.id}" url="/ip/wllb/treeData" module="wllb" selectScopeModule="true" notAllowSelectRoot="false" notAllowSelectParent="true"
					cssClass="required" />
				&nbsp;
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="xssx">显示顺序:</label>
			<div class="controls">
				<form:input path="xssx" htmlEscape="false" maxlength="30" value="0" class="required"/>
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
