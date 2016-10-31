<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工艺路线列表</title>
	<meta name="decorator" content="default"/>
		<script>
		function organizationList(){
			var jgmc = new Array();
			jgmc = window.frames["techniqueMainFrame"].document.getElementsByClassName("mc");
			jgid = window.frames["techniqueMainFrame"].document.getElementsByName("check");
			
			var str;
			for(var i=0;i<jgmc.length;i++){
				str += "<option value=" + i + ">" + mc[i].innerHTML + "</option>";
			}
			
			var list = document.getElementById("techniqueList");
			list.innerHTML = str;
			list.selectedIndex = -1;
			list.onchange = function(){
				window.frames["techniqueMainFrame"].location.href = "${ctx}/ip/technique/EditForm?id=" + gylxid[list.selectedIndex].value;
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
			<iframe id="techniqueMenuFrame" name="techniqueMenuFrame" src="${ctx}/ip/technique/tree" style="overflow:visible;"
				scrolling="yes" frameborder="no" width="100%"></iframe>
		</div>
		<div id="openClose" class="close">&nbsp;</div>
		<div id="right">
			<iframe id="techniqueMainFrame" name="techniqueMainFrame" src="${ctx}/ip/technique/editform?id=<%=request.getParameter("id") %>" style="overflow:visible;"
				scrolling="yes" frameborder="no" width="100%"></iframe>
		</div>
	</div>
	<script type="text/javascript"> 
		var leftWidth = "200"; // 左侧窗口大小
		function wSize(){
			var strs=getWindowSize().toString().split(",");
			$("#techniqueMenuFrame, #techniqueMainFrame, #openClose").height(strs[0]-5);
			$("#right").width($("body").width()-$("#left").width()-$("#openClose").width()-5);
		}
	</script>
	<script src="${ctxStatic}/common/wsize.min.js" type="text/javascript"></script>
</body>
</html>