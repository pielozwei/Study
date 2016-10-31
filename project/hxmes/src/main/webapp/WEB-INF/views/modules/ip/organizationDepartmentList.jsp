<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>组织机构部门管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
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
			var check_array = document.getElementsByName("check");
			for (var i = 0; i < check_array.length; i++) {
				if (check_array[i].checked == true) {
					rusult = check_array[i].value;
				}
			}
			if(rusult == 0){
				alert("请至少选择一项");
			}else
			{window.location.href = "${ctx}/ip/organizationDepartment/form?id=" + rusult;}
			//window.location.href="/hxmes/ip/organization/form?id="+rusult；
			//alert("edit"+rusult);
		}

		function del_click() {
			var rusult = 0;
			var check_array = document.getElementsByName("check");
			for (var i = 0; i < check_array.length; i++) {
				if (check_array[i].checked == true) {
					rusult = check_array[i].value;
				}
			}
			if(rusult == 0){
				alert("请至少选择一项");
			}else{
			var herf = "${ctx}/ip/organizationDepartment/delete?id=" + rusult;
			confirmx('确认要删除该工作经历吗？', herf);}
		}
		
		function CheckSelect() { //遍历form
			var all = document.getElementById("SelectAll");
			var checkbox = document.getElementsByName("check"); //检查是否是指定的控件
			for (var i = 0; i < checkbox.length; i++) { //提取控件
				if (all.checked == true) { //正选
					checkbox[i].checked = true;
				} else { //反选
					checkbox[i].checked = false;
				}
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ip/organizationDepartment/">组织机构部门列表</a></li>
		<li class="pull-right">
				<input id="btnAdd" class="btn btn-primary " type="button" value="新增" onclick="window.location.href='${ctx}/ip/organizationDepartment/form'" />
		</li>
	</ul>
	<form:form id="searchForm" modelAttribute="organizationDepartment" action="${ctx}/ip/organizationDepartment/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>部门名称 ：</label><form:input path="bmmc" htmlEscape="false" maxlength="50" class="input-small"/>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>
	<tags:message content="${message}"/>
	<div style="overflow: auto;">
	<table id="contentTable" class="table table-striped table-bordered table-condensed" >
		<thead>
			<tr>
				<th><input name="SelectAll" type="checkbox" id="SelectAll"
					onclick="CheckSelect()" />全选</th>
				<th>序号</th>
				<th>机构编码</th>
				<th>机构名称</th>
				<th>机构简称</th>
				<th>联系电话</th>
				<th>值班电话</th>
				<th>行政主要领导</th>
				<th>行政副领导</th>
				<th>党务主要领导</th>
				<th>党务副领导</th>
				<th>机构简介</th>
				<th>是否有分址</th>
				<th>分址地址</th>
				<th>集团编码</th>
				<th>行业编码</th>
				<th>其它编码</th>
				<th>是否全局性单位</th>
				<th>是否实体单位</th>
				<th>业务类型</th>
				<th>机构类型</th>
				<th>所属机构</th>
				<th>所属行业</th>
				<th>经济性质</th>
				<th>产品性质</th>
				<th>生产规模</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="organizationDepartment">
			<tr>
				<td><input type="checkbox" value="${organizationDepartment.id}"
						name="check" /></td>
				<td>${organizationDepartment.xssx}</td>
				<td>${organizationDepartment.bmbm}</td>
				<td><a href="${ctx}/ip/organizationDepartment/form?id=${organizationDepartment.id}">${organizationDepartment.bmmc}</a></td>
				<td>${organizationDepartment.bmjc}</td>
				<td>${organizationDepartment.lxdh}</td>
				<td>${organizationDepartment.lxdh}</td>
				<td>${organizationDepartment.xzzgldmc}</td>
				<td>${organizationDepartment.xzfgldmc}</td>
				<td>${organizationDepartment.dwzgldmc}</td>
				<td>${organizationDepartment.dwfgldmc}</td>
				<td>${organizationDepartment.organization.jgjj}</td>
				<td>${organizationDepartment.organization.sfyfz}</td>
				<td></td>
				<td>${organizationDepartment.organization.jtbm}</td>
				<td>${organizationDepartment.organization.hybm}</td>
				<td>${organizationDepartment.organization.qtbm}</td>
				<td>${organizationDepartment.sfqjxjg}</td>
				<td>${organizationDepartment.sfstxjg}</td>
				<td>${organizationDepartment.ywlx}</td>
				<td>${organizationDepartment.organization.jglx}</td>
				<td>${organizationDepartment.organization.jgmc}</td>
				<td>${organizationDepartment.organization.sshy}</td>
				<td>${organizationDepartment.organization.jjxz}</td>
				<td>${organizationDepartment.organization.hyxz}</td>
				<td>${organizationDepartment.organization.scgm}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
	&nbsp;
	<input id="btnQuery" class="btn btn-primary" type="submit" value="修改" onclick="edit_click()" />&nbsp;
	<input id="btnDelete" class="btn btn-primary" type="button" value="删除" onclick="del_click()" />
	<div class="pagination">${page}</div>
</body>
</html>
