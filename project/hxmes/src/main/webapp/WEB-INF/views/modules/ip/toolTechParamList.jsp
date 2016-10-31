<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>关键技术参数管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出关键技术参数数据吗？","系统提示",function(v,h,f){
					if(v == "ok"){
						exportTable('contentTable',"","","工具关键技术参数数据",'${ctx}');
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
        function delList(){
			//获取勾选的条数
			var checked = new Array;
			var rows = document.getElementsByName("zj");
			var j=0;
			for ( var i = 0; i < rows.length; i++) {
				if (rows[i].checked){
					checked[j]=rows[i].value;
					j++;
				}
			}
			if(checked.length>=1){
				confirmx('确认要删除所选的关键技术参数吗？', "${ctx}/ip/toolTechParam/deleteList?ids="+checked);
			}else{
				confirmx('请选择至少一行数据', window.location.href);
				return;
			}
		}
        function fanhui(){
        	var ipMenuFrame=$("#shebeiMenuFrame",parent.document);
        	ipMenuFrame.contents().find("#tree a.curSelectedNode")[0].click();
        }
        function addf() {
    		window.location.href = "${ctx}/ip/toolTechParam/form?tool.id=${toolId}";
    	}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ip/toolTechParam/?toolId=${toolId}">关键技术参数列表</a></li>
		<%-- <shiro:hasPermission name="ip:toolTechParam:edit"><li><a href="${ctx}/ip/toolTechParam/form?tool.id=${toolId}">关键技术参数添加</a></li></shiro:hasPermission> --%>
		<button class="btn pull-right btn-primary" onclick="addf();">新增</button>
	</ul>
	<form:form id="searchForm" modelAttribute="toolTechParam" 
	action="${ctx}/ip/toolTechParam/?toolId=${toolId}" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>名称 ：</label><form:input path="csmc" htmlEscape="false" maxlength="50" class="input-small"/>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr>
			<th><input type="checkbox" id="zj" onclick="$('.zj').prop('checked',$(this).prop('checked'));"/></th>
			<th>参数代码</th>
			<th>参数名称</th>
			<th>参数说明</th>
			<th>参数值类型</th>
			<th>参数计量单位</th>
			<th>标准值</th>
			<th>上限值</th>
			<th>下限值</th>
			<th>备注</th>
			<%-- <shiro:hasPermission name="ip:toolTechParam:edit"><th>操作</th></shiro:hasPermission> --%>
		</tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="toolTechParam">
			<tr>
				<td><input type="checkbox" class="zj" name="zj" value="${toolTechParam.id}" /></td>
				<td><a href="${ctx}/ip/toolTechParam/form?id=${toolTechParam.id}">${toolTechParam.csdm}</a></td>
				<td><a href="${ctx}/ip/toolTechParam/form?id=${toolTechParam.id}">${toolTechParam.csmc}</a></td>
				<td>${toolTechParam.cssm}</td>
				<td>${fns:getDictLabel(toolTechParam.cszlx, 'd_qzqjcszlx', '无')}</td>
				<td>${toolTechParam.jldwId.jldwmc}</td>
				<td>${toolTechParam.bzz}</td>
				<td>${toolTechParam.sxz}</td>
				<td>${toolTechParam.xxz}</td>
				<td>${toolTechParam.bz}</td>
				<%-- <shiro:hasPermission name="ip:toolTechParam:edit"><td>
    				<a href="${ctx}/ip/toolTechParam/form?id=${toolTechParam.id}">修改</a>
					<a href="${ctx}/ip/toolTechParam/delete?id=${toolTechParam.id}" onclick="return confirmx('确认要删除该关键技术参数吗？', this.href)">删除</a>
				</td></shiro:hasPermission> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<shiro:hasPermission name="ip:toolTechParam:edit"><div>
		<!-- <button class="btn btn-primary" onclick="delList();">编辑</button> -->
		<button class="btn btn-primary" onclick="delList();">删除</button>
		<button class="btn btn-primary" id="btnExport">导出</button>
		<button class="btn btn-primary" onclick="fanhui();">返回基本信息列表</button>
	</div></shiro:hasPermission>
	<div class="pagination">${page}</div>
</body>
</html>
