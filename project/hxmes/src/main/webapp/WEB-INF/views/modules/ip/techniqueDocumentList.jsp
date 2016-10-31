<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工艺路线管理工艺文件</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	$(document).ready(function() {
		$("#btnExport").click(function(){
			top.$.jBox.confirm("确认要导出数据吗？","系统提示",function(v,h,f){
				if(v == "ok"){
					exportTable('contentTable',"","","参工艺文件数据",'${ctx}');
				}
			},{buttonsFocus:1});
			top.$('.jbox-body .jbox-icon').css('top','55px');
		});
	});	
	$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }

		function edit_click() {
			var rusult = 0;
			var number = 0;
			var check_array = document.getElementsByName("Select");
			for (var i = 0; i < check_array.length; i++) {
				if (check_array[i].checked == true) {
					rusult = check_array[i].value;
					number++;
				}
			}
			if (number > 1) {
				alert("一次只能修改一条信息");
				return;
			}else if(number == 0){
				alert("请选中一条需要修改的信息");
				return;
			}
			window.location.href = "${ctx}/ip/techniqueDocument/form?id=" + rusult;
			//window.location.href="/hxmes/ip/organization/form?id="+rusult；
			//alert("edit"+rusult);
		}

		function del_click() {
			var rusult = 0;
			var number = 0;
			var check_array = document.getElementsByClassName("Select");
			for (var i = 0; i < check_array.length; i++) {
				if (check_array[i].checked == true) {
					rusult = check_array[i].value;
					number++;
				}
			}
			if(number == 0){
				alert("请选中一条需要删除的信息");
				return;
			}else if (number > 1) {
				var idAll = new Array();
				var count = 0;
				var herfs = new Array();
				for (var i = 0; i < check_array.length; i++) {
					if (check_array[i].checked == true) {
						idAll[count] = check_array[i].value;
						count++;
					}
				}
				for (var j = 0; j < count; j++) {
					herfs[j] = idAll[j];
				}
				var herfss = "${ctx}/ip/techniqueDocument/deletes?id=" + herfs.toString();
				confirmx('确认要删除这些工艺文件信息吗？', herfss);
			} else {
			var herf = "${ctx}/ip/techniqueDocument/delete?id=" + rusult;
			confirmx('确认要删除该工艺文件信息吗？', herf);
			}
		}

		function CheckSelect() { //遍历form
			var all = document.getElementById("SelectAll");
			var checkbox = document.getElementsByName("Select"); //检查是否是指定的控件
			for (var i = 0; i < checkbox.length; i++) { //提取控件
				if (all.checked == true) { //正选
					checkbox[i].checked = true;
				} else { //反选
					checkbox[i].checked = false;
				}
			}
		}
		window.onload = function() {
			var xl = document.getElementsByClassName("xl");
			for (i = 0; i < xl.length; i++) {
				xl[i].innerHTML = i + 1;
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active">
			<a href="${ctx}/ip/techniqueDocument/">工艺文件列表</a>
		</li>
		<li class="pull-right">
			<input id="btnAdd" class="btn btn-primary " type="button" value="新增" onclick="window.location.href='${ctx}/ip/techniqueDocument/form'" />
		</li>
	</ul>
	<form:form id="searchForm" modelAttribute="techniqueDocument" action="${ctx}/ip/techniqueDocument/" method="post" class="breadcrumb form-search">
	
	<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
	<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
	<label>工序 ：</label>
			<tags:treeselect id="gylx_id" name="technique.id" value="${list.id}" labelName="name"
			labelValue="${list.name}" title="工序名称" url="/ip/technique/treeData"/>
		<label>是否启用 ：</label>
		<form:select class="input-small" path="sfqy">
			<form:options items="${fns:getDictList('yes_no')}" itemValue="value" itemLabel="label" htmlEscape="false"/>
		</form:select>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit"
			value="查询" />
	</form:form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
		<tr><th>全选</th>
		<th>序号</th>
		<th>工序</th>
		<th>文件编码</th>
		<th>文件名称</th>
		<th>版本</th>
		<th>文件类型</th>
		<th>文件</th>
		<th>是否启用</th>
		<tbody>
		<c:forEach items="${page.list}" var="techniqueDocument">
			<tr>
				<td><input type="checkbox" value="${techniqueDocument.id}" name="Select" /></td>
				<td>${techniqueDocument.fwxh}</td>
				<td>${techniqueDocument.technique.mc}</td>
				<td>${techniqueDocument.wjbm}</td>
				<td><a href="${ctx}/ip/techniqueDocument/form?id=${techniqueDocument.id}">${techniqueDocument.wjbtmc}</a></td>
				<td>${techniqueDocument.bbh}</td>
				<td>${techniqueDocument.wjlb}</td>
				<td>${techniqueDocument.fjwjm}</td>
				<td>${techniqueDocument.sfqy}</td>

			</tr>
		</c:forEach>
		</tbody>
	</table>
	&nbsp;
	<input id="btnEdit" class="btn btn-primary" type="button" value="编辑" onclick="edit_click()" />&nbsp;
	<input id="btnDelete" class="btn btn-primary" type="button" value="删除" onclick="del_click()" />	&nbsp;
	<button class="btn btn-primary" id="btnExport">导出</button>
	<div class="pagination">${page}</div>
</body>
</html>
