<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>BOM明细管理</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
	$(document).ready(function() {
		$("#inputForm").validate();
	});
	function submitAndAdd() {
		$('#inputForm').attr("action", "${ctx}/ip/productBOMDetail/save?type=add").submit();
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active">
			<a href="${ctx}/ip/productBOMDetail/form?id=${productBOMDetail.id}&productBOMSheet.id=${productBOMDetail.productBOMSheet.id}"> BOM明细 </a>
		</li>
		<li class="pull-right">
			<input id="btnSubmitAndAdd" class="btn btn-primary" value="保存并新增" onclick="submitAndAdd();" />
			<input class="btn btn-primary" type="button" value="保 存" onclick="$('#inputForm').submit();" />
			<input id="btnCancel" class="btn btn-primary" type="button" value="返 回"
				onclick="window.location.href='${ctx}/ip/productBOMDetail/list?productBOMSheet.id=${productBOMDetail.productBOMSheet.id}'" />
			<input TYPE='button' class="btn btn-primary" value='重置' onClick="$('#inputForm')[0].reset();">
		</li>
	</ul>
	<br />
	<form:form id="inputForm" modelAttribute="productBOMDetail" action="${ctx}/ip/productBOMDetail/save" method="post" class="form-horizontal">
		<form:hidden path="id" />
		<tags:message content="${message}" />
		<form:hidden path="productBOMSheet.id" readonly="true" value="${productBOMDetail.productBOMSheet.id}" maxlength="200" class="required" />
		<div class="control-group">
			<label class="control-label" for="zwlbm">子物料:</label>
			<div class="controls">
				<%-- <tags:treepreserve id="productBOMDetail6" name="zwlbm.id" value="${productBOMDetail.zwlbm.id}" labelName="zwlbm.wlmc"
					labelValue="${productBOMDetail.zwlbm.wlmc}" title="产品树" url="/ip/productBOMSheet/treeData?id=${productBOMDetail.productBOMSheet.id}"
					module="productBOMSheet" selectScopeModule="true" notAllowSelectRoot="false" notAllowSelectParent="true" cssClass="required" /> --%>
				<tags:taglistselect url="/Integrate/getProductMaterial?id=${productBOMDetail.productBOMSheet.id}" name="zwlbm.id" id="wlbm" cssClass="required"
					itemNames="物料编码,物料名称,规格,型号,重要ABC,计量单位,综合物料类别,IAEA编码" labelValue="${productBOMDetail.zwlbm.wlmc}" labelName="wlmc"
					itemCodes="wlbm,wlmc,gg,xh,zyabc,jldwmc,wlzhlb,iaeabm" title="物料基本信息" value="${productBOMDetail.zwlbm.id}" width="700" height="450"
					pageSize="10"></tags:taglistselect>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="sl">数量:</label>
			<div class="controls">
				<form:input path="sl" htmlEscape="false" maxlength="200" class="required digits" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="jldw">单位:</label>
			<div class="controls">
				<form:select path="jldw.id">
					<form:options items="${fns:getJldwDictList()}" itemLabel="jldwmc" itemValue="id" htmlEscape="false" class="required" />
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="sfmj">是否末级:</label>
			<div class="controls">
				<form:select path="sfmj">
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label" for="bz">备注:</label>
			<div class="controls">
				<form:textarea path="bz" htmlEscape="false" rows="4" maxlength="200" class="input-xxlarge" />
			</div>
		</div> --%>
		<div class="form-actions"></div>
	</form:form>
</body>
</html>
