<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>组织机构管理</title>
	<meta name="decorator" content="default"/>
		<script>
		function organizationList(){
			var jgmc = new Array();
			jgmc = window.frames["organizationMainFrame"].document.getElementsByClassName("jgmc");
			jgid = window.frames["organizationMainFrame"].document.getElementsByName("check");
			
			var str;
			for(var i=0;i<jgmc.length;i++){
				str += "<option value=" + i + ">" + jgmc[i].innerHTML + "</option>";
			}
			
			var list = document.getElementById("organizationList");
			list.innerHTML = str;
			list.selectedIndex = -1;
			list.onchange = function(){
				window.frames["organizationMainFrame"].location.href = "${ctx}/ip/organizationDepartment/list?organization.id=" + jgid[list.selectedIndex].value;
				//window.frames["organizationMainFrame"].location.href = "${ctx}/ip/organizationDepartment/list";
			}
		}
		window.onload = function(){
			var jgid = new Array();
			organizationList();
		}
	</script>
</head>
<body>
	<div id="content" class="row-fluid">
		<div id="left">
			<shiro:hasPermission name="ip:organization:edit">
				&nbsp;<select id="organizationList" class="input-small">
				</select>&nbsp;
				&nbsp;<input id="btnAdd" class="btn btn-primary" type="button" value="新增" onclick="window.location.href='${ctx}/ip/organization/form'" />
			</shiro:hasPermission>
			<iframe id="organizationMenuFrame" name="organizationMenuFrame" src="${ctx}/ip/organization/tree" style="overflow:visible;"
				scrolling="yes" frameborder="no" width="100%"></iframe>
		</div>
		<div id="openClose" class="close">&nbsp;</div>
		<div id="right">
			<iframe id="organizationMainFrame" name="organizationMainFrame" src="${ctx}/ip/organization/list" style="overflow:visible;"
				scrolling="yes" frameborder="no" width="100%"></iframe>
		</div>
	</div>
	<script type="text/javascript"> 
		var leftWidth = "200"; // 左侧窗口大小
		function wSize(){
			var strs=getWindowSize().toString().split(",");
			$("#organizationMenuFrame, #organizationMainFrame, #openClose").height(strs[0]-5);
			$("#right").width($("body").width()-$("#left").width()-$("#openClose").width()-5);
		}
	</script>
	<script src="${ctxStatic}/common/wsize.min.js" type="text/javascript"></script>
</body>
</html>