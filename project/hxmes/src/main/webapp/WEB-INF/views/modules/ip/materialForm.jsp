<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>物料编码信息管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		$("#wlbm").focus();
		var code = '${wlbm.wlbm}';
		$("#inputForm").validate({
		rules : {
			wlbm : {
				remote : "${ctx}/ip/wlbm/checkWlbm?oldWlbm=" + code
			}
		},
		messages : {
			wlbm : {
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
			<a href="${ctx}/ip/wlbm/form?id=${wlbm.id}&wllb.id=${wlbm.wllb.id}&_module=wlbm">
				物料编码信息
				<shiro:hasPermission name="wuliao:wlbm:edit">${not empty wlbm.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="wuliao:wlbm:edit">查看</shiro:lacksPermission>
			</a>
		</li>
		<li class="pull-right">
			<input class="btn btn-primary" type="button" value="保 存" onclick="$('#inputForm').submit();" />
			<input id="btnCancel" class="btn btn-primary" type="button" value="返 回" onclick="history.go(-1)" />
			<input TYPE='button' class="btn btn-primary" value='重置' onClick="$('#inputForm')[0].reset();">
		</li>
	</ul>
	<br />

	<form:form id="inputForm" modelAttribute="wlbm" action="${ctx}/ip/wlbm/save?wllbId=${wlbm.wllb.id}" method="post" class="form-horizontal">
		<form:hidden path="id" />
		<tags:message content="${message}" />
		<div class="control-group">
			<label class="control-label" for="wllbbm">物料类别:</label>
			<div class="controls">
			<%-- 	<tags:treeselect id="wllb" name="wllb.id" value="${wlbm.wllb.id}" labelName="wllb.wllbmc" labelValue="${wlbm.wllb.wllbmc}" title=""
					url="/ip/wllb/treeData" module="wllb" selectScopeModule="true" notAllowSelectRoot="false" notAllowSelectParent="true" />
				&nbsp; --%>
				<form:input path="wllb.id" htmlEscape="false" maxlength="200" class="required" readonly="true" />

			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="wlbm">物料编码:</label>
			<div class="controls">
				<form:input path="wlbm" htmlEscape="false" maxlength="200" class="required" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="wlmc">物料名称:</label>
			<div class="controls">
				<form:input path="wlmc" htmlEscape="false" maxlength="200" class="required" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="wljc">物料简称:</label>
			<div class="controls">
				<form:input path="wljc" htmlEscape="false" maxlength="200" class="required" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="cwbm">财务编码:</label>
			<div class="controls">
				<form:input path="cwbm" htmlEscape="false" maxlength="200" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="iaeabm">IAEA编码:</label>
			<div class="controls">
				<form:input path="iaeabm" htmlEscape="false" maxlength="200" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="gg">规格:</label>
			<div class="controls">
				<form:input path="gg" htmlEscape="false" maxlength="200" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="xh">型号:</label>
			<div class="controls">
				<form:input path="xh" htmlEscape="false" maxlength="200" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="jlfs">计量方式:</label>
			<div class="controls">
				<form:select path="jlfs">
					<form:options items="${fns:getDictList('d_jlfs')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="jldw">计量单位:</label>
			<div class="controls">
				<form:select path="jldw.id">
					<form:options items="${fns:getJldwDictList()}" itemLabel="jldwmc" itemValue="id" htmlEscape="false" class="required" />
				</form:select>
				&nbsp;
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="wlzhlb">物料综合类别:</label>
			<div class="controls">
				<form:select path="wlzhlb">
					<form:options items="${fns:getDictList('d_wlzhlb')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="zyabc">重要ABC:</label>
			<div class="controls">
				<form:select path="zyabc">
					<form:options items="${fns:getDictList('d_abc')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="pfabc">频发ABC:</label>
			<div class="controls">
				<form:select path="pfabc">
					<form:options items="${fns:getDictList('d_abc')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="jhdwcb">计划单位成本(元):</label>
			<div class="controls">
				<form:input path="jhdwcb" htmlEscape="false" class="number" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="fyjgts">复验间隔天数(天):</label>
			<div class="controls">
				<form:input path="fyjgts" htmlEscape="false" class="digits" />

			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="zcbcqx">最长保存期限(天):</label>
			<div class="controls">
				<form:input path="zcbcqx" htmlEscape="false" class="digits" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="xssx">显示顺序:</label>
			<div class="controls">
				<form:input path="xssx" value="0" htmlEscape="false" class="digits" />
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
			<label class="control-label" for="wlstx">物料实体性:</label>
			<div class="controls">
				<form:select path="wlstx">
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
