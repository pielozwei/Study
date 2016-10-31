<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>标准产品管理</title>
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
				confirmx_tree('确认要删除吗？', "${ctx}/ip/standardProduct/deleteList?ids="+checked,checked);
			}else{
				confirmx('请选择至少一行数据', window.location.href);
				return;
			}
		}
		
		// 确认对话框
		function confirmx_tree(mess, href,checked){
			top.$.jBox.confirm(mess,'系统提示',function(v,h,f){
				if(v=='ok'){
					//hasChildOrSb(checked,href);
					location = href;
				}
			},{buttonsFocus:1});
			top.$('.jbox-body .jbox-icon').css('top','55px');
			return false;
		}
		
		$(document).ready(function() {
			$("#btnExport").click(function(){
				/*submit: function (v, h, f) { return true; }, 
				点击信息按钮后的回调函数，返回true时表示关闭窗口，参数有三个，
				v表示所点的按钮的返回值，h表示窗口内容的jQuery对象，f表示窗
				口内容里的form表单键值 */
				top.$.jBox.confirm("确认要导出数据吗？","系统提示",function(v,h,f){
					if(v == "ok"){
						exportTable('contentTable',"","","厂级标准产品定义",'${ctx}');
						//$("#searchForm").attr("action","${ctx}/shebei/sbcc/export?id=${sbcc.id}&pageNo=${page.pageNo}&pageSize=${page.pageSize}").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
		});
		
		function addf() {
			window.location.href = "${ctx}/ip/standardProduct/form";
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ip/standardProduct/">标准产品列表</a></li>
		<%-- <shiro:hasPermission name="ip:standardProduct:edit"><li><a href="${ctx}/ip/standardProduct/form">标准产品添加</a></li></shiro:hasPermission> --%>
		<button class="btn pull-right btn-primary" onclick="addf();">新增</button>
	</ul>
	<form:form id="searchForm" modelAttribute="standardProduct" action="${ctx}/ip/standardProduct/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>名称 ：</label><form:input path="cpmc" htmlEscape="false" maxlength="50" class="input-small"/>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>选择</th>
				<th>产品编号</th>
				<th>产品名称</th>
				<th>标准时间</th>
				<th>标准批量单位</th>
				<%-- <shiro:hasPermission name="ip:standardProduct:edit"><th>操作</th></shiro:hasPermission> --%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="standardProduct">
			<tr>
				<td><input type="checkbox" name="zj" value="${standardProduct.id}"/></td>
				<td><a href="${ctx}/ip/standardProduct/form?id=${standardProduct.id}">${standardProduct.cpbh}</a></td>
				<td>${standardProduct.cpmc}</td>
				<td>${standardProduct.bzsj}</td>
				<td>${standardProduct.bzpldw}</td>
				<%-- <shiro:hasPermission name="ip:standardProduct:edit"><td>
    				<a href="${ctx}/ip/standardProduct/form?id=${standardProduct.id}">修改</a>
					<a href="${ctx}/ip/standardProduct/delete?id=${standardProduct.id}" onclick="return confirmx('确认要删除该标准产品吗？', this.href)">删除</a>
				</td></shiro:hasPermission> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div>
		<button class="btn btn-primary" onclick="editList('zj','${ctx}/ip/standardProduct/form')">编辑</button>
		<button class="btn btn-primary" onclick="delList();">删除</button>
		<button class="btn btn-primary" id="btnExport">导出</button>
	</div>
	<div class="pagination">${page}</div>
</body>
</html>
