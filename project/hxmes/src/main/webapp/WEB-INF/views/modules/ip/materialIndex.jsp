<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>物料管理</title>
<meta name="decorator" content="default" />
</head>
<body>
	<div id="content" class="row-fluid">
		<input type="hidden" id="cur_wlbm" />
		<div id="left">
			<iframe id="wuliaoMenuFrame" name="wuliaoMenuFrame" src="${ctx}/ip/tree?module=${module}" style="overflow: visible;" scrolling="yes" frameborder="no" width="100%"></iframe>
		</div>
		<div id="openClose" class="close">&nbsp;</div>
		<div id="right">
			<iframe id="wuliaoMainFrame" name="wuliaoMainFrame" src="${ctx}/ip/none" style="overflow: visible;" scrolling="yes" frameborder="no" width="100%"></iframe>
		</div>
	</div>
	<script type="text/javascript">
		var leftWidth = "160"; // 左侧窗口大小
		function wSize() {
			var strs = getWindowSize().toString().split(",");
			$("#wuliaoMenuFrame, #wuliaoMainFrame, #openClose").height(
					strs[0] - 5);
			$("#right").width(
					$("body").width() - $("#left").width()
							- $("#openClose").width() - 35);
		}
	</script>
	<script src="${ctxStatic}/common/wsize.min.js" type="text/javascript"></script>
</body>
</html>