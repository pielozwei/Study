<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>工作中心管理</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<div id="content" class="row-fluid">
	<input type="hidden" id="cur_sbccid" />
		<div id="left">
			<iframe id="gzzxMenuFrame" name="gzzxMenuFrame" src="${ctx}/gzzx/gzzxbc/tree?module=${module}" style="overflow:visible;"
				scrolling="yes" frameborder="no" width="100%"></iframe>
		</div>
		<div id="openClose" class="close">&nbsp;</div>
		<div id="right">
			<iframe id="gzzxMainFrame" name="gzzxMainFrame" src="${ctx}/gzzx/gzzxjg/none" style="overflow:visible;"
				scrolling="yes" frameborder="no" width="100%"></iframe>
		</div>
	</div>
	<script type="text/javascript"> 
		var leftWidth = "160"; // 左侧窗口大小
		function wSize(){
			var strs=getWindowSize().toString().split(",");
			$("#gzzxMenuFrame, #gzzxMainFrame, #openClose").height(strs[0]-35);
			$("#right").width($("body").width()-$("#left").width()-$("#openClose").width()-35);
		}
	</script>
	<script src="${ctxStatic}/common/wsize.min.js" type="text/javascript"></script>
</body>
</html>