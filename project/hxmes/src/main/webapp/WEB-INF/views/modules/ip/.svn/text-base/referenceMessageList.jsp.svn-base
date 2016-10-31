<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>参考消息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnExport").click(function(){
				top.$.jBox.confirm("确认要导出参考消息数据吗？","系统提示",function(v,h,f){
					if(v == "ok"){
						exportTable('contentTable',"","","参考消息数据",'${ctx}');
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
				$.post("${ctx}/ip/referenceMessage/hasSend?ids="+checked, {}, function(data){
					if(data==true){
						confirmx('删除失败，您所选择的含有已发送的数据', window.location.href);
					}else{
						confirmx('确认要删除所选的参考消息吗？', "${ctx}/ip/referenceMessage/deleteList?ids="+checked);
					}
				});
			}else{
				confirmx('请选择至少一行数据', window.location.href);
				return;
			}
		}
        function sendList(){
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
				$.post("${ctx}/ip/referenceMessage/hasSend?ids="+checked, {}, function(data){
					if(data==true){
						confirmx('发送失败，您所选择的含有已发送的数据', window.location.href);
					}else{
						confirmx('确认要发送所选的参考消息吗？', "${ctx}/ip/referenceMessage/send?ids="+checked);
					}
					
				});
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
				var sffs=tr.find("td input.sffs").val();
				if(sffs==0){//已发送
					confirmx('该消息已发送，不能修改', window.location.href);
				}else if(sffs==1){
					location.href="${ctx}/ip/referenceMessage/form?id="+tds.val();
				}
					
			}else{
				confirmx('请选择一行数据', window.location.href);
				return;
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ip/referenceMessage/">参考消息列表</a></li>
		<%-- <shiro:hasPermission name="ip:referenceMessage:edit"><li><a href="${ctx}/ip/referenceMessage/form">参考消息添加</a></li></shiro:hasPermission> --%>
		<shiro:hasPermission name="ip:referenceMessage:edit"><li class="pull-right">
			<input id="btnAdd" class="btn btn-primary" type="button" value="新增" onclick="window.location.href='${ctx}/ip/referenceMessage/form'" />
		</li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="referenceMessage" action="${ctx}/ip/referenceMessage/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>名称 ：</label><form:input path="bt" htmlEscape="false" maxlength="50" class="input-small"/>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr>
			<th><input type="checkbox" id="zj" onclick="$('.zj').prop('checked',$(this).prop('checked'));"/></th>
			<th>序号</th>
			<th>参考消息名称</th>
			<th>负责工作中心</th>
			<th>发送人</th>
			<th>计划开始时间</th>
			<th>计划完成时间</th>
			<th>是否已发送</th>
		</tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="referenceMessage" varStatus="status">
			<tr>
				<td><input type="checkbox" class="zj" name="zj" value="${referenceMessage.id}" /></td>
				<td>${status.index+1}</td>
				<td><a href="${ctx}/ip/referenceMessage/form?id=${referenceMessage.id}">${referenceMessage.bt}</a></td>
				<td>${referenceMessage.workCenter.gzzxmc}</td>
				<td>${referenceMessage.fsr.name}</td>
				<td><fmt:formatDate value="${referenceMessage.jhkssj}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${referenceMessage.jhwcsj}" pattern="yyyy-MM-dd"/></td>
				<td>
					${fns:getDictLabel(referenceMessage.sffs, 'd_yesno', '无')}
					<input type="hidden" class="sffs" value="${referenceMessage.sffs}" />
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div>
		<shiro:hasPermission name="ip:referenceMessage:edit">
			<button class="btn btn-primary" onclick="delList();">删除</button>
			<button class="btn btn-primary" onclick="updateList();">修改</button>
			<button class="btn btn-primary" onclick="sendList();">发送</button>
		</shiro:hasPermission>
		<button class="btn btn-primary" id="btnExport">导出</button>
	</div>
	<div class="pagination">${page}</div>
</body>
</html>
