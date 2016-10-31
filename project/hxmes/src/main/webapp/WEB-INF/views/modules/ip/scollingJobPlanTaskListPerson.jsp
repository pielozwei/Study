<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>滚动工作计划任务管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出任务数据吗？","系统提示",function(v,h,f){
					if(v == "ok"){
						exportTable('contentTable',"","","滚动工作计划任务数据",'${ctx}');
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
		 function updateList(){
				//获取勾选的条数
				var tds = $("#contentTable td input[name='zj']:checked");
				if(tds.length==1){
					var tr=tds.parent().parent();
					var fbzt=tr.find("td input.fbzt").val();
					var zxzt=tr.find("td input.zxzt").val();
					if(fbzt==1&&zxzt==3){//已取消
						confirmx('该任务已取消，不能修改', window.location.href);
					}else if(fbzt==1&&zxzt!=3){
						location.href="${ctx}/ip/task/form?id="+tds.val();
					}else if(fbzt==0){
						//location.href="${ctx}/ip/scollingJobPlanTask/form?id="+tds.val();
					}
						
				}else{
					confirmx('请选择一行数据', window.location.href);
					return;
				}
			}
		 function dayin(){
			 var objTable=$("#contentTable").clone()[0];
			 $(objTable).attr("class","table");
			 for(var i=0;i<objTable.rows.length;i++){
			      for(var j=0;j<objTable.rows[i].cells.length;j++){
			   	   if(j==0)$(objTable.rows[i].cells[j]).remove();
			   		if(j==5){
			   			var td6=$(objTable.rows[i].cells[j]);
			   			var td6Text=td6.text();
			   			$(objTable).find("a").replaceWith(function(){
			   			  return td6Text;
			   			 })
			   		}
			      }
			   }
			 printTable(objTable);
		 }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ip/task/">个人任务列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="task" 
		action="${ctx}/ip/task/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>名称 ：</label><form:input path="gzrwmc" htmlEscape="false" maxlength="50" class="input-small"/>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr>
			<th><input type="checkbox" id="zj" onclick="$('.zj').prop('checked',$(this).prop('checked'));"/></th>
			<th>序号</th>
			<th>发布状态</th>
			<th>执行状态</th>
			<th>进展程度</th>
			<th>工作任务</th>
			<th>负责人</th>
			<th>协助人</th>
		</tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="task" varStatus="status">
			<tr>
				<td>
					<input type="checkbox" class="zj" name="zj" value="${task.id}" />
					<input type="hidden" class="zxzt" value="${task.zxzt}" />
				</td>
				<td>${status.index+1}</td>
				<td>
					${fns:getDictLabel(task.fbzt, 'd_rwfbzt', '无')}
					<input type="hidden" class="fbzt" value="${task.fbzt}" />
				</td>
				<td>
					${fns:getDictLabel(task.zxzt, 'd_rwzxzt', '已取消')}
					<input type="hidden" class="zxzt" value="${task.zxzt}" />
				</td>
				<td>${fns:getDictLabel(task.jzcd, 'd_rwjzcd', '无')}</td>
				<td>
					<a href="${ctx}/ip/task/form?id=${task.id}">${task.gzrwmc}</a>
				</td>
				<td>${task.rwfzr.name}</td>
				<td>${task.rwxzr.name}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<table id="contentTable2" class="table" style="display:none;">
		<thead><tr>
			<th>序号</th>
			<th>发布状态</th>
			<th>执行状态</th>
			<th>进展程度</th>
			<th>工作任务</th>
			<th>负责人</th>
			<th>协助人</th>
		</tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="task" varStatus="status">
			<tr>
				<td>${status.index+1}</td>
				<td>
					${fns:getDictLabel(task.fbzt, 'd_rwfbzt', '无')}
					<input type="hidden" class="fbzt" value="${task.fbzt}" />
				</td>
				<td>
					${fns:getDictLabel(task.zxzt, 'd_rwzxzt', '已取消')}
					<input type="hidden" class="zxzt" value="${task.zxzt}" />
				</td>
				<td>${fns:getDictLabel(task.jzcd, 'd_rwjzcd', '无')}</td>
				<td>${task.gzrwmc}</td>
				<td>${task.rwfzr.name}</td>
				<td>${task.rwxzr.name}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
	<div>
		<button class="btn btn-primary" id="btnExport">导出</button>
		<!-- <button class="btn btn-primary" onclick="printTable('contentTable2')">打印</button> -->
		<button class="btn btn-primary" onclick="dayin()">打印</button>
		<button class="btn btn-primary" onclick="updateList();">修改</button>
	</div>
	<div class="pagination">${page}</div>
</body>
</html>
