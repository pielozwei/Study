<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工作中心维护管理</title>
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
				confirmx_tree('确认要删除所选的工作中心吗？', "${ctx}/gzzx/gzzxwh/deleteList?ids="+checked,checked);
			}else{
				confirmx('请选择至少一行数据', window.location.href);
				return;
			}
		}
		
		// 确认对话框
		function confirmx_tree(mess, href,checked){
			top.$.jBox.confirm(mess,'系统提示',function(v,h,f){
				if(v=='ok'){
					hasChildOrSb(checked,href);
				}
			},{buttonsFocus:1});
			top.$('.jbox-body .jbox-icon').css('top','55px');
			return false;
		}
		//有子节点或者有设备
		function hasChildOrSb(checked,href){
			$.ajax({
				type: "post",
				dataType:"json",
				url: "${ctx}/gzzx/gzzxwh/checkHasChild?ids="+checked,
				beforeSend: function(XMLHttpRequest){
				},
				success: function(data, textStatus){
					if(data.flag==0){
						loading('正在提交，请稍等...');
						location = href;
					}else if(data.flag==1){
						//confirmx('您选择的仓库<'+data.mc+'>下面有子节点，请先删除子节点！', href);
						confirmx('您选择的工作中心<'+data.mc+'>下面有子节点，请先删除子节点！',"${ctx}/gzzx/gzzxwh/list");
					}
				}
			});
		}
		//将信息导出到excel表格中
		$(document).ready(function() {
		$("#btnExport").click(function(){
			/*submit: function (v, h, f) { return true; }, 
			点击信息按钮后的回调函数，返回true时表示关闭窗口，参数有三个，
			v表示所点的按钮的返回值，h表示窗口内容的jQuery对象，f表示窗
			口内容里的form表单键值 */
			top.$.jBox.confirm("确认要导出用户数据吗？","系统提示",function(v,h,f){
				if(v == "ok"){
					exportTable('contentTable',"","","工作中心数据",'${ctx}');
					//$("#searchForm").attr("action","${ctx}/shebei/sbcc/export?id=${sbcc.id}&pageNo=${page.pageNo}&pageSize=${page.pageSize}").submit();
				}
			},{buttonsFocus:1});
			top.$('.jbox-body .jbox-icon').css('top','55px');
		});
	});
		
		function addf() {
			window.location.href = "${ctx}/gzzx/gzzxwh/form";
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/gzzx/gzzxwh/">工作中心维护列表</a></li>
		<%-- <shiro:hasPermission name="gzzx:gzzxwh:edit"><li><a href="${ctx}/gzzx/gzzxwh/form">工作中心维护添加</a></li></shiro:hasPermission> --%>
		<button class="btn pull-right btn-primary" onclick="addf();">新增</button>
	</ul>
	<form:form id="searchForm" modelAttribute="gzzxwh" action="${ctx}/gzzx/gzzxwh/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>名称 ：</label><form:input path="name" htmlEscape="false" maxlength="50" class="input-small"/>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>
	<tags:message content="${message}"/>
	<div style="overflow:auto;">
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th width="5%">选择</th>
				<th>序号</th>
				<th>工作中心编码</th>
				<th>工作中心名称</th>
				<th>工作中心简称</th>
				<th>联系电话</th>
				<th>值班电话</th>
				<th>行政主要领导岗位名称</th>
				<th>党务主管领导岗位名称</th>
				<th>保密主管领导岗位名称</th>
				<th>安全主管领导岗位名称</th>
				<th>简介</th>
				<th>是否有分址</th>
				<th>上级工作中心</th>
				<th>是否全局性单位</th>
				<th>是否实体单位</th>
				<th>经度</th>
				<th>纬度</th>
				<th>网址</th>
				<th>邮箱</th>
				<th>地址</th>
				<th>状态</th>
				<%-- <shiro:hasPermission name="gzzx:gzzxwh:edit"><th>操作</th></shiro:hasPermission> --%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="gzzxwh" varStatus="vars">
			<tr>
				<td><input type="checkbox" name="zj" value="${gzzxwh.id}"/></td>
				<td>${vars.count}</td>
				<td><a href="${ctx}/gzzx/gzzxwh/form?id=${gzzxwh.id}">${gzzxwh.gzzxbm}</a></td>
				<td>${gzzxwh.gzzxmc}</td>
				<td>${gzzxwh.gzzxjc}</td>
				<td>${gzzxwh.lxdh}</td>
				<td>${gzzxwh.zbyddh}</td>
				<td>${gzzxwh.xzzgldgwmc}</td>
				<td>${gzzxwh.dwzgldgwmc}</td>
				<td>${gzzxwh.bmzgldgwmc}</td>
				<td>${gzzxwh.aqzgldgwmc}</td>
				<td>${gzzxwh.jj}</td>
				<td>${fns:getDictLabel(gzzxwh.sfyfz, 'gzzx_wh_sfyfz', '无')}</td>
				<td>${gzzxwh.sjgzzxid.gzzxmc}</td>
				<td>${fns:getDictLabel(gzzxwh.sfqjxdw, 'gzzx_wh_sfsqjxdw', '无')}</td>
				<td>${fns:getDictLabel(gzzxwh.sfsstdw, 'gzzx_wh_sfsstdw', '无')}</td>
				<td>${gzzxwh.jd}</td>
				<td>${gzzxwh.wd}</td>
				<td>${gzzxwh.wz}</td>
				<td>${gzzxwh.yx}</td>
				<td>${gzzxwh.dz}</td>
				<td>${fns:getDictLabel(gzzxwh.sfqy, 'gzzx_wh_zt', '无')}</td>
			<%-- 	<shiro:hasPermission name="gzzx:gzzxwh:edit"><th>
    				<a href="${ctx}/gzzx/gzzxwh/form?id=${gzzxwh.id}">修改</a>
					<a href="${ctx}/gzzx/gzzxwh/delete?id=${gzzxwh.id}" onclick="return confirmx('确认要删除该工作中心维护吗？', this.href)">删除</a>
				</th></shiro:hasPermission> --%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div>
		<button class="btn btn-primary" onclick="delList();">删除</button>
		<button class="btn btn-primary" id="btnExport">导出</button>
		<button class="btn btn-primary" onclick="stopStateList('zj','${ctx}/gzzx/gzzxwh/updateStateList?ids=','0');">启用</button>
		<button class="btn btn-primary" onclick="stopStateList('zj','${ctx}/gzzx/gzzxwh/updateStateList?ids=','1');">禁用</button>
		<button class="btn btn-primary" onclick="editList('zj','${ctx}/gzzx/gzzxwh/form')">编辑</button>
	</div>
	<div class="pagination">${page}</div>
	</div>
</body>
</html>
