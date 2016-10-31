<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>滚动工作计划管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出滚动工作计划数据吗？","系统提示",function(v,h,f){
					if(v == "ok"){
						exportTable('contentTable',"","","滚动工作计划数据",'${ctx}');
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});
			$("#btnPublish").click(function(){
				//获取勾选的条数
				var tds = $("#contentTable td input[name='zj']:checked");
				if(tds.length>=1){
					var checked = [];
					for (var i = 0; i< tds.length; i++) {
						checked.push(tds.eq(i).val());
						var tr=tds.eq(i).parent().parent();
						var fbzt=tr.find("td input.fbzt").val();
						if(fbzt!=0){//已取消
							confirmx('所选计划有已发布的，操作失败', window.location.href);
							return;
						}
					}
					confirmx('确认要发布所选的滚动工作计划吗？', "${ctx}/ip/scollingJobPlan/publishList?ids="+checked);
				}else{
					confirmx('请选择至少一行数据', window.location.href);
					return;
				}
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
			var tds = $("#contentTable td input[name='zj']:checked");
			if(tds.length>=1){
				var checked = [];
				for (var i = 0; i< tds.length; i++) {
					checked.push(tds.eq(i).val());
					var tr=tds.eq(i).parent().parent();
					var fbzt=tr.find("td input.fbzt").val();
					if(fbzt!=0){//已取消
						confirmx('所选计划有已发的，操作失败', window.location.href);
						return;
					}
				}
				confirmx('确认要删除所选的滚动工作计划吗？', "${ctx}/ip/scollingJobPlan/deleteList?ids="+checked);
			}else{
				confirmx('请选择至少一行数据', window.location.href);
				return;
			}
		}
        function updateList(){
			//获取勾选的条数
			var tds = $("#contentTable td input[name='zj']:checked");
			if(tds.length==1){
				var tr=tds.parent().parent();
				var fbzt=tr.find("td input.fbzt").val();
				if(fbzt!=0){//已取消
					confirmx('所选计划有已发的，操作失败', window.location.href);
				}else if(fbzt==0){
					location.href="${ctx}/ip/scollingJobPlan/form?id="+tds.val();
				}
			}else{
				confirmx('请选择一行数据', window.location.href);
				return;
			}
		}
        function canAdd(){
        	$.post("${ctx}/ip/scollingJobPlan/canAdd",{},function(data){
        		if(data==true){
        			window.location.href='${ctx}/ip/scollingJobPlan/form';
        		}else {
        			top.$.jBox.tip("您所负责的工作中心都有未发布的计划，所以不能新增计划","warning");
        		}
        	});
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ip/scollingJobPlan/">滚动工作计划列表</a></li>
		<shiro:hasPermission name="ip:scollingJobPlan:edit"><li class="pull-right">
			<input id="btnAdd" class="btn btn-primary" type="button" value="新增" onclick="canAdd();">
		</li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="scollingJobPlan" action="${ctx}/ip/scollingJobPlan/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>名称 ：</label><form:input path="jhmc" htmlEscape="false" maxlength="50" class="input-small"/>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr>
			<th><input type="checkbox" id="zj" onclick="$('.zj').prop('checked',$(this).prop('checked'));"/></th>
			<th>滚动工作计划编号</th>
			<th>发布状态</th>
			<th>滚动工作计划名称</th>
			<th>负责工作中心</th>
			<th>创建人</th>
			<th>计划开始时间</th>
			<th>计划截止时间</th>
		</tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="scollingJobPlan">
			<tr>
				<td><input type="checkbox" class="zj" name="zj" value="${scollingJobPlan.id}" /></td>
				<td>${scollingJobPlan.jhbh}</td>
				<td>
					${fns:getDictLabel(scollingJobPlan.fbzt, 'd_jhfbzt', '无')}
					<input type="hidden" class="fbzt" value="${scollingJobPlan.fbzt}" />
				</td>
				<td><a href="${ctx}/ip/scollingJobPlanTask/?scollingJobPlanId=${scollingJobPlan.id}">${scollingJobPlan.jhmc}</a></td>
				<td>${scollingJobPlan.workCenter.gzzxmc}</td>
				<td>${scollingJobPlan.createBy.loginName}</td>
				<td><fmt:formatDate value="${scollingJobPlan.jhkssj}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${scollingJobPlan.jhjzsj}" pattern="yyyy-MM-dd"/></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<%-- <shiro:hasPermission name="ip:scollingJobPlan:edit"> --%><div>
		<button class="btn btn-primary" onclick="delList();">删除</button>
		<button class="btn btn-primary" onclick="updateList();">修改</button>
		<button class="btn btn-primary" id="btnExport">导出</button>
		<button class="btn btn-primary" id="btnPublish">发布</button>
	</div><%-- </shiro:hasPermission> --%>
	<div class="pagination">${page}</div>
</body>
</html>
