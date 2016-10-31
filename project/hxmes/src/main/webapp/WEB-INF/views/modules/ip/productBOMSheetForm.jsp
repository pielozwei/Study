<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>BOM清单管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		$("#cpbomdbh").focus();
		/* var code = '${productBOMSheet.cpbomdbh}';
		$("#inputForm").validate({
		rules : {
			cpbomdbh : {
				remote : "${ctx}/ip/productBOMSheet/checkCpbomdbh?oldcCpbomdbh=" + code
			}
		},
		messages : {
			cpbomdbh : {
				remote : "编码已经存在!"
			}
		}
		}); */
	});
	function validateForm() {
		//validate方法参数可选
		return $("#inputForm").validate().form();
	}

	function doSubmit() {
		//验证通过后提交
		if (validateForm()) {
			var wlbm = $('#wlmc').val();
			var sfqy = $('#sfqy').val();
			var bb = $('#bb').val();
			var cpbomdbh = $('#cpbomdbh').val();
			var id = $('#id').val();
			$.ajax({
			type : "post",
			data : {
			'wlbm' : wlbm,
			'sfqy' : sfqy,
			'bb' : bb,
			'cpbomdbh' : cpbomdbh,
			'id' : id
			},
			url : '${ctx}/ip/productBOMSheet/checkCpbomdbh',
			success : function(data) {
				debugger;
				if (data == ''||data==null) {
					inputForm.submit();
				} else {
					top.$.jBox.tip(data, 'error');
				}

			}
			});
		}
	}
	function delList() {
		//获取勾选的条数
		batchFnc('确认要删除所选的信息吗？', "${ctx}/ip/productBOMSheet/delete", "zj", "")
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active">
			<a href="${ctx}/ip/productBOMSheet/form?id=${productBOMSheet.id}">
				BOM清单
				<shiro:hasPermission name="chanpin:productBOMSheet:edit">${not empty productBOMSheet.id?'修改':'添加'}</shiro:hasPermission>
				<shiro:lacksPermission name="chanpin:productBOMSheet:edit">查看</shiro:lacksPermission>
			</a>
		</li>
		<li class="pull-right">
			<input class="btn btn-primary" type="button" value="保 存" onclick="doSubmit();" />
			<input id="btnCancel" class="btn btn-primary" type="button" value="返 回" onclick="window.location.href='${ctx}/ip/productBOMSheet/'" />
			<input TYPE='BUTTON' class="btn btn-primary" value='重置' onClick="$('#inputForm')[0].reset();">
		</li>


	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="productBOMSheet" action="${ctx}/ip/productBOMSheet/save" method="post" class="form-horizontal">
		<form:hidden path="id" />

		<tags:message content="${message}" />
		<div class="control-group">
			<label class="control-label" for="cpbomdbh">BOM单编号:</label>
			<div class="controls">
				<form:input path="cpbomdbh" htmlEscape="false" maxlength="200" class="required" />
			</div>
		</div>

		<div class="control-group">
			<label class="control-label" for="wlbm">物料:</label>
			<div class="controls">
				<tags:taglistselect url="/Integrate/getMaterial" name="wlbm.id" id="wlbm" cssClass="required" itemNames="物料编码,物料名称,规格,型号,重要ABC,计量单位,综合物料类别,IAEA编码"
					labelValue="${productBOMSheet.wlbm.wlmc}" labelName="wlmc" itemCodes="wlbm,wlmc,gg,xh,zyabc,jldwmc,wlzhlb,iaeabm" title="物料基本信息"
					value="${productBOMSheet.wlbm.id}" width="700" height="450" pageSize="10"></tags:taglistselect>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="bomzb">BOM组别:</label>
			<div class="controls">
				<form:select path="bomzb">
					<form:options items="${fns:getDictList('d_bomzb')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="bb">版本:</label>
			<div class="controls">
				<form:input path="bb" htmlEscape="false" maxlength="200" class="required" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="sfzy">是否在用:</label>
			<div class="controls">
				<form:select path="sfzy">
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
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

	</form:form>
	<c:if test="${type!='add'}">
		<div class="nav nav-tabs">
			<iframe id="bommx" name="bommx" width="100%" src="${ctx}/ip/productBOMDetail/list?productBOMSheet.id=${productBOMSheet.id}"
				style="overflow: hidden;" scrolling="no" frameborder="no" width="100%" height="510px"> </iframe>
		</div>
	</c:if>
</body>
</html>
