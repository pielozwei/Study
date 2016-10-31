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
		function quoteList(){//引用只能选择一条
			//引用前的检查：1.当前消息的接收工作中心是否有未发布的计划；如果没有：XXX工作中心没有添加的计划，请先创建工作计划 Unpublished plan
			//			2.已引用的不能再引用
			//然后跳到 新增任务里，当前工作中心为消息的接收工作中心，当前计划为未发布的那个计划
			//获取勾选的条数
			var checked = new Array;
			var workCenterName = new Array;
			var rows = document.getElementsByName("zj");
			var j=0;
			for ( var i = 0; i < rows.length; i++) {
				if (rows[i].checked){
					checked[j]=rows[i].value;
					workCenterName[j]=$(rows[i]).parents("tr").find("td").eq(3).text();
					j++;
				}
			}
			if(checked.length==1){
				$.post("${ctx}/ip/referenceMessage/hasUnpublishedPlan?id="+checked[0], {}, function(data){
					if(data==true){
						$.post("${ctx}/ip/referenceMessage/hasQuote?id="+checked[0], {}, function(data){
							if(data==true){
								confirmx('引用失败，该消息已引用过', window.location.href);
							}else{
								confirmx('确认要引用所选的参考消息吗？', "${ctx}/ip/referenceMessage/quote?id="+checked[0]);
							}
						});
					}else{
						confirmx("引用失败，"+workCenterName[0]+'工作中心没有可添加的计划，请先创建工作计划', window.location.href);
					}
				});
			}else{
				confirmx('只能请选择一行数据', window.location.href);
				return;
			}
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/ip/referenceMessage/listForWorkCenter">参考消息列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="referenceMessage" action="${ctx}/ip/referenceMessage/listForWorkCenter" method="post" class="breadcrumb form-search">
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
			<th>是否已引用</th>
		</tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="referenceMessage" varStatus="status">
			<tr>
				<td><input type="checkbox" class="zj" name="zj" value="${referenceMessage.id}" /></td>
				<td>${status.index+1}</td>
				<td><a href="${ctx}/ip/referenceMessage/formWorkCenter?id=${referenceMessage.id}">
					<c:if test="${referenceMessage.sfyd == 1}"><b>${referenceMessage.bt}</b></c:if>
					<c:if test="${referenceMessage.sfyd != 1}">${referenceMessage.bt}</c:if>
				</a></td>
				<td>${referenceMessage.workCenter.gzzxmc}</td>
				<td>${referenceMessage.fsr.name}</td>
				<td><fmt:formatDate value="${referenceMessage.jhkssj}" pattern="yyyy-MM-dd"/></td>
				<td><fmt:formatDate value="${referenceMessage.jhwcsj}" pattern="yyyy-MM-dd"/></td>
				<td>${fns:getDictLabel(referenceMessage.sfyyg, 'd_yesno', '无')}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div>
		<button class="btn btn-primary" onclick="quoteList();">引用</button>
		<button class="btn btn-primary" id="btnExport">导出</button>
	</div>
	<div class="pagination">${page}</div>
</body>
</html>
