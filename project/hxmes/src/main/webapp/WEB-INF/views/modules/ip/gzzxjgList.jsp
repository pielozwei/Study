<%-- <%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工作中心机构管理</title>
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
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/gzzx/gzzxjg/">工作中心机构列表</a></li>
		<shiro:hasPermission name="gzzx:gzzxjg:edit"><li><a href="${ctx}/gzzx/gzzxjg/form">工作中心机构添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="gzzxjg" action="${ctx}/gzzx/gzzxjg/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>名称 ：</label><form:input path="name" htmlEscape="false" maxlength="50" class="input-small"/>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>
	<tags:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>名称</th><th>备注</th><shiro:hasPermission name="gzzx:gzzxjg:edit"><th>操作</th></shiro:hasPermission></tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="gzzxjg">
			<tr>
				<td><a href="${ctx}/gzzx/gzzxjg/form?id=${gzzxjg.id}">${gzzxjg.name}</a></td>
				<td>${gzzxjg.remarks}</td>
				<shiro:hasPermission name="gzzx:gzzxjg:edit"><td>
    				<a href="${ctx}/gzzx/gzzxjg/form?id=${gzzxjg.id}">修改</a>
					<a href="${ctx}/gzzx/gzzxjg/delete?id=${gzzxjg.id}" onclick="return confirmx('确认要删除该工作中心机构吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
 --%>
 <%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>Droid</title>
<%@include file="/WEB-INF/views/include/treeview.jsp" %>
<style type="text/css">
select {
	width: 150px;
	height: 210px;
	border: 3px solid;
	font-size: 20px;
	color: #909993;
}

</style>
<script type="text/javascript">
	//得到指定数组指定元的位置
	function searchKeys(needle, haystack)
	{
	    var result = [];
	    for (i in haystack)
	{
	if (haystack[i] == needle)
	{
	    result.push(i);
	}
	}
	    return result;
	}

	//申明一个数组用来存放本次添加的设备id
	var a1=new Array();
	//申明一个数组用来存放本次删除的设备id
	var a2=new Array();
	//申明一个数组用来存放本次添加机构的id
	var a3=new Array();
	//申明一个数组用来存放本次删除机构的id
	var a4=new Array();
	var move = function(obj) {
		var sel1 = document.getElementById('sel1');
		var sel2 = document.getElementById('sel2');
		if (obj.value == '>>') {
			for (var i = 0; i < sel1.children.length; i++) {
				var x = sel1.children[i];
				if (x.selected) {
					sel2.appendChild(x);
					//alert(a2.indexOf(x.value));
					//a2.toString().indexOf(x.value);
					if(a2.indexOf(x.value)> -1){
						var result = searchKeys(x.value, a2);
						a2.splice(result,1);
					}else{
						a1.push(x.value);
					}
					i = -1;
					continue;
				}
			}
		} else if (obj.value == '<<') {
			for (var i = 0; i < sel2.children.length; i++) {
				var x = sel2.children[i];
				if (x.selected) {
					sel1.appendChild(x);
					
					if(a1.indexOf(x.value)> -1){
						var result = searchKeys(x.value, a1);
						a1.splice(result,1);
					}else{
						a2.push(x.value);
					}
					i = -1;
					continue;
				}
			}
		}
		/* alert(a1.length);
		alert(a2.length); */
	}
	
	var moveNew = function(obj) {
		//alert(1);
		var sel1New = document.getElementById('sel1New');
		var sel2New = document.getElementById('sel2New');
		if (obj.value == '>>') {
			for (var i = 0; i < sel1New.children.length; i++) {
				var x = sel1New.children[i];
				if (x.selected) {
					sel2New.appendChild(x);
					if(a4.indexOf(x.value)> -1){
						var result = searchKeys(x.value, a4);
						a4.splice(result,1);
					}else{
						a3.push(x.value);
					}
					i = -1;
					continue;
				}
			}
		} else if (obj.value == '<<') {
			for (var i = 0; i < sel2New.children.length; i++) {
				var x = sel2New.children[i];
				if (x.selected) {
					sel1New.appendChild(x);
					if(a3.indexOf(x.value)> -1){
						var result = searchKeys(x.value, a3);
						a3.splice(result,1);
					}else{
						a4.push(x.value);
					}
					i = -1;
					continue;
				}
			}
		}
	}
	function subm(){
		//将a1和a2数组转换为字符串
		var str1="";
		var str2="";
		var str3="";
		var str4="";
		for(var i=0;i<a1.length;i++){
			if(i==0){
				str1=str1+""+a1[i];
			}else{
				str1=str1+","+a1[i];	
			}
		}
		for(var i=0;i<a2.length;i++){
			if(i==0){
				str2=str2+""+a2[i];
			}else{
				str2=str2+","+a2[i];	
			}
		}
		for(var i=0;i<a3.length;i++){
			if(i==0){
				str3=str3+""+a3[i];
			}else{
				str3=str3+","+a3[i];	
			}
		}
		for(var i=0;i<a4.length;i++){
			if(i==0){
				str4=str4+""+a4[i];
			}else{
				str4=str4+","+a4[i];	
			}
		}
		//alert(str1);
		window.location.href="${ctx}/gzzx/gzzxjg/saveSB?ids="+str1+"&ipGzzxId=${ipGzzxId}"+"&ids1="+str2+"&ids2="+str3+"&ids3="+str4;
	}
	$(function(){
		alert('${message}');
	})
</script>
</head>
<body>
	<div style="margin-top: 60px;" class="control-group"></div>
	<hr />
	<div style="margin-left: 140px" >
		<form:form id="inputForm" modelAttribute="gzzxjg" action="${ctx}/gzzx/gzzxjg/save" method="post" class="form-horizontal" path="id">
			<form:hidden path="id"/>
			<tags:message type="hidden" content="${message}"/>
			<table>
					<tr>
						<td style="background-color: rgb(64, 64, 64); height: 30px;"><font
							size="3" color="white" style="margin-left: 4px;">所有机构:</font></td>
						<td></td>
						<td style="background-color: rgb(64, 64, 64); height: 30px;"><font
							size="3" color="white" style="margin-left: 4px;">工作中心机构:</font></td>
					</tr>
					<tr>
						<td><select multiple="multiple" id="sel1New">
							<c:forEach items="${organizations }" var="each">
								<option value="${each.id }">${each.jgmc }</option>
							</c:forEach>
						</select></td>
						<td>
							<table>
								<tr>
									<td><input type="button" value=">>" onclick="moveNew(this);" />
									</td>
								</tr>
								<tr>
									<td><input type="button" value="<<" onclick="moveNew(this);" /></td>
								</tr>
							</table>
						</td>
						<td><select multiple="multiple" id="sel2New">
								<c:forEach items="${gzzxjgs }" var="each">
								<option value="${each.id }">${each.zzjgbm.jgmc }</option>
							</c:forEach>
						</select></td>
					</tr>
				</table>
			
			<div style="margin-top: 45px;" >
				<table>
				<tr>
					<td style="background-color: rgb(64, 64, 64); height: 30px;"><font
						size="3" color="white" style="margin-left: 4px;">所有设备域:</font></td>
					<td></td>
					<td style="background-color: rgb(64, 64, 64); height: 30px;"><font
						size="3" color="white" style="margin-left: 4px;">工作中心使用设备:</font></td>
				</tr>
				<tr>
					<td><select multiple="multiple" id="sel1">
							<c:forEach items="${sb }" var="each">
								<option value="${each.id }">${each.name }</option>
							</c:forEach>
					</select></td>
					<td>
						<table>
							<tr>
								<td><input type="button" value=">>" onclick="move(this);" />
								</td>
							</tr>
							<tr>
								<td><input type="button" value="<<" onclick=" move(this);" /></td>
							</tr>
						</table>
					</td>
					<td><select multiple="multiple" id="sel2">
						<c:forEach items="${gzzxsbs }" var="each">
								<option value="${each.id }">${each.sbybm.name }</option>
							</c:forEach>
					</select></td>
				</tr>
			</table>
				<div class="form-actions">
				<div style="height: 30px;"></div>
			<shiro:hasPermission name="gzzx:gzzxwh:edit">
				<input id="btnSubmit"  type="button" value="保 存" onclick="subm();" style="width: 70px;height: 30px;"/>&nbsp;
			</shiro:hasPermission>
			<input id="btnCancel" type="button" value="返 回" onclick="history.go(-1)" style="width: 70px;height: 30px;"/>
		</div>
		</form:form>
	</div>
</body>
</html>
 